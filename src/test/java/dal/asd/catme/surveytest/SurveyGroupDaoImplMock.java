package dal.asd.catme.surveytest;

import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.survey.ISurveyGroupDao;

import java.util.List;

public class SurveyGroupDaoImplMock implements ISurveyGroupDao
{
    @Override
    public int getGroupSize(int surveyId)
    {
        if (surveyId == 7)
        {
            return 3;
        }
        return 0;
    }

    @Override
    public List<User> getSurveyParticipants(int surveyId)
    {
        List<User> users = FormSurveyMock.formParticipants();

        return users;
    }
}
