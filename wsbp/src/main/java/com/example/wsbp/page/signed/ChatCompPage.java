package com.example.wsbp.page.signed;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("ChatComp")
public class ChatCompPage extends WebPage {

    public ChatCompPage(IModel<String> userNameModel) {
        var userNameLabel = new Label("userName", userNameModel);
        add(userNameLabel);

        var toHomeLink = new BookmarkablePageLink<>("toHome", SignedPage.class);
        add(toHomeLink);
    }
}
