package dal.asd.catme.surveyresponse;

public class SurveyResponseAbstractFactoryMock implements ISurveyResponseAbstractFactory
{
    static ISurveyResponseAbstractFactory abstractFactory = null;
    ISurveyResponseDao surveyResponseDao;
    ISurveyResponseService surveyResponseService;

    public SurveyResponseAbstractFactoryMock()
    {
        surveyResponseDao = new SurveyResponseDaoMock();
        surveyResponseService = new SurveyResponseServiceImpl(surveyResponseDao);
    }

    @Override
    public ISurveyResponseDao makeSurveyResponseDao()
    {
        return surveyResponseDao;
    }

    @Override
    public ISurveyResponseService makeSurveyResponseService()
    {
        return surveyResponseService;
    }
}
