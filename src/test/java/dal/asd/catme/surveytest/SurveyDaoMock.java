package dal.asd.catme.surveytest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.courses.Course;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.survey.*;

import java.util.List;

public class SurveyDaoMock implements ISurveyDao
{
    ISurveyModelAbstractFactory surveyModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeSurveyModelAbstractFactory();
    List<Question> questionsList = FormSurveyMock.formQuestionsList();

    @Override
    public Survey getSurvey(String courseId) throws SurveyException
    {
        Survey survey = surveyModelAbstractFactory.makeSurvey();
        if (courseId != null)
        {
            if (courseId.equalsIgnoreCase("CSCI5100"))
            {
                survey.setSurveyId(7);
            } else if (courseId.equalsIgnoreCase("CSCI5409"))
            {
                survey.setSurveyId(9);
            }
        }
        return survey;
    }

    @Override
    public List<Question> fetchCourseSurveyQuestions(String courseId) throws SurveyException
    {
        if (courseId != null)
        {
            if (courseId.equalsIgnoreCase("CSCI5100"))
            {
                return questionsList;
            }
        }
        return null;
    }

    @Override
    public Rule getSurveyQuestionRule(int questionId) throws SurveyException
    {
        Rule rule = surveyModelAbstractFactory.makeRule();
        if (questionId == 22)
        {
            rule.setRuleId(78);
            rule.setRuleType("Group Similar");
            rule.setRuleValue("9 rating");
        }
        return rule;
    }

    @Override
    public int addToSurvey(Survey survey, Question question) throws SurveyException
    {
        if (survey != null && question != null)
        {
            SurveyQuestion surveyQuestion = surveyModelAbstractFactory.makeSurveyQuestion();
            surveyQuestion.setQuestion(question);
            if (survey.getSurveyQuestions().add(surveyQuestion))
            {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int saveSurvey(Survey survey) throws SurveyException
    {
        if (survey != null)
        {
            if (survey.getSurveyQuestions().size() > 0 && survey.getIsPublished() == false)
            {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteSurveyQuestion(Survey survey, Question question) throws SurveyException
    {
        if (survey != null && question != null)
        {
            SurveyQuestion surveyQuestion = surveyModelAbstractFactory.makeSurveyQuestion();
            surveyQuestion.setQuestion(question);
            if (survey.getSurveyQuestions().get(0).getQuestion().getQuestionId() == question.getQuestionId())
            {
                survey.getSurveyQuestions().remove(0);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean isSurveyPublished(Course course) throws SurveyException
    {
        if (course != null)
        {
            return course.getCourseId().equalsIgnoreCase("CSCI5100");
        }
        return false;
    }

    @Override
    public int publishSurvey(Survey survey) throws SurveyException
    {
        if (survey != null)
        {
            if (survey.getSurveyQuestions().size() > 0 && survey.getIsPublished() == false)
            {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int getSurveyQuestionPriority(Survey survey, Question question) throws SurveyException
    {
        if (survey != null && question != null)
        {
            if (survey.getSurveyQuestions().size() > 0)
            {
                return survey.getSurveyQuestions().get(0).getPriority();
            }
        }
        return 0;
    }

}
