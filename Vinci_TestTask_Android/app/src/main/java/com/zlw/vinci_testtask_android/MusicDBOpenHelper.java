package com.zlw.vinci_testtask_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicDBOpenHelper extends SQLiteOpenHelper {

	private static String DB_NAME = "music_zlw.db";
	private static int DB_VERSION = 1;

	public MusicDBOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS music_info"
				+ "(music_id INTEGER PRIMARY KEY AUTOINCREMENT,singer VARCHAR, song VARCHAR)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
