package dal.asd.catme.studentlistimporttest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVParser;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class CSVParserTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICSVParserAbstractFactory icsvParserAbstractFactory = baseAbstractFactory.makeCSVParserAbstractFactory();

    @Test
    void getStudentsFromFileTest()
    {
        ICSVParser icsvParser = icsvParserAbstractFactory.makeCSVParser();

        File file1 = new File("src/test/java/dal/asd/catme/studentlistimporttest/test.csv");
        File file2 = new File("src/test/java/dal/asd/catme/studentlistimporttest/test1.csv");
        try
        {
            ICSVReader icsvReader = icsvParserAbstractFactory.makeCSVReader(new FileInputStream(file1));
            ICSVReader icsvReader1 = icsvParserAbstractFactory.makeCSVReader(new FileInputStream(file2));
            assertNotNull(icsvParser.getStudentsFromFile(icsvReader));
            assertNull(icsvParser.getStudentsFromFile(icsvReader1));
        } catch (FileNotFoundException e)
        {
            fail();
            e.printStackTrace();
        }

        assertNull(icsvParser.getStudentsFromFile(null));

    }

    @Test
    void validBannerIdTest()
    {
        ICSVParser icsvParser = icsvParserAbstractFactory.makeCSVParser();

        assertTrue(icsvParser.validBannerId("B00851820"));
        assertTrue(icsvParser.validBannerId("B00000000"));
        assertTrue(icsvParser.validBannerId("b00123455"));

        assertFalse(icsvParser.validBannerId("a00000000"));
        assertFalse(icsvParser.validBannerId(""));
        assertFalse(icsvParser.validBannerId("B10000000"));
        assertFalse(icsvParser.validBannerId("B1000000"));
        assertFalse(icsvParser.validBannerId("B00000a0"));
    }

    @Test
    void validNamesTest()
    {
        System.out.println("Validating Names");

        ICSVParser icsvParser = icsvParserAbstractFactory.makeCSVParser();

        assertTrue(icsvParser.validNames("A", "A"));
        assertTrue(icsvParser.validNames("1", "A"));
        assertTrue(icsvParser.validNames("A", "1"));

        assertFalse(icsvParser.validNames("", ""));
        assertFalse(icsvParser.validNames("name", ""));
        assertFalse(icsvParser.validNames("", "name"));
        assertFalse(icsvParser.validNames(null, null));

    }

    @Test
    void validEmailIdTest()
    {
        ICSVParser icsvParser = icsvParserAbstractFactory.makeCSVParser();

        assertTrue(icsvParser.validEmailId("tp890953@dal.ca"));
        assertTrue(icsvParser.validEmailId("Tapan.Prajapati@dal.ca"));
        assertTrue(icsvParser.validEmailId("tapan.prajapati24598@gmail.com"));
        assertTrue(icsvParser.validEmailId("tapan_prajapati24598@gmail.com"));

        assertFalse(icsvParser.validEmailId("_asdf.@gmail.com"));
        assertFalse(icsvParser.validEmailId("@@gmail"));
        assertFalse(icsvParser.validEmailId("abc@234"));
    }
}
