package dal.asd.catme.survey;

import java.util.List;

public class Survey
{
    int surveyId;
    int groupSize;
    String surveyName;
    List<SurveyQuestion> surveyQuestions;
    Boolean isPublished;

    public Boolean getIsPublished()
    {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished)
    {
        this.isPublished = isPublished;
    }

    public int getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(int surveyId)
    {
        this.surveyId = surveyId;
    }

    public int getGroupSize()
    {
        return groupSize;
    }

    public void setGroupSize(int groupSize)
    {
        this.groupSize = groupSize;
    }

    public String getSurveyName()
    {
        return surveyName;
    }

    public void setSurveyName(String surveyName)
    {
        this.surveyName = surveyName;
    }

    public List<SurveyQuestion> getSurveyQuestions()
    {
        return surveyQuestions;
    }

    public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions)
    {
        this.surveyQuestions = surveyQuestions;
    }

    @Override
    public String toString()
    {
        return "Survey [surveyId=" + surveyId + ", groupSize=" + groupSize + ", surveyName=" + surveyName
                + ", surveyQuestions=" + surveyQuestions + ", isPublished=" + isPublished + "]";
    }
}
