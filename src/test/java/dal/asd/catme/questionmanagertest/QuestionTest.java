package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.Option;
import dal.asd.catme.questionmanager.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    @Test
    public void getQuestionIdTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        question.setQuestionId(1);
        assertEquals(question.getQuestionId(), 1);
    }

    @Test
    public void setQuestionIdTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        question.setQuestionId(1);
        assertEquals(question.getQuestionId(), 1);
    }

    @Test
    public void getQuestionTextTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        question.setQuestionText("Text");
        assertEquals(question.getQuestionText(), "Text");
    }

    @Test
    public void setQuestionTextTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        question.setQuestionText("Text");
        assertEquals(question.getQuestionText(), "Text");
    }

    @Test
    public void getQuestionTypeTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        question.setQuestionType("NUMERIC");
        assertEquals(question.getQuestionType(), "NUMERIC");
    }

    @Test
    public void setQuestionTypeTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        question.setQuestionType("NUMERIC");
        assertEquals(question.getQuestionType(), "NUMERIC");
    }

    @Test
    public void getQuestionTitleTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        question.setQuestionTitle("Title");
        assertEquals(question.getQuestionTitle(), "Title");
    }

    @Test
    public void setQuestionTitleTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        question.setQuestionTitle("Title");
        assertEquals(question.getQuestionTitle(), "Title");
    }

    @Test
    public void getOptionWithOrderTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        List<Option> list = listOption();
        question.setOptionWithOrder(list);
        assertEquals(question.getOptionWithOrder(), list);
    }

    @Test
    public void setOptionWithOrderTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        List<Option> list = listOption();
        question.setOptionWithOrder(list);
        assertEquals(question.getOptionWithOrder(), list);
    }

    @Test
    public void getCreatedDateTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        Date date = new Date(2020, 7, 12);
        question.setCreatedDate(date);
        assertEquals(question.getCreatedDate(), date);
    }

    @Test
    public void setCreatedDateTest()
    {
        Question question = modelAbstractFactory.makeQuestion();

        Date date = new Date(2020, 7, 12);
        question.setCreatedDate(date);
        assertEquals(question.getCreatedDate(), date);
    }

    @Test
    public void addOptionTest()
    {
        Question question = modelAbstractFactory.makeQuestion();
        Option option = modelAbstractFactory.makeOption();

        option.setDisplayText("Option 1");
        question.addOption(option);

        assertEquals("Option 1", question.getOptionWithOrder().get(0).getDisplayText());
    }

    private List<Option> listOption()
    {
        List<Option> list = new ArrayList<>();

        Option o = modelAbstractFactory.makeOption();
        o.setStoredAs(1);
        o.setDisplayText("Option");
        list.add(o);

        return list;
    }
}