package com.android.hrtkzt.pog.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hrtkzt.pog.BuildConfig;
import com.android.hrtkzt.pog.R;
import com.android.hrtkzt.pog.config.POGPreference;
import com.android.hrtkzt.pog.database.POGDatabaseHelper;
import com.android.hrtkzt.pog.element.Horse;
import com.android.hrtkzt.pog.util.Util;

import java.sql.SQLData;

/**
 * Created by kazhirot on 6/9/15.
 */
public class HorseInfoActivity extends Activity {

    private final String TAG = HorseInfoActivity.class.getSimpleName();

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

        String selectSQL = "select * from horse_table where name='" + horseName + "';";
        Cursor cursor = null;

        try {
            cursor = rdb.rawQuery(selectSQL, null);
        }catch(Exception e) {
            e.printStackTrace();
        }
        cursor.moveToFirst();

        Util.logDebug(TAG, cursor.toString());

        final Horse horse = new Horse(cursor);
        TextView nameText = (TextView) findViewById(R.id.horseName);
        nameText.setText(horse.getName());

        TextView nameSex = (TextView) findViewById(R.id.horseSex);
        nameSex.setText(horse.getSex());

        TextView nameMother = (TextView) findViewById(R.id.horseMother);
        nameMother.setText(horse.getMother());

        TextView nameFather = (TextView) findViewById(R.id.horseFather);
        nameFather.setText(horse.getFather());

        TextView nameMotherFather = (TextView) findViewById(R.id.horseMotherFather);
        nameMotherFather.setText(horse.getMotherFather());

        TextView nameBrother = (TextView) findViewById(R.id.horseBrother);
        nameBrother.setText(horse.getBrother());

        TextView nameOwner = (TextView) findViewById(R.id.horseOwner);
        nameOwner.setText(horse.getOwner());

        TextView nameBelong = (TextView) findViewById(R.id.horseBelong);
        nameBelong.setText(horse.getBelong());

        TextView nameTrainer = (TextView) findViewById(R.id.horseTrainer);
        nameTrainer.setText(horse.getTrainer());

        TextView nameProducer = (TextView) findViewById(R.id.horseProducer);
        nameProducer.setText(horse.getProducer());

        TextView nameBirthday = (TextView) findViewById(R.id.horseBirthday);
        nameBirthday.setText(horse.getBirthday());

        TextView nameSale = (TextView) findViewById(R.id.horseSale);
        nameSale.setText(horse.getSale());

        TextView namePrice = (TextView) findViewById(R.id.horsePrice);
        namePrice.setText(horse.getPrice());

        TextView nameMode = (TextView) findViewById(R.id.horseMode);
        nameMode.setText(horse.getMode());

        TextView nameOneMode = (TextView) findViewById(R.id.horseOneMode);
        nameOneMode.setText(horse.getOneMode());

        TextView nameClub = (TextView) findViewById(R.id.horseClub);
        nameClub.setText(horse.getClub());

        TextView nameUpdate = (TextView) findViewById(R.id.horseUpdate);
        nameUpdate.setText(horse.getUpdate());

        TextView nameId = (TextView) findViewById(R.id.horseId);
        nameId.setText(String.valueOf(horse.getId()));

        Button button = (Button) findViewById(R.id.insertButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(horse.getId());
                boolean flag = Util.putMyHorse(getApplicationContext(), id);

                String message = "";
                if(flag) {
                    message = "登録しました";
                } else {
                    message = "登録できません";
                }

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
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
