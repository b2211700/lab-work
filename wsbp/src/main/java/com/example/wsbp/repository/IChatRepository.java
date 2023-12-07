package com.example.wsbp.repository;

import com.example.wsbp.data.Chat;

import java.util.ArrayList;
import java.util.List;

public interface IChatRepository {

    /**
     * チャットの全メッセージを取得する。
     *
     * @return チャットの全メッセージ
     */

    List<Chat> reply();

    public int insert(String userName, String userPass);

    public int delete(String userName);
    public boolean exists(String userName, String userPass);



}



