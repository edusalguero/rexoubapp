package com.edusalguero.rexoubapp.infrastructure.persistence.mongo;

import com.edusalguero.rexoubapp.domain.model.monitor.Report;
import com.edusalguero.rexoubapp.domain.model.monitor.ReportId;
import com.edusalguero.rexoubapp.domain.model.monitor.ReportRepository;
import com.edusalguero.rexoubapp.domain.model.server.Server;
import com.edusalguero.rexoubapp.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public class ReportRepositoryMongo implements ReportRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Report ofId(ReportId reportId) {
        return mongoTemplate.findById(reportId.getId(), Report.class);
    }

    @Override
    public Collection<Report> ofUser(User user) {
        Criteria criteria = Criteria.where("user.id").is(user.id());
        return mongoTemplate.find(Query.query(criteria), Report.class);
    }

    @Override
    public Collection<Report> ofServer(Server server) {
        Criteria criteria = Criteria.where("server.id").is(server.id());
        return mongoTemplate.find(Query.query(criteria), Report.class);
    }

    @Override
    public ReportId nextIdentity() {
        final String random = UUID.randomUUID().toString().toUpperCase();
        return new ReportId(random);
    }

    public void save(Report report) {
        mongoTemplate.save(report);
    }
}
