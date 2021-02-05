package dal.asd.catme.questionmanager;

public class QuestionManagerAbstractFactoryImpl implements IQuestionManagerAbstractFactory
{
    private final IQuestionDao questionDao;
    private final IQuestionManagerService questionManagerService;
    private final IListQuestionsService listQuestionsService;

    public QuestionManagerAbstractFactoryImpl()
    {
        questionDao = new QuestionDaoImpl();
        questionManagerService = new QuestionManagerServiceImpl(questionDao);
        listQuestionsService = new ListQuestionsServiceImpl(questionDao);
    }

    @Override
    public IQuestionDao makeQuestionDao()
    {
        return questionDao;
    }

    @Override
    public IQuestionManagerService makeQuestionManagerService()
    {
        return questionManagerService;
    }

    @Override
    public IListQuestionsService makeListQuestionsService()
    {
        return listQuestionsService;
    }
}
