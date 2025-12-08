package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.AbstractAdjust;

/**
 * 
    {
        "id": "first_sunday_advent",
        "name": "Primer Domingo de Adviento",
        "kind": "LITURGY",
        "payload": {
            "computus": {
                "type": "RELATIVE_TO_INLINE_COMPUTUS",
                "inline": {
			        "computus": {
			            "type": "FIXED_COMPUTUS",
			            "month": 11,
			            "day": 30
			        }
                },
                "adjust": {
                    "type": "CLOSEST_WEEKDAY", (CLOSEST_WEEKDAY|NEXT_NTH_WEEKDAY|PREIOUS_NTH_WEEKDAY|ADD_VALUES)
                    "weekday": SUNDAY,
                }
            }
        }
    }
 */
public class RelativeToInlineComputus extends RelativeToComputus {
	protected AbstractComputus inlineComputus;
	
	public RelativeToInlineComputus(AbstractComputus dependentComputus, List<AbstractAdjust> adjustList) {
		super(ComputusTypeEnum.RELATIVE_TO_INLINE_COMPUTUS,adjustList);
		this.inlineComputus = dependentComputus;
	}
	@Override
	public LocalDate compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		LocalDate base = inlineComputus.compute(liturgicalYear, ctx, registry);
		LocalDate cur = base;
		for (AbstractAdjust adjust: getAdjustList()) {
			cur = adjust.apply(cur);
		}
		return cur;
	}
    @Override
    public Set<String> referencedRuleIds(){
        // If the inline computus references rules, propagate them; otherwise empty
        return inlineComputus == null ? Collections.emptySet() : inlineComputus.referencedRuleIds();
    }

	public AbstractComputus getInlineComputusComputus() {
		return inlineComputus;
	}
}
