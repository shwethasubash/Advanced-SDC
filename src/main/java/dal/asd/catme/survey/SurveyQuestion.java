package dal.asd.catme.survey;

import dal.asd.catme.questionmanager.Question;

public class SurveyQuestion
{
    int surveyQuestionId;
    Question question;
    Rule rule;
    int priority;

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public int getSurveyQuestionId()
    {
        return surveyQuestionId;
    }

    public void setSurveyQuestionId(int surveyQuestionId)
    {
        this.surveyQuestionId = surveyQuestionId;
    }

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public Rule getRule()
    {
        return rule;
    }

    public void setRule(Rule rule)
    {
        this.rule = rule;
    }

    @Override
    public String toString()
    {
        return "SurveyQuestion [surveyQuestionId=" + surveyQuestionId + ", question=" + question + ", rule=" + rule
                + ", priority=" + priority + "]";
    }


}
