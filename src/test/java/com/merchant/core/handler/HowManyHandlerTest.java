package com.merchant.core.handler;

import com.merchant.component.QuestionInfo;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyu
 * @date 2019-09-19 18:45
 */
public class HowManyHandlerTest {

    private HowManyHandler handler = new HowManyHandler();
    String source = "how many Credits is glob prok Silver ?";
    private QuestionInfo questionInfo;

    @Test
    public void supportQuestion() {
        assertTrue(handler.supportQuestion(source));
        assertFalse(handler.supportQuestion("many Credits is glob prok Silver ?"));
    }

    @Test
    public void handleQuestion() {
        questionInfo = new QuestionInfo("");
        List<String> wordList = new LinkedList<>();
        wordList.add("glob");
        wordList.add("prok");
        questionInfo.setWordList(wordList);
        questionInfo.setArabic(2);
        questionInfo.setMetalUnitPrice(17f);
        questionInfo.setMetal("Silver");
        assertEquals("glob prok Silver is 34 Credits", handler.handleQuestion(questionInfo));

    }
}