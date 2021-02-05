package dal.asd.catme.accesscontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatmeException extends Exception
{

    private static final Logger log = LoggerFactory.getLogger(CatmeException.class);

    private static final long serialVersionUID = 1L;

    public CatmeException()
    {
        super();
    }

    public CatmeException(String message)
    {
        super(message);
        log.error("Unable to process : " + message);
    }

}
