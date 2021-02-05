package dal.asd.catme.studentlistimport;

import java.io.InputStream;

public class CSVParserAbstractFactoryImpl implements ICSVParserAbstractFactory
{
    private final ICSVParser icsvParser;

    public CSVParserAbstractFactoryImpl()
    {
        icsvParser = new CSVParserImpl();
    }

    @Override
    public ICSVReader makeCSVReader(InputStream inputStream)
    {
        return new CSVReader(inputStream);
    }

    @Override
    public ICSVParser makeCSVParser()
    {
        return icsvParser;
    }
}
