package dal.asd.catme.studentlistimport;

import java.io.InputStream;

public interface ICSVParserAbstractFactory
{
    ICSVReader makeCSVReader(InputStream inputStream);

    ICSVParser makeCSVParser();
}
