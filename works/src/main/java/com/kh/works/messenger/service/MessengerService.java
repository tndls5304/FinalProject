package com.kh.works.messenger.service;

import com.kh.works.alarm.vo.AlarmVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.handler.NotificationHandler;
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
//    알림 소켓 추가
    private final NotificationHandler notificationHandler;

    public int write(MessengerVo vo) {
//        기존 코드
//        return dao.write(vo);

//        알림 소켓 추가
        int result = dao.write(vo);

        if (result == 1) {
            String notificationMessage = "새로운 쪽지가 도착했습니다. 보내는 사람: " + vo.getSenderEmpNo();
            dao.saveAlarm(vo.getReceiverEmpNo(), notificationMessage);
            notificationHandler.sendNotification(notificationMessage);
        }

        return result;

    }

    public List<EmployeeVo> getEmployeeList() {
        return dao.getEmployeeList();
    }

    public List<MessengerVo> getMessengerList(String senderEmpNo, String receiverEmpNo) {
        return dao.getMessengerList(senderEmpNo, receiverEmpNo);
    }

    public List<MessengerVo> getReceivedList(String receiverEmpNo) {
        return dao.getReceivedList(receiverEmpNo);
    }

    public List<MessengerVo> getSentList(String senderEmpNo) {
        return dao.getSentList(senderEmpNo);
    }

    public List<MessengerVo> getDetailPage(String messenNO) {
        return dao.getDetailPage(messenNO);
    }

    public List<MessengerVo> getUnreadList(String receiverEmpNo) {
        return dao.getUnreadList(receiverEmpNo);
    }

    public int getUnreadCount(String receiverEmpNo) {
        return dao.getUnreadCount(receiverEmpNo);
    }

    public int read(int messenNo) {
        return dao.read(messenNo);
    }

    public MessengerVo getMessengerById(int messenNo) {
        return dao.getMessengerById(messenNo);
    }

    public List<MessengerVo> getImportantList(String empNo) {
        return dao.getImportantList(empNo);
    }

    public int importantStatus(int messenNo, String empNo) {
        return dao.importantStatus(messenNo, empNo);
    }


    //----------------------------------기존 코드------------------------------------------
//    public List<MessengerVo> getImportantList(String receiverEmpNo, String senderEmpNo) {
//        return dao.getImportantList(receiverEmpNo, senderEmpNo);
//    }
//
//    public int importantStatus(int messenNo) {
//        return dao.importantStatus(messenNo);
//    }

    public List<MessengerVo> searchByKeyword(String keyWord, String empNo) {
        return dao.searchByKeyword(keyWord, empNo);
    }

    public List<MessengerVo> trash(String empNo) {
        return dao.trash(empNo);
    }

    public void trashMessen(List<Integer> messenNoList, String empNo) {
        for(int messenNo : messenNoList){
            dao.trashMessen(messenNo, empNo);
        }
    }


    @Transactional
    public void delete(List<Integer> messenNoList, String empNo) {
        for(int messenNo : messenNoList){
            dao.deleteStatus(messenNo, empNo);
            dao.deleteMessen(messenNo, empNo);
        }
    }


    public List<AlarmVo> getAlarmInfor(String receiverEmpNo) {
        return dao.getAlarmInfor(receiverEmpNo);
    }

    public void readAlarm(String receiverEmpNo) {
        dao.readAlarm(receiverEmpNo);
    }
}

