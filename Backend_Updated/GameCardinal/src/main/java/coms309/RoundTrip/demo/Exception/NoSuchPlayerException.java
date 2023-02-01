package coms309.RoundTrip.demo.Exception;

public class NoSuchPlayerException extends RuntimeException
{
    public NoSuchPlayerException(String message)
    {
        super(message);
    }
}
