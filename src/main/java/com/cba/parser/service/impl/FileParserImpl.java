package com.cba.parser.service.impl;

import com.cba.parser.service.FileParser;
import com.cba.parser.utility.Constants;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileParserImpl implements FileParser {
    public void fileParser(String filePath,String filePathOut){
        try{
            List<String> fileTextList = Files.readAllLines(Paths.get(filePath));
            int transactionCnt =1;
            Iterator<String> itr = fileTextList.iterator();
            Map<String,String> map = new HashMap<>();
            while(itr.hasNext()){
                String strLine = itr.next();
                if(!strLine.contains(Constants.NotARecord1)&&!strLine.contains(Constants.NotARecord2)){
                    String[] keyValArr = strLine.split(Constants.TranscSpliterator);
                    map.put(keyValArr[0].trim(),keyValArr[1].trim());
                }
                if(strLine.contains(Constants.EndOfTransaction)){
                    String filePathOutput = filePathOut+"/"+Constants.OutputFileName+transactionCnt;
                    saveTransactionInExcel(map,filePathOutput);
                    map.clear();
                    transactionCnt++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveTransactionInExcel(Map<String,String> mapInput, String filePathOut) throws IOException {
        FileOutputStream out = null;
        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet spreadSheet = workbook.createSheet(Constants.SheetName);
            XSSFRow row;
            int rowId =0;
            for(Map.Entry<String,String> entry:mapInput.entrySet()){
                row = spreadSheet.createRow(rowId++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }
            out = new FileOutputStream(new File(filePathOut+Constants.FileFormat));
            workbook.write(out);
        }finally {
            out.close();
        }
    }
}
