package com.merchant.core.extractor;

import com.merchant.component.QuestionInfo;

/**
 * @author liyu
 * @date 2019-09-18 12:48
 */
public interface Extractor {
    int getOrder();
    boolean canExtract(QuestionInfo questionInfo);
    void extract(QuestionInfo questionInfo);
    default boolean canLearn(QuestionInfo questionInfo) {return false;}
    default void learn(QuestionInfo questionInfo){}
}
