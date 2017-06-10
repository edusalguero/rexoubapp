package com.edusalguero.rexoubapp.domain.model.contact;

import com.edusalguero.rexoubapp.domain.shared.ValidationException;
import com.edusalguero.rexoubapp.domain.shared.validator.SlackWebhookUrlValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;

@Embeddable
public class Slack {
    private String slackWebhookUrl;
    private String slackChannelOrUsername;

    protected Slack()
    {

    }
    public Slack(String slackWebhookUrl, String slackChannelOrUsername) {
        setSlackWebhookUrl(slackWebhookUrl);
        this.slackChannelOrUsername = slackChannelOrUsername;
    }

    private void setSlackWebhookUrl(String slackWebhookUrl) {
        SlackWebhookUrlValidator slackWebhookUrlValidator = new SlackWebhookUrlValidator();
        if (!slackWebhookUrl.isEmpty() && !slackWebhookUrlValidator.validate(slackWebhookUrl)) {
            throw new ValidationException(String.format("%s is not a valid Slack Webhook URL", slackWebhookUrl));
        }
        this.slackWebhookUrl = slackWebhookUrl;
    }
    public String getSlackWebhookUrl() {
        return slackWebhookUrl;
    }

    public String getSlackChannelOrUsername() {
        return slackChannelOrUsername;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return slackWebhookUrl.isEmpty() || slackChannelOrUsername.isEmpty();
    }
}