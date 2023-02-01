package coms309.RoundTrip.demo.Exception;

import coms309.RoundTrip.demo.Exception.StorageException;

public class StorageFileNotFoundException extends StorageException
{

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}