package com.merchant.component;

import com.merchant.core.extractor.Extractor;
import com.merchant.core.handler.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @author liyu
 * @date 2019-09-18 12:53
 */
public class ProcessorInitializer {

    private static final Logger logger = LogManager.getLogger(ProcessorInitializer.class);
    private static final String CLASS_FILE_EXTEND_NAME = ".class";
    private String corePackage;
    private List<String> coreClassList = new ArrayList<>();
    private ClassLoader classLoader = ProcessorInitializer.class.getClassLoader();

    public ProcessorInitializer(String corePackage) {
        this.corePackage = corePackage;
    }

    public Map<String, List> init(){
        getCoreClassList(corePackage.replace(".", "/"));
        return getCoreComponent();
    }

    private void getCoreClassList(String path){
        URL url = classLoader.getResource(path);
        if(url != null && url.getFile() != null){
            File file = new File(url.getFile());
            if(file.isFile()){
                loadFile(file, path);
            }else if(file.isDirectory() && file.list() != null){
                loadDirectory(file, path);
            }
        }
    }

    private Map<String, List> getCoreComponent(){
        Map<String, List> coreComponent = new HashMap<>();
        List<Extractor> extractors = new ArrayList<>();
        List<Handler> handlers = new LinkedList<>();
        for(String classPath : coreClassList){
            try {
                Class<?> clazz = classLoader.loadClass(classPath);
                if(!clazz.isInterface() && Extractor.class.isAssignableFrom(clazz)){
                    extractors.add((Extractor) clazz.newInstance());
                }
                if(!clazz.isInterface() && Handler.class.isAssignableFrom(clazz)){
                    handlers.add((Handler) clazz.newInstance());
                }
            } catch (ClassNotFoundException e) {
                logger.error("can not load core class", e);
            } catch (IllegalAccessException e) {
                logger.error("illegal access", e);
            } catch (InstantiationException e) {
                logger.error("can't initialize class", e);
            }
        }
        coreComponent.put("extractors", extractors);
        coreComponent.put("handlers", handlers);
        return coreComponent;
    }

    private void loadFile(File file, String path){
        if(file.getName().endsWith(CLASS_FILE_EXTEND_NAME)){
            String fullPath = file.getPath();
            String latterPartPath = fullPath.substring(fullPath.indexOf(path.replace('/', File.separatorChar)));
            String classPath = latterPartPath.replace(CLASS_FILE_EXTEND_NAME, "").replace(File.separatorChar, '.');
            coreClassList.add(classPath);
        }
    }

    private void loadDirectory(File file, String path){
        File[] files = file.listFiles(item -> item.isDirectory() || item.getName().endsWith(CLASS_FILE_EXTEND_NAME));
        if(files != null){
            Arrays.stream(files).forEach(item -> getCoreClassList(path + "/" + item.getName()));
        }

    }
}
