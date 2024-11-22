package com.example.abhi_rao_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {
    private EditText etImageUrl;
    private ImageView ivPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        etImageUrl = findViewById(R.id.etImageUrl);
        ivPlaceholder = findViewById(R.id.ivPlaceholder);
        Button btnFetch = findViewById(R.id.btnFetch);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.btnBack);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etImageUrl = findViewById(R.id.etImageUrl);
                String imageUrl = etImageUrl.getText().toString();
                ImageView imageView = findViewById(R.id.ivPlaceholder);
                new ImageLoadTask(imageUrl, imageView).execute();
            }
        });



        btnSave.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("savedData", etImageUrl.getText().toString());
            setResult(RESULT_OK, returnIntent);
            //finish();
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private boolean isValidImageUrl(String url) {
        return url.matches("^(https?://.*\\.(?:png|jpg|jpeg|gif|bmp))$");
    }
}