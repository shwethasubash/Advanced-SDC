package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuestionManagerModelAbstractFactoryTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();

    @Test
    void makeQuestionTest()
    {
        IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();
        assertNotNull(questionManagerModelAbstractFactory.makeQuestion());
    }

    @Test
    void makeOptionTest()
    {
        IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();
        assertNotNull(questionManagerModelAbstractFactory.makeOption());
    }

    @Test
    void makeQuestionTitleTest()
    {
        IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();
        assertNotNull(questionManagerModelAbstractFactory.makeQuestionTitle());
    }
}
