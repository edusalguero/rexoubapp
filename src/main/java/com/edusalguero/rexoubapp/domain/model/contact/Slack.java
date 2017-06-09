package com.edusalguero.rexoubapp.domain.model.contact;

public class Slack {
    private String slackWebhookUrl;
    private String slackChannelOrUsername;

    public Slack(String slackWebhookUrl, String slackChannelOrUsername) {
        this.slackWebhookUrl = slackWebhookUrl;
        this.slackChannelOrUsername = slackChannelOrUsername;
    }

    public String getSlackWebhookUrl() {
        return slackWebhookUrl;
    }

    public String getSlackChannelOrUsername() {
        return slackChannelOrUsername;
    }
}