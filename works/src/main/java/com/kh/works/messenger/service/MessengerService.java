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

    //기존 쪽지 작성하기 + 알림 기능 - Socket을 사용하기 위한 Service
    public int write(MessengerVo vo) {
//        기존 코드
//        return dao.write(vo);

//        알림 소켓 추가
        int result = dao.write(vo);
        if (result == 1) {
            String notificationMessage = "새로운 쪽지가 도착했습니다. \n" + vo.getSenderName() + " 님이 쪽지를 보냈습니다.";
            //알림을 저장하는 메서드
            dao.saveAlarm(vo.getReceiverEmpNo(), notificationMessage);
            //NotificationHandler.java 파일 확인하기
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


    //부모 테이블인 MESSENGER, 자식 테이블인 MESSENGER_STATUS에서 모두 삭제해야 하기 때문에,
    //query문을 2개 날렸다 > Transactional 애노테이션 사용
    @Transactional
    public void delete(List<Integer> messenNoList, String empNo) {
        for(int messenNo : messenNoList){
            dao.deleteStatus(messenNo, empNo);
            dao.deleteMessen(messenNo, empNo);
        }
    }

    //알림 기능 - Socket을 사용하기 위한 Service
    public List<AlarmVo> getAlarmInfor(String receiverEmpNo) {
        return dao.getAlarmInfor(receiverEmpNo);
    }

    public void readAlarm(String receiverEmpNo) {
        dao.readAlarm(receiverEmpNo);
    }
}

