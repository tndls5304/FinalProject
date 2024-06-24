package com.kh.works.messenger.service;

import com.kh.works.messenger.dao.MessengerDao;
import com.kh.works.messenger.vo.MessengerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessengerService {

    private final MessengerDao dao;

    public int write(MessengerVo vo) {
        return dao.write(vo);
    }

    public List<MessengerVo> getMessengerList() {
        return dao.getMessengerList();
    }

    public List<MessengerVo> getReceivedList() {
        return dao.getReceivedList();
    }

    public List<MessengerVo> getSentList() {
        return dao.getSentList();
    }

    public List<MessengerVo> getUnreadList() {
        return dao.getUnreadList();
    }

    public int markAsRead(int messenNo) {
        return dao.markAsRead(messenNo);
    }
}

