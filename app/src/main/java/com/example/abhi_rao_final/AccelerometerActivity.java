package com.example.abhi_rao_final;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView tvXAxis, tvYAxis, tvZAxis, tvMagnitude;
    private boolean isCapturing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        tvXAxis = findViewById(R.id.tvXAxis);
        tvYAxis = findViewById(R.id.tvYAxis);
        tvZAxis = findViewById(R.id.tvZAxis);
        tvMagnitude = findViewById(R.id.tvMagnitude);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnBack = findViewById(R.id.btnBack);

        btnStart.setOnClickListener(v -> {
            isCapturing = true;
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        });

        btnStop.setOnClickListener(v -> {
            isCapturing = false;
            sensorManager.unregisterListener(this);
        });

        btnSave.setOnClickListener(v -> {
            Intent returnIntent = new Intent();
            String data = "X: " + tvXAxis.getText().toString() + "\n" +
                    "Y: " + tvYAxis.getText().toString() + "\n" +
                    "Z: " + tvZAxis.getText().toString() + "\n" +
                    "Magnitude: " + tvMagnitude.getText().toString();
            returnIntent.putExtra("savedData", data);
            setResult(RESULT_OK, returnIntent);
            //finish();
        });

        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && isCapturing) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            float magnitude = (float) Math.sqrt(x*x + y*y + z*z);
            tvXAxis.setText("X: " + x);
            tvYAxis.setText("Y: " + y);
            tvZAxis.setText("Z: " + z);
            tvMagnitude.setText("Magnitude: " + magnitude);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this application
    }
}