package dal.asd.catme.surveyresponse;

import dal.asd.catme.questionmanager.Question;

import java.util.List;

public class SurveyResponse
{
    private Question question;
    private List<String> answer;

    public Question getQuestion()
    {
        return question;
    }

    public void setQuestion(Question question)
    {
        this.question = question;
    }

    public List<String> getAnswer()
    {
        return answer;
    }

    public void setAnswer(List<String> answer)
    {
        this.answer = answer;
    }
}
