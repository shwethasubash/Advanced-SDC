package dal.asd.catme.survey;

public interface ISurveyModelAbstractFactory
{
    Survey makeSurvey();

    Rule makeRule();

    SurveyQuestion makeSurveyQuestion();
}
