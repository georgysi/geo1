package com.example.service.impl;

import com.example.util.AlertsFormatUtils;
import com.example.util.FlowType;
//import ca.tamnoon.dao.util.OperationStatus;
//import ca.tamnoon.dao.util.TaskStatus;
//import com.example.util.TaskType;
import com.example.dto.request.NotificationRequest;
import com.example.dto.responce.NotificationResponse;
//import ca.tamnoon.service.AuditHistoryService;
import com.example.service.NotificationService;

//import ca.tamnoon.util.AlertsFormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

/**
 * DefaultNotificationServiceImpl
 * Describes Default Notification Service contract implementation
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private AuditHistoryService auditHistoryService;

    @Override
    public NotificationResponse notify(String traceabilityId, FlowType flowType, NotificationRequest notificationRequest) {

        NotificationResponse notificationResponse = new NotificationResponse();

        StringBuilder result = new StringBuilder();

        final String errorMsg = validateNotificationRequest(traceabilityId, flowType, notificationRequest);

        if (!errorMsg.isEmpty()) {
            notificationResponse.setResponse(errorMsg);
        } else {
            result.append("Callback Result <");
            log.info("R   E   S   U   L   T  %%%%%%%%%%%%%%");
            log.error("R   E   S   U   L   T  %%%%%%%%%%%%%% ERRROOOOOOOOOOOOR");
            if (null != notificationRequest.getUrl() && !notificationRequest.getUrl().isEmpty()) {
                result.append(
                        callback(traceabilityId, flowType, notificationRequest)
                );
            } else {
                result.append("Skipped Callback Notification");
            }
            result.append("> ");

            notificationResponse.setResponse(result.toString());
        }

        log.info("{}", notificationResponse);

        return notificationResponse;
    }

    private String validateNotificationRequest(String traceabilityId, FlowType flowType, NotificationRequest notificationRequest) {

        String errorMsg = StringUtils.EMPTY;

        if ((null == notificationRequest.getUrl() || notificationRequest.getUrl().isEmpty())) {
            errorMsg = "Unable To Deliver Notification : Invalid Callback Url Or Email " + notificationRequest;
            log.error(errorMsg);

          /*  auditHistoryService.audit(
                    traceabilityId,
                    flowType,
                    TaskType.NOTIFICATION,
                    OperationStatus.FAILED,
                    TaskStatus.FAILURE,
                    errorMsg); */
        }

        return errorMsg;
    }

    private String callback(String traceabilityId, FlowType flowType, NotificationRequest notificationRequest) {
        final String startMessage = "Attempting To Deliver Callback : " + notificationRequest;
        log.info(startMessage);

       /* auditHistoryService.audit(
                traceabilityId,
                flowType,
                TaskType.NOTIFICATION,
                OperationStatus.STARTED,
                TaskStatus.SUCCESS,
                startMessage); */

        HttpEntity<?> requestEntity =
                new HttpEntity<>(notificationRequest.getMessage(), AlertsFormatUtils.buildHttpHeaders(traceabilityId));

        ResponseEntity<String> response;
        try {
            //Validate url
            URL url = new URL(notificationRequest.getUrl());
            log.debug("Url is valid {}", url);
            log.info("Url is valid ------------------------{}", url);

            log.debug("<<<<<<<<============ Notification request body {}", requestEntity.getBody());
            response = restTemplate.exchange(
                    notificationRequest.getUrl(),
                    HttpMethod.POST,
                    requestEntity,
                    String.class);

            log.info("EXCHANGE SUCEEDED +++++++++++++++++++++", url);
            log.debug(">>>>>>>>============ Notification response body {}", response.getBody());

        } catch (Exception e) {
            final String errorMessage = "Notification Callback Attempt Failed : " + e.getLocalizedMessage();

            /*auditHistoryService.audit(
                    traceabilityId,
                    flowType,
                    TaskType.NOTIFICATION,
                    OperationStatus.FAILED,
                    TaskStatus.FAILURE,
                    errorMessage);*/

            log.error(errorMessage);

            return errorMessage;
        }

        final String successMsg = "Delivered Callback Notification : " + response.getBody();
        log.info(successMsg);

        /*auditHistoryService.audit(
                traceabilityId,
                flowType,
                TaskType.NOTIFICATION,
                OperationStatus.COMPLETED,
                TaskStatus.SUCCESS,
                successMsg);*/

        return successMsg;
    }

}