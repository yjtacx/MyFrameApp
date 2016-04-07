package com.yjt.frame.db.tables;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yjt.frame.app.MyApplication;
import com.yjt.frame.bean.ACBean;
import com.yjt.frame.db.DBConstant;
import com.yjt.frame.db.DBHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yujiangtao on 2016/1/24.
 */
public class ACDaoImpl implements ACDao {
    private DBHelper mHelper = null;

    public ACDaoImpl(Context context) {
        mHelper = DBHelper.getInstance(context);
    }

    private final static short TYPE_ADD = 0X01;
    private final static short TYPE_DELETE = 0X02;
    private final static short TYPE_UPDATE = 0X03;

    public interface DatabaseChangeListener {
        void insert(ACBean ac);

        void delete(int p);

        void update(ACBean ab, int p);

    }

    private DatabaseChangeListener databaseChangeListener = null;

    public void setDataBaseChangeListener(DatabaseChangeListener listener) {
        this.databaseChangeListener = listener;
    }

    private void notifyDatabaseChange(short type, ACBean ac, int p) {
        for (DatabaseChangeListener listener : MyApplication.instance.sets
                ) {
            switch (type) {
                case TYPE_ADD:
                    listener.insert(ac);
                    break;
                case TYPE_DELETE:
                    listener.delete(p);
                    break;
                case TYPE_UPDATE:
                    listener.update(ac, p);
                    break;
            }
        }
    }
    public int insertACList(List<ACBean> mlist){
        int number=0;
        StringBuilder sqlbuilder = new StringBuilder();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        sqlbuilder.append("insert into ")
                .append(DBConstant.TABLE_NAME_AC)
                .append("(")
                .append(DBConstant.COLOUM_AC_NAME).append(",")
                .append(DBConstant.COLOUM_AC_ACCOUNT).append(",")
                .append(DBConstant.COLOUM_AC_PWD).append(",")
                .append(DBConstant.COLOUM_AC_EMAIL).append(",")
                .append(DBConstant.COLOUM_AC_TYPEID).append(",")
                .append(DBConstant.COLOUM_AC_REMARK).append(",")
                .append(DBConstant.COLOUM_AC_CREATETIME).append(",")
                .append(DBConstant.COLOUM_AC_MODIFYTIME)
                .append(")values(?,?,?,?,?,?,?,?)");
        db.beginTransaction();
        for (ACBean ac : mlist) {
            //循环所要插入的数据
            if(isExistsNotClose(ac))continue;
            number++;
            db.execSQL(sqlbuilder.toString(),
                    new Object[]{
                            ac.getName(),
                            ac.getAccount(),
                             ac.getAcpwd(),
                            ac.getEmail(),
                            ac.getTypeid(),
                            ac.getRemark(),
                            ac.getCreatetime(),
                            ac.getModifytime()});
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return number;
    }

    @Override
    public void insertAC(ACBean ac) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long creattime = System.currentTimeMillis();
        ac.setCreatetime(creattime + "");
        ac.setModifytime(creattime + "");
        StringBuilder sqlbuilder = new StringBuilder();
        sqlbuilder.append("insert into ")
                .append(DBConstant.TABLE_NAME_AC)
                .append("(")
                .append(DBConstant.COLOUM_AC_NAME).append(",")
                .append(DBConstant.COLOUM_AC_ACCOUNT).append(",")
                .append(DBConstant.COLOUM_AC_PWD).append(",")
                .append(DBConstant.COLOUM_AC_EMAIL).append(",")
                .append(DBConstant.COLOUM_AC_TYPEID).append(",")
                .append(DBConstant.COLOUM_AC_REMARK).append(",")
                .append(DBConstant.COLOUM_AC_CREATETIME).append(",")
                .append(DBConstant.COLOUM_AC_MODIFYTIME)
                .append(")values(?,?,?,?,?,?,?,?)");
        db.execSQL(sqlbuilder.toString(),
                new Object[]{
                       ac.getName(),
                        ac.getAccount(),
                        ac.getAcpwd(),
                        ac.getEmail(),
                        ac.getTypeid(),
                        ac.getRemark(),
                                 creattime,
                                 creattime});
        Cursor cursor = db.rawQuery("select last_insert_rowid() from " + DBConstant.TABLE_NAME_AC, null);
        if (cursor.moveToFirst()) {
            int strid = cursor.getInt(0);
            ac.setId(strid);
        }
        db.close();
        notifyDatabaseChange(TYPE_ADD, ac, 0);
    }

