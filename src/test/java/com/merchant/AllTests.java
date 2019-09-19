package com.merchant;

import com.merchant.component.FileLoaderTest;
import com.merchant.component.LineProcessorTest;
import com.merchant.core.extractor.MetalUnitPriceExtractorTest;
import com.merchant.core.extractor.RomanToArabicExtractorTest;
import com.merchant.core.extractor.WordToRomanExtractorTest;
import com.merchant.core.handler.HowManyHandlerTest;
import com.merchant.core.handler.HowMuchHandlerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ FileLoaderTest.class, LineProcessorTest.class,
        MetalUnitPriceExtractorTest.class, RomanToArabicExtractorTest.class, WordToRomanExtractorTest.class,
        HowManyHandlerTest.class, HowMuchHandlerTest.class})
public class AllTests {
}
