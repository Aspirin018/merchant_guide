package com.merchant.core.handler;

import com.merchant.component.QuestionInfo;

/**
 * @author liyu
 * @date 2019-09-18 13:03
 */
public class HowMuchHandler implements Handler {

    @Override
    public boolean supportQuestion(String source) {
        return source.startsWith("how much is ");
    }

    @Override
    public String handleQuestion(QuestionInfo questionInfo) {
        String words = String.join(" ", questionInfo.getWordList());
        return words + " is " + questionInfo.getArabic();
    }
}
