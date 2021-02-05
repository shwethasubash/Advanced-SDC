package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.IListQuestionsService;
import dal.asd.catme.questionmanager.IQuestionManagerAbstractFactory;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.questionmanager.QuestionDatabaseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class ListQuestionsServiceTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerAbstractFactory questionManagerAbstractFactory = baseAbstractFactory.makeQuestionManagerAbstractFactory();

    @Test
    void getQuestionsTest()
    {

        IListQuestionsService listQuestionsService = questionManagerAbstractFactory.makeListQuestionsService();

        try
        {
            listQuestionsService.getQuestions("instructor");

        } catch (QuestionDatabaseException e)
        {
            fail();
            e.printStackTrace();
        }

        try
        {
            listQuestionsService.getQuestions(null);
            fail();
        } catch (QuestionDatabaseException e)
        {

        }
    }

    @Test
    void sortByDateTest()
    {
        IListQuestionsService listQuestionsService = questionManagerAbstractFactory.makeListQuestionsService();

        try
        {
            listQuestionsService.getQuestions("instructor");

            List<Question> questionList = listQuestionsService.sortByDate();

            Question q1 = questionList.get(0);
            Question q2 = questionList.get(1);

            //date of question1 comes after date of question2
            if (q1.getCreatedDate().compareTo(q2.getCreatedDate()) >= 0)
            {
                fail();
            }
        } catch (QuestionDatabaseException e)
        {
            fail();
        }
    }

    @Test
    void sortByTitleTest()
    {
        IListQuestionsService listQuestionsService = questionManagerAbstractFactory.makeListQuestionsService();

        try
        {
            listQuestionsService.getQuestions("instructor");

            List<Question> questionList = listQuestionsService.sortByTitle();

            Question q1 = questionList.get(0);
            Question q2 = questionList.get(1);

            //question 1 comes after question2
            if (q1.getQuestionTitle().compareTo(q2.getQuestionTitle()) < 0)
            {
                fail();
            }
        } catch (QuestionDatabaseException e)
        {
            fail();
        }
    }
}