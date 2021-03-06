package beyondfit.bfpopulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ItemSelectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getSupportActionBar().setTitle("Add Menu Item");
        Bundle bundle = getIntent().getExtras();
        getSupportActionBar().setTitle("Add "+ bundle.getString("item"));
    }

}
