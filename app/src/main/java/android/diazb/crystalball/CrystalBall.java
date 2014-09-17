package android.diazb.crystalball;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class CrystalBall extends Activity {

    private TextView answerText;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float acceleration;
    private float currentAcceleration;
    private float previousAcceleration;

    private final SensorEventListener sensorListener= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x=event.values[0];
            float y=event.values[1];
            float z=event.values[2];

            previousAcceleration=currentAcceleration;
            currentAcceleration= FloatMath.sqrt(x*x+y*y+z*z);
            float delta=currentAcceleration-previousAcceleration;
            acceleration=acceleration*0.9f+delta;

            if(acceleration >20) {
                Toast toast = Toast.makeText(getApplication(), "And now, the results...", Toast.LENGTH_SHORT);
                toast.show();
                answerText.setText(Predictions.get().getPrediction());
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.crystal_ball);
                mediaPlayer.start();

                // start the animation
                answerText.startAnimation(AnimationUtils.loadAnimation(CrystalBall.this, android.R.anim.fade_in));

                int rando=(int) ((Math.random()*10+1));

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crystal_ball);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        acceleration = 0.0f;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        previousAcceleration = SensorManager.GRAVITY_EARTH;

        answerText = (TextView) findViewById(R.id.answerText);

    }

    public static void main(String[] args){
        Random r = new Random();
        int number;

        for(int counter=1; counter<=10;counter++){
            number=1+r.nextInt(6);
            System.out.printIn(number+"");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }

}
