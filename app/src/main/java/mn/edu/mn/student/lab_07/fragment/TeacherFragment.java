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

public class TeacherFragment extends Fragment {
    private TeacherFragment.TeacherFragmentListener listener;
    private TextView tv;
    private EditText profession, degree, subject;
    private Button send;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface TeacherFragmentListener {
        void onInputTeacherSent(CharSequence input);
    }

    public static TeacherFragment newInstance() {
        return new TeacherFragment();
    }

    public void updateTextView(CharSequence newText) {
        tv.setText(newText);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teacher, container, false);

        tv = v.findViewById(R.id.info1);
        profession = v.findViewById(R.id.profession);
        degree = v.findViewById(R.id.degree);
        subject = v.findViewById(R.id.subject);
        send = v.findViewById(R.id.tSend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = "Profession: " + profession.getText() + ", Degree: " + degree.getText() +
                        ", Subjects: " + subject.getText();
                listener.onInputTeacherSent(input);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TeacherFragment.TeacherFragmentListener)
            listener = (TeacherFragment.TeacherFragmentListener) context;
        else
            throw new RuntimeException(context.toString() + " must implement TeacherFragmentListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
