package com.kh.works.notice.service;

import com.kh.works.notice.dao.NoticeDao;
import com.kh.works.notice.vo.NoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao dao;

    public int write(NoticeVo vo) {

        int result = dao.write(vo);

        return result;
    }

    public List<NoticeVo> list() {
        return dao.list();
    }

    public NoticeVo detail(String noticeNo) {
      return dao.detail(noticeNo);
    }

    public int delete(String noticeNo) {
        return dao.delete(noticeNo);
    }

    public List<NoticeVo> empView() {
        return dao.empView();
    }

    public NoticeVo noticeDetail(String noticeNo) {

        NoticeVo vo = dao.noticeDetail(noticeNo);

        int no = Integer.parseInt(noticeNo);
        dao.updateViewCount(no);
        vo.setViewCount(vo.getViewCount()+1);

        return vo;
    }
}
