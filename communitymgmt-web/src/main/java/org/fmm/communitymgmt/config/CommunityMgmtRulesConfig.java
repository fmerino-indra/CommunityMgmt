package org.fmm.communitymgmt.config;

import java.nio.file.Path;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.Rule;
import org.fmm.communitymgmt.calendar.rules.RuleLoader;
import org.fmm.communitymgmt.calendar.rules.RulesEngine;
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
	private RuleLoader ruleLoader;

	@Bean
	public List<Rule> ruleList() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:rules-v3.json");
		Path path = resource.getFile().toPath();
		return ruleLoader.load(path);
	}
	
    @Bean
    public RulesEngine rulesEngine(List<Rule> rules) throws Exception {
        return new RulesEngine(rules);
    }
}
