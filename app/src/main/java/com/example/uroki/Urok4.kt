package com.example.uroki

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Urok4 : AppCompatActivity(), SensorEventListener {

    var manager:SensorManager? = null
    var current_degree:Int = 0
    var tvText_Urok4: TextView? = null
    var imageDinamic: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urok4)

        tvText_Urok4 = findViewById(R.id.tvText_Urok4)
        imageDinamic = findViewById(R.id.imageDinamic)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()

        manager?.registerListener(this, manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()

        manager?.unregisterListener(this)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        val degree:Int = p0?.values?.get(0)?.toInt()!!
        tvText_Urok4?.text = degree.toString()
        val rotationAnim = RotateAnimation(current_degree.toFloat(), (-degree).toFloat(),
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotationAnim.duration = 210
        rotationAnim.fillAfter = true
        current_degree = -degree
        imageDinamic?.startAnimation(rotationAnim)

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}