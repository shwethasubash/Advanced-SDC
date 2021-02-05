package dal.asd.catme.studentlistimporttest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CSVParserAbstractFactoryTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();

    @Test
    void makeCSVReaderTest()
    {
        ICSVParserAbstractFactory icsvParserAbstractFactory = baseAbstractFactory.makeCSVParserAbstractFactory();
        assertNotNull(icsvParserAbstractFactory.makeCSVReader(null));
    }

    @Test
    void makeCSVParserTest()
    {
        ICSVParserAbstractFactory icsvParserAbstractFactory = baseAbstractFactory.makeCSVParserAbstractFactory();
        assertNotNull(icsvParserAbstractFactory.makeCSVParser());
    }
}
