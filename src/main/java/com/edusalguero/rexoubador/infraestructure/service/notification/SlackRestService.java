package com.edusalguero.rexoubador.infraestructure.service.notification;

import com.edusalguero.rexoubador.domain.service.notification.NotificationMessage;
import com.edusalguero.rexoubador.domain.service.notification.SlackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackRestService implements SlackService {

    private static final Logger logger = LoggerFactory.getLogger(SlackRestService.class);

    private RestTemplate restTemplate;

    public SlackRestService() {
        this.restTemplate = new RestTemplate();
    }

    private String encodeMessage(String message) {
        return message.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    @Override
    public void postMessage(String webhookUrl, String to, NotificationMessage notificationMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body;
        String slackMessage = notificationMessage.getMarkdownBody();
        if (to.startsWith("@")) {

            body = "{\"username\":\"" + to + "\",\"text\":\"" + slackMessage + "\"}";
        } else {
            body = "{\"channel\":\"" + to + "\",\"text\":\"" + slackMessage + "\"}";
        }
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        logger.info("Posting slack message: " + body);
        restTemplate.postForLocation(webhookUrl, entity);
        logger.info("Slack message posted");
    }

}
