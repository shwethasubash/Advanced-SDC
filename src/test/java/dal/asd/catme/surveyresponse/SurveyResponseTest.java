package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyResponseTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeSurveyResponseModelAbstractFactory();

    @Test
    void getQuestionTest()
    {
        SurveyResponse surveyResponse = modelAbstractFactory.makeSurveyResponse();

        Question q = questionManagerModelAbstractFactory.makeQuestion();
        surveyResponse.setQuestion(q);

        assertEquals(q, surveyResponse.getQuestion());
    }

    @Test
    void setQuestionTest()
    {
        SurveyResponse surveyResponse = modelAbstractFactory.makeSurveyResponse();

        Question q = questionManagerModelAbstractFactory.makeQuestion();
        surveyResponse.setQuestion(q);

        assertEquals(q, surveyResponse.getQuestion());
    }

    @Test
    void getAnswerTest()
    {
        SurveyResponse surveyResponse = modelAbstractFactory.makeSurveyResponse();

        List<String> answers = new ArrayList<>();
        surveyResponse.setAnswer(answers);

        assertEquals(answers, surveyResponse.getAnswer());
    }

    @Test
    void getSetAnswerTest()
    {
        SurveyResponse surveyResponse = modelAbstractFactory.makeSurveyResponse();

        List<String> answers = new ArrayList<>();
        surveyResponse.setAnswer(answers);

        assertEquals(answers, surveyResponse.getAnswer());
    }
}
