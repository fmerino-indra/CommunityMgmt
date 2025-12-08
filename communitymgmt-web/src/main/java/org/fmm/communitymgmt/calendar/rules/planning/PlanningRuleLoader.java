package org.fmm.communitymgmt.calendar.rules.planning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.RuleCondition;
import org.fmm.communitymgmt.calendar.rules.RuleScope;
import org.fmm.communitymgmt.calendar.rules.conditions.DateRangeCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.FixedDateCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.HolidayCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.WeekOfCondition;
import org.fmm.communitymgmt.calendar.rules.conditions.WeekdayCondition;
import org.fmm.communitymgmt.calendar.rules.planning.effect.CancelEffect;
import org.fmm.communitymgmt.calendar.rules.planning.effect.MoveEffect;
import org.fmm.communitymgmt.calendar.rules.planning.effect.NoneEffect;
import org.fmm.communitymgmt.calendar.rules.planning.effect.RuleEffect;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PlanningRuleLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<PlanningRule> load(Path path) throws IOException {
        JsonNode root = mapper.readTree(Files.readAllBytes(path));
        List<PlanningRule> rules = new ArrayList<>();
        
        for (JsonNode node : root.get("rules")) {
            rules.add(parseRule(node));
        }
        return rules;
    }

    private PlanningRule parseRule(JsonNode node) {
        String id = node.get("id").asText();
        String name = node.get("name").asText();

        RuleScope scope = parseScope(node.get("scope"));
        List<RuleCondition> conditions = parseConditions(node.get("conditions"));
        RuleEffect effect = parseEffect(node.get("effect"));

        return new PlanningRule(id, name, scope, conditions, effect);
    }

    private RuleScope parseScope(JsonNode node) {
        List<String> celebrations = new ArrayList<>();
        node.get("celebrations").forEach(c -> celebrations.add(c.asText()));
        return new RuleScope(celebrations);
    }

    private List<RuleCondition> parseConditions(JsonNode array) {
        List<RuleCondition> list = new ArrayList<>();

        for (JsonNode cond : array) {
            String type = cond.get("type").asText();
            JsonNode v = cond.get("value");

            switch (type) {

                case "FIXED_DATE" -> list.add(
                        new FixedDateCondition(v.get("month").asInt(), v.get("day").asInt())
                );

                case "WEEKDAY" -> list.add(
                        new WeekdayCondition(DayOfWeek.valueOf(v.asText()))
                );

                case "DATE_RANGE" -> list.add(
                        new DateRangeCondition(
                                v.get("start").asText(),
                                v.get("end").asText()
                        )
                );

                case "HOLIDAY" -> list.add(new HolidayCondition(v.asText()));
                case "WEEK_OF" -> {
                	List<Integer> offset = null;
                	JsonNode node = v.get("offset");
                	if (node.isArray()) {
                		offset = new ArrayList<>();
                	    for (final JsonNode objNode : node) {
                	    	offset.add(objNode.asInt());
                	    }
                	};
                	list.add(
                		new WeekOfCondition(v.get("date").asText(), offset)
                	);
                }
                default -> throw new IllegalArgumentException("Unknown condition type: " + type);
            }
        }

        return list;
    }

    private RuleEffect parseEffect(JsonNode node) {
        String type = node.get("type").asText();

        return switch (type) {
        
            case "CANCEL" -> new CancelEffect();
            case "MOVE" -> new MoveEffect(node.get("params").get("newTime").asText());
            default -> new NoneEffect();
        };
    }
}
