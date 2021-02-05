package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SurveyResponseAbstractFactoryTest
{
    @Test
    void getSurveyResponseDaoTest()
    {
        ISurveyResponseDao surveyResponseDao = BaseAbstractFactoryImpl.instance().makeSurveyResponseAbstractFactory().makeSurveyResponseDao();

        assertNotNull(surveyResponseDao);
    }

    @Test
    void getSurveyResponseServiceTest()
    {
        ISurveyResponseService surveyResponseService = BaseAbstractFactoryImpl.instance().makeSurveyResponseAbstractFactory().makeSurveyResponseService();

        assertNotNull(surveyResponseService);
    }
}
