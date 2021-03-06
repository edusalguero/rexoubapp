package com.edusalguero.rexoubapp.domain.model.monitor.harvester;

import com.edusalguero.rexoubapp.domain.model.user.User;

import java.util.Collection;


public interface HarvesterRepository {

    Harvester ofId(HarvesterId harvesterId);

    Collection<Harvester> ofUser(User user);

    HarvesterId nextIdentity();

    void update(Harvester harvester);

}
