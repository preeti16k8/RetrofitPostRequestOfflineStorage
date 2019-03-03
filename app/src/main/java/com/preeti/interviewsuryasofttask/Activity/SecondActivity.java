package com.preeti.interviewsuryasofttask.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.preeti.interviewsuryasofttask.Network.APIClient;
import com.preeti.interviewsuryasofttask.Network.APIInterface;
import com.preeti.interviewsuryasofttask.Adapter.CustomAdapter;
import com.preeti.interviewsuryasofttask.Network.CommonMethod;
import com.preeti.interviewsuryasofttask.Database.DataHelper;
import com.preeti.interviewsuryasofttask.Model.EmailAddress;
import com.preeti.interviewsuryasofttask.Model.LoginResponse;
import com.preeti.interviewsuryasofttask.R;
import com.preeti.interviewsuryasofttask.Model.UserInformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    List<LoginResponse> dummylist;
    DataHelper dbHelper;
    List<UserInformation> userinfomodels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dummylist=new ArrayList<>();
        progressDoalog = new ProgressDialog(SecondActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String Email=bundle.getString("emaill");
        dbHelper = new DataHelper(this);

            if (CommonMethod.isNetworkAvailable(SecondActivity.this)) {
                loginRetrofit2Api(Email);
            }
            else {
                getFromDatabase();
            }

    }


    private void getFromDatabase(){
        if (dbHelper.getUserCount() != 0) {
            Cursor cursor = dbHelper.getuser();
            while(cursor.moveToNext()){
                String email  =cursor.getString(0);
                String fname=cursor.getString(1);
                String lname=cursor.getString(2);
                String imageurl=cursor.getString(3);
                UserInformation p = new UserInformation();
                p.setEmailId(email);
                p.setFirstName(fname);
                p.setLastName(lname);
                p.setImageUrl(imageurl);
                userinfomodels.add(p);
            }

            generateDataList(userinfomodels);
            progressDoalog.dismiss();
        }
        else {

            CommonMethod.showAlert("Internet Connectivity Failure", SecondActivity.this);
        }


    }


    private void loginRetrofit2Api(String Email) {

        EmailAddress emailAddress=new EmailAddress();
        emailAddress.setEmailId(Email);
        APIInterface service = APIClient.getClient().create(APIInterface.class);
        Call<LoginResponse> call=service.getFriendsLocation(emailAddress);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse=response.body();

                List<UserInformation> userinforamtion=loginResponse.getItems();

                dbHelper.deleteUser();
                progressDoalog.dismiss();
                generateDataList(userinforamtion);

                Log.i("Data",userinforamtion.toString());

                for(int i=0;i<loginResponse.getItems().size();i++){
                    Log.d("EmailsList",loginResponse.items.get(i).emailId);
                    String userEmail=loginResponse.items.get(i).emailId;
                    String fname=loginResponse.items.get(i).firstName;
                    String lname=loginResponse.items.get(i).lastName;
                    String userImage=loginResponse.items.get(i).imageUrl;

                    dbHelper.adduser(SecondActivity.this, userEmail,fname, lname, userImage);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(SecondActivity.this, "Something went wrong...Please try later!"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateDataList(List<UserInformation> dummylist) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,dummylist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
