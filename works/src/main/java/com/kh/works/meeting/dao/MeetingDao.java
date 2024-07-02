package com.kh.works.meeting.dao;

import com.kh.works.meeting.mapper.MeetingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MeetingDao {

    private final MeetingMapper mapper;
}
