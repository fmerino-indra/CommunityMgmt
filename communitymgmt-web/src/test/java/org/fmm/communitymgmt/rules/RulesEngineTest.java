package org.fmm.communitymgmt.rules;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.fmm.communitymgmt.calendar.rules.planning.PlanningRule;
import org.fmm.communitymgmt.calendar.rules.planning.PlanningRulesEngine;
import org.fmm.communitymgmt.calendar.rules.planning.effect.CancelEffect;
import org.fmm.communitymgmt.calendar.rules.planning.effect.RuleEffect;
import org.fmm.communitymgmt.common.util.enums.TripodEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//@SpringBootTest(classes = {CommunityMgmtRulesConfig.class, RuleLoader.class, RulesEngine.class})
@SpringBootTest
@ActiveProfiles("test")

class RulesEngineTest {

    @Autowired
    private PlanningRulesEngine engine;

    @Test
    void testWordCancelledOnChristmasWeeks() {
        LocalDate date = LocalDate.of(2025, 12, 26);

        RuleEffect effectOld = engine.evaluateOld(TripodEnum.WORD, date);
        
        PlanningRule rule = engine.evaluate(TripodEnum.WORD, date);
        RuleEffect effect = rule.getEffect();
        
        assertNotNull(effect, "Debe haber un efecto");
        assertTrue(effect instanceof CancelEffect, "La Palabra debe cancelarse en la semana de Navidad");
    }

    @Test
    void testWordCancelledOnChristmas() {
        LocalDate date = LocalDate.of(2025, 12, 25);

        RuleEffect effectOld = engine.evaluateOld(TripodEnum.WORD, date);
        
        PlanningRule rule = engine.evaluate(TripodEnum.WORD, date);
        RuleEffect effect = rule.getEffect();

        assertNotNull(effect, "Debe haber un efecto");
        assertTrue(effect instanceof CancelEffect, "La Palabra debe cancelarse en Navidad");
    }

    @Test
    void testEucharistCancelledWhenChristmasIsSaturday() {
        // 2021-12-25 fue sábado
        LocalDate date = LocalDate.of(2021, 12, 25);

        RuleEffect effectOld = engine.evaluateOld(TripodEnum.LITURGY, date);
        
        PlanningRule rule = engine.evaluate(TripodEnum.WORD, date);
        RuleEffect effect = rule.getEffect();

        assertNotNull(effect, "Debe haber un efecto");
        assertTrue(effect instanceof CancelEffect, "La Eucaristía debe cancelarse si Navidad cae en sábado");
    }

    @Test
    void testEucharistNotCancelledWhenChristmasIsNotSaturday() {
        // 2025-12-25 cae jueves
        LocalDate date = LocalDate.of(2025, 12, 25);

        RuleEffect effectOld = engine.evaluateOld(TripodEnum.LITURGY, date);
        
        PlanningRule rule = engine.evaluate(TripodEnum.WORD, date);
        RuleEffect effect = rule.getEffect();

        assertNull(effect, "La Eucaristía NO debe cancelarse si Navidad no es sábado");
    }

    @Test
    void testConvivenceCancelledDuringSummer() {
        LocalDate date = LocalDate.of(2025, 7, 10);

        RuleEffect effectOld = engine.evaluateOld(TripodEnum.COMMUNITY, date);
        
        PlanningRule rule = engine.evaluate(TripodEnum.WORD, date);
        RuleEffect effect = rule.getEffect();

        assertNotNull(effect);
        assertTrue(effect instanceof CancelEffect);
    }

    @Test
    void testConvivenceOutsideSummerNotCancelled() {
        LocalDate date = LocalDate.of(2025, 9, 5);

        RuleEffect effectOld = engine.evaluateOld(TripodEnum.COMMUNITY, date);
        
        PlanningRule rule = engine.evaluate(TripodEnum.WORD, date);
        RuleEffect effect = rule.getEffect();

        assertNull(effect, "Fuera de verano no debe cancelarse la Convivencia");
    }
}
