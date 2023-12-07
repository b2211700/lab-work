package com.example.wsbp.repository;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository implements IChatRepository {

    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public ChatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(String userName, String message) {
        var sql = "insert into chat values (?, ?)";
        var n = jdbc.update(sql, userName, message);
        return n;
    }
    @Override
    public int delete(String userName) {
        var sql = "delete from chat where user_name = ?";
        return jdbc.update(sql, userName);

    }
    @Override
    public boolean exists(String userName, String msgBody) {
        // ユーザ名とパスワードが一致する情報が auth_user テーブルにあれば、true を返す
        // テーブルになければ、何も返さない
        var sql = "select true from chat "
                + "where user_name = ? and msg_body = ?";

        // 検索用のSQLを実行する方法。検索結果をList（可変長配列）で返す。
        // データの追加時と若干異なるので注意。
        var booles = jdbc.query(sql,
                SingleColumnRowMapper.newInstance(Boolean.class),
                userName, msgBody);
        // Listにデータがある(＝trueの要素ものがある)：照合成功
        // Listにデータがない(要素が何もない)：照合失敗
        return !booles.isEmpty();
    }

    public List<Chat> reply() {
        // chat　テーブルのuser_pass, msg_bodyを返す
        String sql = "select * FROM chat ";
        // クエリを実行して結果を取得
        List<Chat> messages = jdbc.query(sql, DataClassRowMapper.newInstance(Chat.class));

        return messages;
    }
}
