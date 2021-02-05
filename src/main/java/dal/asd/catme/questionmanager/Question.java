package dal.asd.catme.questionmanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question
{
    int questionId;
    String questionText;
    String questionType;
    String questionTitle;
    List<Option> optionWithOrder = new ArrayList<>();
    Date createdDate;

    public int getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(int questionId)
    {
        this.questionId = questionId;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionText(String questionText)
    {
        this.questionText = questionText;
    }

    public String getQuestionType()
    {
        return questionType;
    }

    public void setQuestionType(String questionType)
    {
        this.questionType = questionType;
    }

    public String getQuestionTitle()
    {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle)
    {
        this.questionTitle = questionTitle;
    }

    public List<Option> getOptionWithOrder()
    {
        return optionWithOrder;
    }

    public void addOption(Option option)
    {
        optionWithOrder.add(option);
    }

    public void setOptionWithOrder(List<Option> optionWithOrder)
    {
        this.optionWithOrder = optionWithOrder;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }
}
