package com.example.abhi_rao_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    TextView placeholderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeholderText = findViewById(R.id.textPlaceholder);
        Button btnAccelerometer = findViewById(R.id.btnNavigateToAccelerometer);
        Button btnImage = findViewById(R.id.btnNavigateToImage);

        btnAccelerometer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AccelerometerActivity.class);
            startActivityForResult(intent, 1); // Use a request code to identify the result
        });

        btnImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ImageActivity.class);
            startActivityForResult(intent, 2); // Use a different request code
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update placeholder text based on saved data
        String savedData = getIntent().getStringExtra("savedData");
        if (savedData != null) {
            placeholderText.setText(savedData);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 1) {  // This is the request code for AccelerometerActivity
                String savedData = data.getStringExtra("savedData");
                if (savedData != null) {
                    TextView placeholderText = findViewById(R.id.textPlaceholder);
                    placeholderText.setText(savedData);
                }
            }
            if (requestCode == 2) {  // This is the request code for ImageActivity
                String imageUrl = data.getStringExtra("savedData");
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    ImageView imagePlaceholder = findViewById(R.id.imagePlaceholder);
                    Glide.with(this)
                            .load(imageUrl)
                            .into(imagePlaceholder);
                }
            }
        }
    }
}