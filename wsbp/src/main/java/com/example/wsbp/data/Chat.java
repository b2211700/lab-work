package com.example.wsbp.data;

import java.io.Serializable;

public class Chat implements Serializable {

    private final String userName;
    private final String msg_body;

    public Chat(String userName, String msg_body){
        this.userName = userName;
        this.msg_body = msg_body;
    }

    public String getUserName(){
        return userName;
    }

    public String getMsg_body(){
        return msg_body;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (!userName.equals(chat.userName)) return false;
        return msg_body.equals(chat.msg_body);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + msg_body.hashCode();
        return result;
    }
}
