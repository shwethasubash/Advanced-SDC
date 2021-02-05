package dal.asd.catme.survey;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SurveyGroupController
{
    private static final Logger log = LoggerFactory.getLogger(SurveyGroupController.class);

    ISurveyGroupService surveyGroupService = BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyGroupService();

    @RequestMapping("/surveyGroups/{surveyId}")
    public ModelAndView surveyGroups(@PathVariable("surveyId") int surveyId)
    {
        log.info("Getting groups");
        List<List<User>> groups = surveyGroupService.displaySurveyGroups(surveyId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CatmeUtil.SURVEY_GROUP);
        if (groups == null)
        {
            modelAndView.addObject("message", "Error Creating groups");
            return modelAndView;
        }
        modelAndView.addObject("groups", groups);
        return modelAndView;
    }
}
