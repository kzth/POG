package com.android.hrtkzt.pog.asynctask;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.hrtkzt.pog.R;
import com.android.hrtkzt.pog.activity.HorseInfoActivity;
import com.android.hrtkzt.pog.config.POGPreference;
import com.android.hrtkzt.pog.config.POGconfig;
import com.android.hrtkzt.pog.database.POGDatabaseHelper;
import com.android.hrtkzt.pog.util.Util;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by hirotakazuto on 15/06/09.
 */
public class InitAsyncTask extends AsyncTask<Void, Void, String> {

    private final String TAG = InitAsyncTask.class.getSimpleName();

    private Context mContext;

    private View mView;

    private SQLiteDatabase db;
    private SQLiteDatabase rdb;

    private boolean firstFlag = false;

    public InitAsyncTask(Context context, View v) {
        this.mContext = context;
        this.mView = v;
    }

    @Override
    protected void onPreExecute() {
        if(POGconfig.isUpdateFlag()){
            Toast.makeText(mContext, "データを初期化しています", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected String doInBackground(Void... params) {

        if(POGconfig.isUpdateFlag()){

            AssetManager assetManager = mContext.getResources().getAssets();
            InputStream is = null;
            try {
                is = assetManager.open("data/horse.csv");

                CSVReader csv = new CSVReader(new InputStreamReader(is, "UTF-8"));
                String[] line;
                int lineNo = 1;
                int i = 0;
                while ((line = csv.readNext()) != null) {
                    // databaseに詰める処理
                    insertInitData(line);
                    Util.logDebug(TAG, "this is " + String.valueOf(i));
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Util.put(mContext, POGPreference.PREF_KEY_FIRST_EXEC, "true");
        }

        return null;
    }

    @Override
    public void onPostExecute(String params) {

        if(POGconfig.isUpdateFlag()){
            Toast.makeText(mContext, "データの初期化が完了しました", Toast.LENGTH_SHORT).show();
        }

        POGDatabaseHelper dbHelper = new POGDatabaseHelper(mContext);
        rdb = dbHelper.getReadableDatabase();

        String selectSQL = "select * from horse_table;";
        Cursor cursor = null;

        try {
            cursor = rdb.rawQuery(selectSQL, null);
        }catch(Exception e) {
            e.printStackTrace();
        }

        String[] horseName = new String[100];
        int i = 0;
        cursor.moveToFirst();
        do {
            horseName[i] = cursor.getString(1);
            i++;
            if(i == 100) break;
        }while(cursor.moveToNext());

        // view
        ListView lv = (ListView) mView.findViewById(R.id.horseListView);
        ArrayAdapter adapter = new ArrayAdapter(mContext, R.layout.list_item, horseName);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listview = (ListView)parent;
                String item = (String) listview.getItemAtPosition(position);

                Intent intent = new Intent(mContext, HorseInfoActivity.class);
                intent.putExtra(POGPreference.EXTRA_KEY_HORSE_NAME, item);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    private void insertInitData(String[] line) {

        String dbName = POGconfig.getHorseDbTableName();

        POGDatabaseHelper dbHelper = new POGDatabaseHelper(mContext);
        db = dbHelper.getWritableDatabase();

        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, dbName);
        int isStableIndex = ih.getColumnIndex(POGconfig.getIsStableName());
        int nameIndex = ih.getColumnIndex(POGconfig.getNameName());
        int sexIndex = ih.getColumnIndex(POGconfig.getSexName());
        int motherIndex = ih.getColumnIndex(POGconfig.getMotherName());
        int fatherIndex = ih.getColumnIndex(POGconfig.getFatherName());
        int motherFatherIndex = ih.getColumnIndex(POGconfig.getMotherFatherName());
        int brotherIndex = ih.getColumnIndex(POGconfig.getBrotherName());
        int ownerIndex = ih.getColumnIndex(POGconfig.getOwnerName());
        int belongIndex = ih.getColumnIndex(POGconfig.getBelongName());
        int trainerIndex = ih.getColumnIndex(POGconfig.getTrainerName());
        int producerIndex = ih.getColumnIndex(POGconfig.getProducerName());
        int birthdayIndex = ih.getColumnIndex(POGconfig.getBirthdayName());
        int saleIndex = ih.getColumnIndex(POGconfig.getSaleName());
        int priceIndex = ih.getColumnIndex(POGconfig.getPriceName());
        int modeIndex = ih.getColumnIndex(POGconfig.getModeName());
        int oneModeIndex = ih.getColumnIndex(POGconfig.getOneModeName());
        int clubIndex = ih.getColumnIndex(POGconfig.getClubName());
        int updateIndex = ih.getColumnIndex(POGconfig.getUpdateName());

        ih.prepareForInsert();
        ih.bind(isStableIndex, line[0]);
        ih.bind(nameIndex    , line[1]);
        ih.bind(sexIndex     , line[2]);
        ih.bind(motherIndex  , line[3]);
        ih.bind(fatherIndex  , line[4]);
        ih.bind(motherFatherIndex, line[5]);
        ih.bind(brotherIndex , line[6]);
        ih.bind(ownerIndex   , line[7]);
        ih.bind(belongIndex  , line[8]);
        ih.bind(trainerIndex , line[9]);
        ih.bind(producerIndex, line[10]);
        ih.bind(birthdayIndex, line[11]);
        ih.bind(saleIndex    , line[12]);
        ih.bind(priceIndex   , line[13]);
        ih.bind(modeIndex    , line[14]);
        ih.bind(oneModeIndex , line[15]);
        ih.bind(clubIndex    , line[16]);
        ih.bind(updateIndex, line[17]);
        ih.execute();

        db.close();

    }
}
