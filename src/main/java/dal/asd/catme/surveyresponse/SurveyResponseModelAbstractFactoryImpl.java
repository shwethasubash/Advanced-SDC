package dal.asd.catme.surveyresponse;

public class SurveyResponseModelAbstractFactoryImpl implements ISurveyResponseModelAbstractFactory
{
    @Override
    public SurveyResponse makeSurveyResponse()
    {
        return new SurveyResponse();
    }

    @Override
    public SurveyResponseBinder makeSurveyResponseBinder()
    {
        return new SurveyResponseBinder();
    }
}
