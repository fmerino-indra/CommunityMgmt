package org.fmm.communitymgmt.calendar.rules.liturgy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.liturgy.computus.AbstractComputus;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.EasterComputus;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.FixedComputus;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.RelativeToBaseComputus;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.RelativeToFixedComputus;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.RelativeToInlineComputus;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.AbstractAdjust;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.AddAdjust;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.AdjustEnum;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.ClosestWeekdayAdjust;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.NextNthWeekdayAdjust;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.OnOrNextWeekdayAdjust;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.OnOrPreviousWeekdayAdjust;
import org.fmm.communitymgmt.calendar.rules.liturgy.computus.adjust.PreviousNthWeekdayAdjust;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * E.g.
 * 			[{
 *			    "id": "easter",
 *			    "name": "Domingo de Pascua",
 *			    "kind": "LITURGY",
 *			    "payload": {
 *			        "computus": {
 *			            "type": "EASTER_COMPUTUS"
 *			        }
 *			    }
 *			}, {
 *			    "id": "first_sunday_advent",
 *			    "name": "Primer Domingo de Adviento",
 *			    "kind": "LITURGY",
 *			    "payload": {
 *			        "computus": {
 *			            "type": "FIXED_COMPUTUS",
 *			            "month": 11,
 *			            "day": 30
 *			        }
 *			    }
 *			}, {
 *	            "id": "first_sunday_advent",
 *	            "name": "Primer Domingo de Adviento",
 *	            "kind": "LITURGY",
 *	            "payload": {
 *	                "computus": {
 *	                    "type": "RELATIVE_TO_FIXED_COMPUTUS",
 *	                    "month": 11,
 *	                    "day": 30,
 *	                    "adjust": {
 *	                        "type": "CLOSEST_WEEKDAY", (CLOSEST_WEEKDAY|ON_OR_NEXT_NTH_WEEKDAY|PREIOUS_NTH_WEEKDAY|ADD_VALUES)
 *	                        "weekday": SUNDAY,
 *	                    }
 *	                }
 *	            }
 *	        }, {
 *	            "id": "first_sunday_advent",
 *	            "name": "Primer Domingo de Adviento",
 *	            "kind": "LITURGY",
 *	            "payload": {
 *	                "computus": {
 *	                    "type": "RELATIVE_TO_INLINE",
 *	                    "inline": {
 *	                    ...
 *	                    },
 *	                    "adjust": {
 *	                        "type": "CLOSEST_WEEKDAY", (CLOSEST_WEEKDAY|ON_OR_NEXT_NTH_WEEKDAY|PREIOUS_NTH_WEEKDAY|ADD_VALUES)
 *	                        "weekday": SUNDAY,
 *	                    }
 *	                }
 *	            }
 *            }, {
 *		        "id": "first_sunday_advent",
 *		        "name": "Primer Domingo de Adviento",
 *		        "kind": "LITURGY",
 *		        "payload": {
 *		            "computus": {
 *		                "type": "RELATIVE_TO_BASE",
 *		                "base": "christmas",
 *		                "adjust":{
 *		                    "type": "ADD_VALUES",
 *		                    "value": -4,
 *		                    "unit": WEEKS (WEEKS | DAYS)
 *		                }
 *		            }
 *		        }
 *	        }]
 *
 */
@Component
public class LiturgyRuleLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Carga fichero JSON con las reglas litúrgicas.
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public List<LiturgyRule> load(Path path) throws IOException {
        JsonNode root = mapper.readTree(Files.readAllBytes(path));
        List<LiturgyRule> rules = new ArrayList<>();
        
        for (JsonNode node : root.get("rules")) {
            rules.add(parseRule(node));
        }
        return rules;
    }

    private LiturgyRule parseRule(JsonNode node) {
        String id = node.get("id").asText();
        String name = node.get("name").asText();
        String kind = node.get("kind").asText();
        String override = null;
        if (node.has("override"))
        	override = node.get("override").asText();
        
        int liturgicalYearShift = 0;
        if (node.has("liturgicalYearShift"))
        	liturgicalYearShift = node.get("liturgicalYearShift").asInt();
        
        LiturgyRuleScope scope = parseScope(node.get("scope"));
        //List<RuleCondition> conditions = parseConditions(node.get("conditions"));

        AbstractComputus computus = parseComputus(node.get("payload").get("computus"));
        if (!kind.equals("LITURGY"))
        	throw new RuntimeException("[FMMP] Bad JSON structure");
        
        return new LiturgyRule(id, name, liturgicalYearShift,scope, computus,override);
    }

    /**
     * universal | county | diocese | local
     * @param node
     * @return
     */
    private LiturgyRuleScope parseScope(JsonNode node) {
    	if (node == null)
    		return null;
    	
    	LiturgyRuleScope scope = null;
        JsonNode arrayJsonNode = null;
    	
        List<String> values = new ArrayList<>();
        String scopeStr = null;
        
        scopeStr = node.get("magnitude").asText();
        
        scope = new LiturgyRuleScope(scopeStr, values);
        arrayJsonNode = node.get("values");
        
		if (arrayJsonNode != null && arrayJsonNode.isArray()) {
			for (JsonNode magnNode: arrayJsonNode) {
				scope.addMagnitude(magnNode.asText());
			}
		}
        return scope;
    }

    private AbstractComputus parseComputus(JsonNode node) {
    	String type = node.get("type").asText();
    	AbstractComputus computus = null;
    	
    	int month, day;
    	
    	String base;
    	JsonNode adjustJson;
    	List<AbstractAdjust> adjustList = null;
    	
    	adjustJson = node.get("adjust");
    	if (adjustJson != null)
    		adjustList = parseAdjust(adjustJson);
        switch (type) {
        /*
 		*/
        case "EASTER_COMPUTUS" -> {
        	computus = new EasterComputus();
        }
        case "FIXED_COMPUTUS" -> {
        	month = node.get("month").asInt();
        	day = node.get("day").asInt();
        	computus = new FixedComputus(month, day);
        }
        case "RELATIVE_TO_FIXED_COMPUTUS" -> {
        	month = node.get("month").asInt();
        	day = node.get("day").asInt();
        	computus = new RelativeToFixedComputus(month, day, adjustList);
        }
        case "RELATIVE_TO_INLINE_COMPUTUS" -> {
        	JsonNode inlineComputusNode = null;
        	inlineComputusNode = node.get("inline").get("computus");
        	AbstractComputus inlineComputus = parseComputus(inlineComputusNode);
        	computus = new RelativeToInlineComputus(inlineComputus, adjustList);
        }
		case "RELATIVE_TO_BASE_COMPUTUS" -> {
			base = node.get("base").asText();
			computus = new RelativeToBaseComputus(base, adjustList);
		}
        }
        
        return computus;
    }
