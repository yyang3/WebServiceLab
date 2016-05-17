package yyang3.tacoma.uw.edu.webservicelab.authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.OutputStreamWriter;

import yyang3.tacoma.uw.edu.webservicelab.CourseActivity;
import yyang3.tacoma.uw.edu.webservicelab.R;

public class SignInActivity extends AppCompatActivity implements LoginFragment.LoginInterfaceListener{

    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        if(!mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN),false)) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new LoginFragment()).commit();
        } else {
            Intent temp = new Intent(this, CourseActivity.class);
            startActivity(temp);
            finish();
        }
        setContentView(R.layout.activity_sign_in);
    }


    @Override
    public void login(String userID, String pwd) {
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Check if the login and password are valid
            //new LoginTask().execute(url);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                        openFileOutput(getString(R.string.LOGIN_FILE)
                                , Context.MODE_PRIVATE));
                outputStreamWriter.write("email = " + userID + ";");
                outputStreamWriter.write("password = " + pwd);
                outputStreamWriter.close();
                Toast.makeText(this,"Stored in File Successfully!", Toast.LENGTH_LONG)
                        .show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No network connection available. Cannot authenticate user", Toast.LENGTH_SHORT).show();
            return;
        }
        mSharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), true);
        Intent temp = new Intent(this, CourseActivity.class);
        startActivity(temp);
        finish();
    }
}
