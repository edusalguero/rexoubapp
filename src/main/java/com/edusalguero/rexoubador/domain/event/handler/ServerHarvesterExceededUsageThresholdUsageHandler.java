package com.edusalguero.rexoubador.domain.event.handler;

import com.edusalguero.rexoubador.domain.event.ServerHarvesterExceededUsageThresholdUsage;
import com.edusalguero.rexoubador.domain.event.ThresholdExceededUsageType;
import com.edusalguero.rexoubador.domain.model.event.EventRepository;
import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.server.harvester.ServerHarvester;
import com.edusalguero.rexoubador.domain.model.user.User;
import com.edusalguero.rexoubador.domain.model.user.UserRepository;
import com.edusalguero.rexoubador.domain.service.notification.EmailService;
import com.edusalguero.rexoubador.domain.service.notification.EventMessage;
import com.edusalguero.rexoubador.domain.service.notification.SlackService;
import com.edusalguero.rexoubador.domain.shared.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServerHarvesterExceededUsageThresholdUsageHandler extends ServerEventHandler implements EventHandler<ServerHarvesterExceededUsageThresholdUsage> {

    @Autowired
    public ServerHarvesterExceededUsageThresholdUsageHandler(UserRepository userRepository, EventRepository eventRepository, EmailService emailService, SlackService slackService) {
        super(userRepository, eventRepository, emailService, slackService);
    }

    @Override
    public void handle(ServerHarvesterExceededUsageThresholdUsage event) {
        User user = userRepository.ofId(event.getUserId());
        Server server = user.server(event.getServerId());
        ServerHarvester serverHarvester = server.harvester(event.getServerHarvesterId());
        String type = event.getType() == ThresholdExceededUsageType.ALERT ? "ALERT" : "WARNING";
        Boolean notify = event.getType() == ThresholdExceededUsageType.ALERT && serverHarvester.notifyAlert()
                || event.getType() == ThresholdExceededUsageType.WARNING && serverHarvester.notifyWarning();
        String body = "Harvester [" + serverHarvester.harvester().label() + "] exceeds " + type + " value (" + event.getExceededValue() + "% usage) in server [" + server.label() + "/ " + server.ip() + "]";
        EventMessage notificationMessage = new EventMessage("Harvest threshold " + event.getType() + " value exceeded", body, event.occurredOn());
        notificationMessage.addExtraData("Harvested data", serverHarvester.getLastHarvest().getData());
        createAndNotifyEvent(user, server, notificationMessage, notify);
    }
}
