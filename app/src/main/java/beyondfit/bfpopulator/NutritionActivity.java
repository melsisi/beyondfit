package beyondfit.bfpopulator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NutritionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Add Nutrition Facts");

        final ArrayList<String> mSelectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle("Dietary requirements")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(R.array.plate_contains_array, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(
                                            getResources().getStringArray(R.array.plate_contains_array)[which]);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Globals.getInstance().setDietaryRequirements(mSelectedItems);
                    }
                });

        // Create the AlertDialog object and return it
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save_nutrition_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plateNameString = ((EditText) findViewById(R.id.plate_name_text_box))
                        .getText().toString();
                if(plateNameString.length() == 0) {
                    Snackbar.make(view, "Plate name can't be empty!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                Globals g = Globals.getInstance();
                BusinessMenu bM = g.getBusinessMenu();

                Map<String, Plate> plates = bM.getPlates();

                PlateItem newPlateItem = new PlateItem();

                String caloriesString = ((EditText) findViewById(R.id.calories_text_box))
                        .getText().toString();
                if(caloriesString.length() > 0)
                    newPlateItem.setCalories(Integer.parseInt(caloriesString));

                String proteinString = ((EditText) findViewById(R.id.protein_text_box))
                        .getText().toString();
                if(proteinString.length() > 0)
                    newPlateItem.setProtein(Integer.parseInt(proteinString));

                String carbsString = ((EditText) findViewById(R.id.carbs_text_box))
                        .getText().toString();
                if(carbsString.length() > 0)
                    newPlateItem.setCarbs(Integer.parseInt(carbsString));

                String starchString = ((EditText) findViewById(R.id.starch_text_box))
                        .getText().toString();
                if(starchString.length() > 0)
                    newPlateItem.setCarbsStarch(Integer.parseInt(starchString));

                String fiberString = ((EditText) findViewById(R.id.fiber_text_box))
                        .getText().toString();
                if(fiberString.length() > 0)
                    newPlateItem.setCarbsFiber(Integer.parseInt(fiberString));

                String sugarString = ((EditText) findViewById(R.id.sugar_text_box))
                        .getText().toString();
                if(sugarString.length() > 0)
                    newPlateItem.setCarbsSugar(Integer.parseInt(sugarString));

                String fatsString = ((EditText) findViewById(R.id.fats_text_box))
                        .getText().toString();
                if(fatsString.length() > 0)
                    newPlateItem.setFat(Integer.parseInt(fatsString));

                String satFatString = ((EditText) findViewById(R.id.sat_fat_text_box))
                        .getText().toString();
                if(satFatString.length() > 0)
                    newPlateItem.setFatSat(Integer.parseInt(satFatString));

                String unSatFatString = ((EditText) findViewById(R.id.unsat_fat_text_box))
                        .getText().toString();
                if(unSatFatString.length() > 0)
                    newPlateItem.setFatUnsat(Integer.parseInt(unSatFatString));

                String transFatString = ((EditText) findViewById(R.id.trans_fat_text_box))
                        .getText().toString();
                if(transFatString.length() > 0)
                    newPlateItem.setFatTrans(Integer.parseInt(transFatString));

                String glString = ((EditText) findViewById(R.id.gl_text_box))
                        .getText().toString();
                if(glString.length() > 0)
                    newPlateItem.setGL(Integer.parseInt(glString));

                String sodiumString = ((EditText) findViewById(R.id.sodium_text_box))
                        .getText().toString();
                if(sodiumString.length() > 0)
                    newPlateItem.setSalt(Integer.parseInt(sodiumString));

                Plate thisPlate;

                if(plates != null && !plates.containsKey(plateNameString)) {
                    thisPlate = new Plate();
                    plates.put(plateNameString, thisPlate);
                }
                else
                    thisPlate = plates.get(plateNameString);


                thisPlate.setDietaryRequirements(Globals.getInstance().getDietaryRequirements());

                List<PlateItem> thisPlateItems = thisPlate.getPlateItems();

                thisPlateItems.add(newPlateItem);

                g.setBusinessMenu(bM);

                new DynamoDBManagerTask()
                        .execute(DynamoDBManagerType.INSERT_ITEM);

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
