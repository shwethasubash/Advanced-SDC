package dal.asd.catme.questionmanager;

public class QuestionManagerModelAbstractFactoryImpl implements IQuestionManagerModelAbstractFactory
{
    @Override
    public Question makeQuestion()
    {
        return new Question();
    }

    @Override
    public Option makeOption()
    {
        return new Option();
    }

    @Override
    public QuestionTitle makeQuestionTitle()
    {
        return new QuestionTitle();
    }
}
