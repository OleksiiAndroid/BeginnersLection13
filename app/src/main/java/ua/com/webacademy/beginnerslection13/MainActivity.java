package ua.com.webacademy.beginnerslection13;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Student>> {

    private ProgressDialog mDialog;

    private SaveTask mSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStudents(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSaveTask != null) {
            mSaveTask.cancel(true);
        }
    }

    private void getStudents(boolean restart) {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        if (restart) {
            getLoaderManager().restartLoader(0, null, this);
        } else {
            getLoaderManager().initLoader(0, null, this);
        }
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAdd:
                editStudent(new Student());
                break;
        }
    }

    private void editStudent(Student student) {
        final AddEditStudentFragment fragment = AddEditStudentFragment.newInstance(student);

        fragment.setStudentListener(new AddEditStudentFragment.StudentListener() {
            @Override
            public void save(Student student) {
                saveStudent(student);
            }

            @Override
            public void cancel() {
                getStudents(false);
            }
        });

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentView, fragment);
        fTrans.commit();
    }

    private void viewStudent(Student student) {
        ViewStudentFragment fragment = ViewStudentFragment.newInstance(student);
        fragment.show(getSupportFragmentManager(), "dialog");
    }

    private void saveStudent(Student student) {
        mSaveTask = new SaveTask();
        mSaveTask.execute(student);
    }

    @Override
    public Loader<ArrayList<Student>> onCreateLoader(int i, Bundle bundle) {
        return new StudentsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Student>> loader, ArrayList<Student> students) {
        StudentsFragment fragment = StudentsFragment.newInstance(students);

        fragment.setStudentsListener(new StudentsFragment.StudentsListener() {
            @Override
            public void edit(Student student) {
                editStudent(student);
            }

            @Override
            public void view(Student student) {
                viewStudent(student);
            }
        });

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragmentView, fragment);
        fTrans.commit();

        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Student>> loader) {

    }

    class SaveTask extends AsyncTask<Student, Void, Boolean> {

        private ProgressDialog mDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Wait...");
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected Boolean doInBackground(Student... params) {
            Boolean result = false;

            try {
                Student student = params[0];

                DataBaseHelper helper = new DataBaseHelper(MainActivity.this);
                result = helper.saveStudent(student);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }

            if (result) {
                getStudents(true);
            } else {
                Toast.makeText(MainActivity.this, "Error saving student", Toast.LENGTH_LONG).show();
            }
        }
    }
}
