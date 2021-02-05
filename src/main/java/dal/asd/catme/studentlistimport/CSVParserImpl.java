package dal.asd.catme.studentlistimport;

import dal.asd.catme.accesscontrol.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CSVParserImpl implements ICSVParser
{
    private static final Logger log = LoggerFactory.getLogger(CSVParserImpl.class);
    static int BANNERID = 0;
    static int LASTNAME = 1;
    static int FIRSTNAME = 2;
    static int EMAILID = 3;

    static int BANNERID_LENGTH = 9;
    static int CSV_COLUMNS = 4;

    @Override
    public ArrayList<User> getStudentsFromFile(ICSVReader reader)
    {
        if (reader == null)
            return null;

        try
        {
            ArrayList<String> lines = reader.readFile();
            ArrayList<User> students = new ArrayList<>();

            for (String line : lines)
            {
                String[] parts = line.split(",");
                if (validLine(parts))
                {
                    User student = new User();
                    student.setBannerId(parts[BANNERID]);
                    student.setFirstName(parts[FIRSTNAME]);
                    student.setLastName(parts[LASTNAME]);
                    student.setEmail(parts[EMAILID]);

                    students.add(student);
                } else
                {
                    log.info("Invalid data in line: " + line);
                    return null;
                }
            }
            return students;
        } catch (InvalidFileFormatException e)
        {
            e.printStackTrace();
            return null;
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private boolean validLine(String[] parts)
    {
        return validBannerId(parts[BANNERID]) &&
                validNames(parts[FIRSTNAME], parts[LASTNAME]) &&
                validEmailId(parts[EMAILID]);
    }

    @Override
    public boolean validBannerId(String bannerId)
    {
        bannerId = bannerId.trim();

        if (bannerId.length() < BANNERID_LENGTH || bannerId.length() > BANNERID_LENGTH)
        {
            log.info("Invalid length for Banner Id");
            return false;
        }
        if (bannerId.substring(0, 3).equalsIgnoreCase("B00") == false)
        {
            log.info("Banner Id not starting with 'B00'");
            return false;
        }

        return Pattern.matches("\\d{6}", bannerId.substring(3));
    }

    @Override
    public boolean validNames(String firstname, String lastname)
    {

        if (firstname == null || firstname.trim().isEmpty())
        {
            log.info("Invalid Firstname");
            return false;
        }

        if (lastname == null || lastname.trim().isEmpty())
        {
            log.info("Invalid Lastname");
            return false;
        }

        return true;
    }

    @Override
    public boolean validEmailId(String emailId)
    {
        emailId = emailId.trim();
        //credit for regex: https://regexr.com/2rhq7
        return Pattern.matches("[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", emailId);
    }
}
