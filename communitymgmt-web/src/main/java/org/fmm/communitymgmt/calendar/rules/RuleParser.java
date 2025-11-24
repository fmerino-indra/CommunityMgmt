package org.fmm.communitymgmt.calendar.rules;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.conditions.Condition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RuleParser {

    public static List<Rule> parseFromResource(String resourcePath) throws Exception {
        ObjectMapper m = new ObjectMapper();
        try (InputStream is = RuleParser.class.getResourceAsStream(resourcePath)) {
            JsonNode root = m.readTree(is);
            List<Rule> rules = new ArrayList<>();
            for (JsonNode rnode : root.get("rules")) {
                String id = rnode.get("id").asText();
                String block = rnode.get("block").asText();
                String reason = rnode.has("reason") ? rnode.get("reason").asText() : null;
                Object meta = rnode.has("meta") ? m.convertValue(rnode.get("meta"), Object.class) : null;
                List<Condition> conds = ConditionFactory.listFromJson(rnode.get("conditions"));
                rules.add(new Rule(id, block, conds, reason, meta));
            }
            return rules;
        }
    }
}
