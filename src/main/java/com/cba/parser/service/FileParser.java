package com.cba.parser.service;

import java.io.IOException;
import java.util.Map;

public interface FileParser {
    void fileParser(String inputFilePath,String outputPath);
    void saveTransactionInExcel(Map<String,String> map, String outputPath) throws IOException;
}
