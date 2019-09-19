package com.merchant.core.extractor;

import com.merchant.component.QuestionInfo;
import com.merchant.component.RomanNumeral;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyu
 * @date 2019-09-18 12:56
 */
public class WordToRomanExtractor implements Extractor {

    private Map<String, RomanNumeral> wordMapper = new HashMap<>();

    private String canLearnRegex = "[a-z]+\\sis\\s[A-Z]";

    private String canExtractRegex = "([\\s\\S]*)([a-z]+\\s)([\\s\\S]*)";

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public boolean canExtract(QuestionInfo questionInfo) {
        String source = questionInfo.getSource();
        return source != null && source.matches(canExtractRegex) && !source.matches(canLearnRegex);
    }

    @Override
    public void extract(QuestionInfo questionInfo) {
        String[] ss = questionInfo.getSource().split(" ");
        for(String s : ss){
            if(wordMapper.containsKey(s)){
                questionInfo.getWordList().add(s);
                questionInfo.getRomanList().add(wordMapper.get(s));
            }
        }
    }

    @Override
    public boolean canLearn(QuestionInfo questionInfo) {
        return questionInfo.getSource().matches(canLearnRegex);
    }

    @Override
    public void learn(QuestionInfo questionInfo) {
        String[] ss = questionInfo.getSource().split(" ");
        wordMapper.put(ss[0], RomanNumeral.parse(ss[2]));
    }

    public void setWordMapper(Map<String, RomanNumeral> wordMapper) {
        this.wordMapper = wordMapper;
    }

    public Map<String, RomanNumeral> getWordMapper() {
        return wordMapper;
    }
}
