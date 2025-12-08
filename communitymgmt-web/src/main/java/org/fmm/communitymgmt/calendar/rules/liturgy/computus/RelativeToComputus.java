package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.util.List;

import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.AbstractAdjust;

public abstract class RelativeToComputus extends AbstractComputus {

	protected List<AbstractAdjust> adjustList;

	public RelativeToComputus(ComputusTypeEnum type, List<AbstractAdjust> adjustList) {
		super(type);
		this.adjustList = adjustList;
	}

	public List<AbstractAdjust> getAdjustList() {
		return adjustList;
	}
	
    @Override
    public String toString() {
        String concat = "";
        concat = concat + String.format("Adjust: [");
        for (AbstractAdjust a : adjustList) {
        	concat = concat+a.toString();
        }

        concat = concat + "]";
        return concat;
    }
	
}