package dal.asd.catme.passwordtest;

import dal.asd.catme.util.RandomTokenGenerator;
import org.junit.jupiter.api.Test;

import static dal.asd.catme.accesscontrol.MailSenderUtil.TOKEN_LENGTH;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RandomTokenGeneratorTest
{
    @Test
    void generateRandomPasswordTest()
    {
        assertNotNull(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
    }
}