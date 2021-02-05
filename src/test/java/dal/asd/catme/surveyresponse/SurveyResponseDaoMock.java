package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.POJOMock;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.List;

public class SurveyResponseDaoMock implements ISurveyResponseDao
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeSurveyResponseModelAbstractFactory();

    @Override
    public String getPublishedSurveyId(String courseId)
    {
        for (String course : POJOMock.getPublishedCourses())
        {
            if (course.equals(courseId))
            {
                return course;
            }
        }

        return null;
    }

    @Override
    public List<SurveyResponse> getSurveyQuestions(String surveyId)
    {
        List<SurveyResponse> responses = new ArrayList<>();

        SurveyResponse s = modelAbstractFactory.makeSurveyResponse();
        responses.add(s);

        Question q = questionManagerModelAbstractFactory.makeQuestion();
        q.setQuestionText("New Question");
        q.setQuestionType(CatmeUtil.NUMERIC_DB);

        s.setQuestion(q);

        if (surveyId.equals("5308"))
        {
            return responses;
        }
        return null;
    }

    @Override
    public boolean saveResponses(SurveyResponseBinder binder, String bannerId)
    {
        return binder.getQuestionList().size() != 0;
    }

    @Override
    public boolean saveAttempt(String surveyId, String bannerId)
    {
        return surveyId != null && bannerId != null;
    }

    @Override
    public boolean isSurveyAttempted(String surveyId, String bannerId)
    {
        return surveyId.equals("5100") && bannerId.equals("B00121212");
    }
}
