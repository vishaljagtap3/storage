package in.bitcode.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("vishal_prefs", Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("code", 1010);
        editor.putString("name", "Vishal Jagtap");
        editor.commit();

        int code = prefs.getInt("code", 0);
        String name = prefs.getString("name", "NA");

        Log.e("tag", name + " " + code);

        SharedPreferences prefs1 = getPreferences(Activity.MODE_PRIVATE);
        editor = prefs1.edit();
        editor.putInt("code", 10190);
        editor.putString("name", "Vishal Jagtap @ Bitcode");
        editor.commit();

        name = prefs1.getString("name", "NA");
        code = prefs1.getInt("code", -1);
        Log.e("tag", name + " " + code);

        File appDir = getFilesDir().getParentFile();
        Log.e("tag", "App Int Dir: " + appDir.getAbsolutePath());
        for(File file: appDir.listFiles()) {
            Log.e("tag", file.getAbsolutePath());
        }
        Log.e("tag", "-----------------------------------------");

        File spDir = new File(appDir, "shared_prefs");
        Log.e("tag", "SP Dir: " + spDir.getAbsolutePath() + " " + spDir.exists());
        for(File file: spDir.listFiles()) {
            Log.e("tag", file.getAbsolutePath());

            try {
                FileInputStream fin = new FileInputStream( file );
                byte[]data = new byte[3000];
                int count = fin.read(data);
                Log.e("tag", new String(data, 0, count));
                fin.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}