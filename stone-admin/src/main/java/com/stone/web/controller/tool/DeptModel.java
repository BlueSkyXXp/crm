package com.stone.web.controller.tool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptModel {

    private String id;

    private String ardeptName;

    private Integer order;

    private String parentId;
}
