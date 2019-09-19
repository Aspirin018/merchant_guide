package com.merchant.component;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author liyu
 * @date 2019-09-19 18:40
 */
public class FileLoaderTest {

    private FileLoader fileLoader = new FileLoader();

    @Test
    public void loadFile() {
        File file = fileLoader.loadFile().getFile();
        assertTrue(file.exists());
        assertEquals("test.txt", file.getName());
    }

    @Test
    public void getBufferedReader() {
    }
}