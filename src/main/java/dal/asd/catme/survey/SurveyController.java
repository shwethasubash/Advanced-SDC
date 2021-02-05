package dal.asd.catme.survey;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.questionmanager.*;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyController
{
    private static final Logger log = LoggerFactory.getLogger(SurveyController.class);

    IListQuestionsService listQuestionsService;
    IQuestionManagerAbstractFactory questionmanager = BaseAbstractFactoryImpl.instance().makeQuestionManagerAbstractFactory();
    ISurveyService surveyService = BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyService();
    ISurveyModelAbstractFactory surveyModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeSurveyModelAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeAccessControlModelAbstractFactory();
    ICourseModelAbstractFactory courseModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeCourseModelAbstractFactory();
    IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeQuestionManagerModelAbstractFactory();

    @RequestMapping("/createSurvey")
    public ModelAndView loadSurveyPage(@RequestParam(name = "courseId") String courseId)
    {
        log.info("Loading the survey for the course : " + courseId);
        ModelAndView modelAndView = new ModelAndView();
        User currentUser = accessControlModelAbstractFactory.makeUser();
        Course course = courseModelAbstractFactory.makeCourse();
        course.setCourseId(courseId.trim());
        Survey survey = null;
        List<Question> questionBank = new ArrayList<>();
        currentUser.setBannerId(SecurityContextHolder.getContext().getAuthentication().getName());
        listQuestionsService = questionmanager.makeListQuestionsService();
        try
        {
            log.info("Fetching questions from question bank ");
            if (currentUser.getBannerId().length() > 0)
            {
                questionBank = listQuestionsService.getQuestions(currentUser.getBannerId());
            } else
            {
                log.error("Failed to fetch user's ID for fetching question bank");
                throw new SurveyException("Error in fetching current user details ");
            }
            if (course.getCourseId().length() > 0)
            {
                survey = surveyService.loadSurvey(course);
                survey.setIsPublished(surveyService.isSurveyPublished(course));
            } else
            {
                log.error("Failed to fetch Course details");
                throw new SurveyException("Error in fetching c details ");
            }
        } catch (SurveyException e)
        {
            log.error("Failed while loading the survey");
            e.printStackTrace();
        } catch (QuestionDatabaseException e)
        {
            log.error("Failed while fetching questions from question bank to the survey");
            e.printStackTrace();
        }
        modelAndView.setViewName("createSurvey");
        modelAndView.addObject("questionBank", questionBank);
        modelAndView.addObject("survey", survey);
        modelAndView.addObject("course", course);
        modelAndView.addObject("isPublished", survey.getIsPublished());
        log.info("Displaying Survey page");
        return modelAndView;
    }

    @RequestMapping("/survey/add")
    public String addQuestionToSurvey(@ModelAttribute Question question, @ModelAttribute Survey survey, @ModelAttribute Course course)
    {
        log.info("Adding question to the survey");
        try
        {
            if (survey == null || question == null)
            {
                log.error("Question or Survey object cannot be fetched from Html page");
                throw new SurveyException("Error addding question to survey");
            } else
            {
                surveyService.addToSurvey(survey, question);
            }

        } catch (SurveyException e)
        {
            log.error("Failed while adding question to the survey");
            e.printStackTrace();
        }
        return CatmeUtil.ADD_QUESTION_SURVEY_PAGE + course.getCourseId();
    }

    @RequestMapping(value = "/survey/saveAndPublish", method = RequestMethod.POST, params = "action=Save")
    public ModelAndView saveSurvey(@ModelAttribute Survey survey, @ModelAttribute Course course)
    {
        log.info("Saving the survey");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CatmeUtil.SAVE_SURVEY_PAGE);
        int returnedRows = 0;
        try
        {
            if (survey == null)
            {
                log.error("Survey object cannot be fetched from Html page");
                throw new SurveyException("Error saving question to survey");
            } else
            {
                returnedRows = surveyService.saveSurvey(survey);
                modelAndView.addObject("returnedRows", returnedRows);
            }
        } catch (SurveyException e)
        {
            log.error("Failed while saving the survey");
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/survey/saveAndPublish", method = RequestMethod.POST, params = "action=Publish")
    public ModelAndView publishSurvey(@ModelAttribute Survey survey, @ModelAttribute Course course)
    {
        log.info("Publishing the survey");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CatmeUtil.PUBLISH_SURVEY_PAGE);
        int returnedRows = 0;
        try
        {
            if (survey == null)
            {
                log.error("Survey object cannot be fetched from Html page");
                throw new SurveyException("Error publishing survey");
            } else
            {
                returnedRows = surveyService.publishSurvey(survey);
                modelAndView.addObject("returnedRows", returnedRows);
            }
        } catch (SurveyException e)
        {
            log.error("Failed while publishing the survey");
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("/survey/{surveyId}/deleteQuestion/{questionid}/course/{courseId}")
    public String deleteQuestion(@PathVariable("questionid") int questionId, @PathVariable("surveyId") int surveyId, @PathVariable("courseId") String courseId)
    {
        log.info("Deleting the survey question");
        Survey survey = surveyModelAbstractFactory.makeSurvey();
        survey.setSurveyId(surveyId);
        Question question = questionManagerModelAbstractFactory.makeQuestion();
        question.setQuestionId(questionId);
        try
        {
            if (survey == null || question == null)
            {
                log.error("Survey and Question object cannot be fetched from Html page for deleteing");
                throw new SurveyException("Error deleting question");
            } else
            {
                surveyService.deleteSurveyQuestion(survey, question);
            }
        } catch (SurveyException e)
        {
            log.error("Deleting the question failed");
            e.printStackTrace();
        }
        return CatmeUtil.ADD_QUESTION_SURVEY_PAGE + courseId;
    }
}
