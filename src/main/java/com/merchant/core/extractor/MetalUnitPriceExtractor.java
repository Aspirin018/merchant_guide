package com.merchant.core.extractor;

import com.merchant.component.QuestionInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyu
 * @date 2019-09-18 13:02
 */
public class MetalUnitPriceExtractor implements Extractor {

    private String canExtractRegex = "([\\s\\S]*)[A-Z][a-z]+([\\s\\S]*)";
    private String canLearnRegex = "([a-z]+\\s)+[A-Z][a-z]+\\sis\\s\\d+\\sCredits";

    private Map<String, Float> metalUnitPriceMapper = new HashMap<>();

    @Override
    public int getOrder() {
        return 20;
    }

    @Override
    public boolean canExtract(QuestionInfo questionInfo) {
        return questionInfo.getArabic() != null && questionInfo.getSource().replace("Credits", "").matches(canExtractRegex);
    }

    @Override
    public void extract(QuestionInfo questionInfo) {
        String source = questionInfo.getSource();
        String[] ss = source.split(" ");
        for(String s : ss){
            if(metalUnitPriceMapper.containsKey(s)){
                questionInfo.setMetal(s);
                questionInfo.setMetalUnitPrice(metalUnitPriceMapper.get(s));
            }
        }
    }

    @Override
    public boolean canLearn(QuestionInfo questionInfo) {
        return questionInfo.getArabic() != null && questionInfo.getSource().matches(canLearnRegex);
    }

    @Override
    public void learn(QuestionInfo questionInfo) {
        String source = questionInfo.getSource();
        String[] ss = source.split(" ");
        String metal = null;
        float totalPrice = 0f;
        for(String s : ss){
            if(s.matches("[A-Z][a-z]+") && !"Credits".equals(s)){
                metal = s;
            }
            if(s.matches("\\d+")){
                totalPrice = Float.parseFloat(s);
            }
        }
        metalUnitPriceMapper.put(metal, totalPrice / questionInfo.getArabic());
    }

    public Map<String, Float> getMetalUnitPriceMapper() {
        return metalUnitPriceMapper;
    }

    public void setMetalUnitPriceMapper(Map<String, Float> metalUnitPriceMapper) {
        this.metalUnitPriceMapper = metalUnitPriceMapper;
    }
}
