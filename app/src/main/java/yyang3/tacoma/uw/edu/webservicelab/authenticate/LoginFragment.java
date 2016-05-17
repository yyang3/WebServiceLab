package yyang3.tacoma.uw.edu.webservicelab.authenticate;


import android.app.Fragment;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import yyang3.tacoma.uw.edu.webservicelab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        final EditText userIdText = (EditText) v.findViewById(R.id.sign_in_username);
        final EditText pwdText = (EditText) v.findViewById(R.id.sign_in_passw);
        Button signInButton = (Button) v.findViewById(R.id.sign_in_but);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = userIdText.getText().toString();
                String pwd = pwdText.getText().toString();
                if (TextUtils.isEmpty(userId))  {
                    Toast.makeText(v.getContext(), "Enter userid", Toast.LENGTH_SHORT).show();
                    userIdText.requestFocus();
                    return;             }
                if (!userId.contains("@")) {
                    Toast.makeText(v.getContext(), "Enter a valid email address", Toast.LENGTH_SHORT).show();
                    userIdText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pwd))  {
                    Toast.makeText(v.getContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    pwdText.requestFocus();
                    return;
                }
                if (pwd.length() < 6) {
                    Toast.makeText(v.getContext(), "Enter password of at least 6 characters", Toast.LENGTH_SHORT).show();
                    pwdText.requestFocus();
                    return;
                }
                ((SignInActivity) getActivity()).login(userId, pwd);
            }
        });
        return v;
    }

    public interface LoginInterfaceListener {
        public void login(String userID, String pwd);
    }

}
