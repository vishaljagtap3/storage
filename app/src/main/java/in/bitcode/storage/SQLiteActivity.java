package in.bitcode.storage;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SQLiteActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = openOrCreateDatabase(
                "db_students",
                Activity.MODE_PRIVATE,
                null,
                new DatabaseErrorHandler() {
                    @Override
                    public void onCorruption(SQLiteDatabase dbObj) {
                        mt("Something went wrong while opening the db...");
                    }
                }
        );

        try {
            db.execSQL("create table students(id integer primary key, name text, marks integer)");
        } catch (SQLException e) {
        }

        /*try {
            addStudent(22, "BB", 68);
            addStudent(11, "AA", 89);
            addStudent(44, "DD", 84);
            addStudent(33, "CC", 92);
        }
        catch (Exception e) {
        }*/


        /*String insQuery = "insert into students values(55, 'EE', 78)";
        db.rawQuery(insQuery,null);

        insQuery = "insert into students values(?, ?, ?)";
        db.rawQuery(insQuery, new String[] {"25", "FF", "88" });*/

        populate();


        db.close();
    }

    private void populate() {

        ArrayList<Student> students = new ArrayList<>();

        //String queryStudents = "select * from students"; //bad
        //String queryStudents = "select id, name, marks from students";
        //Cursor c = db.rawQuery(queryStudents, null);

        /*
        Cursor c = db.query(
                "students",
                new String[]{"id", "name", "marks"},
                "marks > ? and city = ?",
                new String[]{"90", "Pune"},
                null,
                null,
                "marks desc"
                );
         */
        Cursor c = db.query(
                "students",
                null,
                null,
                null,
                null,
                null,
                "marks desc"
        );


        while (c.moveToNext()) {
            students.add(new Student(c.getInt(0), c.getString(1), c.getInt(2)));
        }
        c.close();

        for (Student s : students) {
            mt(s.toString());
        }
    }

    private void addStudent(int id, String name, int marks) {

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("marks", marks);

        //db.insert("students", "dob, fees", values);
        long rowNum = db.insert("students", null, values);
        if (rowNum >= 0) {
            mt(id + " inserted...");
        } else {
            mt(id + " not inserted...");
        }

    }

    private void mt(String text) {
        Log.e("tag", text);
    }
}
