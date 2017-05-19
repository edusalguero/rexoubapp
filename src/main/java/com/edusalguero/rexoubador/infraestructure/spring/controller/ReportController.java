package com.edusalguero.rexoubador.infraestructure.spring.controller;

import com.edusalguero.rexoubador.application.report.ServerReportResponse;
import com.edusalguero.rexoubador.application.report.ServerReportsUseCase;
import com.edusalguero.rexoubador.application.report.UserReportsUseCase;
import com.edusalguero.rexoubador.domain.model.server.ServerId;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/v1/reports", produces = "application/json")
@Api(description="Authenticated user reports queryfier")
public class ReportController extends AuthenticatedUserController {

    private final ServerReportsUseCase serverReportsUseCase;

    private final UserReportsUseCase userReportsUseCase;

    @Autowired
    public ReportController(ServerReportsUseCase serverReportsUseCase, UserReportsUseCase userReportsUseCase) {
        this.serverReportsUseCase = serverReportsUseCase;
        this.userReportsUseCase = userReportsUseCase;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<ServerReportResponse> ofUser() {
        return userReportsUseCase.execute(getAuthenticatedUserId());
    }

    @RequestMapping(path = "/{serverId}", method = RequestMethod.GET)
    public Collection<ServerReportResponse> ofServer(@PathVariable String serverId) {
        return serverReportsUseCase.execute(getAuthenticatedUserId(), new ServerId(serverId));
    }
}
