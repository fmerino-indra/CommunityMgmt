package org.fmm.communitymgmt.controller.interceptors;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import org.fmm.communitymgmt.dto.DataJwtSecContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ValidateCommunityBelongingInterceptor implements HandlerInterceptor {

	public static final String X_DATA_JWT="x-data-jwt";
	
	private ObjectMapper mapper = new ObjectMapper(); 
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		if (!(handler instanceof HandlerMethod method))
			return true;
		
		RequiresClaims annotationContainer = null;
		RequiresClaim annotation = null; 
//		Authentication auth = null;

		annotationContainer=method.getMethodAnnotation(RequiresClaims.class);
		annotation = method.getMethodAnnotation(RequiresClaim.class);
		
		if (annotation == null && annotationContainer == null)
			return true;
		
//		auth=SecurityContextHolder.getContext().getAuthentication();
//		if (!(auth instanceof @SuppressWarnings("unused") JwtAuthenticationToken jwtAuth)) {
//			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
//			return false;
//		}
		
		SignedJWT dataJwt = null;
		Map<String, Object> jwtClaims = null;

		
		Map<String, String> uriTemplateVars = null;

        //@TODO Recoger los valores enviados de los Header y del Body como formulario
        //String body=null; // De momento no está
        
        boolean match = false;
		
        try {
			dataJwt = initJwt(request.getHeader(X_DATA_JWT));
    	} catch (ParseException pe) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malformed token");
			return false;
    	} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not data token JWT or malformed token");
			return false;
    	}
        
        
        try {
			jwtClaims = dataJwt.getJWTClaimsSet().getClaims();
		} catch (ParseException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malformed token");
			return false;
    	} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not data token JWT or malformed token");
			return false;
    	}
		uriTemplateVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		if (annotationContainer != null) {
			for (RequiresClaim annotationCheck: annotationContainer.value()) {
				try {
					match = processAnnotation(annotationCheck,jwtClaims, uriTemplateVars, request);
			    	if (!match) {
			        	response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token: claim '" + annotation.claim() + "' does not match parameter '" + annotation.parameter() + "'");
			    		return false;
			    	}
				} catch (DataFormatException e) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malformed claim in dataJwt");
					return false;
				}
			}
		} else {
			try {
				match = processAnnotation(annotation,jwtClaims, uriTemplateVars, request);
		    	if (!match) {
		        	response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token: claim '" + annotation.claim() + "' does not match parameter '" + annotation.parameter() + "'");
		    		return false;
		    	}
				
			} catch (DataFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malformed claim in dataJwt");
				return false;
			}
		}
		/*
		if (annotationContainer == null && annotation != null) {
			if (!annotation.value().isBlank()) {
				valueToCheck = annotation.value();
			} else if (annotation.source().equals(ParamSource.REQUEST)) {
				// RequestParams
				valueToCheck = request.getParameter(annotation.parameter());
			} else {
				// Path variable
				valueToCheck = uriTemplateVars.get(annotation.parameter());
			}
		}
		try {
			match = validateValue(valueToCheck, jwtClaims, annotation.claim(), annotation.jsonAttr(), annotation.allowList());
		} catch (DataFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage() + "Malformed claim '" + annotation.claim() + "' in dataJwt");
			return false;
		}
		*/
		
		// Lo consideramos AND
