package com.he.heshop;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> cartProductList;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        recyclerView = findViewById(R.id.recycler_view);
        checkoutButton = findViewById(R.id.checkout_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartProductList = new ArrayList<>();
        // 从购物车中加载产品
        loadCartProducts();

        productAdapter = new ProductAdapter(cartProductList);
        recyclerView.setAdapter(productAdapter);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartProductList.isEmpty()) {
                    Toast.makeText(ShoppingCartActivity.this, "购物车为空", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(ShoppingCartActivity.this, CheckoutActivity.class));
                }
            }
        });
    }

    private void loadCartProducts() {
        // 从本地数据库或内存中加载购物车中的产品
        // 示例代码中，我们直接添加一些测试数据
        cartProductList.add(new Product("购物车商品1", "这是购物车中的商品1", 100, null));
        cartProductList.add(new Product("购物车商品2", "这是购物车中的商品2", 80, null));
    }
}
