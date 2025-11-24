package org.fmm.communitymgmt.service.raffle;

import java.time.LocalDate;

import org.fmm.communitymgmt.service.raffle.dto.RaffleDTO;

public interface CommunityGroupsRaffleService {

    /** 
     * Basado en el algoritmo de Fisher-Yates
     * @param persons
     * @return reordered persons
     */
    RaffleDTO prepareRaffle(LocalDate fromLDT, LocalDate toLDT);

    RaffleDTO raffle(RaffleDTO raffle);

}