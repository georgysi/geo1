package com.example.service.impl;

import com.example.converter.EntityToDtoConverter;
import com.example.dao.entity.dbalert;
import com.example.dto.responce.AlertCategoryResponse;
import com.example.repository.dbalertRepository;
import com.example.service.AlertService;
import com.example.util.AlertConstants;
import com.example.util.AlertsFormatUtils;
import com.example.util.FlowType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AlertServiceImpl implements AlertService {

    private static final String ALERT_NOT_FOUND = "Alert {} not found";
    @Autowired
    private dbalertRepository alertRepository;

    @Autowired
    private EntityToDtoConverter entityToDtoConverter;
    @Override

    public AlertCategoryResponse getAlertById(Integer alertId) {
        dbalert alert = searchAlert(alertId);

        AlertCategoryResponse alertResponse =
                this.entityToDtoConverter.convertAlertToAlertResponse(alert);
        return alertResponse;

    }

    @Override
    public List<AlertCategoryResponse> getAllAlerts() {
        AlertsFormatUtils.printFlowSeparator(true, FlowType.GET_ALL_ALERTS);
        System.out.print("GEORGY IMPL******************************");
        log.info("in getAllAlerts");
        Iterator<dbalert> alerts = this.alertRepository.findAll().iterator();


        if (!alerts.hasNext()) {

           log.error("Alerts Not Present in DB");
            throw new EntityNotFoundException("Alerts " + AlertConstants.NOT_FOUND);
        }

        List<AlertCategoryResponse> result = new ArrayList<>();



        while (alerts.hasNext()) {
            final dbalert alert = alerts.next();
            result.add(this.entityToDtoConverter.convertAlertToAlertResponse(alert));
        }


        AlertsFormatUtils.printFlowSeparator(false, FlowType.GET_ALL_ALERTS);

        return result;

    }


    private dbalert searchAlert(Integer id) {
        log.info("Verifying Alert {}", id);

        Optional<dbalert> alertOptional = this.alertRepository.findById(id);

        if (alertOptional.isPresent()) {
            dbalert existingAlert = alertOptional.get();

            log.info("Found Alert {}", existingAlert.getId());

            return alertOptional.get();
        }

        log.error("ALERT_NOT_FOUND", id);
        throw new EntityNotFoundException(" Get Alert " + id + AlertConstants.NOT_FOUND);
    }
}
