package com.example.slackapi;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.conversations.ConversationsHistoryRequest;
import com.slack.api.methods.response.conversations.ConversationsHistoryResponse;
import com.slack.api.model.Message;

import java.io.IOException;
import java.util.List;

    public class SlackMessageReader {

        public static void main(String[] args) {
            // Slack API 토큰
            String token = "";

            // 메시지를 읽어올 채널 ID
            String channelId = "";

            Slack slack = Slack.getInstance();

            try {
                ConversationsHistoryRequest request = ConversationsHistoryRequest.builder()
                        .token(token)
                        .channel(channelId)
                        .limit(2)
                        .build();

                ConversationsHistoryResponse response = slack.methods().conversationsHistory(request);

                if (response.isOk()) {
                    List<Message> messages = response.getMessages();
                    for (Message message : messages) {
                        System.out.println(message.getUser());
                        System.out.println(message.getText());
                        System.out.println(message.getTs());
                        System.out.println("-----------------------");
                    }
                } else {
                    System.err.println("Error: " + response.getError());
                }

            } catch (IOException | SlackApiException e) {
                e.printStackTrace();
            }
        }
    }