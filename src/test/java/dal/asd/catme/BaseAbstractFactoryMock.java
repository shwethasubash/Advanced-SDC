package dal.asd.catme;

import dal.asd.catme.accesscontrol.AccessControlModelAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontroltest.AccessControlAbstractFactoryMock;
import dal.asd.catme.courses.CourseModelAbstractFactoryImpl;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.coursestest.CourseAbstractFactoryMock;
import dal.asd.catme.database.DatabaseAbstractFactoryImpl;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import dal.asd.catme.passwordtest.PasswordAbstractFactoryMock;
import dal.asd.catme.questionmanager.IQuestionManagerAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.QuestionManagerModelAbstractFactoryImpl;
import dal.asd.catme.questionmanagertest.QuestionManagerAbstractFactoryMock;
import dal.asd.catme.studentlistimport.CSVParserAbstractFactoryImpl;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyModelAbstractFactory;
import dal.asd.catme.survey.SurveyModelAbstractFactoryImpl;
import dal.asd.catme.surveyresponse.ISurveyResponseAbstractFactory;
import dal.asd.catme.surveyresponse.ISurveyResponseModelAbstractFactory;
import dal.asd.catme.surveyresponse.SurveyResponseAbstractFactoryMock;
import dal.asd.catme.surveyresponse.SurveyResponseModelAbstractFactoryImpl;
import dal.asd.catme.surveytest.SurveyAbstractFactoryMock;

public class BaseAbstractFactoryMock implements IBaseAbstractFactory
{
    private static IBaseAbstractFactory baseAbstractFactory = null;
    private IAccessControlAbstractFactory accessControlAbstractFactory = null;
    private IAccessControlModelAbstractFactory accessControlModelAbstractFactory = null;
    private ICourseAbstractFactory courseAbstractFactory = null;
    private ICourseModelAbstractFactory courseModelAbstractFactory = null;
    private IDatabaseAbstractFactory databaseAbstractFactory = null;
    private IPasswordAbstractFactory passwordAbstractFactory = null;
    private IQuestionManagerAbstractFactory questionManagerAbstractFactory = null;
    private IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = null;
    private ICSVParserAbstractFactory icsvParserAbstractFactory = null;
    private ISurveyResponseAbstractFactory surveyResponseAbstractFactory = null;
    private ISurveyResponseModelAbstractFactory surveyResponseModelAbstractFactory = null;
    private ISurveyAbstractFactory surveyAbstractFactory;
    private ISurveyModelAbstractFactory surveyModelAbstractFactory;

    public static IBaseAbstractFactory instance()
    {
        if (baseAbstractFactory == null)
        {
            baseAbstractFactory = new BaseAbstractFactoryMock();
        }
        return baseAbstractFactory;
    }

    @Override
    public IAccessControlAbstractFactory makeAccessControlAbstractFactory()
    {
        if (accessControlAbstractFactory == null)
        {
            accessControlAbstractFactory = new AccessControlAbstractFactoryMock();
        }
        return accessControlAbstractFactory;
    }

    @Override
    public IAccessControlModelAbstractFactory makeAccessControlModelAbstractFactory()
    {
        if (accessControlModelAbstractFactory == null)
        {
            accessControlModelAbstractFactory = new AccessControlModelAbstractFactoryImpl();
        }
        return accessControlModelAbstractFactory;
    }

    @Override
    public ICourseAbstractFactory makeCourseAbstractFactory()
    {
        if (courseAbstractFactory == null)
        {
            courseAbstractFactory = new CourseAbstractFactoryMock();
        }
        return courseAbstractFactory;
    }

    @Override
    public ICourseModelAbstractFactory makeCourseModelAbstractFactory()
    {
        if (courseModelAbstractFactory == null)
        {
            courseModelAbstractFactory = new CourseModelAbstractFactoryImpl();
        }
        return courseModelAbstractFactory;
    }

    @Override
    public IDatabaseAbstractFactory makeDatabaseAbstractFactory()
    {
        if (databaseAbstractFactory == null)
        {
            databaseAbstractFactory = new DatabaseAbstractFactoryImpl();
        }
        return databaseAbstractFactory;
    }

    @Override
    public IPasswordAbstractFactory makePasswordAbstractFactory()
    {
        if (passwordAbstractFactory == null)
        {
            passwordAbstractFactory = new PasswordAbstractFactoryMock();
        }
        return passwordAbstractFactory;
    }

    @Override
    public IQuestionManagerAbstractFactory makeQuestionManagerAbstractFactory()
    {
        if (questionManagerAbstractFactory == null)
        {
            questionManagerAbstractFactory = new QuestionManagerAbstractFactoryMock();
        }
        return questionManagerAbstractFactory;
    }

    @Override
    public IQuestionManagerModelAbstractFactory makeQuestionManagerModelAbstractFactory()
    {
        if (questionManagerModelAbstractFactory == null)
        {
            questionManagerModelAbstractFactory = new QuestionManagerModelAbstractFactoryImpl();
        }
        return questionManagerModelAbstractFactory;
    }

    @Override
    public ICSVParserAbstractFactory makeCSVParserAbstractFactory()
    {
        if (icsvParserAbstractFactory == null)
        {
            icsvParserAbstractFactory = new CSVParserAbstractFactoryImpl();
        }
        return icsvParserAbstractFactory;
    }

    @Override
    public ISurveyResponseAbstractFactory makeSurveyResponseAbstractFactory()
    {
        if (surveyResponseAbstractFactory == null)
        {
            surveyResponseAbstractFactory = new SurveyResponseAbstractFactoryMock();
        }
        return surveyResponseAbstractFactory;
    }

    @Override
    public ISurveyResponseModelAbstractFactory makeSurveyResponseModelAbstractFactory()
    {
        if (surveyResponseModelAbstractFactory == null)
        {
            surveyResponseModelAbstractFactory = new SurveyResponseModelAbstractFactoryImpl();
        }
        return surveyResponseModelAbstractFactory;
    }

    @Override
    public ISurveyAbstractFactory makeSurveyAbstractFactory()
    {
        if (surveyAbstractFactory == null)
        {
            surveyAbstractFactory = new SurveyAbstractFactoryMock();
        }
        return surveyAbstractFactory;
    }


    @Override
    public ISurveyModelAbstractFactory makeSurveyModelAbstractFactory()
    {
        if (surveyModelAbstractFactory == null)
        {
            surveyModelAbstractFactory = new SurveyModelAbstractFactoryImpl();
        }
        return surveyModelAbstractFactory;
    }

    @Override
    public ICSVParserAbstractFactory makeIcsvParserAbstractFactory()
    {
        return null;
    }

}
