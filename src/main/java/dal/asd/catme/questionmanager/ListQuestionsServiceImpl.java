package dal.asd.catme.questionmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class ListQuestionsServiceImpl implements IListQuestionsService
{
    IQuestionDao questionDao;
    List<Question> questions;

    private static final Logger log = LoggerFactory.getLogger(ListQuestionsServiceImpl.class);

    public ListQuestionsServiceImpl(IQuestionDao questionDao)
    {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions(String instructor) throws QuestionDatabaseException
    {
        log.info("Calling QestionDao for getting questions");
        this.questions = questionDao.getQuestionTitles(instructor);
        return questions;
    }

    @Override
    public List<Question> sortByDate()
    {
        log.info("Sorting questions by date");
        Collections.sort(questions, (question1, question2) ->
        {
            return (question1.getCreatedDate().compareTo(question2.getCreatedDate()));
        });
        return questions;
    }

    @Override
    public List<Question> sortByTitle()
    {
        log.info("Sorting questions by title");
        Collections.sort(questions, (question1, question2) ->
        {
            return question1.getQuestionTitle().compareTo(question2.getQuestionTitle());
        });
        return questions;
    }
}