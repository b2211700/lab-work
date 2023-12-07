
package com.example.wsbp.page.signed;

import com.example.wsbp.data.Chat;
import com.example.wsbp.service.IChatService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;
@AuthorizeInstantiation(Roles.USER)
@MountPath("Chat")
public class ChatPage extends WebPage {

    //IUserService を IoC/DI する
    @SpringBean
    private IChatService chatservice;

    public ChatPage() {

        var userNameModel = Model.of("");
        var msgBodyModel = Model.of("");

        //var toChatLink = new BookmarkablePageLink<>("toChat", HomePage.class);
        //add(toChatLink);

        //配置したFormコンポーネントを匿名クラス化して処理を上書きする
        Form<Void> chatInfoForm = new Form<>("chatInfo") {
            @Override
            protected void onSubmit() {
                var userName = userNameModel.getObject();
                var msgBody = msgBodyModel.getObject();
                var msg = "投稿データ："
                        + userName
                        + ","
                        + msgBody;
                System.out.println(msg);
                // IoC/DI した userService のメソッドを呼び出す
                chatservice.registerUser(userName, msgBody);
                // この1行を追加
                setResponsePage(new ChatCompPage(userNameModel));
            }
        };
        add(chatInfoForm);

        var userNameField = new TextField<>("userName", userNameModel) {
            // onInitialize() は全てのコンポーネントに備わっている、初期化時の処理。
            // オーバーライドするときは super.onInitialize() を忘れずに残しておく。
            @Override
            protected void onInitialize() {
                super.onInitialize();
                // 文字列の長さを8〜32文字に制限するバリデータ
                var validator = StringValidator.lengthBetween(8, 32);
                add(validator);
            }
        };
        chatInfoForm.add(userNameField);

        var msgBodyField = new TextField<>("msg_Body", msgBodyModel) ;
        chatInfoForm.add(msgBodyField);

        // Service からデータベースのユーザ一覧をもらい、Modelにする
        // List型のモデルは Model.ofList(...) で作成する。
        // なお、DBや外部のWEB-APIなどのデータを取得する場合、通常はLoadableDetachableModelを利用する
        // 参考：https://ci.apache.org/projects/wicket/guide/9.x/single.html#_detachable_models
        var chatsModel = Model.ofList(chatservice.replyChat());

        // List型のモデルを表示する ListView
        var chatsLV = new ListView<>("chats", chatsModel) {
            @Override
            protected void populateItem(ListItem<Chat> listItem) {
                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                var chat = itemModel.getObject(); // 元々のListの n 番目の要素

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。
                var userNameModel = Model.of(chat.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var chatModel = Model.of(chat.getMsg_body());
                var chatLabel = new Label("msg_Body", chatModel);
                listItem.add(chatLabel);
            }
        };
        add(chatsLV);



    }
}
