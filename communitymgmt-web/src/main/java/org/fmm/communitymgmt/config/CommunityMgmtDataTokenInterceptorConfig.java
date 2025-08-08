package org.fmm.communitymgmt.config;

import org.fmm.communitymgmt.controller.interceptors.ValidateCommunityBelongingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommunityMgmtDataTokenInterceptorConfig implements WebMvcConfigurer {
	private final ValidateCommunityBelongingInterceptor claimInterceptor;
	
	public CommunityMgmtDataTokenInterceptorConfig(ValidateCommunityBelongingInterceptor claimInterceptor) {
		this.claimInterceptor = claimInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(claimInterceptor);
	}
}
