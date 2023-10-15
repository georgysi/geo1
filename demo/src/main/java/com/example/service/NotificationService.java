package com.example.service;

import com.example.util.FlowType;

import com.example.dto.responce.NotificationResponse;
import com.example.dto.request.NotificationRequest;

/**
 * NotificationService
 * Describes Notification Service contract
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
public interface NotificationService {

    NotificationResponse notify(String traceabilityId, FlowType flowType, NotificationRequest notificationRequest);

}
