package dal.asd.catme.accesscontrol;

import java.util.List;

public class User
{
    String bannerId;
    String lastName;
    String firstName;
    String email;
    String password;
    List<Role> role;

    public String getBannerId()
    {
        return bannerId;
    }

    public void setBannerId(String bannerId)
    {
        this.bannerId = bannerId;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<Role> getRole()
    {
        return role;
    }

    public void setRole(List<Role> role)
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "User [bannerId=" + bannerId + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
                + ", password=" + password + ", role=" + role + "]";
    }
}