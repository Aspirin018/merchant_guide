package com.merchant;

import com.merchant.component.FileLoader;
import com.merchant.component.LineProcessor;
import com.merchant.component.ProcessorInitializer;
import com.merchant.framework.UnIntelligibleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author liyu
 * @date 2019-09-18 11:10
 */
public class RomanNumeralApp {
    private static final Logger logger = LogManager.getLogger(RomanNumeralApp.class);

    public static void main(String[] args) {
        BufferedReader reader = new FileLoader().loadFile(args).getBufferedReader();
        LineProcessor lineProcessor = new LineProcessor(new ProcessorInitializer("com.merchant.core").init());
        try {
            String line;
            while((line = reader.readLine()) != null){
                handleLine(lineProcessor, line);
            }
        } catch (IOException e) {
            logger.error("can not read file", e);
        }
    }

    private static void handleLine(LineProcessor processor, String line){
        try {
            processor.process(line.trim());
        } catch (UnIntelligibleException e){
            logger.info(e.getMessage());
        }
    }
}
