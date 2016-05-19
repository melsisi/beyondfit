package beyondfit.bfpopulator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    public static AmazonClientManager clientManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clientManager = new AmazonClientManager(this);

        setContentView(R.layout.activity_activity_splash);

        animations();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(SplashActivity.this.getLayoutInflater().inflate(R.layout.dialog_signin, null))
                        // Add action buttons
                        .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...

                                AlertDialog d = (AlertDialog) dialog;
                                String usernameString = ((EditText) d.findViewById(R.id.username)).getText().toString();
                                Globals.getInstance().setBusinessID(usernameString);

                                try {
                                    Globals.getInstance().setBusinessName(new DynamoDBManagerTask().
                                            execute(DynamoDBManagerType.GET_BUSINESS_NAME).get().getName());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }

                                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public void animations(){
        final ImageView image = (ImageView)findViewById(R.id.splash_image_view);
        Animation animationMovepos = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_up);
        image.startAnimation(animationMovepos);
    }
}
