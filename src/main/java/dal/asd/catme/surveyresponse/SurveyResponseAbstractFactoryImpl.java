package dal.asd.catme.surveyresponse;

public class SurveyResponseAbstractFactoryImpl implements ISurveyResponseAbstractFactory
{
    ISurveyResponseDao surveyResponseDao;
    ISurveyResponseService surveyResponseService;

    public SurveyResponseAbstractFactoryImpl()
    {
        surveyResponseDao = new SurveyResponseDaoImpl();
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
