package org.fmm.communitymgmt.calendar.rules.effect;

public class MoveEffect extends RuleEffect {

	private final String newTime; 
	
	public MoveEffect(String newTime) {
		super(RuleEffectType.MOVE);
		this.newTime = newTime;
	}

	public String getNewTime() {
		return newTime;
	}

}
