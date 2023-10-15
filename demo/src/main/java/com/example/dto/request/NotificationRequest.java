package com.example.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * NotificationRequest
 * Describes Notification request DTO
 *
 * @author Binyamin (Dima) Pyanin
 * @version POC
 * @since April 24,2023
 */
@Data
@Builder
public class NotificationRequest {
    @NotEmpty
    @NotNull
    private String url;
    @NotEmpty
    @NotNull
    private String message;
}