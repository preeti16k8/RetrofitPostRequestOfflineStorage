package com.preeti.interviewsuryasofttask.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.preeti.interviewsuryasofttask.Network.APIClient;
import com.preeti.interviewsuryasofttask.Network.APIInterface;
import com.preeti.interviewsuryasofttask.R;

public class MainActivity extends AppCompatActivity {

    APIInterface apiInterface;
    Button loginSub;
    EditText et_Email;
    String email="";

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
                userLogin();
            }

        });
    }


    private void userLogin(){

        email = et_Email.getText().toString().trim();
        if (email.isEmpty()) {
            et_Email.setError("Email is required");
            et_Email.requestFocus();
            return ;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_Email.setError("Enter a valid email");
            et_Email.requestFocus();
            return;
        }

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("emaill", et_Email.getText().toString());

        startActivity(intent);
    }


}
