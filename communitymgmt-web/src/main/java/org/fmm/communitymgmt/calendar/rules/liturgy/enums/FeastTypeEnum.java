package org.fmm.communitymgmt.calendar.rules.liturgy.enums;

public enum FeastTypeEnum {
    FIXED(1,"Fixed"),
    MOVEABLE(2,"Moveable");
    /*
	public static FeastRankEnum from(String name) {
		switch (name) {
			case "LITURGY": return LITURGY;
			case "WORD": return WORD;
			case "COMMUNITY": return COMMUNITY;
			default: return WORD;
		}
	}
	*/
    FeastTypeEnum(int id, String name) {
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
