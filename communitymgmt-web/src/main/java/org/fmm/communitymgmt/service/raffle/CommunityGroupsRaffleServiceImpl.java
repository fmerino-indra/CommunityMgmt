package org.fmm.communitymgmt.service.raffle;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.groups.Absence;
import org.fmm.communitymgmt.common.model.groups.AssignedRafflePerson;
import org.fmm.communitymgmt.common.model.groups.CandidatesRaffle;
import org.fmm.communitymgmt.common.model.groups.Raffle;
import org.fmm.communitymgmt.common.model.groups.ReserveRafflePerson;
import org.fmm.communitymgmt.common.model.groups.Service;
import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.fmm.communitymgmt.common.repository.groups.AbsenceRepository;
import org.fmm.communitymgmt.common.repository.groups.AssignedRafflePersonRepository;
import org.fmm.communitymgmt.common.repository.groups.CandidateRaffleRepository;
import org.fmm.communitymgmt.common.repository.groups.RaffleRepository;
import org.fmm.communitymgmt.common.repository.groups.ReserveRafflePersonRepository;
import org.fmm.communitymgmt.common.repository.groups.ServiceRepository;
import org.fmm.communitymgmt.common.util.DateUtil;
import org.fmm.communitymgmt.service.raffle.dto.AvailabilityDTO;
import org.fmm.communitymgmt.service.raffle.dto.RaffleDTO;
import org.fmm.communitymgmt.service.raffle.dto.RandomInfo;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;

@org.springframework.stereotype.Service("AcollyteAdminService")
public class CommunityGroupsRaffleServiceImpl implements CommunityGroupsRaffleService {

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private AssignedRafflePersonRepository arpRepository;

    @Autowired
    private ReserveRafflePersonRepository rrpRepository;

    @Autowired
    private AbsenceRepository absenceRepository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private RaffleRepository raffleRepository;
    
    private Random random = null;

    @Autowired
    private CandidateRaffleRepository candidatesRaffleRepository;
    
    @PostConstruct
    private void init() {
        random = new Random(System.currentTimeMillis());
    }
    
    @Override
    public RaffleDTO prepareRaffle(LocalDate fromLDT, LocalDate toLDT) {
        RaffleDTO raffle = null;
        List<Service> servicios = null;
        List<Person> persons = null; 

        raffle = new RaffleDTO();
        
        
        LocalTime time = LocalTime.of(23, 59, 59);

        ZonedDateTime from, to;
        
        from = DateUtil.localDateToZonedDateTime(fromLDT); // Devuelve a las 00:00
        to = DateUtil.localDateToZonedDateTime(toLDT, time);
        
        // Esto no está bien para este tipo de sorteos.
        // Habría que pensar en cómo se prepara
        //servicios = serviceRepository.listServicesFromTo(from, to); 
        raffle.setEventos(servicios);
        
        // Esto ok, hay que cargar los candidatos
        persons=personRepository.listAllPerson();
        raffle.setAllCandidates(persons);
        
        raffle = loadCandidatesForRaffle(raffle);
        raffle.sortByAvailability();
        
        return raffle;
    }
    
    public RaffleDTO raffleSimpleGroup(LocalDate fromLDT, LocalDate toLDT) {
        List<Service> servicios = null;
        List<Person> persons = null; 

    	RaffleDTO raffle = null;

    	raffle = new RaffleDTO();
    	
    	raffle.setAllCandidates(null);
    	
        // Esto ok, hay que cargar los candidatos
        persons=personRepository.listAllPerson();
        raffle.setAllCandidates(persons);
        
        raffle = loadCandidatesForRaffle(raffle);
        raffle.sortByAvailability();
    	
    	return raffle;
    }
    
