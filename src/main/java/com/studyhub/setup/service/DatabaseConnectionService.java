package com.studyhub.setup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyhub.setup.repository.DatabaseConnectionRepository;
import com.studyhub.setup.model.DatabaseConnectionInfo;

@Service
public class DatabaseConnectionService {
    
    private final DatabaseConnectionRepository databaseConnectionRepository;

    public DatabaseConnectionService(DatabaseConnectionRepository databaseConnectionRepository) {
        this.databaseConnectionRepository = databaseConnectionRepository;
    }

    @Transactional(readOnly = true) 
    public DatabaseConnectionInfo checkConnection() {
        return databaseConnectionRepository.findConnectionInfo();
    }
}
