package com.edusalguero.rexoubapp.infrastructure.workers;

import com.edusalguero.rexoubapp.application.monitor.HarvestServersMonitoringDataUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HarvestWorker implements Workable {
    private static final Logger logger = LoggerFactory.getLogger(HarvestWorker.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final HarvestServersMonitoringDataUseCase harvestServersMonitoringDataUseCase;

    @Autowired
    public HarvestWorker(HarvestServersMonitoringDataUseCase harvestServersMonitoringDataUseCase) {
        this.harvestServersMonitoringDataUseCase = harvestServersMonitoringDataUseCase;
    }

    @Override
    @Scheduled(fixedRate = 10000)
    @Transactional
    public void work() {
        logger.info("==> Harvesting monitoring data at {}...", dateFormat.format(new Date()));
        harvestServersMonitoringDataUseCase.execute();
        logger.info("==> Data harvested at {}...", dateFormat.format(new Date()));
    }
}
