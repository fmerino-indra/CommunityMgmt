package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

public class PeriodGen<T> {
	
	private T init = null;
	private T end = null;
	
	public T getInit() {
		return init;
	}
	public void setInit(T init) {
		this.init = init;
	}
	public T getEnd() {
		return end;
	}
	public void setEnd(T end) {
		this.end = end;
	}
}
