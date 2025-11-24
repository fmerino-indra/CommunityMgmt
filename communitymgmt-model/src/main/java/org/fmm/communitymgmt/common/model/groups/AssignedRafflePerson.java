package org.fmm.communitymgmt.common.model.groups;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class AssignedRafflePerson extends RafflePerson {

    /**
     * 
     */
    private static final long serialVersionUID = 9023577207547469211L;
}
