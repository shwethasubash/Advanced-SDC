package dal.asd.catme;

import dal.asd.catme.accesscontrol.AccessControlAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.AccessControlModelAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.courses.CourseAbstractFactoryImpl;
import dal.asd.catme.courses.CourseModelAbstractFactoryImpl;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.database.DatabaseAbstractFactoryImpl;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import dal.asd.catme.password.PasswordAbstractFactoryImpl;
import dal.asd.catme.questionmanager.IQuestionManagerAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.QuestionManagerAbstractFactoryImpl;
import dal.asd.catme.questionmanager.QuestionManagerModelAbstractFactoryImpl;
import dal.asd.catme.studentlistimport.CSVParserAbstractFactoryImpl;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyModelAbstractFactory;
import dal.asd.catme.survey.SurveyAbstractFactoryImpl;
import dal.asd.catme.survey.SurveyModelAbstractFactoryImpl;
import dal.asd.catme.surveyresponse.ISurveyResponseAbstractFactory;
import dal.asd.catme.surveyresponse.ISurveyResponseModelAbstractFactory;
import dal.asd.catme.surveyresponse.SurveyResponseAbstractFactoryImpl;
import dal.asd.catme.surveyresponse.SurveyResponseModelAbstractFactoryImpl;

public class BaseAbstractFactoryImpl implements IBaseAbstractFactory
{
    private static IBaseAbstractFactory baseAbstractFactory = null;
    private ISurveyAbstractFactory surveyAbstractFactory;
    private ISurveyModelAbstractFactory surveyModelAbstractFactory;
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

    public static IBaseAbstractFactory instance()
    {
        if (baseAbstractFactory == null)
        {
            baseAbstractFactory = new BaseAbstractFactoryImpl();
        }
        return baseAbstractFactory;
    }

    public IAccessControlAbstractFactory makeAccessControlAbstractFactory()
    {
        if (accessControlAbstractFactory == null)
        {
            accessControlAbstractFactory = new AccessControlAbstractFactoryImpl();
        }
        return accessControlAbstractFactory;
    }

    public IAccessControlModelAbstractFactory makeAccessControlModelAbstractFactory()
    {
        if (accessControlModelAbstractFactory == null)
        {
            accessControlModelAbstractFactory = new AccessControlModelAbstractFactoryImpl();
        }
        return accessControlModelAbstractFactory;
    }

    public ICourseAbstractFactory makeCourseAbstractFactory()
    {
        if (courseAbstractFactory == null)
        {
            courseAbstractFactory = new CourseAbstractFactoryImpl();
        }
        return courseAbstractFactory;
    }

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

    public IPasswordAbstractFactory makePasswordAbstractFactory()
    {
        if (passwordAbstractFactory == null)
        {
            passwordAbstractFactory = new PasswordAbstractFactoryImpl();
        }
        return passwordAbstractFactory;
    }

    public IQuestionManagerAbstractFactory makeQuestionManagerAbstractFactory()
    {
        if (questionManagerAbstractFactory == null)
        {
            questionManagerAbstractFactory = new QuestionManagerAbstractFactoryImpl();
        }
        return questionManagerAbstractFactory;
    }

    public IQuestionManagerModelAbstractFactory makeQuestionManagerModelAbstractFactory()
    {
        if (questionManagerModelAbstractFactory == null)
        {
            questionManagerModelAbstractFactory = new QuestionManagerModelAbstractFactoryImpl();
        }
        return questionManagerModelAbstractFactory;
    }

    public ICSVParserAbstractFactory makeCSVParserAbstractFactory()
    {
        if (icsvParserAbstractFactory == null)
        {
            icsvParserAbstractFactory = new CSVParserAbstractFactoryImpl();
        }
        return icsvParserAbstractFactory;
    }

    public ISurveyResponseAbstractFactory makeSurveyResponseAbstractFactory()
    {
        if (surveyResponseAbstractFactory == null)
        {
            surveyResponseAbstractFactory = new SurveyResponseAbstractFactoryImpl();
        }
        return surveyResponseAbstractFactory;
    }

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
            surveyAbstractFactory = new SurveyAbstractFactoryImpl();
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
        // TODO Auto-generated method stub
        return null;
    }
}