package com.merchant.core.extractor;

import com.merchant.component.QuestionInfo;
import com.merchant.component.RomanNumeral;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyu
 * @description ${description}
 * @date 2019-09-19 18:44
 */
public class RomanToArabicExtractorTest {

    private RomanToArabicExtractor romanToArabicExtractor = new RomanToArabicExtractor();
    private QuestionInfo questionInfo;

    @Test
    public void canExtract() {
        questionInfo = Mockito.mock(QuestionInfo.class);
        List<RomanNumeral> romanList = new LinkedList<>();
        romanList.add(RomanNumeral.I);
        Mockito.when(questionInfo.getRomanList()).thenReturn(romanList);
        Mockito.when(questionInfo.getSource()).thenReturn("glob glob Silver is 34 Credits");
        assertTrue(romanToArabicExtractor.canExtract(questionInfo));
    }

    @Test
    public void extract() {
        questionInfo = new QuestionInfo("glob glob Silver is 34 Credits");
        List<RomanNumeral> romanList = new LinkedList<>();
        romanList.add(RomanNumeral.I);
        romanList.add(RomanNumeral.I);
        questionInfo.setRomanList(romanList);
        romanToArabicExtractor.extract(questionInfo);
        assertEquals(2, questionInfo.getArabic().intValue());
    }
}