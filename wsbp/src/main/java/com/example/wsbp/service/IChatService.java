package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;

import java.util.List;

public interface IChatService {

    void registerUser(String userName, String msgBody);

    void removeUser(String userName);

    boolean existsUser(String userName, String msgBody);

    /**
     * メッセージ一覧を、Chat型のリストで検索する
     *
     * @return Chat型のListインスタンス
     */

    List<Chat> replyChat();
}
