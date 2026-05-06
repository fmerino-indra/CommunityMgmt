package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.fmm.communitymgmt.calendar.rules.liturgy.AbstractLiturgyRule;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleContext;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.AbstractAdjust;
import org.fmm.communitymgmt.calendar.rules.liturgy.result.LiturgyDateResult;

/**
 * 
    {
        "id": "christ_the_king",
        "name": "Cristo Rey",
        "kind": "LITURGY",
        "payload": {
            "computus": {
                "type": "RELATIVE_TO_BASE",
                "base": "first_sunday_advent",
                "adjust": [
                    {
                        "type": "PREVIOUS_WEEKDAY",
                        "weekday": "SUNDAY"
                    }
                ]
            }
        }
    },

    {
        "id": "first_sunday_advent",
        "name": "Primer Domingo de Adviento",
        "kind": "LITURGY",
        "payload": {
            "computus": {
                "type": "RELATIVE_TO_BASE",
                "base": "christmas",
                "adjust":{
                    "type": "ADD_VALUES",
                    "value": -4,
                    "unit": WEEKS (WEEKS | DAYS)
                }
            },
            "validation": {...}
        }
    },
    {
        "id": "first_sunday_advent",
        "name": "Primer Domingo de Adviento",
        "kind": "LITURGY",
        "payload": {
            "computus": {
                "type": "RELATIVE_TO_FIXED",
                "month": 11,
                "day": 30,
                "adjust": {
                    "type": "CLOSEST_WEEKDAY", (CLOSEST_WEEKDAY|NEXT_NTH_WEEKDAY|PREIOUS_NTH_WEEKDAY|ADD_VALUES)
                    "weekday": SUNDAY,
                }
            }
        }
    }
 */
public class RelativeToBaseComputus extends AbstractRelativeToComputus {
	private String baseRuleId;
	public RelativeToBaseComputus(String baseRuleId, List<AbstractAdjust> adjustList) {
		super(ComputusTypeEnum.RELATIVE_TO_BASE_COMPUTUS, adjustList);
		this.baseRuleId = baseRuleId;
	}

	public String getBaseRuleId() {
		return baseRuleId;
	}


	@Override
	public LiturgyDateResult compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		
		AbstractLiturgyRule baseRule = registry.getFestRule(baseRuleId);
		if (baseRule == null)
			throw new IllegalStateException("Base rule not found: " + baseRuleId);
		// compute base (it should have been computed earlier)
		LocalDate baseDate = registry.getComputedFeastRule(baseRuleId);
		if (baseDate == null) {
			// If not computed yet, attempt to compute now (defensive)
			baseDate = (LocalDate)baseRule.getComputus().compute(liturgicalYear, ctx, registry).getResult();
			registry.setComputedFeastRule(baseRuleId, baseDate);
		}
		LocalDate cur = baseDate;
		for (AbstractAdjust adjust: getAdjustList()) {
			cur = adjust.apply(cur);
		}
		return new LiturgyDateResult(cur);
	}
    @Override
    public Set<String> referencedRuleIds() {
    	return Collections.singleton(baseRuleId); 
    }
    
    @Override
    public String toString() {
    	String aux = String.format("Computus{ %s -> %s (adjust: [", type.name(), baseRuleId);
    	for (AbstractAdjust adjust: adjustList) {
    		aux+=adjust.toString();
    	}
    	aux+="])}";
        return aux;
    }
    
}
