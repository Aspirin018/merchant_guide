package com.merchant.component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author liyu
 * @date 2019-09-18 12:49
 */
public class QuestionInfo {

    private String source;

    private List<String> wordList = new LinkedList<>();

    private List<RomanNumeral> romanList = new LinkedList<>();

    private Integer arabic;

    private String metal;

    private Float metalUnitPrice;

    public QuestionInfo(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    public List<RomanNumeral> getRomanList() {
        return romanList;
    }

    public void setRomanList(List<RomanNumeral> romanList) {
        this.romanList = romanList;
    }

    public Integer getArabic() {
        return arabic;
    }

    public void setArabic(int arabic) {
        this.arabic = arabic;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public Float getMetalUnitPrice() {
        return metalUnitPrice;
    }

    public void setMetalUnitPrice(Float metalUniitPrice) {
        this.metalUnitPrice = metalUniitPrice;
    }
}
