package dal.asd.catme.questionmanager;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("questions")
public class QuestionManagerController
{
    IQuestionManagerAbstractFactory questionManagerAbstractFactory = BaseAbstractFactoryImpl.instance().makeQuestionManagerAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeAccessControlModelAbstractFactory();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeQuestionManagerModelAbstractFactory();

    private static final Logger log = LoggerFactory.getLogger(QuestionManagerController.class);

    @Value("${questionTypes}")
    private String[] questionTypes;

    @RequestMapping("")
    public ModelAndView loadPage()
    {
        User currentUser = accessControlModelAbstractFactory.makeUser();
        currentUser.setBannerId(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView questionsPage = new ModelAndView();
        questionsPage.setViewName(CatmeUtil.QUESTION_MANAGER_HOME);
        IListQuestionsService listQuestionsService = questionManagerAbstractFactory.makeListQuestionsService();

        try
        {
            listQuestionsService.getQuestions(currentUser.getBannerId());
            List<Question> questionList = listQuestionsService.sortByTitle();
            questionsPage.addObject("questions", questionList);
        } catch (QuestionDatabaseException e)
        {
            String message = e.getMessage();
            questionsPage.addObject("message", message);
        }

        return questionsPage;
    }

    @GetMapping("sort")
    public ModelAndView sortTable(@RequestParam(name = "with") String sortBy)
    {
        IListQuestionsService listQuestionsService = questionManagerAbstractFactory.makeListQuestionsService();
        ModelAndView questionsPage = new ModelAndView();
        questionsPage.setViewName(CatmeUtil.QUESTION_MANAGER_HOME);
        List<Question> questionList;

        if (sortBy.equalsIgnoreCase("Date"))
        {
            questionList = listQuestionsService.sortByDate();
        } else
        {
            questionList = listQuestionsService.sortByTitle();
        }

        questionsPage.addObject("questions", questionList);
        questionsPage.addObject("sortBy", sortBy);

        return questionsPage;
    }

    @RequestMapping("/createQuestion")
    public String createQuestion(Model model)
    {
        log.info("****QuestionManagerController - createQuestion Invoked*****");
        model.addAttribute("question", modelAbstractFactory.makeQuestion());
        model.addAttribute("questionTitle", "");
        return "createQuestion";
    }

    @RequestMapping("/questionType")
    public String createQuestionType(@ModelAttribute Question question)
    {
        log.info("****QuestionManagerController - createQuestionType Invoked*****");
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("***user***" + userId);
        IQuestionManagerService questionManagerServiceImpl = questionManagerAbstractFactory.makeQuestionManagerService();
        return questionManagerServiceImpl.findQuestionType(question, userId);
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, params = "action=addOption")
    public String addOption(@ModelAttribute Question question)
    {
        List<Option> options = question.getOptionWithOrder();
        Option o = modelAbstractFactory.makeOption();
        o.setStoredAs(options.size() + 1);
        options.add(o);
        question.setOptionWithOrder(options);
        return "optionEditor";
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, params = "action=create")
    public String addQuestion(@ModelAttribute Question question)
    {
        log.info("****QuestionManagerController - addQuestion Invoked*****");
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("***here*** " + question.toString());
        IQuestionManagerService questionManagerServiceImpl = questionManagerAbstractFactory.makeQuestionManagerService();
        int questionId = questionManagerServiceImpl.createQuestion(question, userId);
        int result = questionManagerServiceImpl.createOptions(questionId, question.getOptionWithOrder());

        if (result > 0)
        {
            return CatmeUtil.QUESTION_CREATION_SUCCESS;
        } else
        {
            return CatmeUtil.QUESTION_FAILURE_PAGE;
        }
    }

    @ModelAttribute("questionTypes")
    public String[] getQuestionTypes()
    {
        return questionTypes;
    }

    @PostMapping("/deleteQuestion/{questionId}")
    public String deleteQuestion(Model model, @PathVariable("questionId") String questionId)
    {
        int qId = Integer.parseInt(questionId);
        IQuestionManagerService questionManagerServiceImpl = questionManagerAbstractFactory.makeQuestionManagerService();
        questionManagerServiceImpl.deleteQuestion(qId);
        return "redirect:/questions";
    }

    @GetMapping("/deleteQuestionConfirmation/{questionId}")
    public String deleteQuestionConfirmation(@PathVariable("questionId") String questionId, Model model)
    {
        model.addAttribute("questionId", questionId);
        return "deleteQuestionConfirmation";
    }
}