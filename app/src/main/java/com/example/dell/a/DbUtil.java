package com.example.dell.a;

import com.example.dell.a.bean.DbBean;
import com.example.dell.a.dao.DaoMaster;
import com.example.dell.a.dao.DaoSession;
import com.example.dell.a.dao.DbBeanDao;

import java.util.List;

/**
 * Created by DELL on 2019/9/20.
 */

public class DbUtil {
    private static DbUtil dbUtil;
    private final DbBeanDao dbBeanDao;

    private DbUtil(){
        // 创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getApp(), "shu.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        dbBeanDao = daoSession.getDbBeanDao();
    }
    public static DbUtil getDbUtil(){
        if (dbUtil == null){
            synchronized (DbUtil.class){
                if (dbUtil == null){
                    dbUtil = new DbUtil();
                }
            }
        }
        return dbUtil;
    }
    // 插入
    public long insert(DbBean dbBean){
        if (!isInserted(dbBean)){
            long l = dbBeanDao.insertOrReplace(dbBean);
            return l;
        }
        return -1;
    }
    // 删除
    public boolean deltet(DbBean dbBean){
        if (isInserted(dbBean)){
            dbBeanDao.delete(dbBean);
            return true;
        }
        return false;
    }
    private boolean isInserted(DbBean dbBean) {
        List<DbBean> list = dbBeanDao.queryBuilder().where(DbBeanDao.Properties.Title.eq(dbBean.getTitle())).list();
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public List<DbBean> query(){
        List<DbBean> list = dbBeanDao.queryBuilder().list();
        return list;
    }
}
