import com.cba.parser.FileParserApplication;
import com.cba.parser.exception.MissingArgumentException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileParserTest {
    @Test
    public void testFileParserTransactionCnt(){
        int transactionCnt = 0;
        FileParserApplication.main(new String[]{System.getProperty("user.dir")+"/src/test/resources/"+"Sample.txt","target"});
        File folder = new File("target");
        File[] listOfFiles = folder.listFiles();
        for(File file:listOfFiles){
            String fileName = file.getName();
            if(fileName.startsWith("Transaction")){
                transactionCnt++;
            }
        }
        Assert.assertEquals(transactionCnt,2);
    }
    }
