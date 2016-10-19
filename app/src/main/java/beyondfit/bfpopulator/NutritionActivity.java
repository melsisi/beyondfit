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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save_nutrition_fab);
        fab.setOnClickListener(new View.OnClickListener() {


            public void m (final View view, final String plateNameString, final Plate thisPlate, final PlateItem newPlateItem) {
                final ArrayList<String> mSelectedItems = new ArrayList();  // Where we track the selected items
                final String[] spicyLevel = new String[1];

                final Globals g = Globals.getInstance();
                final BusinessMenu bM = g.getBusinessMenu();

                //Multiple Choice
                AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
                // Set the dialog title
                builder1.setTitle("This menu item contains \n (Check ALL that apply!)")
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
                                            //} else if (mSelectedItems.contains(which)) {
                                        } else {
                                            // Else, if the item is already in the array, remove it
                                            mSelectedItems.remove(getResources().getStringArray(R.array.plate_contains_array)[which]);
                                            //mSelectedItems.remove(which);
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        })
                        // Set the action buttons
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                mSelectedItems.add(spicyLevel[0]);
                                Globals.getInstance().setDietaryRequirements(mSelectedItems);
                                thisPlate.setDietaryRequirements(Globals.getInstance().getDietaryRequirements());
                                List<PlateItem> thisPlateItems = thisPlate.getPlateItems();

                                thisPlateItems.add(newPlateItem);
                                final Map<String, Plate> plates = bM.getPlates();

                                if(plates != null && !plates.containsKey(plateNameString)) {
                                    plates.put(plateNameString, thisPlate);
                                }

                                g.setBusinessMenu(bM);
                                new DynamoDBManagerTask()
                                        .execute(DynamoDBManagerType.INSERT_ITEM);

                                Intent intent = new Intent(view.getContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        });

                // Create the AlertDialog object and return it
                final AlertDialog dialog1 = builder1.create();
                dialog1.setCancelable(false);
                dialog1.show();


                //Single Choice

                AlertDialog.Builder builder2 = new AlertDialog.Builder(view.getContext());
                // Set the dialog title
                builder2.setTitle("Spicy Level")
                        // Specify the list array, the items to be selected by default (null for none),
                        // and the listener through which to receive callbacks when items are selected
                        .setSingleChoiceItems (R.array.spicy_level, 0,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //mSelectedItems.add(
                                        //        getResources().getStringArray(R.array.spicy_level)[i]);
                                        spicyLevel[0] = getResources().getStringArray(R.array.spicy_level)[i];
                                    }
                                })
                        // Set the action buttons
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //Globals.getInstance().setDietaryRequirements(mSelectedItems);
                                dialog.dismiss();
                            }
                        });

                // Create the AlertDialog object and return it
                final AlertDialog dialog2 = builder2.create();
                dialog2.setCancelable(false);
                dialog2.show();
            }



            @Override
            public void onClick(final View view) {
                final String plateNameString = ((EditText) findViewById(R.id.plate_name_text_box))
                        .getText().toString();
                if(plateNameString.length() == 0) {
                    Snackbar.make(view, "Menu item name can't be empty!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                final Globals g = Globals.getInstance();
                final BusinessMenu bM = g.getBusinessMenu();

                final Map<String, Plate> plates = bM.getPlates();

                final PlateItem newPlateItem = new PlateItem();

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

                final Plate[] thisPlate = new Plate[1];

                if(plates != null && !plates.containsKey(plateNameString)) {
                    thisPlate[0] = new Plate();
                    //plates.put(plateNameString, thisPlate[0]);
                    m(view, plateNameString, thisPlate[0], newPlateItem);
                }
                else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
                    // Set the dialog title
                    builder1.setTitle("Menu item '" + plateNameString + "' already exists \nDo you want to append to it?")
                            // Specify the list array, the items to be selected by default (null for none),
                            // and the listener through which to receive callbacks when items are selected
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            })
                            // Set the action buttons
                            .setPositiveButton("Append", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    thisPlate[0] = plates.get(plateNameString);
                                    m(view, plateNameString, thisPlate[0], newPlateItem);
                                }
                            });

                    // Create the AlertDialog object and return it
                    final AlertDialog dialog1 = builder1.create();
                    dialog1.setCancelable(false);
                    dialog1.show();
                }
            }
        });

    }

}
