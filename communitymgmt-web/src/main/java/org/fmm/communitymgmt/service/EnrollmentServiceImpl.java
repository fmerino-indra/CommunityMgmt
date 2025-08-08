package org.fmm.communitymgmt.service;

import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.common.model.Invitation;
import org.fmm.communitymgmt.common.model.InvitationRMarriage;
import org.fmm.communitymgmt.common.model.InvitationRSingle;
import org.fmm.communitymgmt.common.model.InvitationRelationship;
import org.fmm.communitymgmt.common.model.Membership;
import org.fmm.communitymgmt.common.model.Person;
import org.fmm.communitymgmt.common.model.RMarriage;
import org.fmm.communitymgmt.common.model.RSingle;
import org.fmm.communitymgmt.common.model.Relationship;
import org.fmm.communitymgmt.common.repository.InvitationMarriageRepository;
import org.fmm.communitymgmt.common.repository.InvitationRelationshipRepository;
import org.fmm.communitymgmt.common.repository.InvitationRepository;
import org.fmm.communitymgmt.common.repository.InvitationSingleRepository;
import org.fmm.communitymgmt.common.repository.MarriageRepository;
import org.fmm.communitymgmt.common.repository.PersonRepository;
import org.fmm.communitymgmt.common.repository.RelationshipRepository;
import org.fmm.communitymgmt.common.repository.SingleRepository;
import org.fmm.communitymgmt.common.util.Gender;
import org.fmm.communitymgmt.common.util.InvitationState;
import org.fmm.communitymgmt.common.util.OrderListEnum;
import org.fmm.communitymgmt.dto.SignatureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service("EnrollmentService")
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	InvitationRepository invitationRepository;
	
	@Autowired
	PersonRepository personRepository;

	@Autowired
	InvitationRelationshipRepository invitationRelationshipRepository;

	@Autowired
	InvitationMarriageRepository invitationMarriageRepository;

	@Autowired
	InvitationSingleRepository invitationSingleRepository;
	
	@Autowired
	SingleRepository singleRepository;
	
	@Autowired
	MarriageRepository marriageRepository;

	@Autowired
	RelationshipRepository relationshipRepository;
	
	@Override
	public List<Invitation> getInvitationsByCommunity(Integer communityId) {
		List<Invitation> list = invitationRepository.listAllInvitationsByCommunityId(communityId); 
		return list;
	}

	@Override
	public Invitation createInvitation(Invitation invitation) {
		return invitationRepository.save(invitation);
	}

	private Invitation assignInvitation(Invitation invitation, Person p) {
		Invitation invitationDev = null;
		// Primero actualizamos

		invitationDev = invitationRepository.findFullInvitationById(invitation.getId()).get();
		// Si ya tiene asignada una persona, se devuelve error. FMMP
		if (invitationDev.getPerson() != null)
			throw new RuntimeException("Invitation has been previously assigned. Contact your responsible");
		invitationDev.setState(InvitationState.P);
		invitationDev.setPerson(p);
		invitationDev = invitationRepository.save(invitationDev);
		
		// Ahora hay que crear:
		// invitation_relationship y demás instancias.
		return invitationDev;
	}

	public Invitation createAllInvitationStructure(Invitation invitation, Integer personId) {
		Person p = null;
		Optional<Person> oPerson = null;

		oPerson = personRepository.findById(personId);
		if (oPerson.isPresent())
			p = oPerson.get();
		else
			throw new RuntimeException("Person not found");

		invitation = assignInvitation(invitation, p);
		
		// TODO FMMP: Comprobar que la invitación ya tiene la relación -> Se lanza excepción, de momento
		// Se podría verificar que la persona es la misma, que la invitación es para el estado civil de la persona y comprobar qué falta, y qué trae la llamada, para completarlo.
		
		InvitationRelationship ir = null;
		InvitationRSingle irs = null;
		InvitationRMarriage irm = null;
		
		
		if (invitation.getForMarriage()) {
			irm = new InvitationRMarriage();
			irm.setRelationshipName(invitation.getName());
			irm.setDescription("Marriage of " + formatName(p));
			irm.setOrderList(100);
			if (Gender.M.equals(p.getGender())) {
				irm.setHusband(p);
			} else {
				irm.setWife(p);
			}
			irm = invitationMarriageRepository.save(irm);
			ir = irm;
		} else {
			irs = new InvitationRSingle();
			irs.setPerson(p);
			irs.setRelationshipName(invitation.getName());
			irs.setOrderList(200);
			
			irs = invitationSingleRepository.save(irs);
			ir = irs;
		}
		invitation.setInvitationRelationship(ir);
		invitationRepository.save(invitation);
		
		return invitation;
	}
	
	private String formatName (Person person) {
		return person.getNickname()!=null?person.getNickname():person.getName();
	}

	@Override
	public List<Invitation> getInvitationsByPerson(Integer personId) {
		return invitationRepository.listAllInvitationsByPersonId(personId, InvitationState.P);
	}

	@Override
	public Invitation updateBrotherSignature(Integer communityId, Integer invitationId, SignatureDTO signatureDTO) {
		Optional<Invitation> oInvitation = null;
		Invitation invitation = null;
		
		oInvitation=invitationRepository.findFullInvitationById(invitationId);
		if (oInvitation.isPresent()) {
			invitation = oInvitation.get();
			invitation.setPersonSignature(signatureDTO.getSignature());
			invitation.setPersonPublicKey(signatureDTO.getPublicKey());
			invitation = invitationRepository.save(invitation);
			return invitation;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void acceptBrother(Integer communityId, Integer invitationId) {
		/*
		 * 1º Validar si tiene estructura
		 * 2º Si no tiene -> crearla
		 * 3º Si la tiene -> seguir
		 * 
		 * Incluir como miembro de la comunidad
		 */
		
		Optional<Invitation> oInvitation = null;
		Invitation invitation = null;
		Person person = null;
		
		Relationship r = null;
		Optional<? extends RSingle> oRs = null;
		RSingle rs = null;
		Optional<? extends RMarriage> oRm = null;
		RMarriage rm = null;
		
		oInvitation = invitationRepository.findFullInvitationById(invitationId);
		if (oInvitation.isPresent()) {
			invitation = oInvitation.get();
		} else
			throw new RuntimeException("Invitation not found");
		
		person = invitation.getPerson();

		if (person.getMarried()) {
			oRm = marriageRepository.findRMarriageByPersonId(person.getId());
			if (oRm.isEmpty())
				rm = createMarriage(person);
			else 
				rm = oRm.get();
			r = rm;
		} else {
			oRs = singleRepository.findRSingleByPersonId(person.getId());
			if (oRs.isEmpty())
				rs = createSingle(person);
			else
				rs = oRs.get();
			r = rs;
		}
		
		createMembership(r, invitation);
	}

	private RMarriage createMarriage(Person person) {
		RMarriage aux = new RMarriage();
//		aux.setDate(date);
		
		// Se ponen los dos porque son not null
		aux.setHusband(person);
		aux.setWife(person);
		String husbandName=null;
		String wifeName=null;
		
		if (Gender.M == person.getGender()) {
			husbandName = formatName(person);
			wifeName = "Pending";
		} else {
			husbandName = "Pending";
			wifeName = formatName(person);
			
		}
		aux.setDescription(String.format("Marriage of %s and %s", husbandName, wifeName));
		aux.setRelationshipName(String.format("%s and %s", husbandName, wifeName));

		aux.setOrderList(OrderListEnum.MARRIAGES);
		marriageRepository.save(aux);
		return aux;
	}

	private RSingle createSingle(Person person) {
		RSingle single = new RSingle();
		single.setPerson(person);
		single.setRelationshipName(String.format("%s", person.getNickname()));
		single.setOrderList(OrderListEnum.SINGLES);
		singleRepository.save(single);
		return single;
	}

	private void createMembership(Relationship r, Invitation invitation) {
		Membership membership = null;
		membership = new Membership();
		
		membership.setCommunity(invitation.getCommunity());
		membership.setRelationship(r);
		membership.setPerson(invitation.getPerson());
		membership.setMembershipType(invitation.getInvitationType());
	}
}
