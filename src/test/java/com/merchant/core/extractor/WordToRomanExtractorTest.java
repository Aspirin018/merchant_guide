package com.merchant.core.extractor;

import com.merchant.component.QuestionInfo;
import com.merchant.component.RomanNumeral;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author liyu
 * @description ${description}
 * @date 2019-09-19 18:45
 */
public class WordToRomanExtractorTest {

    private WordToRomanExtractor wordToRomanExtractor = new WordToRomanExtractor();
    private Map<String, RomanNumeral> wordMapper = new HashMap<>();
    private QuestionInfo questionInfo;

    @Test
    public void canExtract() {
        questionInfo = Mockito.mock(QuestionInfo.class);
        Mockito.when(questionInfo.getSource()).thenReturn("glob is I");
        assertFalse(wordToRomanExtractor.canExtract(questionInfo));
        Mockito.when(questionInfo.getSource()).thenReturn("glob glob Silver is 34 Credits");
        assertTrue(wordToRomanExtractor.canExtract(questionInfo));
    }

    @Test
    public void extract() {
        questionInfo = new QuestionInfo("glob glob Silver is 34 Credits");
        wordMapper.put("glob", RomanNumeral.I);
        wordToRomanExtractor.setWordMapper(wordMapper);
        wordToRomanExtractor.extract(questionInfo);
        assertTrue(questionInfo.getWordList().contains("glob"));
        assertTrue(questionInfo.getRomanList().contains(RomanNumeral.I));
    }

    @Test
    public void canLearn() {
        questionInfo = Mockito.mock(QuestionInfo.class);
        Mockito.when(questionInfo.getSource()).thenReturn("glob is I");
        assertTrue(wordToRomanExtractor.canLearn(questionInfo));
        Mockito.when(questionInfo.getSource()).thenReturn("glob glob Silver is 34 Credits");
        assertFalse(wordToRomanExtractor.canLearn(questionInfo));
    }

    @Test
    public void learn() {
        questionInfo = new QuestionInfo("glob is I");
        wordToRomanExtractor.learn(questionInfo);
        assertTrue(wordToRomanExtractor.getWordMapper().containsKey("glob"));
        assertEquals(RomanNumeral.I, wordToRomanExtractor.getWordMapper().get("glob"));
    }
}