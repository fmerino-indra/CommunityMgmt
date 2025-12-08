package org.fmm.communitymgmt.config;

import java.nio.file.Path;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.RulesEngine;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRule;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleLoader;
import org.fmm.communitymgmt.calendar.rules.planning.PlanningRule;
import org.fmm.communitymgmt.calendar.rules.planning.PlanningRuleLoader;
import org.fmm.communitymgmt.calendar.rules.planning.PlanningRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class CommunityMgmtRulesConfig {

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private PlanningRuleLoader ruleLoader;

	@Autowired
	private LiturgyRuleLoader liturgyRuleLoader;
	
	@Bean
	public List<PlanningRule> ruleList() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:planning/rules-planning-v3.json");
		Path path = resource.getFile().toPath();
		return ruleLoader.load(path);
	}
/*
	@Bean
	public List<LiturgyRule> liturgyRuleList() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:liturgy/rules-liturgical-v2.json");
		Path path = resource.getFile().toPath();
		return liturgyRuleLoader.load(path);
	}
	*/
	
    @Bean
    public RulesEngine rulesEngine(List<PlanningRule> rules) throws Exception {
        return new PlanningRulesEngine(rules);
    }
}
