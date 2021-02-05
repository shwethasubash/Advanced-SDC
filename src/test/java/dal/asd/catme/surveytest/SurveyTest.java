package dal.asd.catme.surveytest;

import dal.asd.catme.survey.Survey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyTest
{
    @Test
    public void getIsPublished()
    {
        Survey survey = FormSurveyMock.formSurvey();
        assertEquals(false, survey.getIsPublished());
    }

    @Test
    public void setIsPublished()
    {
        Survey survey = FormSurveyMock.formSurvey();
        survey.setIsPublished(true);
        assertEquals(true, survey.getIsPublished());
    }

    @Test
    public void getSurveyId()
    {
        Survey survey = FormSurveyMock.formSurvey();
        assertEquals(7, survey.getSurveyId());
    }

    @Test
    public void setSurveyId()
    {
        Survey survey = FormSurveyMock.formSurvey();
        survey.setSurveyId(9);
        assertEquals(9, survey.getSurveyId());
    }

    @Test
    public void getGroupSize()
    {
        Survey survey = FormSurveyMock.formSurvey();
        assertEquals(7, survey.getSurveyId());
    }

    @Test
    public void setGroupSize()
    {
        Survey survey = FormSurveyMock.formSurvey();
        survey.setGroupSize(3);
        assertEquals(3, survey.getGroupSize());
    }

    @Test
    public void getSurveyName()
    {
        Survey survey = FormSurveyMock.formSurvey();
        assertEquals("CS survey", survey.getSurveyName());
    }

    @Test
    public void setSurveyName()
    {
        Survey survey = FormSurveyMock.formSurvey();
        survey.setSurveyName("ASDC survey");
        assertEquals(true, survey.getSurveyName().equalsIgnoreCase("ASDC survey"));
    }

    @Test
    public void getSurveyQuestions()
    {
        Survey survey = FormSurveyMock.formSurvey();
        survey.setSurveyQuestions(FormSurveyMock.formSurveyQuestion());
        assertEquals(22, survey.getSurveyQuestions().get(0).getQuestion().getQuestionId());
    }

    @Test
    public void setSurveyQuestions()
    {
        Survey survey = FormSurveyMock.formSurvey();
        survey.setSurveyQuestions(FormSurveyMock.formSurveyQuestion());
        survey.getSurveyQuestions().get(0).getQuestion().setQuestionId(23);
        assertEquals(23, survey.getSurveyQuestions().get(0).getQuestion().getQuestionId());
    }

}
