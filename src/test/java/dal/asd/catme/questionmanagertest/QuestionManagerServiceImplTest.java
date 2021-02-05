package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.*;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionManagerServiceImplTest
{
    Option option;
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerAbstractFactory questionManagerAbstractFactory = baseAbstractFactory.makeQuestionManagerAbstractFactory();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    @Test
    public void findQuestionTypeTest()
    {
        IQuestionManagerService questionManagerService = questionManagerAbstractFactory.makeQuestionManagerService();

        Question question = modelAbstractFactory.makeQuestion();
        question.setQuestionText("Which Framework?");
        question.setQuestionType("Free Text");
        question.setQuestionTitle("UI");

        Question question1 = modelAbstractFactory.makeQuestion();
        question1.setQuestionText("Rate Java");
        question1.setQuestionType(CatmeUtil.CHECKBOX);
        question1.setQuestionTitle("UI");

        assertEquals(CatmeUtil.QUESTION_CREATION_SUCCESS, questionManagerService.findQuestionType(question, CatmeUtil.USER_ROLE_ID));
        assertEquals(CatmeUtil.OPTION_EDITOR, questionManagerService.findQuestionType(question1, CatmeUtil.USER_ROLE_ID));
    }

    @Test
    public void createQuestionTest()
    {
        IQuestionManagerService questionManagerService = questionManagerAbstractFactory.makeQuestionManagerService();

        Question question = modelAbstractFactory.makeQuestion();
        question.setQuestionText("Which Framework?");
        question.setQuestionType("Free Text");
        question.setQuestionTitle("UI");

        assertEquals(CatmeUtil.ONE, questionManagerService.createQuestion(question, CatmeUtil.USER_ROLE_ID));
    }

    @Test
    public void createOptionsTest()
    {
        IQuestionManagerService questionManagerService = questionManagerAbstractFactory.makeQuestionManagerService();
        List<Option> options = formQuestionOptions();
        assertEquals(CatmeUtil.ONE, questionManagerService.createOptions(CatmeUtil.QUESTION_ID, options));
    }

    @Test
    public void deleteQuestionTest()
    {
        IQuestionManagerService questionManagerService = questionManagerAbstractFactory.makeQuestionManagerService();

        assertEquals(CatmeUtil.ONE, questionManagerService.deleteQuestion(1));
    }

    private List<Option> formQuestionOptions()
    {
        List<Option> options = new ArrayList<>();
        option = modelAbstractFactory.makeOption();
        option.setDisplayText("Angular");
        option.setStoredAs(1);
        options.add(option);
        return options;

    }

}