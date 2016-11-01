package com.zlw.vinci_testtask_android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zlw.bean.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicDao {

    private SQLiteDatabase db;

    public MusicDao(SQLiteDatabase db) {
        super();
        this.db = db;
    }

    public void addMusic(Music music) {
        db.execSQL("insert into music_info(singer,song) values('" + music.getSinger() + "','" + music.getSong() + "')");
    }

    public List<Music> getMusic() {
        List<Music> list = new ArrayList<Music>();
        Music music = null;
        String sql = "select * from music_info Order By singer";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                music = new Music();
                music.setSinger(cursor.getString(cursor.getColumnIndex("singer")));
                music.setSong(cursor.getString(cursor.getColumnIndex("song")));
                list.add(music);
            }
        }
        cursor.close();

        return list;
    }

}
