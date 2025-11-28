package org.fmm.communitymgmt.common.util.enums;

public enum TripodEnum {
    LITURGY(1,"Liturgy"), // Eucharist
    WORD(2,"Word"), // Word Service
    COMMUNITY(3,"Community"); // Convivence
    
	public static TripodEnum from(String name) {
		switch (name) {
			case "LITURGY": return LITURGY;
			case "WORD": return WORD;
			case "COMMUNITY": return COMMUNITY;
			default: return WORD;
		}
	}
    TripodEnum(int id, String name) {
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
