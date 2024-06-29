package com.kh.works.notice.dao;

import com.kh.works.notice.mapper.NoticeMapper;
import com.kh.works.notice.vo.NoticeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDao {

    private final NoticeMapper mapper;

    public int write(NoticeVo vo) {
        return mapper.write(vo);
    }

    public List<NoticeVo> list() {
        return mapper.list();
    }

    public NoticeVo detail(String noticeNo) {
        return mapper.detail(noticeNo);
    }

    public int delete(String noticeNo) {
        return mapper.delete(noticeNo);
    }
}
