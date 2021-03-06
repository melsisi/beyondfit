package beyondfit.bfpopulator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddItemActivityFragment extends Fragment {

    private boolean plateExists = false;

    public AddItemActivityFragment() {
    }

    final Globals g = Globals.getInstance();
    final BusinessMenu bM = g.getBusinessMenu();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_add_item, container, false);

        Intent intent = getActivity().getIntent();
        String prevPlateName = intent.getStringExtra("itemName");
        if(prevPlateName != null && prevPlateName.length() > 0){
            ((EditText) rootView.findViewById(R.id.item_name_text)).setText(prevPlateName);
            ((EditText) rootView.findViewById(R.id.item_name_text)).setEnabled(false);
            plateExists = true;
        }

        LinearLayout itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.meat_linearlayout);
        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("Animal Protein", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.carbs_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("carbs", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.veggies_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("vegetables", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.fruitss_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("fruits", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.oils_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("oils", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.legumes_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("legumes", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.soup_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("soup", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.beverage_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("beverage", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.misc_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed("misc", rootView);
            }
        });

        return rootView;
    }

    private void proceed(final String item, final View rootView) {

        String plateName = ((EditText) rootView.findViewById(R.id.item_name_text)).getText().toString();
        if(plateName.length() == 0) {
            Snackbar.make(rootView, "Menu Item name can't be empty!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }

        Map<String, Plate> plates = bM.getPlates();
        if(plates != null && plates.containsKey(plateName) && !plateExists) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(rootView.getContext());
            // Set the dialog title
            builder1.setTitle("Menu item '" + plateName + "' already exists \nDo you want to append to it?")
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
                            goToItemSelectedActivity(item, rootView);
                        }
                    });

            // Create the AlertDialog object and return it
            final AlertDialog dialog1 = builder1.create();
            dialog1.setCancelable(false);
            dialog1.show();
        } else {
            goToItemSelectedActivity(item, rootView);
        }


        /*if(!plateExists) {
            final ArrayList<String> mSelectedItems = new ArrayList();  // Where we track the selected items
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the dialog title
            builder.setTitle("Menu item contains (Check ALL that apply):")
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
                            // User clicked Done, save selected items and proceed

                            Globals.getInstance().setDietaryRequirements(mSelectedItems);

                            goToItemSelectedActivity(item, rootView);
                        }
                    });

            // Create the AlertDialog object and return it
            final AlertDialog dialog = builder.create();
            //dialog.setCancelable(false);
            dialog.show();
        }
        else //--x--
        */
            //goToItemSelectedActivity(item, rootView);
    }

    private void goToItemSelectedActivity(String item, View rootView) {
        String plateName = ((EditText) rootView.findViewById(R.id.item_name_text)).getText().toString();

        Intent intent;
        if(item.equals("Animal Protein") || item.equals("carbs") || item.equals("misc"))
            intent = new Intent(rootView.getContext(), ItemSelectedActivity.class);
        else
            intent = new Intent(rootView.getContext(), ItemDetailActivity.class);
        intent.putExtra("item", item);
        intent.putExtra("name", plateName);
        startActivity(intent);
    }


}