//    	if (!match) {
//        	response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token: claim '" + annotation.claim() + "' does not match parameter '" + annotation.parameter() + "'");
//    		return false;
//    	}
    	return true;
	}
	
	private boolean processAnnotation(RequiresClaim annotation, Map<String, Object> jwtClaims,
			Map<String, String> uriTemplateVars, HttpServletRequest request) throws DataFormatException {
        String valueToCheck = null;

		if (annotation != null) {
			if (!annotation.value().isBlank()) {
				valueToCheck = annotation.value();
			} else if (annotation.source().equals(ParamSource.REQUEST)) {
				// RequestParams
				valueToCheck = request.getParameter(annotation.parameter());
			} else {
				// Path variable
				valueToCheck = uriTemplateVars.get(annotation.parameter());
			}
		}
		return validateValue(valueToCheck, jwtClaims, annotation.claim(), annotation.jsonAttr(), annotation.allowList());
	}
	
	private boolean validateValue(String valueToCheck, Map<String, Object> jwtClaims, String claim, String jsonAttr, boolean allowList) throws DataFormatException {
		Map<String, Object> map = null;
		Object claimValue = null;
		Object otherValueToCheck = null;
		if (jsonAttr != null) {
			map = processJson(jwtClaims, claim);
			claimValue = map.get(jsonAttr);
			if (claimValue==null) {
				throw new DataFormatException();
			}
		} else {
			claimValue = jwtClaims.get(claim);
		}
		
		//Intentamos convertirlo a número:
		otherValueToCheck = convertToInteger(valueToCheck);
		if (otherValueToCheck == null)	
			otherValueToCheck=valueToCheck;
		
		return switch (claimValue) {
			case List<?> list -> allowList && list.contains(otherValueToCheck);
			case String s -> s.equals(otherValueToCheck);
			case Number n -> n.toString().equals(otherValueToCheck);
			default -> false;
		};
	}

	private static Integer convertToInteger(Object possibleNumber) {
		if (possibleNumber == null || possibleNumber.toString().isBlank()) {
			return null;
		}
		try {
			return Integer.parseInt(possibleNumber.toString());
		} catch (NumberFormatException nfe) {
			return null;
		}
	}
	
	private Map<String, Object> processJson(Map<String, Object> jwtClaims, String claim) throws DataFormatException {
		Object jsonObject = null;
		jsonObject = jwtClaims.get(claim);
		try {
		if (jsonObject != null)
			return mapper.readValue(jsonObject.toString(), new TypeReference<>() {});
		} catch (JsonMappingException jme) {
		} catch (JsonProcessingException jpe) {
		}
		throw new DataFormatException();
	}
	
	@SuppressWarnings("unchecked")
	//@Override
	public boolean preHandleOld(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod method))
			return true;
		
		RequiresClaim annotation = method.getMethodAnnotation(RequiresClaim.class);
		if (annotation == null)
			return true;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof @SuppressWarnings("unused") JwtAuthenticationToken jwtAuth)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
			return false;
		}
		
		SignedJWT dataJwt = null;
		Integer communityId = null;
		
		Map<String, String> uriTemplateVars = null;
		
        String pathValue = null;
		try {
			dataJwt = initJwt(request.getHeader(X_DATA_JWT));
    	} catch (ParseException pe) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malformed token");
			return false;
    	} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Not data token JWT or malformed token");
			return false;
    	}
		
		uriTemplateVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		pathValue=uriTemplateVars.get(annotation.parameter());
		
    	communityId = Integer.valueOf(pathValue);
    	
    	communityId = validateCommunityIdFromJwt(dataJwt, communityId);
    	if (communityId != null)
    		return true;

    	response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token");
    	return false;
	}
	
	private SignedJWT initJwt(String jwtString) throws ParseException {
//    	String jwtString = headers.get("x-data-jwt");
		SignedJWT dataJwt = null;
		if (jwtString == null || jwtString.isBlank())
			throw new RuntimeException("No token");
		dataJwt = SignedJWT.parse(jwtString);
    	dataJwt.getJWTClaimsSet();
    	return dataJwt;
	}
	
	private Integer validateCommunityIdFromJwt(SignedJWT dataJwt, Integer communityId) throws NumberFormatException, ParseException {
		String idsStr = null;
		try {
			idsStr = dataJwt.getJWTClaimsSet().getStringClaim("secContext");			
		} catch (Exception e) {
			
		}
		try {
			if (DataJwtSecContext.fromJson(idsStr).getMyCommunitiesIds().contains(communityId))
				return communityId;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
	

}
