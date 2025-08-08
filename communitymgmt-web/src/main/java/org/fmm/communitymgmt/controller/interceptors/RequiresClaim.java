package org.fmm.communitymgmt.controller.interceptors;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresClaim {
	/**
	 * The claim name where resides the value in JWT
	 * @return the claim name
	 */
	String claim();

	/**
	 * If the claim value is JSON format, the name of the attribute inside this JSON
	 * E.g.:<br>
	 * <code>
	 *  &ensp;"secContext": {<br>
     *  &emsp;&ensp; "allCommunitiesIds": [14, 15],<br>
     *  &emsp;&ensp; "personId": 76,<br>
     *  &emsp;&ensp; "name": "Félix"<br>
     *  &ensp;}<br></code>
	 *  &emsp;<code>@RequiresClaim(name="...", jsonAttr="<b>allCommunitiesIds</b>")</code>
	 * @return the JSON attribute name where 
	 */
	String jsonAttr() default ""; 

	boolean allowList() default false;
	/**
	 * A constant value to check against the claim value of the JWT
	 * @return the constant value
	 */
	String value() default "";

	/**
	 * When the searched value is not a constant but is passed in the URI as a <code>@PathVariable</code> or a <code>@RequestParam</code> 
	 * <br>E.g.:<br>
	 * &emsp;&emsp;<code>@GetMapping("/{<b>communityId</b>}/invitations")</code>
	 * The value in <code>parameter</code> would be<br>
	 * &emsp;&emsp;<code>@RequiresClaim(name="...", parameter="<b>communityId</b>, source="<b>ParamSource.PATH</b>")</code>
	 * @return The name of the parameter (path variable or request parameter).
	 */
	String parameter() default "";
	
	ParamSource source() default ParamSource.REQUEST;
}
