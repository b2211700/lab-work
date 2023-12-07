package com.example.wsbp.page;

import com.example.wsbp.page.signed.ChatPage;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import com.example.wsbp.service.ISampleService;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {

    // 下の2行を追加
    @SpringBean
    private ISampleService service;

    public HomePage() {
        // "Wicket-Spring-Boot" を表示するLabel
        var youModel = Model.of("Wicket-Spring-Boot");
        var youLabel = new Label("you", youModel);
        add(youLabel);

        // 学籍番号を表示するLabel
        var gakusekiModel = Model.of("b2211700");
        var gakusekiLabel = new Label("gakuseki", gakusekiModel);
        add(gakusekiLabel);

        // 名前を表示するlabel
        var nameModel = Model.of("鳥谷部花心");
        var nameLabel = new Label("name", nameModel);
        add(nameLabel);

        // 現在の時刻を表示するlabel
        var timeModel = Model.of(service.makeCurrentHMS());
        var timeLabel = new Label("time", timeModel);
        add(timeLabel);

        // 0~9の乱数を表示するlabel
        var randModel = Model.of(service.makeRandInt());
        var randLabel = new Label("rand", randModel);
        add(randLabel);

        //　ユーザ追加を表示するlabel
        var toUserMakerLink = new BookmarkablePageLink<>("toUserMaker", UserMakerPage.class);
        add(toUserMakerLink);

        //　ユーザーを削除するlabal
        var removeUserLink = new BookmarkablePageLink<>("removeUser", UserDeletePage.class);
        add(removeUserLink);


    }

}