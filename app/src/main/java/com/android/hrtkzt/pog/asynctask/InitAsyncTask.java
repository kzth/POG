package com.android.hrtkzt.pog.asynctask;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.View;

import com.android.hrtkzt.pog.config.POGconfig;
import com.android.hrtkzt.pog.database.POGDatabaseHelper;
import com.android.hrtkzt.pog.util.Util;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hirotakazuto on 15/06/09.
 */
public class InitAsyncTask extends AsyncTask<Void, Void, String> {

    private final String TAG = InitAsyncTask.class.getSimpleName();

    private Context mContext;

    private View mView;

    private SQLiteDatabase db;

    public InitAsyncTask(Context context, View v) {
        this.mContext = context;
        this.mView = v;
    }

    @Override
    protected String doInBackground(Void... params) {

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

        return null;
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
        ih.bind(updateIndex  , line[17]);
        ih.execute();

        db.close();
    }

    @Override
    public void onPostExecute(String... params) {


    }
}
