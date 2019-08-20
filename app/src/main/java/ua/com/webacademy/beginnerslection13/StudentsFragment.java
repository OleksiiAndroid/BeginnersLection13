package ua.com.webacademy.beginnerslection13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class StudentsFragment extends Fragment {
    private static final String EXTRA_STUDENTS = "ua.com.webacademy.beginnerslection14.extra.STUDENTS";

    private ListView mListView;
    private ArrayList<Student> mStudents;

    public StudentsFragment() {

    }

    public static StudentsFragment newInstance(ArrayList<Student> students) {
        StudentsFragment fragment = new StudentsFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_STUDENTS, students);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mStudents = getArguments().getParcelableArrayList(EXTRA_STUDENTS);
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mStudents
        );

        View fragmentView = inflater.inflate(R.layout.fragment_students, container, false);

        mListView = fragmentView.findViewById(R.id.listView);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mListener != null) {
                    mListener.edit(mStudents.get(i));
                }
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mListener != null) {
                    mListener.view(mStudents.get(i));
                }

                return true;
            }
        });

        return fragmentView;
    }


    private StudentsListener mListener;

    public void setStudentsListener(StudentsListener listener) {
        mListener = listener;
    }

    public interface StudentsListener {
        void edit(Student student);

        void view(Student student);
    }
}
