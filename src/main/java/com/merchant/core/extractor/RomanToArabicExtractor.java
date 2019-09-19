package com.merchant.core.extractor;

import com.merchant.component.QuestionInfo;
import com.merchant.component.RomanNumeral;
import com.merchant.framework.UnIntelligibleException;

import java.util.List;

/**
 * @author liyu
 * @date 2019-09-18 13:01
 */
public class RomanToArabicExtractor implements Extractor {

    private String canExtractRegex = "([\\s\\S]*)([a-z]+\\s)+([\\s\\S]*)";
    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public boolean canExtract(QuestionInfo questionInfo) {
        return !questionInfo.getRomanList().isEmpty() && questionInfo.getSource().matches(canExtractRegex);
    }

    @Override
    public void extract(QuestionInfo questionInfo) {
        List<RomanNumeral> romans = questionInfo.getRomanList();
        int i = 0;
        int arabicValue = 0;
        RomanNumeral repeatRoman = RomanNumeral.NONE;
        int repeatTime = 0;
        while(i < romans.size()){
            RomanNumeral current = romans.get(i);
            if(current.compare(repeatRoman) != 0){
                repeatRoman = current;
                repeatTime = 1 ;
            }else{
                repeatTime++;
            }
            if(repeatTime > repeatRoman.getMaxRepeatTime()){
                throw new UnIntelligibleException("symbol repeat time beyond");
            }
            if(i == romans.size() - 1){
                arabicValue += current.getValue();
                break;
            }
            RomanNumeral next = romans.get(i+1);
            int temp = current.getValue();
            if(current.compare(next) < 0 && current.getCanBeSubtractedBy().contains(next)){
                temp = next.getValue() - current.getValue();
                i++;
                repeatRoman = next;
                repeatTime = 1;
            }
            arabicValue += temp;
            i++;
        }
        questionInfo.setArabic(arabicValue);
    }
}
