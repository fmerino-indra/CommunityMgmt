package org.fmm.communitymgmt.calendar.rules.liturgy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;

import org.fmm.communitymgmt.calendar.rules.liturgy.result.LiturgyPeriodResult;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;

//@SpringBootTest(classes = {CommunityMgmtRulesConfig.class, RuleLoader.class, RulesEngine.class})
@SpringBootTest
@ActiveProfiles("test")

class LiturgicalRulesEvaluatorTest {

	@Autowired
	private ResourceLoader resourceLoader;

	private final Logger logger = LoggerFactory.getLogger(LiturgicalRulesEvaluatorTest.class);
    
    @Test
    void testWordCancelledOnChristmasWeeks() throws Exception {
        LiturgyRuleRegistry liturgicalRegistry = null;
        // Load rules
//        File f = new File("liturgy-rules-v3.json");
//        assertTrue(f.exists(), "liturgy-rules-v3.json must exist in working directory for this test");

        //    	Map<RuleKindEnum, List<AbstractLiturgyRule>> allRules = null;

        LiturgyRuleLoader loader = new LiturgyRuleLoader();
        
		Resource resource = resourceLoader.getResource("classpath:liturgy/liturgy-rules-v5.json");
		assertTrue(resource.exists(), "liturgy-rules-v5.json must exist in working directory for this test");
		Path path = resource.getFile().toPath();
        
		liturgicalRegistry = loader.load(path);
        
//        List<AbstractLiturgyRule> liturgicalRules = allRules.get(RuleKindEnum.LITURGY);
//        List<AbstractLiturgyRule> periodRules = allRules.get(RuleKindEnum.LITURGICAL_PERIOD);
/*
        for (AbstractLiturgyRule r: liturgicalRules) 
        	liturgicalRegistry.register(r);
*/
        int litYear = 2026;
        LiturgyRuleEvaluator ev = new LiturgyRuleEvaluator(liturgicalRegistry);
        List<LiturgicalFeastDto> feasts = ev.evaluate(litYear, "es", "ES");
/*        
        LiturgyRuleRegistry liturgicalPeriodRegistry = new LiturgyRuleRegistry();
        for (AbstractLiturgyRule r: periodRules) 
        	liturgicalRegistry.register(r);
*/        
        ev.evaluatePeriods(litYear, "es", "ES");
        
        // find by id helper
        java.util.function.Function<String, LiturgicalFeastDto> byId = (id) -> feasts.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);

        LiturgicalFeastDto easter = byId.apply("easter");
        LiturgicalFeastDto ascension = byId.apply("ascension");
        LiturgicalFeastDto pentecost = byId.apply("pentecost");
        LiturgicalFeastDto holyThursday = byId.apply("holy_thursday");
        LiturgicalFeastDto firstAdvent = byId.apply("first_sunday_advent");
        LiturgicalFeastDto christTheKing = byId.apply("christ_the_king");

        LiturgyPeriodResult periodResult = liturgicalRegistry.getComputedPeriodRule("advent");
        logger.debug("{}", periodResult.getResult().toString());
        
        assertNotNull(easter, "Easter must be computed");
        assertNotNull(ascension, "Ascension must be computed");
        assertNotNull(pentecost, "Pentecost must be computed");
        assertNotNull(holyThursday, "Holy Thursday must be computed");
        assertNotNull(firstAdvent, "First Sunday of Advent must be computed");
        assertNotNull(christTheKing, "Christ the King must be computed");

        // relations
        assertEquals(easter.getDate().plusDays(39), ascension.getDate(), "Ascension = Easter + 39 days");
        assertEquals(easter.getDate().plusDays(49), pentecost.getDate(), "Pentecost = Easter + 49 days");
        assertEquals(easter.getDate().plusDays(-3), holyThursday.getDate(), "Holy Thursday = Easter - 3 days");

        // No pertenecen al mismo año civil
        // Advent/Christ the King relation: Christ the King is previous Sunday before first_sunday_advent
//        LocalDate prevSunday = firstAdvent.getDate().minusDays(1);
//        while(prevSunday.getDayOfWeek()!=java.time.DayOfWeek.SUNDAY) prevSunday = prevSunday.minusDays(1);
//        assertEquals(prevSunday, christTheKing.getDate(), "Christ the King is the Sunday before First Sunday of Advent");
    	
    }
}