    /**
     * 
     * Por cada servicio en RaffleDTO:
     * - Lee la lista de ausencias para la fecha del servicio
     * - Crea una lista de candidatos (para ese servicio)
     * - Crea un AvailabilityDTO con servicio, candidatos
     * - Añade el AvailabilityDTO a un mapa servicio, disponibilidad
     * 
     * @param raffle
     * @return
     */
    private RaffleDTO loadCandidatesForRaffle(RaffleDTO raffle) {
        AvailabilityDTO availability=null; 
        List<Person> personas = null;
        List<Person> candidatos = null;
        List<Absence> exceptions = null;
        List<Person> personAbsence = null;
        
        personas = raffle.getAllCandidates();
        for(Service service:raffle.getEventos()) {
            candidatos = new ArrayList<>();
            exceptions = absenceRepository.findAbsenceByDate(service.getServiceDate().toLocalDate());
            personAbsence = toPersonList(exceptions);
            
            for (Person candidato: personas) {
                if (!personAbsence.contains(candidato))
                    candidatos.add(candidato);
            }
            availability = new AvailabilityDTO(service, candidatos);
            raffle.getAvailabilityMap().put(service, availability);
        }
        return raffle;
    }
    
    public RaffleDTO newRaffle(RaffleDTO raffle) {
    	
    	for (AvailabilityDTO availability: raffle.getAvailabilityList())
    		makeNewRaffle(availability, raffle);
    	
    	return raffle;
    }
    
    private RaffleDTO makeNewRaffle(AvailabilityDTO availability, RaffleDTO raffle) {
    	
    	return raffle;
    }
    
