package dal.asd.catme.survey;

import dal.asd.catme.courses.Course;
import dal.asd.catme.questionmanager.Question;

import java.util.List;

public interface ISurveyDao
{
    Survey getSurvey(String courseId) throws SurveyException;

    List<Question> fetchCourseSurveyQuestions(String courseId) throws SurveyException;

    Rule getSurveyQuestionRule(int questionId) throws SurveyException;

    int addToSurvey(Survey survey, Question question) throws SurveyException;

    int saveSurvey(Survey survey) throws SurveyException;

    int deleteSurveyQuestion(Survey survey, Question question) throws SurveyException;

    boolean isSurveyPublished(Course course) throws SurveyException;

    int publishSurvey(Survey survey) throws SurveyException;

    int getSurveyQuestionPriority(Survey survey, Question question) throws SurveyException;
}

