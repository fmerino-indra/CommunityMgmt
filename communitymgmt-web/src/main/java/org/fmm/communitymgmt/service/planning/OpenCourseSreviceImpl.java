package org.fmm.communitymgmt.service.planning;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgicalFeastDto;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleEvaluator;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleLoader;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;
import org.fmm.communitymgmt.common.model.calendar.LiturgicalFeast;
import org.fmm.communitymgmt.common.model.calendar.LiturgicalPeriod;
import org.fmm.communitymgmt.common.model.calendar.LiturgicalYear;
import org.fmm.communitymgmt.common.repository.calendar.LiturgicalFeastRepository;
import org.fmm.communitymgmt.common.repository.calendar.LiturgicalYearRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service("OpenCourseService")
public class OpenCourseSreviceImpl implements OpenCourseService {
	private final Logger logger = LoggerFactory.getLogger(OpenCourseSreviceImpl.class);

	@Autowired
	private LiturgicalYearRepository yearRepository;
	
	@Autowired
	private LiturgicalFeastRepository feastRepository;

	@Autowired
	private ResourceLoader resourceLoader;
    	
	@Override
	public LiturgicalYear openCourse(int year) {
		Optional<LiturgicalYear> optYear = null;
		LiturgicalYear lYear = null;
		List<LiturgicalFeast> feasts = null;
		List<LiturgicalFeastDto> feastsDto = null;
		LiturgicalFeast feast = null;
		
		optYear = yearRepository.findLiturgicalYearByYear(year);
		if (optYear.isPresent()) {
			lYear = optYear.get();
			feasts = feastRepository.findLiturgicalFeastsByYear(year);
			
		} else {
			lYear = new LiturgicalYear();
			lYear.setId(year);
			lYear.setYear(year);
			lYear.setYearName(""+(year-1)+"-"+year);
			lYear = yearRepository.save(lYear);
			
			try {
				feastsDto = generateFeasts();
				if (feastsDto !=null) {
					feasts = new ArrayList<LiturgicalFeast>(feastsDto.size());
					for (LiturgicalFeastDto dto : feastsDto) {
						feast = LiturgicalFeastDto.fromDto(dto);
						feast.setYear(lYear);
						feast = feastRepository.save(feast);
						feasts.add(feast);
					}
				}
			} catch (IOException e) {
				logger.error("[FMMP] Error generating feasts, {}", e);
			}
		}

		if (feasts != null) {
			for (LiturgicalFeast lf:feasts) {
				logger.debug("{}",lf);
			}
			lYear.setFeasts(feasts);
		}
		return lYear;
	}

	private List<LiturgicalFeastDto> generateFeasts() throws IOException {
        LiturgyRuleRegistry registry = new LiturgyRuleRegistry();
        LiturgyRuleLoader loader = new LiturgyRuleLoader();
        
		Resource resource = resourceLoader.getResource("classpath:liturgy/liturgy-rules-v5.json");
		Path path = null;
		
		
		path = resource.getFile().toPath();
        
        registry = loader.load(path);
/*        
        List<AbstractLiturgyRule> rules = loader.load(path).get(RuleKindEnum.LITURGY);

        for (AbstractLiturgyRule r: rules) 
        	registry.register(r);
*/
        LiturgyRuleEvaluator ev = new LiturgyRuleEvaluator(registry);
        int litYear = 2026;
        List<LiturgicalFeastDto> feasts = ev.evaluate(litYear, "es", "ES");

		return feasts;
	}

	@Override
	public List<LiturgicalPeriod> createPeriods(int year) {
		List<LiturgicalPeriod> periodsList = null;
		
		return periodsList;
	}
}
