package org.fmm.communitymgmt.common.util.enums;

public enum EventTypeEnum {
	INPARISH(1, "In parish"),
    DOMESTIC(2,"Domestic"),
    OTHER(3, "Other"); 
    
    EventTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    private int id;
    private String name;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
