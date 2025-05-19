package org.fmm.communitymgmt.config;

import org.fmm.oauth.springsocial.config.OidcHttpSecurityCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class CommunityMgmtSecurityConfig {
	@Bean
	public OidcHttpSecurityCustomizer projectSecurityCustomizer() {
		return http -> {
			http
				.headers(headers -> headers.frameOptions( frameOptions -> frameOptions
						.sameOrigin()))
				.authorizeHttpRequests(authz -> authz
					.requestMatchers(AntPathRequestMatcher.antMatcher("/public-site/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).authenticated()
				)
				.oauth2ResourceServer(oauth2 -> oauth2.jwt( jwt -> jwt
						.jwtAuthenticationConverter(jwtAuthenticationConverter())))
				;
		};
		
	}
	/**
	 * How to convert JWT claims in Authentication object
	 * @return
	 */
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		return new JwtAuthenticationConverter();
	}
}
