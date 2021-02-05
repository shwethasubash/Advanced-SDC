package dal.asd.catme.questionmanager;

import java.util.List;

public class QuestionTitle
{
    String questionTitle;
    List<Question> questions;

    public String getQuestionTitle()
    {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle)
    {
        this.questionTitle = questionTitle;
    }

    public List<Question> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<Question> questions)
    {
        this.questions = questions;
    }
}
