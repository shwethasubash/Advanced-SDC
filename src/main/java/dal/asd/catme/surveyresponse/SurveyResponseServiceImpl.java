package dal.asd.catme.surveyresponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SurveyResponseServiceImpl implements ISurveyResponseService
{
    private static final Logger log = LoggerFactory.getLogger(SurveyResponseServiceImpl.class);
    ISurveyResponseDao surveyDao;

    public SurveyResponseServiceImpl(ISurveyResponseDao surveyDao)
    {
        this.surveyDao = surveyDao;
    }

    public String getPublishedSurveyId(String courseId) throws SurveyResponseException
    {
        log.info("calling service method to check if survey is published");
        if (stringNullOrEmpty(courseId))
        {
            return null;
        }
        return surveyDao.getPublishedSurveyId(courseId);
    }

    public List<SurveyResponse> getSurveyQuestions(String surveyId) throws SurveyResponseException
    {
        log.info("calling service method to check get list of survey questions");
        if (stringNullOrEmpty(surveyId))
        {
            return null;
        }

        return surveyDao.getSurveyQuestions(surveyId);
    }

    public boolean saveResponses(SurveyResponseBinder binder, String bannerId) throws SurveyResponseException
    {
        log.info("calling service method to save responses of student: " + bannerId);
        if (stringNullOrEmpty(bannerId))
            return false;
        return surveyDao.saveResponses(binder, bannerId) &&
                surveyDao.saveAttempt(binder.getSurveyId(), bannerId);
    }

    public boolean isSurveyAttempted(String suveyId, String bannerId) throws SurveyResponseException
    {
        log.info("calling service method to check if survey is already attempted by student: " + bannerId);
        return surveyDao.isSurveyAttempted(suveyId, bannerId);
    }

    private boolean stringNullOrEmpty(String str)
    {
        return (str == null || str.equals(""));
    }
}
