package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyResponseServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ISurveyResponseAbstractFactory surveyResponseAbstractFactory = baseAbstractFactory.makeSurveyResponseAbstractFactory();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeSurveyResponseModelAbstractFactory();

    @Test
    public void isSurveyPublishedTest()
    {
        ISurveyResponseService surveyResponseService = surveyResponseAbstractFactory.makeSurveyResponseService();

        try
        {
            assertNotNull(surveyResponseService.getPublishedSurveyId("5308"));
            assertNull(surveyResponseService.getPublishedSurveyId(""));
        } catch (SurveyResponseException e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getSurveyQuestionsTest()
    {
        ISurveyResponseService surveyResponseService = surveyResponseAbstractFactory.makeSurveyResponseService();
        try
        {
            assertNotNull(surveyResponseService.getSurveyQuestions("5308"));
            assertNull(surveyResponseService.getSurveyQuestions(""));
        } catch (SurveyResponseException e)
        {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void saveResponsesTest()
    {
        ISurveyResponseService surveyResponseService = surveyResponseAbstractFactory.makeSurveyResponseService();
        SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();
        List<SurveyResponse> questions = new ArrayList<>();

        questions.add(modelAbstractFactory.makeSurveyResponse());
        binder.setQuestionList(questions);

        try
        {
            assertFalse(surveyResponseService.saveResponses(binder, "B00121212"));
            binder.setSurveyId("1");

            assertTrue(surveyResponseService.saveResponses(binder, "B00121212"));
            assertFalse(surveyResponseService.saveResponses(binder, ""));
        } catch (SurveyResponseException e)
        {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void isSurveyAttemptedTest()
    {
        ISurveyResponseService surveyResponseService = surveyResponseAbstractFactory.makeSurveyResponseService();
        try
        {
            assertTrue(surveyResponseService.isSurveyAttempted("5100", "B00121212"));
        } catch (SurveyResponseException e)
        {
            fail();
            e.printStackTrace();
        }
    }
}
