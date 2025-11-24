package org.fmm.communitymgmt.service.raffle.dto;

import java.util.Comparator;

public class AvailabilityComparator implements Comparator<AvailabilityDTO> {

    @Override
    public int compare(AvailabilityDTO o1, AvailabilityDTO o2) {
        if (o1.getnCandidates().equals(o2.getnCandidates()))
            return o1.getDate().compareTo(o2.getDate());
        else
            return o1.getnCandidates() - o2.getnCandidates();
    }
}
