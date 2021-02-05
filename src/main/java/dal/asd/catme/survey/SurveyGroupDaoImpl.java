package dal.asd.catme.survey;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.util.DBQueriesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyGroupDaoImpl implements ISurveyGroupDao
{
    private static final Logger log = LoggerFactory.getLogger(SurveyGroupDaoImpl.class);
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();

    @Override
    public int getGroupSize(int surveyId)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();

        log.info("Getting Group size for survey: " + surveyId);
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(DBQueriesUtil.GET_SURVEY_GROUP_SIZE);
            stmt.setInt(1, surveyId);

            ResultSet rs = db.executeForResultSet(stmt);

            if (rs.next())
            {
                log.info("Group size: " + rs.getInt(1));
                return rs.getInt(1);
            }
            log.info("Group size: 0");
            return 0;
        } catch (SQLException throwables)
        {
            log.info("Error Getting group size");
            throwables.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return 0;
    }

    @Override
    public List<User> getSurveyParticipants(int surveyId)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        log.info("Getting survey participants for survey: " + surveyId);
        List<User> users = new ArrayList<>();
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(DBQueriesUtil.GET_BANNER_ID);
            stmt.setInt(1, surveyId);

            ResultSet rs = db.executeForResultSet(stmt);

            while (rs.next())
            {
                User u = accessControlModelAbstractFactory.makeUser();
                u.setBannerId(rs.getString(1));
                users.add(u);
            }
            return users;
        } catch (SQLException throwables)
        {
            log.info("Error Getting participants");
            throwables.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return null;
    }
}
