package com.preeti.interviewsuryasofttask;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView responseText;
    APIInterface apiInterface;

    Button loginSub;
    EditText et_Email;
    EditText et_Pass;
    private Dialog mDialog;
    String userId;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        loginSub = (Button) findViewById(R.id.btn_submit);
        et_Email = (EditText) findViewById(R.id.edit_email);



        loginSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonMethod.isNetworkAvailable(MainActivity.this))
                    loginRetrofit2Api(et_Email.getText().toString());
                else
                    CommonMethod.showAlert("Internet Connectivity Failure", MainActivity.this);
            }

        });
    }


    private void loginRetrofit2Api(String Email) {
        final LoginResponse login = new LoginResponse(Email);
        Call<LoginResponse> call1 = apiInterface.createUser(login);
        call1.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                Log.d("loginrespspp", String.valueOf(loginResponse));

                Log.e("preeti", "loginResponse 1 --> " + loginResponse);
                if (loginResponse != null) {
                    Log.e("preeti", "getUserId          -->  " + loginResponse.emailId);
                    Log.e("preeti", "getFirstName       -->  " + loginResponse.getFirstName());
                    Log.e("preeti", "getLastName        -->  " + loginResponse.getLastName());
                    Log.e("preeti", "getProfilePicture  -->  " + loginResponse.getImageUrl());



                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "onFailure called ", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }

    public boolean checkValidation() {
        userId = et_Email.getText().toString();
        password = et_Pass.getText().toString();

        Log.e("Keshav", "userId is -> " + userId);
        Log.e("Keshav", "password is -> " + password);

        if (et_Email.getText().toString().trim().equals("")) {
            CommonMethod.showAlert("UserId Cannot be left blank", MainActivity.this);
            return false;
        } else if (et_Pass.getText().toString().trim().equals("")) {
            CommonMethod.showAlert("password Cannot be left blank", MainActivity.this);
            return false;
        }
        return true;
    }
}
