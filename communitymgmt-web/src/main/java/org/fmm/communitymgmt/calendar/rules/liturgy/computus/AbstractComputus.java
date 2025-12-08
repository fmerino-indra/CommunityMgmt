package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

public abstract class AbstractComputus implements ComputusExecutable {

	protected ComputusTypeEnum type;
	
	public AbstractComputus(ComputusTypeEnum type) {
		super();
		this.type = type;
	}

	public ComputusTypeEnum getType() {
		return type;
	}
	
    @Override
    public String toString() {
        return String.format("Computus{ %s}", type.name());
    }
}