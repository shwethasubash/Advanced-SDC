package dal.asd.catme.surveyresponse;

import java.util.List;

public class SurveyResponseBinder
{
    private String surveyId;
    private String courseId;
    private List<SurveyResponse> questionList;

    public String getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(String surveyId)
    {
        this.surveyId = surveyId;
    }

    public String getCourseId()
    {
        return courseId;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }

    public List<SurveyResponse> getQuestionList()
    {
        return questionList;
    }

    public void setQuestionList(List<SurveyResponse> questionList)
    {
        this.questionList = questionList;
    }
}
