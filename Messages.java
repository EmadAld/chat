package com.example.pcc.chatting;
import java.io.Serializable;


public class Messages implements Serializable {
    private static final long serialVersionUID = 1L;
    public final static String MSG_SENT="MSG_SENT";
    public final static String MSG_RECIEVED="MSG_RECIEVED";
    private String message;
    private String message_type;
    Messages (String message , String message_type)
    {
        this.message=message;
        this.message_type=message_type;
    }
    Messages (String message)
    {
        this.message=message;

    }

    public String getMessage() {
        return message ;
    }

    public void setMessage(String message1) {
        message = message1;
    }

    public String getMessage_type() {
        return message_type ;
    }

    public void setMessage_type(String message1_Type) {
        message_type = message1_Type;
    }

}
