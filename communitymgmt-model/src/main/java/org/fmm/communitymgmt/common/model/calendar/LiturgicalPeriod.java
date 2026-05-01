package org.fmm.communitymgmt.common.model.calendar;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


/**
 * The persistent class for the LiturgicalPeriod type of AbstractPeriod table (period).
 */
@Entity
@DiscriminatorValue(value = "1")
public class LiturgicalPeriod extends AbstractPeriod implements Serializable {
	private static final long serialVersionUID = 1L;
}