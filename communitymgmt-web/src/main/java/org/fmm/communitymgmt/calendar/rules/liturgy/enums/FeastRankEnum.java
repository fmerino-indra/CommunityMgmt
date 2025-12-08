package org.fmm.communitymgmt.calendar.rules.liturgy.enums;

public enum FeastRankEnum {
    SOLEMNITY(1,"Solemnity"),
    FEAST(2,"Feast"),
    OBLIGATORY_MEMORIA(3,"Obligatory memoria"), 
    OPTIONAL_MEMORIA(4,"Optional memoria");
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
    FeastRankEnum(int id, String name) {
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
