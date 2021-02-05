package dal.asd.catme.surveytest;

import dal.asd.catme.survey.Rule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleTest
{
    @Test
    public void getRuleIdTest()
    {
        Rule rule = FormSurveyMock.formRule();
        assertEquals(78, rule.getRuleId());
    }

    @Test
    public void setRuleIdTest()
    {
        Rule rule = FormSurveyMock.formRule();
        rule.setRuleId(99);
        assertEquals(99, rule.getRuleId());
    }

    @Test
    public void setRuleTypeTest()
    {
        Rule rule = FormSurveyMock.formRule();
        assertEquals("Group Similar", rule.getRuleType());
    }

    @Test
    public void getRuleTypeTest()
    {
        Rule rule = FormSurveyMock.formRule();
        rule.setRuleType("Numeric");
        assertEquals("Numeric", rule.getRuleType());
    }

    @Test
    public void setRuleValueTest()
    {
        Rule rule = FormSurveyMock.formRule();
        rule.setRuleValue("10 rating");
        assertEquals("10 rating", rule.getRuleValue());
    }

    @Test
    public void getRuleValueTest()
    {
        Rule rule = FormSurveyMock.formRule();
        assertEquals("9 rating", rule.getRuleValue());
    }
}
