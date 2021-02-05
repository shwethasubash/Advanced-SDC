package dal.asd.catme.questionmanagertest;

import dal.asd.catme.questionmanager.*;

public class QuestionManagerAbstractFactoryMock implements IQuestionManagerAbstractFactory
{
    private IQuestionDao questionDao = null;
    private IQuestionManagerService questionManagerService = null;
    private IListQuestionsService listQuestionsService = null;

    @Override
    public IQuestionDao makeQuestionDao()
    {
        if (questionDao == null)
        {
            questionDao = new QuestionDaoMock();
        }
        return questionDao;
    }

    @Override
    public IQuestionManagerService makeQuestionManagerService()
    {
        if (questionManagerService == null)
        {
            questionManagerService = new QuestionManagerServiceImpl(makeQuestionDao());
        }
        return questionManagerService;
    }

    @Override
    public IListQuestionsService makeListQuestionsService()
    {
        if (listQuestionsService == null)
        {
            listQuestionsService = new ListQuestionsServiceImpl(makeQuestionDao());
        }
        return listQuestionsService;
    }
}
