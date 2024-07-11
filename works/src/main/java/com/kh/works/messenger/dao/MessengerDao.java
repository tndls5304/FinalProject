package com.kh.works.messenger.dao;

import com.kh.works.alarm.vo.AlarmVo;
import com.kh.works.employee.vo.EmployeeVo;
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

    public List<EmployeeVo> getEmployeeList() {
        return mapper.getEmployeeList();
    }

    public List<MessengerVo> getMessengerList(String senderEmpNo, String receiverEmpNo) {
        return mapper.getMessengerList(senderEmpNo, receiverEmpNo);
    }

    public List<MessengerVo> getReceivedList(String receiverEmpNo) {
        return mapper.getReceivedList(receiverEmpNo);
    }

    public List<MessengerVo> getSentList(String senderEmpNo) {
        return mapper.getSentList(senderEmpNo);
    }

    public List<MessengerVo> getDetailPage(String messenNo) {
        return mapper.getDetailPage(messenNo);
    }

    public List<MessengerVo> getUnreadList(String receiverEmpNo) {
        return mapper.getUnreadList(receiverEmpNo);
    }

    public int getUnreadCount(String receiverEmpNo) {
        return mapper.getUnreadCount(receiverEmpNo);
    }

    public int read(int messenNo) {
        return mapper.read(messenNo);
    }

    public MessengerVo getMessengerById(int messenNo) {
        return mapper.getMessengerById(messenNo);
    }

    public List<MessengerVo> getImportantList(String empNo) {
        return mapper.getImportantList(empNo);
    }

    public int importantStatus(int messenNo, String empNo) {
        return mapper.importantStatus(messenNo, empNo);
    }


    //-----------------------------------기존 코드------------------------------------
//    public List<MessengerVo> getImportantList(String receiverEmpNo, String senderEmpNo) {
//        return mapper.getImportantList(receiverEmpNo, senderEmpNo);
//    }
//
//    public int importantStatus(int messenNo) {
//        return mapper.importantStatus(messenNo);
//    }

    public List<MessengerVo> searchByKeyword(String keyWord, String empNo) {
        return mapper.searchByKeyword(keyWord, empNo);
    }

    public List<MessengerVo> trash(String empNo) {
        return mapper.trash(empNo);
    }

    public void trashMessen(int messenNo, String empNo) {
        mapper.trashMessen(messenNo, empNo);
    }

    public void deleteStatus(int messenNo, String empNo) {
        mapper.deleteStatus(messenNo, empNo);
    }

    public void deleteMessen(int messenNo, String empNo) {
        mapper.deleteMessen(messenNo, empNo);
    }


    //알림 기능 - Socket을 사용하기 위한 Dao
    public List<AlarmVo> getAlarmInfor(String receiverEmpNo) {
        return mapper.getAlarmInfor(receiverEmpNo);
    }

//    public void readAlarm(String receiverEmpNo) {
//        mapper.readAlarm(receiverEmpNo);
//    }

    public void saveAlarm(String receiverEmpNo, String notificationMessage) {
        mapper.saveAlarm(receiverEmpNo, notificationMessage);
    }


    public int readAlarm(int alarmNo) {
        return mapper.readAlarm(alarmNo);
    }
}

