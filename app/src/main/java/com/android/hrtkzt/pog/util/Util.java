package com.android.hrtkzt.pog.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.hrtkzt.pog.config.POGconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hirotakazuto on 15/06/08.
 */
public class Util {

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
}
