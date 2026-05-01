package org.fmm.communitymgmt.calendar.rules.liturgy.result;

public abstract class AbstractLiturgyResult<T> {

	protected T result;
	
    public AbstractLiturgyResult(
            T result) {
		this.result=result;
    }

    @Override
    public String toString() {
        return String.format("LiturgicalPeriodResult{ %s }", result);
    }
	public T getResult() {
		return result;
	}
}
