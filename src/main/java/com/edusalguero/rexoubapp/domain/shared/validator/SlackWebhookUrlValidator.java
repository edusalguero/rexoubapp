package com.edusalguero.rexoubapp.domain.shared.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlackWebhookUrlValidator implements ValidatorInterface<String> {
    private static final String WEBHOOK_URL =
            "/([a-zA-Z0-9-]+).slack.com.*services\\/(.*)/";

    private static Pattern pattern;

    static {
        pattern = Pattern.compile(WEBHOOK_URL);
    }
    @Override
    public boolean validate(String url) {
        Matcher matcher = pattern.matcher(url);
        return !matcher.matches();
    }
}
