package com.example.praba.prakmob;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.praba.prakmob.api.ApiClient;
import com.example.praba.prakmob.api.ApiService;
import com.example.praba.prakmob.model.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername,etPassword;
    Button btnLogin;
    String inputUsername, inputPassword;
    PreferenceHelper preferencesHelper;
//    private final String USERNAME="admin";
//    private final String PASSWORD="123456";
    ApiService service;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ApiClient.getService();
        preferencesHelper=new PreferenceHelper(getApplicationContext());

        setContentView(R.layout.login_layout);
        etUsername=findViewById(R.id.et_username);
        etPassword=findViewById(R.id.et_password);
        btnLogin=findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            inputUsername=etUsername.getText().toString();
            inputPassword=etPassword.getText().toString();
            service.login(inputUsername,inputPassword)
                .enqueue(new Callback<UserLogin>() {
                    @Override
                    public void onResponse(Call<UserLogin> call, retrofit2.Response<UserLogin> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                            preferencesHelper.setLogin(true);
                            preferencesHelper.setNama(inputUsername);
                            sharedPreferences=getSharedPreferences("user", Context.MODE_PRIVATE);

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLogin> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error :"+t, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

}
