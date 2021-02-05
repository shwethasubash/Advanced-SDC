package dal.asd.catme.surveytest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.survey.ISurveyModelAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class SurveyModelAbstractFactoryTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();

    @Test
    void makeRuleTest()
    {
        ISurveyModelAbstractFactory surveyModelAbstractFactory = baseAbstractFactory.makeSurveyModelAbstractFactory();
        assertNotNull(surveyModelAbstractFactory.makeRule());
    }

    @Test
    void makeSurveyTest()
    {
        ISurveyModelAbstractFactory surveyModelAbstractFactory = baseAbstractFactory.makeSurveyModelAbstractFactory();
        assertNotNull(surveyModelAbstractFactory.makeSurvey());
    }

    @Test
    void makeSurveyQuestionTest()
    {
        ISurveyModelAbstractFactory surveyModelAbstractFactory = baseAbstractFactory.makeSurveyModelAbstractFactory();
        assertNotNull(surveyModelAbstractFactory.makeSurveyQuestion());
    }


}
