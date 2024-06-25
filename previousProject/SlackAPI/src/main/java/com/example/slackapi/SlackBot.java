package com.example.slackapi;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import com.slack.api.bolt.socket_mode.SocketModeApp;

public class SlackBot {

    public static void main(String[] args) throws Exception {
        String botToken = System.getenv("");
        App app = new App(AppConfig.builder().singleTeamBotToken(botToken).build());
        String appToken = System.getenv("");
        SocketModeApp socketModeApp = new SocketModeApp(appToken, app);
        socketModeApp.start();
    }
}