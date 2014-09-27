package android.diazb.crystalball;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.FloatMath;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class CrystalBall extends Activity {

    private TextView answerText;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float acceleration;
    private float currentAcceleration;
    private float previousAcceleration;

    long currentTime;
    long previousTime;
    long delay;
    long elapsed;


    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];


            previousTime = currentTime;
            currentTime = System.currentTimeMillis();
            elapsed = currentTime - previousTime;
            delay = delay + elapsed;

            previousAcceleration = currentAcceleration;
            currentAcceleration = FloatMath.sqrt(x * x + y * y + z * z );
            float delta = currentAcceleration - previousAcceleration;
            acceleration = acceleration * 0.9f + delta;

            ImageView img=(ImageView)findViewById(R.id.animation);
            img.setBackgroundResource(R.drawable.animation);

            AnimationDrawable animation=(AnimationDrawable)img.getBackground();


            img = (ImageView) findViewById(R.id.animation);
            img.setBackgroundResource(R.drawable.animation);

            animation = (AnimationDrawable) img.getBackground();

            if(acceleration > 20 && delay >= 4500) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.crystal_ball);
                mediaPlayer.start();

                if (animation.isRunning()) {

                    animation.stop();
                    animation.start();
                }

                else {
                    animation.start();

                }

                new CountDownTimer(1700, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {

                        answerText = (TextView) findViewById(R.id.answerText);
                        answerText.setText(Predictions.get().getPredictions());

                        answerText.startAnimation(AnimationUtils.loadAnimation(CrystalBall.this, android.R.anim.fade_in));

                        new CountDownTimer(2500, 1000) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {

                                answerText.startAnimation(AnimationUtils.loadAnimation(CrystalBall.this, android.R.anim.fade_out));

                                new CountDownTimer(350, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {

                                        answerText.setText("");

                                    }
                                }.start();

                            }
                        }.start();

                    }
                }.start();

                delay = 0;

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

        delay = 5000;
        previousTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();

        sensorManager = (SensorManager)getSystemService (Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        acceleration = 0.0f;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        previousAcceleration = SensorManager.GRAVITY_EARTH;

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
