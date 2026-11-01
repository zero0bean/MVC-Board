package com.studyhub.setup.repository;

import org.springframework.stereotype.Repository;

import com.studyhub.setup.mapper.DatabaseConnectionMapper;
import com.studyhub.setup.model.DatabaseConnectionInfo;

@Repository
public class DatabaseConnectionRepository {
    private final DatabaseConnectionMapper  databaseConnectionMapper;

    public DatabaseConnectionRepository(DatabaseConnectionMapper databaseConnectionMapper) {
        this.databaseConnectionMapper = databaseConnectionMapper;
    }

    public DatabaseConnectionInfo findConnectionInfo() {
        return databaseConnectionMapper.selectConnectionInfo();
    }
}
