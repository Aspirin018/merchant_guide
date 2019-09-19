package com.merchant.core.extractor;

import com.merchant.component.QuestionInfo;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author liyu
 * @description ${description}
 * @date 2019-09-19 18:44
 */
public class MetalUnitPriceExtractorTest {

    private MetalUnitPriceExtractor metalUnitPriceExtractor = new MetalUnitPriceExtractor();
    private QuestionInfo questionInfo;
    private Map<String, Float> metalUnitPriceMapper = new HashMap<>();

    @Test
    public void canExtract() {
        questionInfo = Mockito.mock(QuestionInfo.class);
        Mockito.when(questionInfo.getArabic()).thenReturn(null);
        assertFalse(metalUnitPriceExtractor.canExtract(questionInfo));
    }

    @Test
    public void extract() {
        metalUnitPriceMapper.put("Silver", 17f);
        metalUnitPriceExtractor.setMetalUnitPriceMapper(metalUnitPriceMapper);
        questionInfo = new QuestionInfo("glob glob Silver is 34 Credits");
        metalUnitPriceExtractor.extract(questionInfo);
        assertEquals("Silver", questionInfo.getMetal());
        assertEquals(17f, questionInfo.getMetalUnitPrice().floatValue(), 0);
    }

    @Test
    public void canLearn() {
        questionInfo = new QuestionInfo("glob glob Silver is 34 Credits");
        questionInfo.setArabic(2);
        assertTrue(metalUnitPriceExtractor.canLearn(questionInfo));
    }

    @Test
    public void learn() {
        questionInfo = new QuestionInfo("glob glob Silver is 34 Credits");
        questionInfo.setArabic(2);
        metalUnitPriceExtractor.learn(questionInfo);
        assertTrue(metalUnitPriceExtractor.getMetalUnitPriceMapper().containsKey("Silver"));
        assertEquals(17f, metalUnitPriceExtractor.getMetalUnitPriceMapper().get("Silver"), 0);
    }
}