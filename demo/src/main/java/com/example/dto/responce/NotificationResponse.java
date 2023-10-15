package com.example.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NotificationResponse
 * Describes Notification response DTO
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {
    private String response;
}
