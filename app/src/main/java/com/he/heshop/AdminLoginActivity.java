package com.he.heshop;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText adminUsernameEditText, adminPasswordEditText;
    private Button adminLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminUsernameEditText = findViewById(R.id.admin_username_edit_text);
        adminPasswordEditText = findViewById(R.id.admin_password_edit_text);
                adminLoginButton = findViewById(R.id.admin_login_button);

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminLogin();
            }
        });
    }

    private void adminLogin() {
        String username = adminUsernameEditText.getText().toString();
        String password = adminPasswordEditText.getText().toString();

        // 简单的用户名密码验证，实际开发中应使用更安全的方式
        if (username.equals("admin") && password.equals("admin")) {
            Toast.makeText(this, "管理员登录成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminLoginActivity.this, ProductManagementActivity.class));
        } else {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
