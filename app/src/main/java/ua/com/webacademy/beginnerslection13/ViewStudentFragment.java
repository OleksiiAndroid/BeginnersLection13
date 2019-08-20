package ua.com.webacademy.beginnerslection13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class ViewStudentFragment extends DialogFragment {
    private static final String EXTRA_STUDENT = "ua.com.webacademy.beginnerslection14.extra.STUDENT";

    private Student mStudent;

    private TextView mTextViewFirstName;
    private TextView mTextViewLasttName;
    private TextView mTextViewAge;

    public ViewStudentFragment() {

    }

    public static ViewStudentFragment newInstance(Student student) {
        ViewStudentFragment fragment = new ViewStudentFragment();

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_STUDENT, student);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mStudent = getArguments().getParcelable(EXTRA_STUDENT);

        View fragmentView = inflater.inflate(R.layout.fragment_view_student, container, false);

        mTextViewFirstName = fragmentView.findViewById(R.id.editTextFirstName);
        mTextViewLasttName = fragmentView.findViewById(R.id.editTextLastName);
        mTextViewAge = fragmentView.findViewById(R.id.editTextAge);

        mTextViewFirstName.setText(mStudent.FirstName);
        mTextViewLasttName.setText(mStudent.LastName);
        mTextViewAge.setText(String.valueOf(mStudent.Age));

        return fragmentView;
    }
}
