package com.kh.works.admin.vo;

import lombok.Data;

@Data
public class SubAdminMenuVo {
    private String  menuNo;
    private String  menuName;
    private String  authSelectYn;
    private String  authInsertYn;
    private String  authModifyYn;
    private String  authRemoveYn;
}
