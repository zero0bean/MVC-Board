package com.studyhub.setup.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.studyhub.setup.model.DatabaseConnectionInfo;

@Mapper
public interface DatabaseConnectionMapper {
    DatabaseConnectionInfo selectConnectionInfo();
}
