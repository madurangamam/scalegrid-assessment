package exception;

import org.springframework.util.StringUtils;

public class UserFormatException extends RuntimeException{

    public UserFormatException(String message){
        super(StringUtils.hasText(message) ? message : "UserFormatException");
    }
}
