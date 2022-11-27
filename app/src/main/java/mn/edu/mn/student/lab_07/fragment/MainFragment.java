package mn.edu.mn.student.lab_07.fragment;

import static mn.edu.mn.student.lab_07.MainActivity.EMPTY;
import static mn.edu.mn.student.lab_07.MainActivity.STUDENT;
import static mn.edu.mn.student.lab_07.MainActivity.TEACHER;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import mn.edu.mn.student.lab_07.MainActivity;
import mn.edu.mn.student.lab_07.R;

public class MainFragment extends Fragment {

    private MainFragmentListener listener;
    private TextView info;
    private EditText lName, fName, age, gender;
    private Spinner type;
    private CharSequence infoString = "";
    private String Info1 = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
            Info1 = savedInstanceState.getString("info");
    }

    public interface MainFragmentListener {
        void onInputMainSent(CharSequence input);
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public void updateTextView(CharSequence newText) {
        info.setText("First name: " + fName.getText().toString() +
                ", Last name: " + lName.getText().toString() +
                ", Age: " + age.getText().toString() +
                ", Gender: " + gender.getText().toString() + ", " + newText);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        info = v.findViewById(R.id.info);
        info.setText(Info1);
        lName = v.findViewById(R.id.lName);
        fName = v.findViewById(R.id.fName);
        age = v.findViewById(R.id.age);
        gender = v.findViewById(R.id.gender);

        type = v.findViewById(R.id.type);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(isEmpty() && !type.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
                    Toast.makeText(getActivity(), "Some fields are empty.", Toast.LENGTH_SHORT).show();
                    type.setSelection(0);
                    return;
                }
                infoString = "Last name: " + lName.getText().toString() + ", First name: " +
                        fName.getText().toString() + ", Age: " + age.getText().toString() +
                        ", Gender: " + gender.getText().toString();

                if(type.getSelectedItem().toString().equalsIgnoreCase(STUDENT)) {
                    ((MainActivity) getActivity()).setType(STUDENT, infoString);
                }else if(type.getSelectedItem().toString().equalsIgnoreCase(TEACHER)) {
                    ((MainActivity) getActivity()).setType(TEACHER, infoString);
                }else if(type.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
                    ((MainActivity) getActivity()).setType(EMPTY, infoString);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("Spinner", "Nothing");
            }
        });

        return v;
    }

    private boolean isEmpty() {
        if((lName.getText().toString().equals("") ||
                fName.getText().toString().equals("") ||
                age.getText().toString().equals("") ||
                gender.getText().toString().equals("")))
            return true;
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainFragment.MainFragmentListener)
            listener = (MainFragment.MainFragmentListener) context;
        else
            throw new RuntimeException(context.toString() + " must implement MainFragmentListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("info", info.getText().toString());
    }
}