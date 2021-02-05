package dal.asd.catme.survey;

public interface ISurveyAbstractFactory
{
    ISurveyService makeSurveyService();

    ISurveyDao makeSurveyDao();

    ISurveyGroupService makeSurveyGroupService();

    ISurveyGroupDao makeSurveyGroupDao();
}
