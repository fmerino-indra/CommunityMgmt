package org.fmm.communitymgmt.controller.calendar;

import java.util.List;

import org.fmm.communitymgmt.common.model.calendar.Event;
import org.fmm.communitymgmt.controller.AbstractCommunityMgmtController;
import org.fmm.communitymgmt.controller.interceptors.ParamSource;
import org.fmm.communitymgmt.controller.interceptors.RequiresClaim;
import org.fmm.communitymgmt.service.planning.CommunityPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/communities/{communityId}/planning")

public class CommunityMgmtCalendarPlanningController extends AbstractCommunityMgmtController {
	@Autowired
	private CommunityPlanningService communityPlanningService;
	
	/*
	 * TODO [FMMP] Es necesario revisar si el user pertenece a la comunidad. Ver ValidateCommunityBelongingInterceptor y anotaciones creadas ad-hoc RequiresClaim
	 */
	@RequiresClaim(claim = "secContext", jsonAttr = "myCommunitiesIds", allowList = true, parameter = "communityId", source = ParamSource.PATH)
	@GetMapping("/{year}/{month}")
	public ResponseEntity<List<Event>> getList(
			@PathVariable("communityId") Integer communityId,
			@PathVariable("year") Integer year,
			@PathVariable("month") Integer month
			) {
		
		List<Event> response = null;
		
		//response = relationshipService.getMembersOfCommunity(communityId);
		response = communityPlanningService.getPlanning(communityId, year, month);
		if (response != null && !response.isEmpty()) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}