package com.stone.common.utils;

import cn.hutool.db.Db;
import com.stone.common.utils.spring.SpringUtils;

import javax.sql.DataSource;

public class Sdb {
    public static Db use() {
        DataSource dataSource = SpringUtils.getBean("masterDataSource");
        return Db.use(dataSource);
    }

    public static Db use(String datasource) {
        DataSource dataSource = SpringUtils.getBean(datasource);
        return Db.use(dataSource);
    }

}
