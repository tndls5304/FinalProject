package com.kh.works.messenger.dao;

import com.kh.works.messenger.mapper.MessengerMapper;
import com.kh.works.messenger.vo.MessengerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessengerDao {

    private final MessengerMapper mapper;

    public int write(MessengerVo vo) {
        return mapper.write(vo);
    }

    public List<MessengerVo> getEmployeeList() {
        return mapper.getEmployeeList();
    }

    public List<MessengerVo> getMessengerList() {
        return mapper.getMessengerList();
    }

    public List<MessengerVo> getReceivedList() {
        return mapper.getReceivedList();
    }

    public List<MessengerVo> getSentList() {
        return mapper.getSentList();
    }

    public List<MessengerVo> getUnreadList() {
        return mapper.getUnreadList();
    }

    public int read(int messenNo) {
        return mapper.read(messenNo);
    }


}

