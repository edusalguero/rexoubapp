package com.edusalguero.rexoubador.domain.model.monitor;

import com.edusalguero.rexoubador.domain.model.server.Server;
import com.edusalguero.rexoubador.domain.model.user.User;

import java.util.Collection;


public interface ReportRepository {

    Report ofId(ReportId reportId);

    Collection<Report> ofUser(User user);

    Collection<Report> ofServer(Server server);

    ReportId nextIdentity();

    void  save(Report report);
}
