package com.merchant.component;

import com.merchant.core.extractor.Extractor;
import com.merchant.core.handler.Handler;
import com.merchant.framework.UnIntelligibleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author liyu
 * @date 2019-09-18 12:53
 */
public class LineProcessor {

    private static final Logger logger = LogManager.getLogger(LineProcessor.class);

    private List<Extractor> extractors = new ArrayList<>();

    private List<Handler> handlers = new ArrayList<>();

    public LineProcessor(Map<String, List> coreComponents) {
        this.extractors = coreComponents.get("extractors");
        if(!extractors.isEmpty()){
            extractors.sort(Comparator.comparing(Extractor::getOrder));
        }
        this.handlers = coreComponents.get("handlers");
    }

    public void process(String source){
        if(source.endsWith("?")){
            handleQuestion(source);
        }else {
            extractByStep(source);
        }
    }

    private void extractByStep(String source){
        QuestionInfo questionInfo = new QuestionInfo(source);
        for(Extractor extractor : extractors){
            if(extractor.canExtract(questionInfo)){
                extractor.extract(questionInfo);
            }
            if(extractor.canLearn(questionInfo)){
                extractor.learn(questionInfo);
            }
        }
    }

    private void handleQuestion(String source){
        Handler handler = handlers.stream().filter(item -> item.supportQuestion(source)).findFirst().orElse(null);
        if(null == handler){
            throw new UnIntelligibleException();
        }
        QuestionInfo questionInfo = new QuestionInfo(source);
        for(Extractor extractor : extractors){
            if(extractor.canExtract(questionInfo)){
                extractor.extract(questionInfo);
            }
        }
        String handleResult = handler.handleQuestion(questionInfo);
        logger.info(handleResult);
    }
}
