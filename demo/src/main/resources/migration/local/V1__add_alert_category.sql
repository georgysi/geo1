use dev_alert;

drop table IF EXISTS ALERT_CATEGORY;

#1.ALERT_CATEGORY

create TABLE IF NOT EXISTS `ALERT_CATEGORY` (
    `ID`                     int         NOT NULL AUTO_INCREMENT,
    `ALERT_CATEGORY_NAME`    varchar(45) NOT NULL,
    `GENERAL_ALERT_ID`       varchar(36) NOT NULL,
    `CREATED_BY`             varchar(50) NOT NULL,
    `CREATION_TIMESTAMP`     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `MODIFIED_BY`            varchar(50)          DEFAULT NULL,
    `MODIFICATION_TIMESTAMP` timestamp   NULL     DEFAULT NULL,
    PRIMARY KEY (`ID`),
    INDEX `fk_GENERAL_ALERT_idx` (`GENERAL_ALERT_ID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;