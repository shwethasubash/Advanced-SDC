package dal.asd.catme.config;

import dal.asd.catme.database.DatabaseAccess;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SystemConfig
{
    private static SystemConfig uniqueInstance = null;
    private final CatmeSecurityConfig catmeServiceConfig;
    private final PasswordEncoder passwordEncoder;
    private final DatabaseAccess databaseAccess;

    public SystemConfig()
    {
        passwordEncoder = new BCryptPasswordEncoder();
        catmeServiceConfig = new CatmeSecurityConfig();
        databaseAccess = new DatabaseAccess();
    }

    public static SystemConfig instance()
    {
        if (null == uniqueInstance)
        {
            uniqueInstance = new SystemConfig();
        }
        return uniqueInstance;
    }

    public PasswordEncoder getPasswordEncoder()
    {
        return passwordEncoder;
    }

    public CatmeSecurityConfig getCatmeServiceConfig()
    {
        return catmeServiceConfig;
    }

    public DatabaseAccess getDatabaseAccess()
    {
        return databaseAccess;
    }
}
