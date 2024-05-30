package com.he.heshop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class AddProductActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText productNameEditText, productDescriptionEditText, productPriceEditText;
    private Button addProductButton, uploadImageButton;
    private ImageView productImageView;
    private com.he.heShop.DatabaseHelper dbHelper;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        dbHelper = new com.he.heShop.DatabaseHelper(this);

        productNameEditText = findViewById(R.id.product_name_edit_text);
        productDescriptionEditText = findViewById(R.id.product_description_edit_text);
        productPriceEditText = findViewById(R.id.product_price_edit_text);
        addProductButton = findViewById(R.id.add_product_button);
        uploadImageButton = findViewById(R.id.upload_image_button);
        productImageView = findViewById(R.id.product_image_view);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                productImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   private String randomId(){
       Random random = new Random();

       // 生成1到5之间的随机数，表示数字的位数
       int digits = random.nextInt(5) + 1;

       // 使用StringBuilder来构建数字字符串
       StringBuilder numberBuilder = new StringBuilder();

       // 根据生成的位数添加随机数字
       for (int i = 0; i < digits; i++) {
           numberBuilder.append(random.nextInt(10)); // 0到9之间的随机数
       }

       return  numberBuilder.toString();

   }
    private void addProduct() {
        String name = productNameEditText.getText().toString();
        String description = productDescriptionEditText.getText().toString();
        int price = Integer.parseInt(productPriceEditText.getText().toString());

        BitmapDrawable drawable = (BitmapDrawable) productImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageBytes = stream.toByteArray();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("id",randomId());
        values.put("name", name);
        values.put("description", description);
        values.put("price", price);
        values.put("image", imageBytes);
        long newRowId = db.insert("products", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "产品添加成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "产品添加失败", Toast.LENGTH_SHORT).show();
        }
    }
}
