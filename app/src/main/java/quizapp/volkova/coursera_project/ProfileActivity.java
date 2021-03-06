package quizapp.volkova.coursera_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    public static final String USER_KEY = "USER_KEY";
    public static final int REQUEST_CODE_PHOTO = 101;
    //public static String PASS_KEY = "PASS_KEY";

    private AppCompatImageView mPhoto;
    private TextView mLogin;
    private TextView mPassword;

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_PHOTO && resultCode == Activity.RESULT_OK && data != null) {
            Uri photoUri = data.getData();
            mPhoto.setImageURI(photoUri);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private View.OnClickListener mOnPhotoClickListener = v -> {
        openGallery();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_profile);

        mPhoto = findViewById(R.id.ivPhoto);
        mLogin = findViewById(R.id.tvEmail);
        mPassword = findViewById(R.id.tvPassword);

        Bundle bundle = getIntent().getExtras();
        User user = (User) bundle.get(USER_KEY);
        mLogin.setText(user.getmLogin());
        mPassword.setText(user.getmPassword());
        //mLogin.setText(bundle.getString(USER_KEY));
        //mPassword.setText(bundle.getString(PASS_KEY));


        mPhoto.setOnClickListener(mOnPhotoClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionLogout:
                startActivity(new Intent(this, AuthActivity.class));
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
}