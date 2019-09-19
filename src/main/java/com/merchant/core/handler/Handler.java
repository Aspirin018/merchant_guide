package com.merchant.core.handler;

import com.merchant.component.QuestionInfo;

/**
 * @author liyu
 * @date 2019-09-18 12:48
 */
public interface Handler {

    boolean supportQuestion(String source);

    String handleQuestion(QuestionInfo questionInfo);
}
