package ua.com.webacademy.beginnerslection13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


public class AddEditStudentFragment extends Fragment {

    private static final String EXTRA_STUDENT = "ua.com.webacademy.beginnerslection14.extra.STUDENT";

    private Student mStudent;

    private EditText mEditTextFirstName;
    private EditText mEditTextLasttName;
    private EditText mEditTextAge;

    public AddEditStudentFragment() {

    }

    public static AddEditStudentFragment newInstance(Student student) {
        AddEditStudentFragment fragment = new AddEditStudentFragment();

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_STUDENT, student);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mStudent = getArguments().getParcelable(EXTRA_STUDENT);

        View fragmentView = inflater.inflate(R.layout.fragment_add_edit_student, container, false);

        mEditTextFirstName = fragmentView.findViewById(R.id.editTextFirstName);
        mEditTextLasttName = fragmentView.findViewById(R.id.editTextLastName);
        mEditTextAge = fragmentView.findViewById(R.id.editTextAge);

        mEditTextFirstName.setText(mStudent.FirstName);
        mEditTextLasttName.setText(mStudent.LastName);
        mEditTextAge.setText(String.valueOf(mStudent.Age));

        fragmentView.findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mStudent.FirstName = mEditTextFirstName.getText().toString();
                    mStudent.LastName = mEditTextLasttName.getText().toString();
                    mStudent.Age = Integer.parseInt(mEditTextAge.getText().toString());

                    mListener.save(mStudent);
                }
            }
        });

        fragmentView.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.cancel();
                }
            }
        });

        return fragmentView;
    }


    private StudentListener mListener;

    public void setStudentListener(StudentListener listener) {
        mListener = listener;
    }

    public interface StudentListener {
        void save(Student student);

        void cancel();
    }
}
