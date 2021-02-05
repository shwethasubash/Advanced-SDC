package dal.asd.catme.surveytest;

import dal.asd.catme.survey.*;

public class SurveyAbstractFactoryMock implements ISurveyAbstractFactory
{
    static ISurveyAbstractFactory surveyAbstractFactory = null;
    ISurveyService surveyService;
    ISurveyDao surveyDao;
    ISurveyGroupDao groupDao;
    ISurveyGroupService surveyGroupService;

    public SurveyAbstractFactoryMock()
    {
        surveyDao = new SurveyDaoMock();
        surveyService = new SurveyServiceImpl(surveyDao);
        groupDao = new SurveyGroupDaoImplMock();
        surveyGroupService = new SurveyGroupServiceImpl(groupDao);
    }


    @Override
    public ISurveyService makeSurveyService()
    {
        return surveyService;
    }

    @Override
    public ISurveyDao makeSurveyDao()
    {
        return surveyDao;
    }

    @Override
    public ISurveyGroupService makeSurveyGroupService()
    {
        return surveyGroupService;
    }

    @Override
    public ISurveyGroupDao makeSurveyGroupDao()
    {
        return groupDao;
    }
}
