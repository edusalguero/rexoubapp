package com.edusalguero.rexoubador.domain.monitor.harvester;

import com.edusalguero.rexoubador.domain.user.User;

import java.util.Collection;


public interface HarvesterRepository {

    Harvester ofId(HarvesterId harvesterId);

    Collection<Harvester> ofUser(User user);

    HarvesterId nextIdentity();

    void update(Harvester harvester);

}
