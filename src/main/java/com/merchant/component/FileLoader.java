package com.merchant.component;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

/**
 * @author liyu
 * @date 2019-09-18 15:07
 */
public class FileLoader {

    private static  final Logger logger = LogManager.getLogger(FileLoader.class);
    private File file;

    public FileLoader loadFile(String... args) {
        if(args.length == 0){
            URL url = this.getClass().getClassLoader().getResource("test.txt");
            file = new File(url.getFile());
        }else{
            file = new File(args[0]);
        }
      return this;
    }

    public BufferedReader getBufferedReader(){
        try {
            return new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            logger.error("can not load file", e);
        }
        return null;
    }

    public File getFile() {
        return file;
    }

}