    public void deleteAC(int id, int p) {
        deleteAC(id);
        notifyDatabaseChange(TYPE_DELETE, null, p);
    }

    @Override
    public void deleteAC(int id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "delete from " + DBConstant.TABLE_NAME_AC + " where _id = ?";
        db.execSQL(sql, new Object[]{id});
        db.close();
    }

    public void updateAC(ACBean ac, int p) {
        updateAC(ac);
        notifyDatabaseChange(TYPE_UPDATE, ac, p);
    }

    @Override
    public void updateAC(ACBean ac) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        StringBuilder sqlbuilder = new StringBuilder();

        sqlbuilder.append("update ")
                .append(DBConstant.TABLE_NAME_AC)
                .append(" set name = ? ")
                .append("and ")
                .append(DBConstant.COLOUM_AC_ACCOUNT)
                .append(" =? and ")
                .append(DBConstant.COLOUM_AC_PWD)
                .append(" =? and ")
                .append(DBConstant.COLOUM_AC_EMAIL)
                .append(" = ? and ")
                .append(DBConstant.COLOUM_AC_TYPEID)
                .append(" = ? and ")
                .append(DBConstant.COLOUM_AC_REMARK)
                .append(" =? where ")
                .append(DBConstant.COLOUM_AC_ID)
                .append(" =?");
//        String sql = "update " + DBConstant.TABLE_NAME_AC + " set name = ? " +
//                "and account = ? and password = ? and email = ? and typeid = ? and remark = ? "+
//                "where _id = ?";
        db.execSQL(sqlbuilder.toString(),
                new Object[]{ac.getName(), ac.getAccount(),
                        ac.getAcpwd(),
                        ac.getEmail(),
                        ac.getTypeid(),
                        ac.getRemark(),
                                 ac.getId()});
        db.close();
    }

    @Override
    public List<ACBean> getACList() {
        List<ACBean> list = new ArrayList<ACBean>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select * from " + DBConstant.TABLE_NAME_AC;
//        StringBuilder sb = new StringBuilder();
        try {
            Cursor cursor = db.rawQuery(sql, new String[]{});
            while (cursor.moveToNext()) {
                ACBean ab = new ACBean();
                ab.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                ab.setAccount(cursor.getString(cursor.getColumnIndex("account")));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                ab.setName(name);
                ab.setAcpwd(cursor.getString(cursor.getColumnIndex("password")));
                ab.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                ab.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
                ab.setTypeid(cursor.getInt(cursor.getColumnIndex("typeid")));
                ab.setCreatetime(cursor.getString(cursor.getColumnIndex("createtime")));
                ab.setModifytime(cursor.getString(cursor.getColumnIndex("modifytime")));
//                sb.append(ab.getName()+"--");
//                int p = getPosition(list,name);
                list.add(ab);
            }
//            LogFileUtil.writeLog(MyApplication.instance.getApplicationContext(),sb.toString()+"\n");
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    @Override
    public boolean isExists(ACBean ac) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String sql = "select * from " + DBConstant.TABLE_NAME_AC + " where name = ? " +
                "and account = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{ac.getName(), ac.getAccount()});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }
    private boolean isExistsNotClose(ACBean ac){
        String sql = "select * from " + DBConstant.TABLE_NAME_AC + " where name = ? " +
                "and account = ?";
        Cursor cursor = mHelper.getReadableDatabase().rawQuery(sql, new String[]{ac.getName(), ac.getAccount()});
        return cursor.moveToNext();
    }

    @Override
    public void clear() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "delete from " + DBConstant.TABLE_NAME_AC;
        db.execSQL(sql, new Object[]{});
        db.close();
    }
}
