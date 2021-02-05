package dal.asd.catme.surveyresponse;

public interface ISurveyResponseAbstractFactory
{
    ISurveyResponseDao makeSurveyResponseDao();

    ISurveyResponseService makeSurveyResponseService();
}
