package com.yjt.frame.db.tables;


import com.yjt.frame.bean.ACBean;

import java.util.List;

public interface ACDao {
    /***
     * 插入一个账号信息
     * @param ac
     */
    void insertAC(ACBean ac);
    /**
     * 删除一个账号
     * @param id
     * @return void
     * @author yujiangtao
     * @date 2016-1-24 下午14:46:18
     */
     void deleteAC(int id);
    /**
     * 更新一个账号信息
     * @param ac
     * @return void
     * @author yujiangtao
     * @date 2016-1-24 下午14:46:18
     */
    void updateAC(ACBean ac);

    /**
     * 得到账号信息列表
     * @return List<ACBean>
     * @author yujiangtao
     * @date 2016-1-24 下午14:46:18
     */
    List<ACBean> getACList();
    /**
     * 账号信息是否存在
     * @param ac
     * @return
     * @return boolean
     * @author yujiangtao
     * @date 2016-1-24 下午14:46:18
     */
    boolean isExists(ACBean ac);
    /**
     * 情况所有账号信息
     * @return
     * @author yujiangtao
     * @date 2016-1-24 下午14:46:18
     */
    void clear();

}
