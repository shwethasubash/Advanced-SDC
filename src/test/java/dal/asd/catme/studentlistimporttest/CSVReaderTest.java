package dal.asd.catme.studentlistimporttest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class CSVReaderTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICSVParserAbstractFactory icsvParserAbstractFactory = baseAbstractFactory.makeCSVParserAbstractFactory();

    @Test
    public void readFileTest()
    {
        File file1 = new File("src/test/java/dal/asd/catme/studentlistimporttest/test.csv");
        try
        {
            ICSVReader csvReader = icsvParserAbstractFactory.makeCSVReader(new FileInputStream(file1));
            assertNotNull(csvReader.readFile());
        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
}
