package dal.asd.catme.surveyresponse;

import java.util.List;

public interface ISurveyResponseService
{
    String getPublishedSurveyId(String courseId) throws SurveyResponseException;

    List<SurveyResponse> getSurveyQuestions(String surveyId) throws SurveyResponseException;

    boolean saveResponses(SurveyResponseBinder binder, String bannerId) throws SurveyResponseException;

    boolean isSurveyAttempted(String suveyId, String bannerId) throws SurveyResponseException;
}
