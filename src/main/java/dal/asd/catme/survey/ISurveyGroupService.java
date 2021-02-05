package dal.asd.catme.survey;

import dal.asd.catme.accesscontrol.User;

import java.util.List;

public interface ISurveyGroupService
{
    List<List<User>> displaySurveyGroups(int surveyId);
}
