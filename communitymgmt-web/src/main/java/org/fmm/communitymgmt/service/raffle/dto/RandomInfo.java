package org.fmm.communitymgmt.service.raffle.dto;

import org.fmm.communitymgmt.common.model.Person;

public class RandomInfo {
    private Person person = null;
    private int selectedTimes = 0;
    private int assignedTimes = 0;
    
    public RandomInfo(Person person) {
        this.person = person;
    }
    
    public Person getPerson() {
        return person;
    }
    public int getSelectedTimes() {
        return selectedTimes;
    }
    public void addSelectedTimes() {
        this.selectedTimes++;
    }
    public int getAssignedTimes() {
        return assignedTimes;
    }
    public void addAssignedTimes() {
        this.assignedTimes++;
    }
    @Override
    public String toString() {
        return person.toString() + "|" + assignedTimes + "|" + selectedTimes;
    }
    
    
}
