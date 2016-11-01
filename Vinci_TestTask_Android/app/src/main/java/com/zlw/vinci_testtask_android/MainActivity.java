package com.zlw.vinci_testtask_android;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.zlw.bean.Music;
import com.zlw.utils.JsonTools;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Music> list = new ArrayList<Music>();
    private MyRecycleViewAdapter adapter;
    private MusicDao musicDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDB();

        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecycleViewAdapter(list);
        rv.setAdapter(adapter);
        new ShowDataAsyncTask().execute();

    }


    private void initDB() {
        SQLiteOpenHelper b = new MusicDBOpenHelper(this);
        musicDao = new MusicDao(b.getReadableDatabase());
    }

    /**
     * ShowDataAsyncTask
     * 任务：读取文件 转换成数据 并保存到数据库中
     * 任务结束： 刷新rv列表数据
     */
    class ShowDataAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            String result = "";
            List<Music> data = null;
            try {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("share", MODE_PRIVATE); //此处表示该应用程序专用
                boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
                if (!isFirstRun) return null;
                //读取文件并存入数据库中
                InputStream in = getResources().openRawResource(R.raw.jsonfile);
                int lenght = in.available();
                byte[] buffer = new byte[lenght];
                in.read(buffer);
                result = new String(buffer);

                data = JsonTools.fromJson(result, new TypeToken<List<Music>>() {
                }.getType());

                for (Music music : data) {
                    musicDao.addMusic(music);
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isFirstRun", false);
                edit.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            List<Music> data = new ArrayList<Music>();
            data = musicDao.getMusic();
            adapter.setList(data);
            adapter.notifyDataSetChanged();
        }
    }
}
