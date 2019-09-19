package com.merchant.core.handler;

import com.merchant.component.QuestionInfo;

/**
 * @author liyu
 * @date 2019-09-18 13:04
 */
public class HowManyHandler implements Handler {

    @Override
    public boolean supportQuestion(String source) {
        return source.startsWith("how many ");
    }

    @Override
    public String handleQuestion(QuestionInfo questionInfo) {
        String words = String.join(" ", questionInfo.getWordList());
        float totalPrice = questionInfo.getArabic() * questionInfo.getMetalUnitPrice();
        return String.format("%s %s is %.0f Credits", words, questionInfo.getMetal(), totalPrice);
    }
}
