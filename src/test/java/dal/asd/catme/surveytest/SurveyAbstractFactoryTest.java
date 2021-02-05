package dal.asd.catme.surveytest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.survey.ISurveyDao;
import dal.asd.catme.survey.ISurveyService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class SurveyAbstractFactoryTest
{
    @Test
    public void makeSurveyServiceTest()
    {
        ISurveyService surveyService = BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyService();
        assertNotNull(surveyService);

    }

    @Test
    public void makeSurveyDaoTest()
    {
        ISurveyDao surveyDao = BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyDao();
        assertNotNull(surveyDao);
    }
}
