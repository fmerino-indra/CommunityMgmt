package org.fmm.communitymgmt.common.model.groups;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
public class ReserveRafflePerson extends RafflePerson {

    /**
     * 
     */
    private static final long serialVersionUID = -282145399565715711L;

}
