package one.group.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import one.group.habittracker.data.HabitContract;
import one.group.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    TextView resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultsView = (TextView) findViewById(R.id.results);
        insertHabit();
        readHabits();
    }

    public void insertHabit() {
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "WALkING");
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_STATUS, HabitContract.HabitEntry.STATUS_DONE);

        HabitDbHelper habitHelper = new HabitDbHelper(this);
        SQLiteDatabase db = habitHelper.getWritableDatabase();
        long insertedID = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        Toast.makeText(this, String.valueOf(insertedID), Toast.LENGTH_SHORT).show();

        db.close();
        habitHelper.close();
    }

    public void readHabits() {
        HabitDbHelper habitDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = habitDbHelper.getReadableDatabase();
        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, new String[]{
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_STATUS
        }, null, null, null, null, null);

        int HABIT_NAME_INDEX = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
        int HABIT_STATUS_INDEX = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_STATUS);
        StringBuilder results = new StringBuilder();
        results.append("RESULTS: ");


        while (cursor.moveToNext()) {
            String habitName = cursor.getString(HABIT_NAME_INDEX);
            int habitStatus = cursor.getInt(HABIT_STATUS_INDEX);

            results.append(cursor.getInt(0) + ":");// get row id
            results.append("{");
            results.append(habitName);
            results.append(habitStatus);
            results.append("}");
            results.append("\n");

        }
        resultsView.setText(results);

    }
}
