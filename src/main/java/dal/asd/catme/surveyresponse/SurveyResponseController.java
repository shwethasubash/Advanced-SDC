package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SurveyResponseController
{
    private static final Logger log = LoggerFactory.getLogger(SurveyResponseController.class);
    ISurveyResponseService surveyService = BaseAbstractFactoryImpl.instance().makeSurveyResponseAbstractFactory().makeSurveyResponseService();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeSurveyResponseModelAbstractFactory();

    @RequestMapping("/viewSurvey")
    public String viewSurvey(@RequestParam(name = "courseId") String courseId, Model m)
    {
        try
        {
            log.info("Getting Survey for course: " + courseId);
            String publishedSurveyId = surveyService.getPublishedSurveyId(courseId);
            String bannerId = SecurityContextHolder.getContext().getAuthentication().getName();

            m.addAttribute("courseId", courseId);

            if (publishedSurveyId == null)
            {
                log.info("Survey is not published");
                m.addAttribute("surveyPublished", false);
                return CatmeUtil.SURVEY_PAGE;
            } else
            {
                m.addAttribute("surveyPublished", true);
            }

            if (surveyService.isSurveyAttempted(publishedSurveyId, bannerId))
            {
                log.info("Survey is already attempted");
                m.addAttribute("surveyPublished", true);
                m.addAttribute("attempted", true);
                return CatmeUtil.SURVEY_PAGE;
            }

            m.addAttribute("attempted", false);
            List<SurveyResponse> surveyQuestions = surveyService.getSurveyQuestions(publishedSurveyId);

            SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();
            binder.setQuestionList(surveyQuestions);
            binder.setSurveyId(publishedSurveyId);
            binder.setCourseId(courseId);

            m.addAttribute("response", binder);
        } catch (SurveyResponseException e)
        {
            m.addAttribute("surveyPublished", true);
            m.addAttribute("attempted", false);
            m.addAttribute("message", e.getMessage());
        }
        return CatmeUtil.SURVEY_PAGE;
    }

    @PostMapping("/saveResponse")
    public String saveResponse(@ModelAttribute SurveyResponseBinder binder, Model m)
    {
        log.info("Responses Received");
        try
        {
            String bannerId = SecurityContextHolder.getContext().getAuthentication().getName();
            surveyService.saveResponses(binder, bannerId);
        } catch (SurveyResponseException e)
        {
            m.addAttribute("surveyPublished", true);
            m.addAttribute("attempted", false);
            m.addAttribute("message", e.getMessage());
            m.addAttribute("courseId", binder.getCourseId());
            return CatmeUtil.SURVEY_PAGE;
        }

        return "redirect:/viewSurvey?courseId=" + binder.getCourseId();
    }
}
