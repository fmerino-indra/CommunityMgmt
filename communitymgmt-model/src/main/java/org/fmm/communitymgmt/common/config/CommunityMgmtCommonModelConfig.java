package org.fmm.communitymgmt.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class CommunityMgmtCommonModelConfig {
	@Bean
	public JavaTimeModule javaTimeModule() {
		return new JavaTimeModule();
	}
	
}
