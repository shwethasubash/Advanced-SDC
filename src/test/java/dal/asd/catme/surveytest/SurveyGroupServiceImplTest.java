package dal.asd.catme.surveytest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyGroupService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class SurveyGroupServiceImplTest
{

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ISurveyAbstractFactory surveyAbstractFactory = baseAbstractFactory.makeSurveyAbstractFactory();

    @Test
    public void getSurveyParticipantsTest()
    {
        ISurveyGroupService surveyService = surveyAbstractFactory.makeSurveyGroupService();
        assertNotNull(surveyService.displaySurveyGroups(7));
    }

}
