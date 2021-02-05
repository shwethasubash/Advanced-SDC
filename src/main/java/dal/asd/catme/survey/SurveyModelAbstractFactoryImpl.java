package dal.asd.catme.survey;

public class SurveyModelAbstractFactoryImpl implements ISurveyModelAbstractFactory
{

    @Override
    public Survey makeSurvey()
    {
        return new Survey();
    }

    @Override
    public Rule makeRule()
    {
        return new Rule();
    }

    @Override
    public SurveyQuestion makeSurveyQuestion()
    {
        return new SurveyQuestion();
    }

}
