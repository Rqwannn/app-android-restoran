package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Response.ResponseAPI;
import com.example.myapplication.ServerManage.APIRequest;
import com.example.myapplication.ServerManage.RetroServer;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextView username, password;
    MaterialButton submit;
    SharedPreferences SessionManagement;
    SharedPreferences.Editor SessionEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);

        SessionManagement = getSharedPreferences("AUTH", MODE_PRIVATE);

        if (SessionManagement.getBoolean("Submit", false)){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = username.getText().toString();
                String newPassword = password.getText().toString();

                if (newUsername.equals("") || newPassword.equals("")){
                    Toast.makeText(Login.this, "Mohon Isi Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    APIRequest CallServer = new RetroServer().KonekToDB().create(APIRequest.class);
                    Call<ResponseAPI> FeedBack = CallServer.Auth(
                            newUsername,
                            newPassword
                    );

                    FeedBack.enqueue(new Callback<ResponseAPI>() {
                        @Override
                        public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                            if (response.body().isStatus()){
                                SessionEditor = SessionManagement.edit();
                                SessionEditor.putString("Username", response.body().getUser().getUsername());
                                SessionEditor.putBoolean("Submit", response.body().isStatus());
                                SessionEditor.putString("Roles", response.body().getUser().getRoles());

                                SessionEditor.apply();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.enter_right_to_right, R.anim.exit_right_to_left);
                            } else {
                                Toast.makeText(Login.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseAPI> call, Throwable t) {
                            Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
}