package org.fmm.communitymgmt.calendar.rules.liturgy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;

//@SpringBootTest(classes = {CommunityMgmtRulesConfig.class, RuleLoader.class, RulesEngine.class})
@SpringBootTest
@ActiveProfiles("test")

class RulesEvaluatorTest {

	@Autowired
	private ResourceLoader resourceLoader;
    
    @Test
    void testWordCancelledOnChristmasWeeks() throws Exception {
        // Load rules
//        File f = new File("liturgy-rules-v3.json");
//        assertTrue(f.exists(), "liturgy-rules-v3.json must exist in working directory for this test");

        LiturgyRuleLoader loader = new LiturgyRuleLoader();
        
		Resource resource = resourceLoader.getResource("classpath:liturgy/liturgy-rules-v4.json");
		assertTrue(resource.exists(), "liturgy-rules-v4.json must exist in working directory for this test");
		Path path = resource.getFile().toPath();
        
        
        List<LiturgyRule> rules = loader.load(path);

        LiturgyRuleRegistry registry = new LiturgyRuleRegistry();
        for (LiturgyRule r: rules) 
        	registry.register(r);

        LiturgyRuleEvaluator ev = new LiturgyRuleEvaluator(registry);
        int litYear = 2026;
        List<LiturgicalFeast> feasts = ev.evaluate(litYear);

        // find by id helper
        java.util.function.Function<String, LiturgicalFeast> byId = (id) -> feasts.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);

        LiturgicalFeast easter = byId.apply("easter");
        LiturgicalFeast ascension = byId.apply("ascension");
        LiturgicalFeast pentecost = byId.apply("pentecost");
        LiturgicalFeast holyThursday = byId.apply("holy_thursday");
        LiturgicalFeast firstAdvent = byId.apply("first_sunday_advent");
        LiturgicalFeast christTheKing = byId.apply("christ_the_king");

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
