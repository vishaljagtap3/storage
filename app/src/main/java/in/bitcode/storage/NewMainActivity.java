package in.bitcode.storage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            File appRoot = getFilesDir();
            mt("App root: " + appRoot.getAbsolutePath());

            File newFile = new File(appRoot, "new_file.txt");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }

            FileOutputStream fout = new FileOutputStream(newFile, true);
            fout.write("I love Android! I Love Pune! I love BitCode!".getBytes());
            fout.close();

            FileInputStream fin = new FileInputStream(newFile);
            byte[] data = new byte[1024 * 4];
            int count;
            while ((count = fin.read(data)) != -1) {
                mt("new_file.txt --> " + new String(data, 0, count));
            }
            fin.close();

            File priImagesDir = new File(appRoot.getAbsolutePath(), "pri_images");
            if (!priImagesDir.exists()) {
                priImagesDir.mkdir();
            }

            File myLogoFile = new File(priImagesDir, "bitcode_logo.png");
            if (!myLogoFile.exists()) {
                myLogoFile.createNewFile();
            }

            fout = new FileOutputStream(myLogoFile, true);
            fout.write("I do not love Android! I do not Love Pune! I do not love BitCode! \n".getBytes());
            fout.close();

            fin = new FileInputStream(myLogoFile);
            while ((count = fin.read(data)) != -1) {
                mt("bitcode_logo.png --> " + new String(data, 0, count));
            }
            fin.close();

            //myLogoFile.delete();
            mt("pri img dir delete " + priImagesDir.delete());

            mt("--------------------------------------------");


            String[] fileNames = appRoot.list();
            for (String fileName : fileNames) {
                mt(fileName);
            }

            mt("--------------------------------------------");

            File[] files = appRoot.listFiles();
            for (File file : files) {
                mt(file.getAbsolutePath() + " - " + file.isFile() + " " + file.length());
                if (file.isDirectory()) {
                    for(File f : file.listFiles()) {
                        mt("   " + f.getAbsolutePath());
                    }
                }
            }


            File cacheDir = getCacheDir();
            mt("Cache dir: " + cacheDir.getAbsolutePath());



            /*
            FileOutputStream fout = openFileOutput("students.txt", Activity.MODE_APPEND | Activity.MODE_PRIVATE);
            fout.write("this is my favourite code in Android".getBytes());
            fout.close();

            FileInputStream fin = openFileInput("students.txt");
            byte [] data = new byte[1024*4];
            int count;
            while( (count = fin.read(data) ) != -1 ) {
                Log.e("tag", new String(data, 0, count));
            }
            fin.close();

            //deleteFile("students.txt");

            File myDir = getDir("my_dir", Activity.MODE_PRIVATE);
            Log.e("tag", "my dir: " + myDir.getAbsolutePath());
            File bitcodeDir =  getDir("bitcode", Activity.MODE_PRIVATE);
            Log.e("tag", "bitcode dir: " + bitcodeDir.getAbsolutePath());

            String [] fileNames = fileList();
            for(String fileName : fileNames) {
                Log.e("tag", "file name: " + fileName);
            }
            */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mt(String text) {
        Log.e("tag", text);
    }
}
