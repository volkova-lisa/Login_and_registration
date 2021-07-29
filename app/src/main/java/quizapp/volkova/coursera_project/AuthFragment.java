package quizapp.volkova.coursera_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public class AuthFragment extends Fragment {

    private EditText loginField;
    private EditText passField;
    private Button loginButton;
    private Button regButton;

    private SharedPreferencesHelper mSharedPreferencesHelper;

    public static AuthFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener logButtonClick = v -> {
        //todo log in

        for (User user: mSharedPreferencesHelper.getUsers()) {
            if(user.getmLogin().equalsIgnoreCase(loginField.getText().toString())
                && user.getmPassword().equals(passField.getText().toString())) {

            }
        }
        if (isEmailValid() && isPassValid()) {
            //enter app
            Intent startProfileIntent =
                    new Intent(getActivity(), ProfileActivity.class);
            startProfileIntent.putExtra(ProfileActivity.USER_KEY,
                    new User(loginField.getText().toString(), passField.getText().toString()));
            //startProfileIntent.putExtra(ProfileActivity.PASS_KEY, passField.getText().toString());
            startActivity(startProfileIntent);
            getActivity().finish();
        }
        else {
            showToast(R.string.login_error);
        }
    };

    private View.OnClickListener regButtonClick = v -> {
        //todo register IT WAS HARD



        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, RegistrationFragment.newInstance())
                .commit();

    };
    private LayoutInflater inflater;
    @Nullable
    private ViewGroup container;
    @Nullable
    private Bundle savedInstanceState;



    private boolean isEmailValid() {
       return !TextUtils.isEmpty(loginField.getText())
               && Patterns.EMAIL_ADDRESS.matcher(loginField.getText()).matches();
    }
    private boolean isPassValid() {
        return !TextUtils.isEmpty(passField.getText());
    }

    private void showToast(@StringRes int message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        
        View v = inflater.inflate(R.layout.ac_auth, container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        loginField = v.findViewById(R.id.etLogin);
        passField= v.findViewById(R.id.etPassword);
        loginButton= v.findViewById(R.id.logButton);
        regButton = v.findViewById(R.id.regButton);

        loginButton.setOnClickListener(logButtonClick);
        regButton.setOnClickListener(regButtonClick);

        return v;
    }

}