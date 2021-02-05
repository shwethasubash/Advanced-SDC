package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyResonseBinderTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeSurveyResponseModelAbstractFactory();

    @Test
    void getSurveyIdTest()
    {
        SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        binder.setSurveyId("ID1");
        assertEquals("ID1", binder.getSurveyId());
    }

    @Test
    void setSurveyIdTest()
    {
        SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        binder.setSurveyId("ID1");
        assertEquals("ID1", binder.getSurveyId());
    }

    @Test
    void getCourseIdTest()
    {
        SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        binder.setCourseId("ID1");
        assertEquals("ID1", binder.getCourseId());
    }

    @Test
    void setCourseIdTest()
    {
        SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        binder.setCourseId("ID1");
        assertEquals("ID1", binder.getCourseId());
    }


    @Test
    void setQuestionListTest()
    {
        SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        List<SurveyResponse> list = new ArrayList<>();
        binder.setQuestionList(list);

        assertEquals(list, binder.getQuestionList());
    }

    @Test
    void getQuestionListTest()
    {
        SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        List<SurveyResponse> list = new ArrayList<>();
        binder.setQuestionList(list);

        assertEquals(list, binder.getQuestionList());
    }
}
