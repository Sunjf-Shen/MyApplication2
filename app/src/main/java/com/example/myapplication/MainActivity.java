package com.example.myapplication;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button login_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initView() {
        usernameEdit = (EditText) findViewById(R.id.usernameEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        login_Btn = (Button) findViewById(R.id.login_Btn);

        login_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_Btn:
                String user = usernameEdit.getText().toString();
                String pass = passwordEdit.getText().toString();
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://39.98.37.28:8085/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                Call<ResponseBody> login = service.getLogin(user, pass);
                login.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String string = response.body().string();
                            Log.e("aaa",""+string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                break;
        }
    }

    private void submit() {
        // validate
        String usernameEditString = usernameEdit.getText().toString().trim();
        if (TextUtils.isEmpty(usernameEditString)) {
            Toast.makeText(this, "用户名：", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordEditString = passwordEdit.getText().toString().trim();
        if (TextUtils.isEmpty(passwordEditString)) {
            Toast.makeText(this, "密码：", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
