package com.edusalguero.rexoubapp.domain.model.monitor;

import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.user.User;

import java.util.Collection;


public interface ReportRepository {

    Report ofId(ReportId reportId);

    Collection<Report> ofUser(User user);

    Collection<Report> ofServer(Server server);

    ReportId nextIdentity();

    void save(Report report);
}
