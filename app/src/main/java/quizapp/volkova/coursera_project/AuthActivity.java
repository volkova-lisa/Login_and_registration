package quizapp.volkova.coursera_project;

import androidx.fragment.app.Fragment;

public class AuthActivity extends  SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return AuthFragment.newInstance();
    }
}
