package com.example.wsbp.service;

import com.example.wsbp.data.Chat;
import com.example.wsbp.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService{

    private IChatRepository chatRepos;

    @Autowired
    public ChatService(IChatRepository chatRepos) {
        this.chatRepos = chatRepos;
    }

    @Override
    public void registerUser(String userName, String msgBody) {
        int n = chatRepos.insert(userName, msgBody);
        System.out.println("記録行数：" + n);

    }
    @Override
    public void removeUser(String userName) {
        int n = chatRepos.delete(userName);
        System.out.println("削除行数：" + n);
    }

    @Override
    public boolean existsUser(String userName, String msgBody) {
        var result = chatRepos.exists(userName, msgBody);
        System.out.println(userName + ", " + msgBody + " のユーザ照合結果：" + result);
        return result;
    }


    @Override
    public List<Chat> replyChat() {
        var users = chatRepos.reply();
        System.out.println("チャット履歴：" + users.size());
        return users;
    }

}