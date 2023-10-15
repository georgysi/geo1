package com.example.service;
import com.example.dto.responce.AlertCategoryResponse;

import java.util.List;

/**
 * AlertService
 * Describes Alert Service contract
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
public interface AlertService {

    AlertCategoryResponse getAlertById(Integer alertId);

    List<AlertCategoryResponse> getAllAlerts();

}

