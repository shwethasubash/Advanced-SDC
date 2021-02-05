package dal.asd.catme.questionmanager;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.DBQueriesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements IQuestionDao
{
    private static final Logger log = LoggerFactory.getLogger(QuestionDaoImpl.class);
    IDatabaseAccess db;
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    public QuestionDaoImpl()
    {
    }

    @Override
    public List<Question> getQuestionTitles(String instructor) throws QuestionDatabaseException
    {
        int QUESTIONID = 1;
        int QUESTIONTITLE = 2;
        int QUESTION = 3;
        int QUESTIONTYPE = 4;
        int CREATEDDATE = 5;

        log.info("Getting List of Questions for instructor: " + instructor);
        List<Question> questionList = new ArrayList<>();
        db = databaseAbstractFactory.makeDatabaseAccess();
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(DBQueriesUtil.GET_QUESTIONS);
            stmt.setString(1, instructor);

            ResultSet rs = db.executeForResultSet(stmt);

            while (rs.next())
            {
                Question q = modelAbstractFactory.makeQuestion();
                q.setQuestionId(rs.getInt(QUESTIONID));
                q.setQuestionTitle(rs.getString(QUESTIONTITLE));
                q.setQuestionText(rs.getString(QUESTION));
                q.setQuestionType(rs.getString(QUESTIONTYPE));
                q.setCreatedDate(rs.getDate(CREATEDDATE));

                questionList.add(q);
            }

            log.info("Total Questions found: " + questionList.size());
            return questionList;
        } catch (SQLException throwables)
        {
            log.error("Error Getting Questions");
            throw new QuestionDatabaseException("Error Getting Questions");
        } finally
        {
            db.cleanUp();
        }
    }

    @Override
    public int deleteQuestion(int questionId)
    {
        int questionDeleted = 0;
        db = databaseAbstractFactory.makeDatabaseAccess();
        try
        {
            if (0 == checkExistingQuestion(questionId) == false)
            {
                PreparedStatement stmt = db.getPreparedStatement(DBQueriesUtil.DELETE_QUESTION_QUERY);
                stmt.setInt(1, questionId);
                stmt.executeUpdate();
                questionDeleted = 1;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return questionDeleted;
    }

    @Override
    public int checkExistingQuestion(int questionId)
    {
        db = databaseAbstractFactory.makeDatabaseAccess();
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = db.getPreparedStatement(DBQueriesUtil.CHECK_EXISTING_QUESTION_QUERY);
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Override
    public int createQuestion(Question question, String user)
    {
        int result = 0;
        db = databaseAbstractFactory.makeDatabaseAccess();
        try
        {
            int questionTitleId = createQuestionTitle(question.getQuestionTitle(), user);

            PreparedStatement preparedStatement = db.getPreparedStatement(DBQueriesUtil.CHECK_QUESTION);
            preparedStatement.setInt(1, questionTitleId);
            preparedStatement.setString(2, question.getQuestionText());
            ResultSet resultSet = db.executeForResultSet(preparedStatement);
            if (!resultSet.next())
            {
                PreparedStatement preparedStatementInsert = db.getPreparedStatement(DBQueriesUtil.INSERT_QUESTION);
                preparedStatementInsert.setInt(1, questionTitleId);
                preparedStatementInsert.setString(2, question.getQuestionText());
                preparedStatementInsert.setString(3, getQuestionType(question.getQuestionType()));
                result = preparedStatementInsert.executeUpdate();
                if (result > 0)
                {
                    preparedStatementInsert = db.getPreparedStatement(DBQueriesUtil.CHECK_QUESTION);
                    preparedStatementInsert.setInt(1, questionTitleId);
                    preparedStatementInsert.setString(2, question.getQuestionText());
                    resultSet = preparedStatementInsert.executeQuery();
                    resultSet.next();
                }
            }
            result = Integer.parseInt(resultSet.getString("QuestionId"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }
        return result;
    }

    @Override
    public int createQuestionTitle(String questionTitle, String user)
    {
        int result = 0;

        db = databaseAbstractFactory.makeDatabaseAccess();
        try
        {
            int userRoleId = getUserRoleId(user);
            PreparedStatement preparedStatement = db.getPreparedStatement(DBQueriesUtil.CHECK_QUESTION_TITLE);
            preparedStatement.setString(1, questionTitle);
            ResultSet resultSet = db.executeForResultSet(preparedStatement);
            if (!resultSet.next())
            {
                preparedStatement = db.getPreparedStatement(DBQueriesUtil.INSERT_QUESTION_TITLE);
                preparedStatement.setString(1, questionTitle);
                preparedStatement.setInt(2, userRoleId);
                result = preparedStatement.executeUpdate();

                if (result > 0)
                {
                    preparedStatement = db.getPreparedStatement(DBQueriesUtil.CHECK_QUESTION_TITLE);
                    preparedStatement.setString(1, questionTitle);
                    resultSet = db.executeForResultSet(preparedStatement);
                    resultSet.next();
                }

            }
            result = Integer.parseInt(resultSet.getString("QuestionTitleId"));

        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            db.cleanUp();
        }

        return result;

    }

    private int getUserRoleId(String user)
    {
        IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
        int userRoleId = 0;
        try
        {
            PreparedStatement preparedStatement = db.getPreparedStatement(DBQueriesUtil.SELECT_ROLE_BY_ROLENAME);
            preparedStatement.setString(CatmeUtil.ONE, CatmeUtil.INSTRUCTOR_ROLE);
            ResultSet resultSet = db.executeForResultSet(preparedStatement);
            if (resultSet.next())
            {
                int roleId = Integer.parseInt(resultSet.getString("roleId"));
                preparedStatement = db.getPreparedStatement(DBQueriesUtil.SELECT_USER_ROLE_BY_BANNERID);
                preparedStatement.setString(1, user);
                preparedStatement.setInt(2, roleId);
                resultSet = db.executeForResultSet(preparedStatement);
                resultSet.next();
            }

            userRoleId = Integer.parseInt(resultSet.getString("UserRoleId"));
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return userRoleId;
    }

    private String getQuestionType(String questionType)
    {
        if (questionType.equalsIgnoreCase(CatmeUtil.FREE_TEXT))
            return CatmeUtil.FREETEXT_DB;
        else if (questionType.equalsIgnoreCase(CatmeUtil.NUMERIC))
            return CatmeUtil.NUMERIC_DB;
        else if (questionType.equalsIgnoreCase(CatmeUtil.MCQ_CHOOSE_MULTIPLE))
            return CatmeUtil.CHECKBOX;
        else
            return CatmeUtil.RADIOBUTTON;
    }

    @Override
    public int createOptions(int questionId, List<Option> options)
    {
        int result = 0;
        db = databaseAbstractFactory.makeDatabaseAccess();
        for (int i = 0; i < options.size(); i++)
        {
            Option option = options.get(i);
            if (option.getDisplayText().trim().length() > 0 && option.getDisplayText().isEmpty() == false)
            {
                try
                {
                    PreparedStatement preparedStatement = db.getPreparedStatement(DBQueriesUtil.CHECK_QUESTION_OPTION);
                    preparedStatement.setInt(1, questionId);
                    preparedStatement.setString(2, option.getDisplayText());
                    ResultSet resultSet = db.executeForResultSet(preparedStatement);
                    if (!resultSet.next())
                    {
                        preparedStatement = db.getPreparedStatement(DBQueriesUtil.INSERT_QUESTION_OPTION);
                        preparedStatement.setInt(1, questionId);
                        preparedStatement.setString(2, option.getDisplayText());
                        preparedStatement.setInt(3, option.getStoredAs());
                        result = preparedStatement.executeUpdate();
                    }
                } catch (SQLException e)
                {
                    e.printStackTrace();
                } finally
                {
                    db.cleanUp();
                }
            }
        }
        return result;
    }
}