    @Override
    public RaffleDTO raffle(RaffleDTO raffle) {
        List<Person> candidates = null;
        List<Person> filteredAssignedCandidates = null;
        List<Person> filteredReserveCandidates = null;
        
        List<Integer> raffledIndexes = null;
        List<Integer> raffledIndexesEphymeral = null;
        
        AssignedRafflePerson auxARP = null;
        ReserveRafflePerson auxRRP = null;

        Person auxPerson = null;
        Raffle raffleEntity = null;
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        
        CandidatesRaffle candidatesRaffle = null;
        
        int needed = 0;
        int reserves = 0;
        int reserveIndex = 0;
        int rnd = -1;
        
        for (AvailabilityDTO availability: raffle.getAvailabilityList()) {
            raffleEntity = new Raffle();
            raffleEntity.setDate(availability.getDate());
            raffleEntity.setRaffleDate(time);
            raffleEntity.setService(availability.getService());
            
            candidates = availability.getCandidates();

            // Raffle Entity
            raffleEntity.setnCandidates(candidates.size());
            raffleEntity.setnReserves(candidates.size());
            raffleEntity.setnSurvivedCandidates(0);
            raffleEntity = raffleRepository.save(raffleEntity);
            
            //Precandidatos
            for (Person candidate: candidates) {
                candidatesRaffle = new CandidatesRaffle();
                candidatesRaffle.setPerson(candidate);
                candidatesRaffle.setRaffle(raffleEntity);
                candidatesRaffle.setnAssigned(raffle.getRandomInfo().get(candidate).getAssignedTimes());
                candidatesRaffle.setnReserved(raffle.getRandomInfo().get(candidate).getSelectedTimes());
                candidatesRaffle.setSurviveAssigned(false);
                candidatesRaffle.setSurviveReserve(false);
                candidatesRaffleRepository.save(candidatesRaffle);
            }

            // Candidatos filtrados
            filteredAssignedCandidates=filterCandidatesByAssigned(candidates, raffle.getRandomInfo());
            filteredReserveCandidates=filterCandidatesByReserve(candidates, raffle.getRandomInfo());
            
            // Raffle Entity
            raffleEntity.setnSurvivedCandidates(filteredAssignedCandidates.size());
            raffleEntity.setnSurvivedReserves(filteredReserveCandidates.size());
            raffleEntity = raffleRepository.save(raffleEntity);

            //Survivers
            for (Person candidate: filteredAssignedCandidates) {
                candidatesRaffle = candidatesRaffleRepository.findByRaffleAndPerson(raffleEntity, candidate);
                candidatesRaffle.setSurviveAssigned(true);
                candidatesRaffleRepository.save(candidatesRaffle);
            }

            for (Person candidate: filteredReserveCandidates) {
                candidatesRaffle = candidatesRaffleRepository.findByRaffleAndPerson(raffleEntity, candidate);
                candidatesRaffle.setSurviveReserve(true);
                candidatesRaffleRepository.save(candidatesRaffle);
            }

            // Número de candidatos
            needed = availability.getService().getNeeded();
            reserves = availability.getService().getnReserves();
            
            raffledIndexes = makeRaffleWithoutRepeat(availability.getService().getNeeded(), filteredAssignedCandidates.size());
            raffledIndexesEphymeral = new ArrayList<>(raffledIndexes.size());
            
            for (int i = 0; i<raffledIndexes.size(); i++) {
                raffledIndexesEphymeral.add(raffledIndexes.get(i));
            }
            
            if (filteredAssignedCandidates != null && filteredAssignedCandidates.size() > 0) {
                while (needed > 0 && raffledIndexesEphymeral.size() > 0) {
                    if (raffledIndexesEphymeral.size() > 0) {
//                        auxPerson = filteredAssignedCandidates.get(0);
                        rnd = raffledIndexesEphymeral.get(0);
                        auxPerson = filteredAssignedCandidates.get(rnd);
                        auxARP = raffleEntity.addAssignedCandidate(auxPerson);
                        raffle.addAssignedCandidate(auxPerson);
                        auxARP.setRandom(rnd);
                        arpRepository.save(auxARP);
                        raffledIndexesEphymeral.remove(0);
                        reserveIndex = filteredReserveCandidates.indexOf(auxPerson);
                        if (reserveIndex > -1)
                            filteredReserveCandidates.remove(reserveIndex);
                        needed--;
                    }
                }
            }

            raffledIndexes = makeRaffleWithoutRepeat(availability.getService().getnReserves(), filteredReserveCandidates.size());
            raffledIndexesEphymeral = new ArrayList<>(raffledIndexes.size());
            
            for (int i = 0; i<raffledIndexes.size(); i++) {
                raffledIndexesEphymeral.add(raffledIndexes.get(i));
            }
            
            if (filteredReserveCandidates != null && filteredReserveCandidates.size() > 0) {
                while (reserves > 0 && raffledIndexesEphymeral.size() > 0) {
                    if (raffledIndexesEphymeral.size() > 0) {
                        rnd = raffledIndexesEphymeral.get(0);
                        auxPerson = filteredReserveCandidates.get(rnd);
                        auxRRP = raffleEntity.addReserveCandidate(auxPerson);
                        raffle.addSelectedCandidate(auxPerson);
                        auxRRP.setRandom(rnd);
                        rrpRepository.save(auxRRP);
                        raffledIndexesEphymeral.remove(0);
                        reserves--;
                    }
                }
            }
        }
        return raffle;
    }

    /**
     * Basado en el algoritmo de Fisher-Yates
     * 
     * Hace una lista de 0 - max: estos son los candidatos
     * Sortea los necesarios creando la lista raffledIntegerList
     * y sacando los que han salido por sorteo de la lista de candidatos
     * 
     * @param needed
     * @param nCandidates
     * @return
     */
    private List<Integer> makeRaffleWithoutRepeat(int needed, int nCandidates) {
        List<Integer> auxCandidates = null;
        List<Integer> raffledIntegerList = null;
        int rnd;
        int min = 0;
        int max = nCandidates-1;
        
        auxCandidates = new ArrayList<>();
        for (int i = 0; i<nCandidates; i++)
            auxCandidates.add(i);
        
        raffledIntegerList = new ArrayList<>(needed);

        while (raffledIntegerList.size() < Math.min(needed,nCandidates)) {
            rnd=random(min,max,random);
            if (auxCandidates.contains(rnd)) {
                auxCandidates.remove(auxCandidates.indexOf(rnd));
                raffledIntegerList.add(rnd);
            }
        }
        return raffledIntegerList;
    }
    
