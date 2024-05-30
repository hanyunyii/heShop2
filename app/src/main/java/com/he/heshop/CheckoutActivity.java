package com.he.heshop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    private EditText addressEditText, phoneEditText;
    private Button placeOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        addressEditText = findViewById(R.id.address_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        placeOrderButton = findViewById(R.id.place_order_button);

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {
        String address = addressEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "请填写所有信息", Toast.LENGTH_SHORT).show();
            return;
        }

        // 这里可以添加将订单保存到本地数据库的逻辑

        Toast.makeText(this, "订单已提交", Toast.LENGTH_SHORT).show();
        finish();
    }
}
