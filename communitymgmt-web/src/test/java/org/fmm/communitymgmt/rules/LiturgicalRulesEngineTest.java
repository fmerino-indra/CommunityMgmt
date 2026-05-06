package org.fmm.communitymgmt.rules;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;

//@SpringBootTest(classes = {CommunityMgmtRulesConfig.class, RuleLoader.class, RulesEngine.class})
@SpringBootTest
@ActiveProfiles("test")

class LiturgicalRulesEngineTest {

    @Autowired
    private ResourceLoader resourceLoader; 

    @Autowired
    private LiturgicalYearRepository yearRepository;
	@Autowired
	private LiturgicalFeastRepository feastRepository;
	@Autowired
	private AbstractPeriodRepository periodRepository;
    
    @Test
    void testLoadRules() throws IOException {
        int year = 2025;
		Optional<LiturgicalYear> optYear = null;
		LiturgicalYear lYear = null;
		List<LiturgicalFeast> feasts = null;
		List<AbstractPeriod> periods = null;

		optYear = yearRepository.findLiturgicalYearByYear(year);
		if (optYear.isPresent()) {
			lYear = optYear.get();
			feasts = feastRepository.findLiturgicalFeastsByYear(year);
			periods = periodRepository.findPeriodByYear(year);
		} else {

		}
    	
        LiturgyRuleRegistry registry = new LiturgyRuleRegistry();
        LiturgyRuleLoader loader = new LiturgyRuleLoader();
        
		Resource resource = resourceLoader.getResource("classpath:liturgy/liturgy-rules-v6.json");
		Path path = null;
		
		path = resource.getFile().toPath();
        registry = loader.load(path);

        List<LiturgicalFeastDto> feastsDto = registry.evaluateFeastRules(year, "es", "ES");
		assertNotNull(feastsDto, "Debe haber fiestas");
        assertFalse(feastsDto.isEmpty(), "Debe existir al menos 1 fiesta");

		List<PeriodDto>periodsDto = registry.evaluatePeriodRules(year, "es", "ES");
        assertNotNull(periodsDto, "Debe haber periodos");
        assertFalse(periodsDto.isEmpty(), "Debe existir al menos 1 período");
        
        Map<LocalDate,Set<String>> periodAndFeastIndex = registry.periodAndFeastIndex();
        assertNotNull(periodAndFeastIndex, "Debe haber index");
        assertFalse(periodAndFeastIndex.isEmpty(), "Debe existir al menos 1 elemento en el index");
        
        LocalDate date = null; 
        LocalDate endDate = null; 

        //date = LocalDate.of(year, 1, 1);
        //endDate=LocalDate.of(year, 12, 31);
        
        date = lYear.getFirstYearDate();
        endDate = lYear.getLastYearDate();
        
        Set<String> auxSet = null;
        while (date.isBefore(endDate) || date.isEqual(endDate)) {
        	auxSet = periodAndFeastIndex.get(date);
//        	System.out.printf("[%td/%tm/%ty] -> [%s]%n", date, date, date, auxSet.stream().collect(Collectors.joining(", ")));
        	System.out.printf("[%ta, %td/%tm/%ty] -> ", date, date, date, date );
        	if (auxSet != null)
        		System.out.printf("[%s]%n", auxSet.stream().collect(Collectors.joining(", ")));
        	else
        		System.out.printf("%n");
        	date = date.plusDays(1);
        }
    }
}
