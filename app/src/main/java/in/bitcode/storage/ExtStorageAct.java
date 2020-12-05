package in.bitcode.storage;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExtStorageAct extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = new ImageView(this);
        setContentView(imageView);

        Picasso.get()
                .load("http://bitcode.in/images/gallery/bitcode_galary_iphone_app_development_1.jpg")
                .centerCrop()
                .error(R.drawable.img2)
                .placeholder(R.drawable.img7)
                .into(imageView);


        String state = Environment.getExternalStorageState();
        if( !state.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }

        File extRootDir =  Environment.getExternalStorageDirectory();
        mt("Ext Root Dir : " + extRootDir.getAbsolutePath());

        File dirMovies = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES
        );
        mt("movies dir: " + dirMovies.getAbsolutePath());

        File [] files = extRootDir.listFiles();
        mt("Ext root permissions " + extRootDir.canRead() + " " + extRootDir.canWrite()  + " " + extRootDir.canExecute());
        mt("Ext movies permissions " + dirMovies.canRead() + " " + dirMovies.canWrite()  + " " + dirMovies.canExecute());

        for(File file : files) {
            mt("file --> " + file.getAbsolutePath());
        }

        File extAppRoot = getExternalFilesDir(null);
        mt("Ext App Root ## " + extAppRoot.getAbsolutePath());
        File extAppMusic = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        mt("Ext App Music ## " + extAppMusic.getAbsolutePath());

        mt("");
        mt("");

        //File appData = new File("/storage/emulated/0/Android/data/");
        File appData = new File("/storage/emulated/0/Android/data/com.amazon.mp3/files/Music");
        for(File file : appData.listFiles()) {
            mt(file.getAbsolutePath());
        }

        File extCache = getExternalCacheDir();
        mt("Ext cache ---> " + extCache.getAbsolutePath());


    }

    private void mt(String text) {
        Log.e("tag", text);
    }

}
