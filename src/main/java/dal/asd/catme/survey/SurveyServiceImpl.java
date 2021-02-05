package dal.asd.catme.survey;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.courses.Course;
import dal.asd.catme.questionmanager.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SurveyServiceImpl implements ISurveyService
{
    private static final Logger log = LoggerFactory.getLogger(SurveyServiceImpl.class);
    ISurveyModelAbstractFactory surveyModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeSurveyModelAbstractFactory();

    public SurveyServiceImpl(ISurveyDao surveyDao)
    {
        this.surveyDao = surveyDao;
    }

    ISurveyDao surveyDao;

    @Override
    public Rule getSurveyQuestionRule(int questionId) throws SurveyException
    {
        return surveyDao.getSurveyQuestionRule(questionId);
    }

    @Override
    public Survey loadSurvey(Course course) throws SurveyException
    {
        Survey survey = null;
        List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
        SurveyQuestion surveyQuestion = null;
        if (course == null)
        {
            log.error("Course object is null while laoding survey");
            throw new SurveyException("Error loading survey");
        } else
        {
            try
            {
                survey = surveyDao.getSurvey(course.getCourseId());
                List<Question> questionList = surveyDao.fetchCourseSurveyQuestions(course.getCourseId());
                for (Question question : questionList)
                {
                    surveyQuestion = surveyModelAbstractFactory.makeSurveyQuestion();
                    Rule rule = surveyDao.getSurveyQuestionRule(question.getQuestionId());
                    int priority = surveyDao.getSurveyQuestionPriority(survey, question);
                    surveyQuestion.setQuestion(question);
                    surveyQuestion.setRule(rule);
                    surveyQuestion.setPriority(priority);
                    surveyQuestionList.add(surveyQuestion);
                }
                if (surveyQuestionList.size() > 0)
                {
                    log.info("Fetching all Survey Questions in Course");
                    survey.setSurveyQuestions(surveyQuestionList);
                }
            } catch (SurveyException e)
            {
                log.error("Failed while fetching all Survey question of a Course");
                e.printStackTrace();
            }
        }
        return survey;
    }

    @Override
    public int addToSurvey(Survey survey, Question question) throws SurveyException
    {
        if (survey == null || question == null)
        {
            log.error("Error adding question to survey");
            throw new SurveyException("Error Adding to survey before Calling DB");
        } else
        {
            log.info("Calling DB to add question to survey");
            return surveyDao.addToSurvey(survey, question);
        }
    }

    @Override
    public int saveSurvey(Survey survey) throws SurveyException
    {
        if (survey == null)
        {
            log.error("Error saving survey");
            throw new SurveyException("Error saving survey before Calling DB");
        } else
        {
            log.info("Calling DB to save survey");
            return surveyDao.saveSurvey(survey);
        }
    }

    @Override
    public int deleteSurveyQuestion(Survey survey, Question question) throws SurveyException
    {
        if (survey == null || question == null)
        {
            log.error("Error deleting question to survey");
            throw new SurveyException("Error Deleting question of a survey before Calling DB");
        } else
        {
            log.info("Deleting question from a survey");
            return surveyDao.deleteSurveyQuestion(survey, question);
        }
    }

    @Override
    public boolean isSurveyPublished(Course course) throws SurveyException
    {
        if (course == null)
        {
            log.error("Error checking whether survey is published, Course is null");
            throw new SurveyException("Error checking the status of a course survey");
        } else
        {
            log.info("Checking the status of a course survey");
            return surveyDao.isSurveyPublished(course);
        }
    }

    @Override
    public int publishSurvey(Survey survey) throws SurveyException
    {
        if (survey == null)
        {
            log.error("Error publishing survey,Survey object is null");
            throw new SurveyException("Error publishing survey before Calling DB");
        } else
        {
            log.info("Calling DB to publish survey");
            return surveyDao.publishSurvey(survey);
        }
    }

    @Override
    public Survey getSurvey(String courseId) throws SurveyException
    {
        if (courseId == null)
        {
            log.error("Error fetching the survey details, courseId  uavailable");
            throw new SurveyException("Error fetching survey details before Calling DB");
        } else
        {
            log.info("Calling DB to fetch Survey details");
            return surveyDao.getSurvey(courseId);
        }
    }

    @Override
    public List<Question> fetchCourseSurveyQuestions(String courseId) throws SurveyException
    {
        if (courseId == null)
        {
            log.error("Error fetching the survey questions details, courseId  uavailable");
            throw new SurveyException("Error fetching survey questions details before Calling DB");
        } else
        {
            log.info("Calling DB to fetch Survey questions details");
            return surveyDao.fetchCourseSurveyQuestions(courseId);
        }
    }

    @Override
    public int getSurveyQuestionPriority(Survey survey, Question question) throws SurveyException
    {
        if (survey == null || question == null)
        {
            log.error("Error finding priority of a survey question");
            throw new SurveyException("Error Adding to survey quesiton priority before Calling DB");
        } else
        {
            log.info("Calling DB to get the priority of a survey question");
            return surveyDao.getSurveyQuestionPriority(survey, question);
        }
    }
}
