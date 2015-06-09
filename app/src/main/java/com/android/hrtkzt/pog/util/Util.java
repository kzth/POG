package com.android.hrtkzt.pog.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.hrtkzt.pog.config.POGPreference;
import com.android.hrtkzt.pog.config.POGconfig;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hirotakazuto on 15/06/08.
 */
public class Util {

    private static final String TAG = Util.class.getSimpleName();

    public static void logDebug(String tag, String debug) {

        Log.d(tag, debug);
    }

    /**
     * プリファレンスファイルに値を保存する.
     *
     * 複数保存対応のため、Mapで値を渡すこと.
     *
     * @param context	コンテキストオブジェクト
     * @param valueMap	保存したい値とキーのMap
     */
    public static void putPreference( Context context, Map<String,String> valueMap ) {

        // プリファレンス編集用オブジェクト取得
        SharedPreferences preference = context.getSharedPreferences( POGconfig.getPreferenceFileName(), Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preference.edit();

        // 値保存
        for( Map.Entry<String, String> entry : valueMap.entrySet() ) {
            editor.putString( entry.getKey(), entry.getValue() );
        }

        editor.commit();
    }

    /**
     * プリファレンスファイルから値を取得する.
     *
     * もし値が存在しない場合は、空文字列が返る.
     *
     * @param context	コンテキストオブジェクト
     * @param key		取得したい値のキー名
     * @return			取得した値
     */
    public static String getPreference( Context context, String key ) {

        // プリファレンス編集用オブジェクト取得
        SharedPreferences preference = context.getSharedPreferences( POGconfig.getPreferenceFileName(), Activity.MODE_PRIVATE );
        return preference.getString( key, "" );

    }

    /**
     * プリファレンスファイルから値を削除する.
     *
     * @param context	コンテキストオブジェクト
     * @param keyList	削除したいキーのList
     */
    public static void removePreference( Context context, List<String> keyList ) {

        // プリファレンス編集用オブジェクト取得
        SharedPreferences preference = context.getSharedPreferences( POGconfig.getPreferenceFileName(), Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preference.edit();

        // 値保存
        for( String key : keyList ) {
            editor.remove( key );
        }

        editor.commit();
    }

    /**
     * プリファレンス保存簡易版
     *
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, String value) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        Util.putPreference(context, map);
    }

    public static boolean isFirstExec(Context context) {
        String flag = getPreference(context, POGPreference.PREF_KEY_FIRST_EXEC);
        if(flag != null && flag.equals("true")) {
            return true;
        }
        return false;
    }

    public static boolean putMyHorse(Context context, String id) {

        List<String> ids = getMyHorse(context);

        if(ids == null ||  ids.size() < 10) {
            ids.add(id);

            StringBuilder builder = new StringBuilder();

            for(String str : ids) {
                builder.append(str).append(",");
            }

            String result = builder.substring(0, builder.length() - 1);

            put(context, POGPreference.PREF_KEY_MY_POG_HORSE, result);
            return true;
        }

        return false;
    }

    public static List<String> getMyHorse(Context context) {

        String idStr = Util.getPreference(context, POGPreference.PREF_KEY_MY_POG_HORSE);
        String[] idStrArray = idStr.split(",");
        List<String> ids = new ArrayList<String>(Arrays.asList(idStrArray));

        return ids;
    }
}
