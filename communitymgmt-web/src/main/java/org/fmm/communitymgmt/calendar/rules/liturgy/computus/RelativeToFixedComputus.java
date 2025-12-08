package org.fmm.communitymgmt.calendar.rules.liturgy.computus;

import java.time.LocalDate;
import java.util.List;

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
                "type": "RELATIVE_TO_FIXED_COMPUTUS",
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
public class RelativeToFixedComputus extends RelativeToComputus {
	private int month;
	private int day;
	
	public RelativeToFixedComputus(int month, int day, List<AbstractAdjust> adjustList) {
		super(ComputusTypeEnum.RELATIVE_TO_FIXED_COMPUTUS, adjustList);
		this.month = month;
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	@Override
	public LocalDate compute(int liturgicalYear, LiturgyRuleContext ctx, LiturgyRuleRegistry registry) {
		LocalDate baseDate = LocalDate.of(liturgicalYear, month, day);
		LocalDate cur = baseDate;
		for (AbstractAdjust adjust: getAdjustList()) {
			cur = adjust.apply(cur);
		}
		return cur;
	}
    @Override
    public String toString() {
    	String aux = String.format("Computus{ %s -> %d/%d (adjust: [", type.name(), day, month);
    	for (AbstractAdjust adjust: adjustList) {
    		aux+=adjust.toString();
    	}
    	aux+="])}";
        return aux;
    }
	
	
}
