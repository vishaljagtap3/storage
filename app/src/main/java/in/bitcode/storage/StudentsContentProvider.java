package in.bitcode.storage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentsContentProvider extends ContentProvider {

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        db = new StudentsDBHelper(getContext(), "db_students", null, 2)
                .getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        List<String> pathSegments = uri.getPathSegments();

        if (pathSegments.get(0).equals("students")) {
            if(pathSegments.size() == 1) {
                return db.query("students", null, null, null, null, null, null);
            }
            else {
                if(pathSegments.size() == 2) {
                    return db.query("students", null, "id = ?", new String[] {pathSegments.get(1)}, null, null, null);
                }
            }

        }

        return null;

        /*
        String tableName = "";
        switch (pathSegments.get(0)) {
            case "students":
                tableName = "students";
                break;
            case "courses":
                tableName = "bitcode_courses";
                break;
        }
        return db.query(
                tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        */





    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
