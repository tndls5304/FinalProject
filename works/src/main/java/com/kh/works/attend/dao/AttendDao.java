package com.kh.works.attend.dao;

import com.kh.works.attend.mapper.AttendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AttendDao {

    private final AttendMapper mapper;

}
