package com.he.heshop;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView productName, productDescription, productPrice;
    private ImageView productImage;
    private Button addToCartButton, buyNowButton;
    private int currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productName = findViewById(R.id.product_name);
        productDescription = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);
        productImage = findViewById(R.id.product_image);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        buyNowButton = findViewById(R.id.buy_now_button);

        currentUserId = getIntent().getIntExtra("userId", -1);
        int productId = getIntent().getIntExtra("productId", -1);
        if (productId != -1) {
            loadProduct(productId);
        }

        addToCartButton.setOnClickListener(v -> {
            if (currentUserId != -1 && productId != -1) {
                addToCart(currentUserId, productId);
            }
        });
    }

    private void loadProduct(int productId) {
        SQLiteDatabase db = new com.he.heShop.DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = db.query("products", null, "id=?", new String[]{String.valueOf(productId)}, null, null, null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
            @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex("price"));
            @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex("image"));
            Bitmap image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

            productName.setText(name);
            productDescription.setText(description);
            productPrice.setText("¥" + price);
            productImage.setImageBitmap(image);
        }
        cursor.close();
    }

    private void addToCart(int userId, int productId) {
        SQLiteDatabase db = new com.he.heShop.DatabaseHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("product_id", productId);
        values.put("quantity", 1); // 默认数量为1

        long newRowId = db.insert("cart", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "已加入购物车", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "加入购物车失败", Toast.LENGTH_SHORT).show();
        }
    }
}
