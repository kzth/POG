package com.android.hrtkzt.pog.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hrtkzt.pog.BuildConfig;
import com.android.hrtkzt.pog.R;
import com.android.hrtkzt.pog.config.POGPreference;
import com.android.hrtkzt.pog.database.POGDatabaseHelper;
import com.android.hrtkzt.pog.element.Horse;

import java.sql.SQLData;

/**
 * Created by kazhirot on 6/9/15.
 */
public class HorseInfoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horse_info);

        Bundle extras = getIntent().getExtras();
        String horseName = "";
        if(extras != null) {
            horseName = extras.getString(POGPreference.EXTRA_KEY_HORSE_NAME);
        }

        //Toast.makeText(this, horseName, Toast.LENGTH_LONG).show();

        POGDatabaseHelper dbHelper = new POGDatabaseHelper(getApplicationContext());
        SQLiteDatabase rdb = dbHelper.getReadableDatabase();

        String selectSQL = "select * from horse_table where name=" + horseName + ";";
        Cursor cursor = null;

        try {
            cursor = rdb.rawQuery(selectSQL, null);
        }catch(Exception e) {
            e.printStackTrace();
        }

        Horse horse = new Horse(cursor);
        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());
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

    public void setView() {

    }
}
