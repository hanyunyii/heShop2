package com.he.heshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ProductManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private Button addProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        recyclerView = findViewById(R.id.recycler_view);
        addProductButton = findViewById(R.id.add_product_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        // 这里你可以添加示例产品或从数据库加载产品
        loadProducts();

        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductManagementActivity.this, AddProductActivity.class));
            }
        });

//        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                // 这里可以添加编辑或删除产品的逻辑
//            }
//        });
    }

    private void loadProducts() {
        SQLiteDatabase db = new com.he.heShop.DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = db.query("products", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
            @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex("price"));
            @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex("image"));
            Bitmap image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            productList.add(new Product(name, description, price, image));
        }
        cursor.close();
    }
}
