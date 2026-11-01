package com.studyhub.setup.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseConnectionInfo {
    
    public static final DateTimeFormatter DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String databaseName;
    private String databaseUser;
    private String serverVersion;
    private LocalDateTime checkedAt;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    public String getCheckedAtText() {
        return checkedAt == null ? "" : checkedAt.format(DATE_TIME_FORMATTER);
    }
}
