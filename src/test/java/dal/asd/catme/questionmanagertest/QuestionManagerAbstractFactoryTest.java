package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuestionManagerAbstractFactoryTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();

    @Test
    void makeQuestionDaoTest()
    {
        IQuestionManagerAbstractFactory questionManagerAbstractFactory = baseAbstractFactory.makeQuestionManagerAbstractFactory();
        assertNotNull(questionManagerAbstractFactory.makeQuestionDao());
    }

    @Test
    void makeQuestionManagerServiceTest()
    {
        IQuestionManagerAbstractFactory questionManagerAbstractFactory = baseAbstractFactory.makeQuestionManagerAbstractFactory();
        assertNotNull(questionManagerAbstractFactory.makeQuestionManagerService());
    }

    @Test
    void makeListQuestionsServiceTest()
    {
        IQuestionManagerAbstractFactory questionManagerAbstractFactory = baseAbstractFactory.makeQuestionManagerAbstractFactory();
        assertNotNull(questionManagerAbstractFactory.makeListQuestionsService());
    }
}
