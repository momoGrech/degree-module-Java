package exceptionHandling;
import java.io.FileNotFoundException;
/**
 * This class is responible for handling exptions and illigal arguments
 *
 * @author (Mohammed Fuad Gurun)
 * @version 1.0
 * @since 01/12/2021
 */
public class Exceptions extends IllegalArgumentException
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class headsetEmailException
     */
    public Exceptions()
    {
        super ();
    }
    
     public Exceptions(String message)
    {
        super (message);
    }

    public Exceptions(Throwable cause)
    {
        super (cause);
    }
    
    public Exceptions(String message, Throwable cause)
    {
        super (message, cause);
    }
    

}
