package dal.asd.catme.surveyresponse;

import java.util.List;

public interface ISurveyResponseDao
{
    String getPublishedSurveyId(String courseId) throws SurveyResponseException;

    List<SurveyResponse> getSurveyQuestions(String surveyId) throws SurveyResponseException;

    boolean saveResponses(SurveyResponseBinder binder, String bannerId) throws SurveyResponseException;

    boolean saveAttempt(String surveyId, String bannerId) throws SurveyResponseException;

    boolean isSurveyAttempted(String surveyId, String bannerId) throws SurveyResponseException;
}