    private List<Person> toPersonList(List<Absence> exceptions) {
        List<Person> response = null;
        response = new ArrayList<>(exceptions.size());
        for (Absence absence: exceptions) {
            response.add(absence.getPerson());
        }
        return response;
    }

    // Si usáramos Math.random sería Math.random()*(max-min+1)+max??? no, sería: Math.random()*(max-min+1)+min
    // min: incluido
    // max: incluido
    private int random(int min, int max, Random random) {
        return random.nextInt(max-min+1)+min;
    }

    /**
     * times-> Es una lista ordenada que contiene las "veces", de la información de random. Ej.[2 veces, 5 veces, 6 veces]
     * Se rescatan los candidatos que han sido asignados un número de veces menor o igual a cada elemento de la lista. 
     * En cuanto hay alguno se devuelve esta lista
     * 
     * @param candidates
     * @param randomInfoMap
     * @return
     */
    private List<Person> filterCandidatesByAssigned(List<Person> candidates, Map<Person, RandomInfo> randomInfoMap) {
        List<Person> newCandidates = null;
        List<Integer> times = null;
        RandomInfo auxRandomInfo = null;
        newCandidates = candidates;
        
        if (candidates.size() > 1) {
            times = new ArrayList<>(candidates.size());
            for (Person candidate: candidates) {
                auxRandomInfo = randomInfoMap.get(candidate);
                if (!times.contains(auxRandomInfo.getAssignedTimes()))
                    times.add(auxRandomInfo.getAssignedTimes());
            }
            Collections.sort(times);
            for (Integer value: times) {
                newCandidates = removeAssignedByMin(value, candidates, randomInfoMap);
                if (newCandidates.size() > 0)
                    break;
            }
        }
        return newCandidates;
    }
    
    private List<Person> filterCandidatesByReserve(List<Person> candidates, Map<Person, RandomInfo> randomInfo) {
        List<Person> newCandidates = null;
        List<Integer> times = null;
        RandomInfo aux = null;
        newCandidates = candidates;
        
        if (candidates.size() > 1) {
            times = new ArrayList<>(candidates.size());
            for (Person candidate: candidates) {
                aux = randomInfo.get(candidate);
                if (!times.contains(aux.getSelectedTimes()))
                    times.add(aux.getSelectedTimes());
            }
            Collections.sort(times);
            for (Integer value: times) {
                newCandidates = removeSelectedByMin(value, candidates, randomInfo);
                if (newCandidates.size() > 0)
                    break;
            }
        }
        return newCandidates;
    }
    
    private List<Person> removeAssignedByMin(int min, List<Person> candidates, Map<Person, RandomInfo>randomInfoMap) {
        RandomInfo auxRandomInfo = null;
        List<Person> newCandidates = null;
        newCandidates = new ArrayList<>();
        
        for (Person person: candidates) {
            auxRandomInfo = randomInfoMap.get(person);
            if (auxRandomInfo.getAssignedTimes() <= min) {
                newCandidates.add(person);
            }
        }
        return newCandidates;
    }

    private List<Person> removeSelectedByMin(int min, List<Person> candidates, Map<Person, RandomInfo>randomInfo) {
        RandomInfo aux = null;
        List<Person> newCandidates = null;
        newCandidates = new ArrayList<>();
        
        for (Person person: candidates) {
            aux = randomInfo.get(person);
            if (aux.getSelectedTimes() <= min) {
                newCandidates.add(person);
            }
        }
        return newCandidates;
    }

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        int min = 0;
        int max = 2;
        for (int i = 0; i<10; i++)
            System.out.println(random.nextInt(max)+min);
    }
    
}
