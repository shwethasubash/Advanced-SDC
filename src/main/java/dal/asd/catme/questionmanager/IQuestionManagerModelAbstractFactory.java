package dal.asd.catme.questionmanager;

public interface IQuestionManagerModelAbstractFactory
{
    Question makeQuestion();

    Option makeOption();

    QuestionTitle makeQuestionTitle();
}
