package beyondfit.bfpopulator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Add Plate Details");
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle("Warning!")
                .setMessage("Nothing has been saved at this point. Discard inputs?")
                // Set the action buttons
                .setNegativeButton("Stay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        // Create the AlertDialog object and return it
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Set the dialog title
                builder.setTitle("Discarding")
                        .setMessage("Nothing has been saved at this point. Discarding inputs.")
                        // Set the action buttons
                        .setNegativeButton("Stay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });

                // Create the AlertDialog object and return it
                final AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
