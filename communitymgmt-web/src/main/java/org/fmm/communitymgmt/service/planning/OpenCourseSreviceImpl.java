package org.fmm.communitymgmt.service.planning;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgicalFeastDto;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleLoader;
import org.fmm.communitymgmt.calendar.rules.liturgy.LiturgyRuleRegistry;
import org.fmm.communitymgmt.calendar.rules.liturgy.PeriodDto;
import org.fmm.communitymgmt.common.model.calendar.AbstractPeriod;
import org.fmm.communitymgmt.common.model.calendar.LiturgicalFeast;
import org.fmm.communitymgmt.common.model.calendar.LiturgicalYear;
import org.fmm.communitymgmt.common.repository.calendar.AbstractPeriodRepository;
import org.fmm.communitymgmt.common.repository.calendar.LiturgicalFeastRepository;
import org.fmm.communitymgmt.common.repository.calendar.LiturgicalYearRepository;
import org.fmm.communitymgmt.common.util.DateUtil;
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
	private AbstractPeriodRepository periodRepository;

	@Autowired
	private ResourceLoader resourceLoader;
    	
	@Override
	public LiturgicalYear openCourse(int year) {
		Optional<LiturgicalYear> optYear = null;
		LiturgicalYear lYear = null;
		List<LiturgicalFeast> feasts = null;
		List<AbstractPeriod> periods = null;
		
		LiturgyRuleRegistry registry = null;
//		LiturgyRuleEvaluator ev = null;
		
		optYear = yearRepository.findLiturgicalYearByYear(year);
		if (optYear.isPresent()) {
			lYear = optYear.get();
			feasts = feastRepository.findLiturgicalFeastsByYear(year);
			periods = periodRepository.findPeriodByYear(year);
			
		} else {
			lYear = new LiturgicalYear();
			lYear.setId(year);
			lYear.setYear(year);
			lYear.setYearName(""+(year-1)+"-"+year);

			lYear.setFirstYearDate(DateUtil.computeFirstSundayOfAdvent(year));
			lYear.setLastYearDate(DateUtil.computeLastDayOfYear(year));
			
			lYear = yearRepository.save(lYear);

			try {
				registry = loadResource("liturgy/liturgy-rules-v6.json");
		        //ev = new LiturgyRuleEvaluator(registry);
				feasts = generateFeasts(registry,lYear);
				periods = generatePeriods(registry, lYear);
			} catch (IOException e) {
				logger.error("[FMMP] Error generating feasts, {}", e);
			}
		}

		if (feasts != null) {
			for (LiturgicalFeast lf:feasts) {
				logger.debug("{}",lf);
			}
			lYear.setFeasts(feasts);
			lYear.setPeriods(periods);
		}
		return lYear;
	}

	private LiturgyRuleRegistry loadResource(String resourceString) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:"+resourceString); //
		Path path = null;
        LiturgyRuleRegistry registry = new LiturgyRuleRegistry();
        LiturgyRuleLoader loader = new LiturgyRuleLoader();
		
		path = resource.getFile().toPath();
        
        registry = loader.load(path);
		return registry;
	}
	private List<LiturgicalFeast> generateFeasts(LiturgyRuleRegistry registry, LiturgicalYear lYear) {
		LiturgicalFeast feast = null;
		List<LiturgicalFeast> feasts = null;
		
        List<LiturgicalFeastDto> feastDtoList = registry.evaluateFeastRules(lYear.getYear(), "es", "ES");
		if (feastDtoList !=null) {
			feasts = new ArrayList<LiturgicalFeast>(feastDtoList.size());
			for (LiturgicalFeastDto dto : feastDtoList) {
				feast = LiturgicalFeastDto.fromDto(dto);
				feast.setYear(lYear);
				feast = feastRepository.save(feast);
				feasts.add(feast);
			}
		}
		return feasts;
	}

	private List<AbstractPeriod> generatePeriods(LiturgyRuleRegistry registry, LiturgicalYear lYear) {
		AbstractPeriod period = null;
		List<AbstractPeriod> periodList = null;
		
		List<PeriodDto> periodDtoList = registry.evaluatePeriodRules(lYear.getYear(), "es", "ES");
		if (periodDtoList != null) {
			periodList = new ArrayList<AbstractPeriod>(periodDtoList.size());
			for (PeriodDto dto: periodDtoList) {
				period = PeriodDto.fromDto(dto);
				period.setYear(lYear);
				period = periodRepository.save(period);
				periodList.add(period);
			}
		}
		return periodList;
	}
}
