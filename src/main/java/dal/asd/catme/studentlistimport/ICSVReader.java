package dal.asd.catme.studentlistimport;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface ICSVReader
{
    ArrayList<String> readFile() throws InvalidFileFormatException, IOException;

    void validateFile(MultipartFile file) throws InvalidFileFormatException;
}