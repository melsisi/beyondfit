package beyondfit.bfpopulator;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_activity_splash);

        animations();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    public void animations(){

        final ImageView image = (ImageView)findViewById(R.id.splash_image_view);
        Animation AnimationMovepos = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_up);
        image.startAnimation(AnimationMovepos);

    }
}
