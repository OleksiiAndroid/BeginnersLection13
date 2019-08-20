package ua.com.webacademy.beginnerslection13;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class StudentsLoader extends AsyncTaskLoader<ArrayList<Student>> {

    private DataBaseHelper mDBHelper;

    public StudentsLoader(Context context) {
        super(context);

        this.mDBHelper = new DataBaseHelper(context);
    }

    @Override
    public ArrayList<Student> loadInBackground() {
        return mDBHelper.getStudents();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
