package com.android.hrtkzt.pog.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.hrtkzt.pog.R;
import com.android.hrtkzt.pog.config.POGconfig;
import com.android.hrtkzt.pog.database.POGDatabaseHelper;
import com.android.hrtkzt.pog.util.Util;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class TopActivity extends Activity {

    private final String TAG = TopActivity.class.getSimpleName();


    private SQLiteDatabase rdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top);

        POGDatabaseHelper dbHelper = new POGDatabaseHelper(getApplicationContext());
        rdb = dbHelper.getReadableDatabase();

        String selectSQL = "select * from horse_table;";
        Cursor cursor = null;

        try {
            cursor = rdb.rawQuery(selectSQL, null);
        }catch(Exception e) {
            e.printStackTrace();
        }

        String[] horseName = new String[10];
        int i = 0;
        cursor.moveToFirst();
        do {
            horseName[i] = cursor.getString(1);
            i++;
            if(i == 10) break;
        }while(cursor.moveToNext());

        // view
        ListView lv = (ListView) findViewById(R.id.horseListView);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, horseName);
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
