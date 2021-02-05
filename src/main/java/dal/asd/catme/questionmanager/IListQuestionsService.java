package dal.asd.catme.questionmanager;

import java.util.List;

public interface IListQuestionsService
{
    List<Question> getQuestions(String instructor) throws QuestionDatabaseException;

    List<Question> sortByDate();

    List<Question> sortByTitle();
}
