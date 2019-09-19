package com.merchant.component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liyu
 * @date 2019-09-18 12:49
 */
public enum RomanNumeral {

    M("M", 1000, 3, Collections.emptyList()),
    D("D", 500, 1, Collections.emptyList()),
    C("C", 100, 3, Arrays.asList(D, M)),
    L("L", 50 , 1, Collections.emptyList()),
    X("X", 10, 3, Arrays.asList(L,C)),
    V("V", 5, 1, Collections.emptyList()),
    I("I",1, 3, Arrays.asList(V, X)),
    NONE("", 0, 1, Collections.emptyList());

    private final String symbol;
    private final int value;
    private final int maxRepeatTime;
    private final List<RomanNumeral> canBeSubtractedBy;

    RomanNumeral(String symbol, int value, int maxRepeatTime, List<RomanNumeral> canBeSubtractedBy) {
        this.symbol = symbol;
        this.value = value;
        this.maxRepeatTime = maxRepeatTime;
        this.canBeSubtractedBy = canBeSubtractedBy;
    }

    public static RomanNumeral parse(String s){
        return Arrays.stream(values()).filter(item -> s.equals(item.symbol)).findAny().orElse(NONE);
    }

    public int compare(RomanNumeral r){
        return Integer.compare(value, r.value);
    }

    public int getValue() {
        return value;
    }

    public int getMaxRepeatTime() {
        return maxRepeatTime;
    }

    public List<RomanNumeral> getCanBeSubtractedBy() {
        return canBeSubtractedBy;
    }


}


