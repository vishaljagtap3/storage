package in.bitcode.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

import java.util.ArrayList;


//Singleton
public class DBUtil {

    private SQLiteDatabase db;
    private static DBUtil dbUtil = null;

    private DBUtil(Context context) {
        db = new StudentsDBHelper(
                context,
                "db_students",
                //new StudentsCursorFactory(),
                null,
                2
        ).getWritableDatabase();
    }

    public static DBUtil getInstance(Context context) {
        if(dbUtil == null ) {
            dbUtil =  new DBUtil(context);
        }
        return dbUtil;
    }

    public ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();

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

        return students;
    }

    public boolean addStudent(int id, String name, int marks) {

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("marks", marks);

        //db.insert("students", "dob, fees", values);
        long rowNum = db.insert("students", null, values);
        if (rowNum >= 0) {
            return true;
        }
        return false;

    }

    public void mt(String text) {
        Log.e("tag", text);
    }

    public int updateStudent(int id, String name, int marks) {

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("marks", marks);

        int count = db.update("students", values, "id = ?", new String[] { id + ""});
        return count;
    }

    public int deleteStudent(int id) {
        int count = db.delete("students", "id = ?", new String[] { id + ""});
        return count;
    }

    class StudentsCursorFactory implements SQLiteDatabase.CursorFactory {

        @Override
        public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
            mt("My code to execute the query and create the cursor...");
            return null;
        }
    }
}
