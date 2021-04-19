package com.cba.parser;

import com.cba.parser.exception.MissingArgumentException;
import com.cba.parser.service.FileParser;
import com.cba.parser.service.impl.FileParserImpl;

public class FileParserApplication {
    public static void main(String[] args){
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        if(inputFilePath==null||outputFilePath==null){
            throw new MissingArgumentException("Either input file path or output file path is missing");
        }
        FileParser fileParser = new FileParserImpl();
        fileParser.fileParser(inputFilePath,outputFilePath);
    }
}
