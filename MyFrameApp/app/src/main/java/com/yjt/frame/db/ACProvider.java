package com.yjt.frame.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;

/**
 * Created by yujiangtao on 2016/1/24.
 */
public class ACProvider extends ContentProvider {

    private static final String LOG_TAG = "com.yjt.frame.providers.ACProvider";
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DBConstant.AUTHORITY, "item", DBConstant.ITEM);
        uriMatcher.addURI(DBConstant.AUTHORITY, "item/#", DBConstant.ITEM_ID);
        uriMatcher.addURI(DBConstant.AUTHORITY, "pos/#", DBConstant.ITEM_POS);
    }

    private static final HashMap<String, String> acProjectionMap;

    static {
        acProjectionMap = new HashMap<String, String>();
        acProjectionMap.put(DBConstant.COLOUM_AC_ID, DBConstant.COLOUM_AC_ID);
        acProjectionMap.put(DBConstant.COLOUM_AC_NAME, DBConstant.COLOUM_AC_NAME);
        acProjectionMap.put(DBConstant.COLOUM_AC_ACCOUNT, DBConstant.COLOUM_AC_ACCOUNT);
        acProjectionMap.put(DBConstant.COLOUM_AC_PWD, DBConstant.COLOUM_AC_PWD);
        acProjectionMap.put(DBConstant.COLOUM_AC_EMAIL, DBConstant.COLOUM_AC_EMAIL);
        acProjectionMap.put(DBConstant.COLOUM_AC_TYPEID, DBConstant.COLOUM_AC_TYPEID);
        acProjectionMap.put(DBConstant.COLOUM_AC_REMARK, DBConstant.COLOUM_AC_REMARK);
        acProjectionMap.put(DBConstant.COLOUM_AC_CREATETIME, DBConstant.COLOUM_AC_CREATETIME);
        acProjectionMap.put(DBConstant.COLOUM_AC_MODIFYTIME, DBConstant.COLOUM_AC_MODIFYTIME);
    }

    private DBHelper dbHelper = null;
    private ContentResolver resolver = null;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        resolver = context.getContentResolver();
        dbHelper = DBHelper.getInstance(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        String limit = null;

        switch (uriMatcher.match(uri)) {
            case DBConstant.ITEM: {
                sqlBuilder.setTables(DBConstant.DB_NAME);
                sqlBuilder.setProjectionMap(acProjectionMap);
                break;
            }
            case DBConstant.ITEM_ID: {
                String id = uri.getPathSegments().get(1);
                sqlBuilder.setTables(DBConstant.DB_NAME);
                sqlBuilder.setProjectionMap(acProjectionMap);
                sqlBuilder.appendWhere(DBConstant.COLOUM_AC_ID + "=" + id);
                break;
            }
            case DBConstant.ITEM_POS: {
                String pos = uri.getPathSegments().get(1);
                sqlBuilder.setTables(DBConstant.DB_NAME);
                sqlBuilder.setProjectionMap(acProjectionMap);
                limit = pos + ", 1";
                break;
            }
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }

        Cursor cursor = sqlBuilder.query(db, projection, selection, selectionArgs, null, null, TextUtils.isEmpty(sortOrder) ? DBConstant.DEFAULT_SORT_ORDER : sortOrder, limit);
        cursor.setNotificationUri(resolver, uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case DBConstant.ITEM:
                return DBConstant.CONTENT_TYPE;
            case DBConstant.ITEM_ID:
            case DBConstant.ITEM_POS:
                return DBConstant.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != DBConstant.ITEM) {
            throw new IllegalArgumentException("Error Uri: " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(DBConstant.DB_NAME, DBConstant.COLOUM_AC_ID, values);
        if (id < 0) {
            throw new SQLiteException("Unable to insert " + values + " for " + uri);
        }
        Uri newUri = ContentUris.withAppendedId(uri, id);
        resolver.notifyChange(newUri, null);
        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case DBConstant.ITEM: {
                count = db.delete(DBConstant.DB_NAME, selection, selectionArgs);
                break;
            }
            case DBConstant.ITEM_ID: {
                String id = uri.getPathSegments().get(1);
                count = db.delete(DBConstant.DB_NAME, DBConstant.COLOUM_AC_ID + "=" + id
                        + (!TextUtils.isEmpty(selection) ? " and (" + selection + ')' : ""), selectionArgs);
                break;
            }
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }

        resolver.notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case DBConstant.ITEM: {
                count = db.update(DBConstant.DB_NAME, values, selection, selectionArgs);
                break;
            }
            case DBConstant.ITEM_ID: {
                String id = uri.getPathSegments().get(1);
                count = db.update(DBConstant.DB_NAME, values, DBConstant.COLOUM_AC_ID + "=" + id
                        + (!TextUtils.isEmpty(selection) ? " and (" + selection + ')' : ""), selectionArgs);
                break;
            }
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
        resolver.notifyChange(uri, null);
        return count;
    }

    @Override
    public Bundle call(String method, String request, Bundle args) {

        if (method.equals(DBConstant.METHOD_GET_ITEM_COUNT)) {
            return getItemCount();
        }

        throw new IllegalArgumentException("Error method call: " + method);
    }

    private Bundle getItemCount() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from " + DBConstant.DB_NAME, null);

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        Bundle bundle = new Bundle();
        bundle.putInt(DBConstant.KEY_ITEM_COUNT, count);
        cursor.close();
        db.close();
        return bundle;
    }
}
