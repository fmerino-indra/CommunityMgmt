package org.fmm.communitymgmt.config;

import java.util.List;

import org.fmm.communitymgmt.calendar.rules.Rule;
import org.fmm.communitymgmt.calendar.rules.RuleParser;
import org.fmm.communitymgmt.calendar.rules.RulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommunityMgmtRulesConfig {

    @Bean
    public RulesEngine rulesEngine() throws Exception {
        List<Rule> rules = RuleParser.parseFromResource("/rules.json");
        return new RulesEngine(rules);
    }
}
