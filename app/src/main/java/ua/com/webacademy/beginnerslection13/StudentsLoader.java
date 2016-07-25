package ua.com.webacademy.beginnerslection13;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class StudentsLoader extends AsyncTaskLoader<List<Student>> {

    private DataBaseHelper mDBHelper;

    public StudentsLoader(Context context) {
        super(context);

        this.mDBHelper = new DataBaseHelper(context);
    }

    @Override
    public List<Student> loadInBackground() {
        return mDBHelper.getStudents();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
