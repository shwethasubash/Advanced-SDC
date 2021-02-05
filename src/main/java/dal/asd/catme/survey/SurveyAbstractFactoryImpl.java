package dal.asd.catme.survey;

public class SurveyAbstractFactoryImpl implements ISurveyAbstractFactory
{
    static ISurveyAbstractFactory surveyAbstractFactory = null;
    ISurveyService surveyService;
    ISurveyDao surveyDao;
    ISurveyGroupService surveyGroupService;
    ISurveyGroupDao surveyGroupDao;

    public SurveyAbstractFactoryImpl()
    {
        surveyDao = new SurveyDaoImpl();
        surveyService = new SurveyServiceImpl(surveyDao);
        surveyGroupDao = new SurveyGroupDaoImpl();
        surveyGroupService = new SurveyGroupServiceImpl(surveyGroupDao);

    }

    public ISurveyService makeSurveyService()
    {
        return surveyService;
    }

    public ISurveyDao makeSurveyDao()
    {
        return surveyDao;
    }

    public ISurveyGroupService makeSurveyGroupService()
    {
        return surveyGroupService;
    }

    public ISurveyGroupDao makeSurveyGroupDao()
    {
        return surveyGroupDao;
    }
}
