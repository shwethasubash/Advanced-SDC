package dal.asd.catme.surveytest;

import dal.asd.catme.survey.SurveyQuestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyQuestionTest
{
    @Test
    public void setQuestionTest()
    {
        SurveyQuestion surveyQuestion = FormSurveyMock.formSurveyQuestionPojo();
        surveyQuestion.setQuestion(FormSurveyMock.formQuestion());
        assertEquals(22, surveyQuestion.getQuestion().getQuestionId());
    }

    @Test
    public void getQuestionTest()
    {
        SurveyQuestion surveyQuestion = FormSurveyMock.formSurveyQuestionPojo();
        assertEquals(22, surveyQuestion.getQuestion().getQuestionId());
    }

    @Test
    public void setRuleTest()
    {
        SurveyQuestion surveyQuestion = FormSurveyMock.formSurveyQuestionPojo();
        surveyQuestion.setQuestion(FormSurveyMock.formQuestion());
        assertEquals(78, surveyQuestion.getRule().getRuleId());
    }

    @Test
    public void getRuleTest()
    {
        SurveyQuestion surveyQuestion = FormSurveyMock.formSurveyQuestionPojo();
        assertEquals(78, surveyQuestion.getRule().getRuleId());
    }

    @Test
    public void setSurveyQuestionIdTest()
    {
        SurveyQuestion surveyQuestion = FormSurveyMock.formSurveyQuestionPojo();
        surveyQuestion.setSurveyQuestionId(15);
        assertEquals(15, surveyQuestion.getSurveyQuestionId());
    }

    @Test
    public void getSurveyQuestionIdTest()
    {
        SurveyQuestion surveyQuestion = FormSurveyMock.formSurveyQuestionPojo();
        assertEquals(85, surveyQuestion.getSurveyQuestionId());
    }

    @Test
    public void setPriority()
    {
        SurveyQuestion surveyQuestion = FormSurveyMock.formSurveyQuestionPojo();
        surveyQuestion.setPriority(6);
        assertEquals(6, surveyQuestion.getPriority());
    }

    @Test
    public void getPriority()
    {
        SurveyQuestion surveyQuestion = FormSurveyMock.formSurveyQuestionPojo();
        assertEquals(9, surveyQuestion.getPriority());
    }

}
