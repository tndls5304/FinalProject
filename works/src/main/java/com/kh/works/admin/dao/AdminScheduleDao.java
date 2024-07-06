package com.kh.works.admin.dao;

import com.kh.works.admin.mapper.AdminScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminScheduleDao {
    private final AdminScheduleMapper mapper;
}