/**
 *                    "adjust":{
 *		                    "type": "ADD_VALUES",
 *		                    "value": -4,
 *		                    "unit": WEEKS (WEEKS | DAYS)
 *		                }

 * @param adjustJson
 * @return
 */
	private List<AbstractAdjust> parseAdjust(JsonNode arrayJsonNode) {
		AbstractAdjust adjust = null;
		String type;
		AdjustEnum typeEnum;
		String weekday, unit;
		int nth,value;
		
		if (arrayJsonNode == null || !arrayJsonNode.isArray())
			return Collections.emptyList();
		
		List<AbstractAdjust> out = new ArrayList<>();
		for (JsonNode adjustJson: arrayJsonNode) {
			type = adjustJson.get("type").asText();
			try {
				typeEnum=AdjustEnum.valueOf(type);
			} catch (IllegalArgumentException iae) {
                throw new IllegalArgumentException("Unknown Adjust type: " + type, iae);
			}
			
			switch (typeEnum) {
			case ADD_DAYS -> {
				value = adjustJson.get("value").asInt();
				adjust = new AddAdjust(value, ChronoUnit.DAYS);
			}
			case ADD_WEEKS -> {
				value = adjustJson.get("value").asInt();
				adjust = new AddAdjust(value, ChronoUnit.WEEKS);
			}
			case ADD_VALUES -> {
				unit = adjustJson.get("unit").asText();
				value = adjustJson.get("value").asInt();
				
				ChronoUnit cUnit = null;
				if (ChronoUnit.DAYS.name().toUpperCase().equals(unit.toUpperCase()))
					cUnit = ChronoUnit.DAYS;
				else if (ChronoUnit.WEEKS.name().toUpperCase().equals(unit.toUpperCase()))
					cUnit = ChronoUnit.WEEKS;
				else 
					throw new IllegalArgumentException("Unknown unit " + unit + " for an AddAdjust");
				
				adjust = new AddAdjust(value, cUnit);
				
			}
			case AdjustEnum.CLOSEST_WEEKDAY -> {
				weekday = adjustJson.get("weekday").asText();
				adjust = new ClosestWeekdayAdjust(DayOfWeek.valueOf(weekday));
			}
			case NEXT_NTH_WEEKDAY -> {
				weekday = adjustJson.get("weekday").asText();
				nth = adjustJson.get("nth").asInt();
				adjust = new NextNthWeekdayAdjust(DayOfWeek.valueOf(weekday), nth);
			}
			case ON_OR_NEXT_NTH_WEEKDAY -> {
				weekday = adjustJson.get("weekday").asText();
				nth = adjustJson.get("nth").asInt();
				adjust = new OnOrNextWeekdayAdjust(DayOfWeek.valueOf(weekday));
			}
			case PREVIOUS_NTH_WEEKDAY -> {
				weekday = adjustJson.get("weekday").asText();
				nth = adjustJson.get("nth").asInt();
				adjust = new PreviousNthWeekdayAdjust(DayOfWeek.valueOf(weekday), nth);
			}
			case ON_OR_PREVIOUS_NTH_WEEKDAY -> {
				weekday = adjustJson.get("weekday").asText();
				nth = adjustJson.get("nth").asInt();
				adjust = new OnOrPreviousWeekdayAdjust(DayOfWeek.valueOf(weekday));
			}
			default -> throw new IllegalArgumentException("Unexpected value: " + typeEnum);
			}
			out.add(adjust);
		}
		return out;
	}
}
