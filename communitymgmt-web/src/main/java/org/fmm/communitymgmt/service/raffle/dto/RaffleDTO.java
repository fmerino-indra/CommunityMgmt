package org.fmm.communitymgmt.service.raffle.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.groups.Service;

public class RaffleDTO {
/**
 *  Disponibilidad
 * 
 *  Para un rango de servicios a sortear, se debe:
 *  1. Calcular para cada servicio, los candidatos disponibles
 *  2. Calcular para cada servi cio, el número de candidatos disponibles, se empezará el sorteo por el servicio que menos candidatos tenga
 *  3. 
 *  
 */

    // Creo que hay que cambiarlo a Map<Person, Integer>
//    private List<Person> selected = null;
//    private Map<Person, Integer> selectedCandidate = null;

    private Map<Person, RandomInfo> randomInfo = null;
    
    private List<Person> allCandidates = null;
    
    // EV y Nev (número de eventos). El número de eventos indicará el momento en que 
    private List<Service> eventos = null;
    private Integer nEv = null;
    
    // CL y Disponibilidad (tamaño de cada lista) 
    private Map<Service, AvailabilityDTO> availabilityMap = null;
    private List<AvailabilityDTO> availabilityList = null;
    
//    public List<Person> getSelected() {
//        return selected;
//    }
//
//    public void setSelected(List<Person> selected) {
//        this.selected = selected;
//    }

    public List<Person> getAllCandidates() {
        return allCandidates;
    }

    public void setAllCandidates(List<Person> allCandidates) {
        this.allCandidates = allCandidates;
        prepareCandidates();
    }

    public Integer getnEv() {
        return nEv;
    }

    public void setnEv(Integer nEv) {
        this.nEv = nEv;
    }

    public Map<Service, AvailabilityDTO> getAvailabilityMap() {
        if (availabilityMap == null)
            availabilityMap = new HashMap<>();
        return availabilityMap;
    }

    public void setAvailabilityMap(Map<Service, AvailabilityDTO> availabilityMap) {
        this.availabilityMap = availabilityMap;
    }

    public List<Service> getEventos() {
        return eventos;
    }

    public void setEventos(List<Service> eventos) {
        this.eventos = eventos;
        if (this.eventos != null)
            this.nEv = this.eventos.size();
    }
    
    public void sortByAvailability() {
        List<AvailabilityDTO> lista = null;
        Stream<AvailabilityDTO> stream = availabilityMap.values().stream();
        lista = stream.collect(Collectors.toList());
        Collections.sort(lista, new AvailabilityComparator());
        availabilityList = lista;
    }

    public void addSelectedCandidate(Person person) {
        getRandomInfo().get(person).addSelectedTimes();
    }

    public void addAssignedCandidate(Person person) {
        getRandomInfo().get(person).addAssignedTimes();
    }

    public Map<Person, RandomInfo> getRandomInfo() {
        if (randomInfo == null)
            randomInfo = new HashMap<>();
        return randomInfo;
    }

    public void setRandomInfo(Map<Person, RandomInfo> randomInfo) {
        this.randomInfo = randomInfo;
    }
    
    private void prepareCandidates() {
        RandomInfo randomInfo = null;
        for (Person person: this.allCandidates) {
            randomInfo = new RandomInfo(person);
            getRandomInfo().put(person, randomInfo);
        }
    }

    public List<AvailabilityDTO> getAvailabilityList() {
        return availabilityList;
    }

    public void setAvailabilityList(List<AvailabilityDTO> availabilityList) {
        this.availabilityList = availabilityList;
    }
}
