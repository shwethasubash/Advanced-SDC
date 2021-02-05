package dal.asd.catme.studentlistimport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVReader implements ICSVReader
{
    private static final Logger log = LoggerFactory.getLogger(CSVReader.class);

    InputStream inputStream;

    public CSVReader(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public ArrayList<String> readFile() throws InvalidFileFormatException, IOException
    {
        log.info("Reading content of file");
        ArrayList<String> lines = new ArrayList<>();

        boolean skip = true;

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) == null == false)
        {
            if (line.isEmpty())
                continue;

            if (skip)
            {
                skip = false;
                continue;
            }

            lines.add(line);

        }

        return lines;
    }


    @Override
    public void validateFile(MultipartFile file) throws InvalidFileFormatException
    {
        log.info("Validating file: " + file.getName());
        if (file.isEmpty())
        {
            throw new InvalidFileFormatException("Please Upload File");
        } else if (file.getSize() >= 10 * 1024 * 1024)
        {
            throw new InvalidFileFormatException("Please Upload File less than 10 mb");
        } else if (file.getContentType().equals("text/csv") == false)
        {
            throw new InvalidFileFormatException("Please Select CSV File");
        }
    }
}
