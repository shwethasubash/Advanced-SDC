package dal.asd.catme.studentlistimport;

import dal.asd.catme.accesscontrol.User;

import java.util.ArrayList;

public interface ICSVParser
{
    ArrayList<User> getStudentsFromFile(ICSVReader reader);

    boolean validBannerId(String bannerId);

    boolean validNames(String firstname, String lastname);

    boolean validEmailId(String emailId);
}
