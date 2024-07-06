package com.kh.works.messenger.service;

import com.kh.works.employee.vo.EmployeeVo;
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

    public int read(int messenNo) {
        return dao.read(messenNo);
    }

    public MessengerVo getMessengerById(int messenNo) {
        return dao.getMessengerById(messenNo);
    }

    public List<MessengerVo> getImportantList(String empNo) {
        return dao.getImportantList(empNo);
    }

    public void importantStatus(int messenNo, String empNo) {
        dao.importantStatus(messenNo, empNo);
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


}

