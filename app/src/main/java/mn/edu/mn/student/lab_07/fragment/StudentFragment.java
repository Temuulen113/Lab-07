package mn.edu.mn.student.lab_07.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mn.edu.mn.student.lab_07.R;

public class StudentFragment extends Fragment {
    private StudentFragmentListener listener;
    private TextView tv;
    private EditText school, grade, course, gpa;
    private Button send;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface StudentFragmentListener {
        void onInputStudentSent(CharSequence input);
    }


    public static StudentFragment newInstance() {
        return new StudentFragment();
    }

    public void updateTextView(CharSequence newText) {
        tv.setText(newText);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student, container, false);

        tv = v.findViewById(R.id.info2);
        school = v.findViewById(R.id.school);
        grade = v.findViewById(R.id.grade);
        course = v.findViewById(R.id.course);
        gpa = v.findViewById(R.id.gpa);
        send = v.findViewById(R.id.sSend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = "School: " + school.getText() + ", Grade: " + grade.getText() +
                        ", Course: " + course.getText() + ", GPA: " + gpa.getText();
                listener.onInputStudentSent(input);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof StudentFragmentListener)
            listener = (StudentFragmentListener) context;
        else
            throw new RuntimeException(context.toString() + " must implement StudentFragmentListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}