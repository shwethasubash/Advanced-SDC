package dal.asd.catme.questionmanager;

import java.util.List;

public interface IQuestionManagerService
{
    int createQuestion(Question question, String user);

    String findQuestionType(Question question, String user);

    int createOptions(int questionId, List<Option> options);

    int deleteQuestion(int questionId);

}