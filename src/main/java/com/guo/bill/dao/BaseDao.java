package com.guo.bill.dao;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: wan
 * Date: 14-9-3
 * Time: 上午1:15
 * To change this template use File | Settings | File Templates.
 */
public class BaseDao extends SqlMapClientTemplate {
    public void isUpdateSuccess(int updateNum,String tableName) {
        if(updateNum ==0) {
            throw new RuntimeException("更新表:"+tableName+",失败");
        }
    }
}
