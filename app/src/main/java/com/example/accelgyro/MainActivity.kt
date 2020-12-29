package com.example.accelgyro

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var textViewX: TextView
    private lateinit var textViewY: TextView
    private lateinit var textViewZ: TextView

    private lateinit var textViewX2: TextView
    private lateinit var textViewY2: TextView
    private lateinit var textViewZ2: TextView

    private lateinit var mSensorManager: SensorManager
    private lateinit var mAccelerometer: Sensor
    private lateinit var mGyro: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        textViewX = findViewById(R.id.textViewX)
        textViewY = findViewById(R.id.textViewY)
        textViewZ = findViewById(R.id.textViewZ)

        textViewX2 = findViewById(R.id.textViewX2)
        textViewY2 = findViewById(R.id.textViewY2)
        textViewZ2 = findViewById(R.id.textViewZ2)

//        Log.d("Accelerometer", "$sensor")
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mGyro, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.d("onSensorChanged", event?.sensor!!.name)
        if(event.sensor!!.name.contains("Accelerometer")) {
            textViewX.text = event.values[0].toString()
            textViewY.text = event.values[1].toString()
            textViewZ.text = event.values[2].toString()
        } else {
            textViewX2.text = event.values[0].toString()
            textViewY2.text = event.values[1].toString()
            textViewZ2.text = event.values[2].toString()

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}