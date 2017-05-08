package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.contact.*;
import com.edusalguero.rexoubador.domain.shared.Status;
import com.edusalguero.rexoubador.domain.model.contact.ContactId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/contacts", produces = "application/json")
public class ContactController extends AuthenticatedUserController {

    @Autowired
    private UserContactsUseCase userContactsUseCase;

    @Autowired
    private ContactInformationUseCase contactInformationUseCase;

    @Autowired
    private ContactCreateUseCase contactCreateUseCase;

    @Autowired
    private ContactDeleteUseCase contactDeleteUseCase;

    @Autowired
    private ContactUpdateUseCase contactUpdateUseCase;

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<ContactResponse> list() {
        return userContactsUseCase.execute(getAuthenticatedUserId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ContactId add(@RequestParam(value = "email", required = false) String email,
                         @RequestParam(value = "slackWebhookUrl", required = false) String slackWebhookUrl,
                         @RequestParam(value = "slackChannelOrUsername", required = false) String slackChannelOrUsername,
                         @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status) {
        ContactCreateRequest contactRequest = new ContactCreateRequest(getAuthenticatedUserId(), email, slackWebhookUrl, slackChannelOrUsername, status);
        return contactCreateUseCase.execute(contactRequest);
    }

    @RequestMapping(path = "/{contactId}", method = RequestMethod.GET)
    public ContactResponse view(@PathVariable String contactId
    ) {
        return contactInformationUseCase.execute(new ContactId(contactId), getAuthenticatedUserId());
    }

    @RequestMapping(path = "/{contactId}", method = RequestMethod.PUT)
    public void update(@PathVariable String contactId,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "slackWebhookUrl") String slackWebhookUrl,
                       @RequestParam(value = "slackChannelOrUsername") String slackChannelOrUsername,
                       @RequestParam(value = "status", required = false, defaultValue = "ENABLED") Status status
    ) {
        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest(getAuthenticatedUserId(), new ContactId(contactId), email, slackWebhookUrl, slackChannelOrUsername, status);
        contactUpdateUseCase.execute(contactUpdateRequest);
    }

    @RequestMapping(path = "/{contactId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String contactId) {

        contactDeleteUseCase.execute(new ContactId(contactId), getAuthenticatedUserId());
    }

}
