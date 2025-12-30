package org.fmm.communitymgmt.calendar.rules.liturgy.enums;

public enum SeasonEnum {
    PASCHAL_TRIDUUM(1,"Paschal Triduum"),
    EASTER_TIME(2,"Easter_Time"),
    LENT(3,"Lent"),
    CHRISTMAS_TIME(3, "Christmas"),
    ADVENT(4,"Advent"),
    ORDINARY_TIME(5,"Ordinary Time");
    
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
    SeasonEnum(int id, String name) {
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
