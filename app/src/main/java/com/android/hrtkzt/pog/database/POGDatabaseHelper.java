package com.android.hrtkzt.pog.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.hrtkzt.pog.config.POGconfig;

/**
 * Created by hirotakazuto on 15/06/07.
 */
public class POGDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static String dbName = POGconfig.getHorseDbName();
    private static String dbTableName = POGconfig.getHorseDbTableName();

    public POGDatabaseHelper(Context context) {
        super(context, dbName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table " + dbTableName + " ("+
                        "   is_stable text not null," +
                        "   name text not null," +
                        "   sex text not null," +
                        "   mother text not null," +
                        "   father text not null," +
                        "   mother_father text not null," +
                        "   brother text," +
                        "   owner text," +
                        "   belong text," +
                        "   trainer text," +
                        "   producer text," +
                        "   birthday text not null," +
                        "   sale text," +
                        "   price text," +
                        "   mode text," +
                        "   one_mode text," +
                        "   club text," +
                        "   updatetime text," +
                        "   _id integer primary key autoincrement" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
