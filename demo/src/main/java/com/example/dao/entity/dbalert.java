package com.example.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ALERT_CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class dbalert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ALERT_CATEGORY_NAME", nullable = false)
    private String alertCategoryName;
    @Column(name = "GENERAL_ALERT_ID", nullable = false)
    private String generalAlertId;
    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name = "CREATION_TIMESTAMP", nullable = false)
    private Timestamp creationTimestamp;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFICATION_TIMESTAMP")
    private Timestamp modificationTimestamp;


}

