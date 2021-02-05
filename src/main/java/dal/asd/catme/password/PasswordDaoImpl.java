package dal.asd.catme.password;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.CatmeException;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class PasswordDaoImpl implements IPasswordDao
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
    PasswordEncoder p;
    private static final Logger log = LoggerFactory.getLogger(PasswordDaoImpl.class);

    @Override
    public void resetPassword(User u) throws CatmeException
    {
        log.info("Restting password");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        p = SystemConfig.instance().getPasswordEncoder();
        try
        {
            String encodedPassword = p.encode(u.getPassword());

            if (PasswordRulesUtil.PASSWORD_HISTORY_ENABLE && matchWithPasswordHistory(u.getBannerId(), u.getPassword()))
            {
                throw new CatmeException("Password should not match with last 10 passwords");
            }

            PreparedStatement resetPasswordStmt = db.getPreparedStatement(RESET_PASSWORD_QUERY);
            resetPasswordStmt.setString(1, encodedPassword);
            resetPasswordStmt.setString(2, u.getBannerId());

            int status = resetPasswordStmt.executeUpdate();

            if (status == 0)
                throw new CatmeException("Error Updating Password");

            PreparedStatement passwordHistoryStmt = db.getPreparedStatement(PasswordRulesUtil.PASSWORD_ADD_HISTORY);
            passwordHistoryStmt.setString(1, encodedPassword);
            passwordHistoryStmt.setString(2, u.getBannerId());

            status = passwordHistoryStmt.executeUpdate();
            if (status == 0)
                throw new CatmeException("Error Creating Password History");

            deleteOverLimitPasswords(u.getBannerId());

        } catch (SQLException e)
        {
            log.error("Resetting password failed while updating in DB");
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
    }

    public void generatePasswordResetToken(User u, String token) throws CatmeException
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        log.info("Generating the reset password token");
        try
        {

            PreparedStatement stmt = db.getPreparedStatement(GENERATE_PASSWORD_RESET_TOKEN);
            stmt.setString(1, u.getBannerId());
            stmt.setString(2, token);

            stmt.executeUpdate();
        } catch (SQLException throwables)
        {
            log.error("Failed while generating token");
            throwables.printStackTrace();
            throw new CatmeException("Error Generating Token.. Try Again");
        } finally
        {
            db.cleanUp();
        }
    }

    public String readBannerIdFromToken(String token) throws CatmeException
    {
        log.info("Checking the user for reset token");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        try
        {

            PreparedStatement stmt = db.getPreparedStatement(READ_BANNERID_FROM_TOKEN);
            stmt.setString(1, token);

            ResultSet rs = db.executeForResultSet(stmt);

            if (rs.next() == false)
            {
                throw new CatmeException("Invalid Link");
            }

            return rs.getString(1);

        } catch (SQLException throwables)
        {
            log.error("Error Generating Token.. Try Again");
            throw new CatmeException("Error Generating Token.. Try Again");
        } finally
        {
            db.cleanUp();
        }
    }

    public void removeToken(String bannerId)
    {
        log.info("Deleting reset token");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        try
        {

            PreparedStatement stmt = db.getPreparedStatement(REMOVE_TOKEN);
            stmt.setString(1, bannerId);

            stmt.executeUpdate();

        } catch (SQLException throwables)
        {
            log.error("Error deleting reset token");
        } finally
        {
            db.cleanUp();
        }
    }

    @Override
    public boolean matchWithPasswordHistory(String bannerId, String password) throws CatmeException
    {
        log.info("Checking password history");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        PasswordEncoder p = SystemConfig.instance().getPasswordEncoder();

        try
        {
            PreparedStatement stmt = db.getPreparedStatement(PasswordRulesUtil.PASSWORD_HISTORY_ORDER_QUERY);
            stmt.setString(1, bannerId);
            ResultSet rs = db.executeForResultSet(stmt);

            while (rs.next())
            {
                String oldPass = rs.getString(1);
                if (p.matches(password, oldPass))
                    return true;
            }
            return false;
        } catch (SQLException throwables)
        {
            log.error("Unable to load password history");
            throwables.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
        return false;
    }

    @Override
    public void deleteOverLimitPasswords(String bannerId) throws CatmeException
    {
        log.info("Deleting old passwords");
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();

        try
        {
            PreparedStatement stmt = db.getPreparedStatement(PasswordRulesUtil.PASSWORD_ALL_HISTORY_ORDER_QUERY);
            stmt.setString(1, bannerId);

            ResultSet rs = db.executeForResultSet(stmt);

            int resultSize = 0;
            int passwordId = 0;
            while (rs.next())
            {
                if (passwordId == 0)
                    passwordId = rs.getInt(1);
                resultSize++;
            }

            if (resultSize > PasswordRulesUtil.PASSWORD_HISTORY_LIMIT)
            {
                PreparedStatement deletePasswordStmt = db.getPreparedStatement(PasswordRulesUtil.PASSWORD_DELETE);
                deletePasswordStmt.setInt(1, passwordId);

                deletePasswordStmt.execute();
            }
        } catch (SQLException throwables)
        {
            log.error("Error removing old passwords");
            throwables.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
    }
}
