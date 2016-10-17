package beyondfit.bfpopulator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        RawMenuItem tempRawMenuItem = null;
        try {
            tempRawMenuItem = new DynamoDBManagerTask().
                    execute(DynamoDBManagerType.GET_MENU).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(tempRawMenuItem != null && tempRawMenuItem.getMenu() != null) {
            try {
                Globals.getInstance().setBusinessMenu(Serializer.deserialize(tempRawMenuItem.getMenu()));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        final Map<String, Plate> plates = Globals.getInstance().getBusinessMenu().getPlates();

        if(plates != null && plates.size() > 0) {
            TextView emptyTextView = (TextView) rootView.findViewById(R.id.empty_textview);
            emptyTextView.setVisibility(View.GONE);

            ListView menuItemsListView = (ListView) rootView.findViewById(R.id.menu_items_listview);
            menuItemsListView.setVisibility(View.VISIBLE);

            Set<String> plateNames = plates.keySet();

            ListMenuItemAdapter customAdapter = new ListMenuItemAdapter(getContext(),
                    R.layout.menu_item_list,
                    R.id.menu_items_listview,
                    new ArrayList(plateNames));

            menuItemsListView.setAdapter(customAdapter);

            menuItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long rowId) {

                    // Creating Dialog builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());

                    // Creating LinearLayout that will contain all dynamically generated plate
                    // item peek forms, separator lines and Textviews
                    LinearLayout layout = new LinearLayout(rootView.getContext(), null);
                    layout.setOrientation(LinearLayout.VERTICAL);

                    // Creating ScrollView to allow for scrolling through plate items
                    ScrollView sv = new ScrollView(rootView.getContext());

                    Plate thisPlate = plates.get(parent.getItemAtPosition(position).toString());
                    int itemNumber = 0;
                    for (PlateItem item:
                         thisPlate.getPlateItems()) {

                        // Creating TextView to display item number
                        TextView itemNumberTextView = new TextView(rootView.getContext());
                        itemNumberTextView.setText("Plate item: " + ++itemNumber);
                        layout.addView(itemNumberTextView);

                        // Creating peek form view
                        View tempView = rootView.inflate(rootView.getContext(), R.layout.plate_peek, null);
                        // Populating peek form
                        ((EditText) tempView.findViewById(R.id.category_edit_text)).
                                setText(item.getMainCategory());
                        ((EditText) tempView.findViewById(R.id.subcategory_edit_text)).
                                setText(item.getSubCategory());
                        ((EditText) tempView.findViewById(R.id.kind_edit_text)).
                                setText(item.getKind());
                        ((EditText) tempView.findViewById(R.id.meat_percent_textbox)).
                                setText(String.valueOf(item.getMeatPercent()));
                        ((EditText) tempView.findViewById(R.id.fat_percent_textbox)).
                                setText(String.valueOf(100 - item.getMeatPercent()));
                        ((EditText) tempView.findViewById(R.id.part_edit_text)).
                                setText(item.getPart());
                        ((EditText) tempView.findViewById(R.id.quantity_textbox)).
                                setText(String.valueOf(item.getQuantity()));
                        ((EditText) tempView.findViewById(R.id.weight_textbox)).
                                setText(String.valueOf(item.getWeight()));
                        ((EditText) tempView.findViewById(R.id.cooking_edit_text)).
                                setText(item.getCookingType());
                        ((EditText) tempView.findViewById(R.id.comments_textbox)).
                                setText(item.getComments());
                        ((EditText) tempView.findViewById(R.id.protein_text_box)).
                                setText(String.valueOf(item.getProtein()));
                        ((EditText) tempView.findViewById(R.id.carbs_text_box)).
                                setText(String.valueOf(item.getCarbs()));
                        ((EditText) tempView.findViewById(R.id.starch_text_box)).
                                setText(String.valueOf(item.getCarbsStarch()));
                        ((EditText) tempView.findViewById(R.id.fiber_text_box)).
                                setText(String.valueOf(item.getCarbsFiber()));
                        ((EditText) tempView.findViewById(R.id.sugar_text_box)).
                                setText(String.valueOf(item.getCarbsSugar()));
                        ((EditText) tempView.findViewById(R.id.fats_text_box)).
                                setText(String.valueOf(item.getFat()));
                        ((EditText) tempView.findViewById(R.id.sat_fat_text_box)).
                                setText(String.valueOf(item.getFatSat()));
                        ((EditText) tempView.findViewById(R.id.unsat_fat_text_box)).
                                setText(String.valueOf(item.getFatUnsat()));
                        ((EditText) tempView.findViewById(R.id.trans_fat_text_box)).
                                setText(String.valueOf(item.getFatTrans()));
                        ((EditText) tempView.findViewById(R.id.calories_text_box)).
                                setText(String.valueOf(item.getCalories()));
                        ((EditText) tempView.findViewById(R.id.gl_text_box)).
                                setText(String.valueOf(item.getGL()));
                        ((EditText) tempView.findViewById(R.id.salt_text_box)).
                                setText(String.valueOf(item.getSalt()));
                        ((EditText) tempView.findViewById(R.id.diet_text_box)).
                                setText(String.valueOf(thisPlate.getDietaryRequirements()));
                        layout.addView(tempView);

                        // Creating separator view
                        View v = new View(tempView.getContext());
                        v.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                5
                        ));
                        v.setBackgroundColor(Color.parseColor("#B3B3B3"));
                        layout.addView(v);
                    }
                    sv.addView(layout);
                    builder.setView(sv)
                            // Add action buttons
                            .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();

                                }
                            });

                    final AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }

        return rootView;
    }
}
