package com.edusalguero.rexoubapp.application.report;

import com.edusalguero.rexoubapp.domain.model.monitor.Report;
import com.edusalguero.rexoubapp.domain.model.monitor.ReportRepository;
import com.edusalguero.rexoubapp.domain.model.user.User;
import com.edusalguero.rexoubapp.domain.model.user.UserId;
import com.edusalguero.rexoubapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserReportsUseCase {
    private final ReportRepository reportRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserReportsUseCase(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public ArrayList<ServerReportResponse> execute(UserId userId) {
        ArrayList<ServerReportResponse> reportResponses = new ArrayList<>();
        User user = userRepository.ofId(userId);
        Collection<Report> reports = reportRepository.ofUser(user);
        for (Report report : reports) {
            reportResponses.add(new ServerReportResponse(report));
        }
        return reportResponses;
    }
}
