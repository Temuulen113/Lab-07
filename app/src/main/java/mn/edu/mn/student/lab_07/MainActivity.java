package mn.edu.mn.student.lab_07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import mn.edu.mn.student.lab_07.fragment.MainFragment;
import mn.edu.mn.student.lab_07.fragment.StudentFragment;
import mn.edu.mn.student.lab_07.fragment.TeacherFragment;

public class MainActivity extends AppCompatActivity implements StudentFragment.StudentFragmentListener, TeacherFragment.TeacherFragmentListener, MainFragment.MainFragmentListener {

    private MainFragment mainFragment;
    private StudentFragment studentFragment;
    private TeacherFragment teacherFragment;

    public static final String EMPTY = "choose";
    public static final String MAIN = "main";
    public static final String STUDENT = "student";
    public static final String TEACHER = "teacher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mainFragment = MainFragment.newInstance();
            teacherFragment = TeacherFragment.newInstance();
            studentFragment = StudentFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.container_main, mainFragment, MAIN).commit();
        } else {
            mainFragment = (MainFragment) fragmentManager.findFragmentByTag(MAIN);
            teacherFragment = (TeacherFragment) fragmentManager.findFragmentByTag(TEACHER);
            if(teacherFragment == null)
                teacherFragment = TeacherFragment.newInstance();
            studentFragment = (StudentFragment) fragmentManager.findFragmentByTag(STUDENT);
            if(studentFragment == null)
                studentFragment = StudentFragment.newInstance();
        }
    }

    @Override
    public void onInputStudentSent(CharSequence input) {
        if(mainFragment.isVisible())
            mainFragment.updateTextView(input);
    }

    @Override
    public void onInputTeacherSent(CharSequence input) {
        if(mainFragment.isVisible())
            mainFragment.updateTextView(input);
    }

    @Override
    public void onInputMainSent(CharSequence input) {

    }

    public void setType(String type, CharSequence info) {
        switch (type) {
            case STUDENT:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_sub, studentFragment, STUDENT).commitNow();
                studentFragment.updateTextView(info);
                break;
            case TEACHER:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_sub, teacherFragment, TEACHER).commitNow();
                teacherFragment.updateTextView(info);
        }
    }
}