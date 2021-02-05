package dal.asd.catme.survey;

public class Rule
{
    int ruleId;
    String ruleType;
    String ruleValue;

    public int getRuleId()
    {
        return ruleId;
    }

    public void setRuleId(int ruleId)
    {
        this.ruleId = ruleId;
    }

    public String getRuleType()
    {
        return ruleType;
    }

    public void setRuleType(String ruleType)
    {
        this.ruleType = ruleType;
    }

    public String getRuleValue()
    {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue)
    {
        this.ruleValue = ruleValue;
    }

    @Override
    public String toString()
    {
        return "Rule [ruleId=" + ruleId + ", ruleType=" + ruleType + ", ruleValue=" + ruleValue + "]";
    }
}
