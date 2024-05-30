package com.he.heshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        int currentUserId = getIntent().getIntExtra("userId", -1);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        loadProducts();

        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);

                intent.putExtra("userId", currentUserId);
                intent.putExtra("productId", productList.get(position).getId());
                startActivity(intent);



            }
        });
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
