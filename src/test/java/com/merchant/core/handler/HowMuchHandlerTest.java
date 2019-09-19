package com.merchant.core.handler;

import com.merchant.component.QuestionInfo;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyu
 * @description ${description}
 * @date 2019-09-19 18:45
 */
public class HowMuchHandlerTest {

    HowMuchHandler handler = new HowMuchHandler();
    private QuestionInfo questionInfo;
    private String source = "how much is pish tegj glob glob ?";

    @Test
    public void supportQuestion() {
        assertTrue(handler.supportQuestion(source));
        assertFalse(handler.supportQuestion("is pish tegj glob glob ?"));
    }

    @Test
    public void handleQuestion() {
        questionInfo = new QuestionInfo("");
        List<String> wordList = new LinkedList<>();
        wordList.add("pish");
        wordList.add("tegj");
        wordList.add("glob");
        wordList.add("glob");
        questionInfo.setWordList(wordList);
        questionInfo.setArabic(10);
        assertEquals("pish tegj glob glob is 10", handler.handleQuestion(questionInfo));

    }
}