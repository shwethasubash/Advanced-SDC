package dal.asd.catme.questionmanager;

public interface IQuestionManagerAbstractFactory
{
    IQuestionDao makeQuestionDao();

    IQuestionManagerService makeQuestionManagerService();

    IListQuestionsService makeListQuestionsService();
}
