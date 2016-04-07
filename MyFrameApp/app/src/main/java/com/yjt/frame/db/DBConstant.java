package com.yjt.frame.db;

import android.net.Uri;

/**
 * Created by yujiangtao on 2015/8/13.
 */
public class DBConstant {
    /*Authority*/
    public static final String AUTHORITY = "yjt.account.providers.ac";
    public static final int VERSION = 1;
    public static final String DB_NAME = "sqlite-ac.db";
    /*Match Code*/
    public static final int ITEM = 1;
    public static final int ITEM_ID = 2;
    public static final int ITEM_POS = 3;

    /*Default sort order*/
    public static final String DEFAULT_SORT_ORDER = "_id asc";
    /*Call Method*/
    public static final String METHOD_GET_ITEM_COUNT = "METHOD_GET_ITEM_COUNT";
    public static final String KEY_ITEM_COUNT = "KEY_ITEM_COUNT";
    /*MIME*/
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.yjt.account";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.yjt.account";

    /*Content URI*/
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/item");
    public static final Uri CONTENT_POS_URI = Uri.parse("content://" + AUTHORITY + "/pos");


    public static final String TABLE_NAME_AC = "ac_tb";
    public static final String COLOUM_AC_ID="_id";
    public static final String COLOUM_AC_NAME="name";
    public static final String COLOUM_AC_ACCOUNT="account";
    public static final String COLOUM_AC_PWD="password";
    public static final String COLOUM_AC_EMAIL="email";
    public static final String COLOUM_AC_TYPEID="typeid";
    public static final String COLOUM_AC_REMARK="remark";
    public static final String COLOUM_AC_CREATETIME="createtime";
    public static final String COLOUM_AC_MODIFYTIME="modifytime";

    public static final String SQL_AC_CREATE =
            "create table " + TABLE_NAME_AC
            + "("+ COLOUM_AC_ID +" integer primary key autoincrement,"
                    +COLOUM_AC_NAME +" text,"
                    +COLOUM_AC_ACCOUNT+" text,"
                    +COLOUM_AC_PWD+" text,"
                    +COLOUM_AC_EMAIL+" text,"
                    +COLOUM_AC_TYPEID+" integer,"
                    +COLOUM_AC_REMARK+" text,"
                    +COLOUM_AC_CREATETIME +" text,"
                    +COLOUM_AC_MODIFYTIME + " text)";
    public static final String SQL_AC_DROP = "drop table if exists "+TABLE_NAME_AC;

}
