package dal.asd.catme.survey;

import dal.asd.catme.accesscontrol.User;

import java.util.List;

public interface ISurveyGroupDao
{

    int getGroupSize(int surveyId);

    List<User> getSurveyParticipants(int surveyId);
}
