package in.bitcode.storage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StudentsContentProvider extends ContentProvider {

    private SQLiteDatabase db;
    private static UriMatcher uriMatcher;
    public static final int ALL_STUDENTS = 1, STUDENT_WITH_ID = 2;
    static  {
        uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI("in.bitcode.students", "students", ALL_STUDENTS);
        uriMatcher.addURI("in.bitcode.students", "students/#", STUDENT_WITH_ID);
        uriMatcher.addURI("in.bitcode.students", "courses", 3);
        uriMatcher.addURI("in.bitcode.students", "courses/#", 4);
    }

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

        switch ( uriMatcher.match(uri) ) {
            case ALL_STUDENTS:
                return db.query("students", null, null, null, null, null, null);
            case STUDENT_WITH_ID:
                return db.query("students", null, "id = ?", new String[] {pathSegments.get(1)}, null, null, null);
        }

        return null;

        /*
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
        */


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
        if(uriMatcher.match(uri) == ALL_STUDENTS) {
            long rowNum = db.insert("students", null, values);
            if(rowNum >= 0){
                return Uri.withAppendedPath(uri, values.getAsInteger("id")+"");
            }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        List<String> pathSegments = uri.getPathSegments();

        switch ( uriMatcher.match(uri) ) {
            case ALL_STUDENTS:
                return db.delete("students", null, null);
            case STUDENT_WITH_ID:
                return db.delete("students", "id = ?", new String[] {pathSegments.get(1)});
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        List<String> pathSegments = uri.getPathSegments();

        switch ( uriMatcher.match(uri) ) {
            case ALL_STUDENTS:
                return db.update("students", values, null, null);
            case STUDENT_WITH_ID:
                return db.update("students", values, "id = ?", new String[] {pathSegments.get(1)});
        }
        return 0;
    }
}
