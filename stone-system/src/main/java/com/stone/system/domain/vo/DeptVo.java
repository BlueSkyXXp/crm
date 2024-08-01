package com.stone.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeptVo {
    /** 用户ID */
    private Long deptId;

    public DeptVo(Long deptId) {
        this.deptId = deptId;
    }

    public DeptVo() {
    }
}
