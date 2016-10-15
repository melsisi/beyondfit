package beyondfit.bfpopulator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class ItemDetailActivityFragment extends Fragment {

    private static String message;

    private boolean showCookingStyleSpinner = false;
    private boolean showKindSpinner = false;
    private boolean showPartSpinner = false;
    private boolean showMeatPercentLinearLayout = false;
    private boolean showQuantityLinearLayout = false;

    private String itemName;

    public static AmazonClientManager clientManager = null;

    public ItemDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        clientManager = new AmazonClientManager(rootView.getContext());

        Intent intent = getActivity().getIntent();
        message = intent.getStringExtra("item");
        itemName = intent.getStringExtra("name");

        int part_string_array_index = 0;
        int kind_string_array_index = 0;

        Spinner spinner;
        ArrayAdapter<CharSequence> spinnerAdapter;
        LinearLayout linearLayout;

        String leftRadioButtonText = "";
        String middleRadioButtonText = "";
        String rightRadioButtonText = "";

        if(message.toLowerCase().equals("poultry")){
            part_string_array_index = R.array.item_poultry_part_string_array;
            kind_string_array_index = R.array.item_poultry_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
            showPartSpinner = true;
            showMeatPercentLinearLayout = true;
            showQuantityLinearLayout = true;
            leftRadioButtonText = "Whole (skin/bone)";
            middleRadioButtonText = "Boneless/skinless";
        }
        else if(message.toLowerCase().equals("red")){
            part_string_array_index = R.array.item_red_part_string_array;
            kind_string_array_index = R.array.item_red_kind_string_array;
            showCookingStyleSpinner = true;
            showPartSpinner = true;
            showKindSpinner = true;
            showMeatPercentLinearLayout = true;
            showQuantityLinearLayout = true;
            leftRadioButtonText = "Steak";
            middleRadioButtonText = "Ground/Minced/Kofta";
            rightRadioButtonText = "Kebab";
        }
        else if(message.toLowerCase().equals("fish")){
            kind_string_array_index = R.array.item_fish_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
            showQuantityLinearLayout = true;
            leftRadioButtonText = "Whole (skin/bone)";
            middleRadioButtonText = "Fillet";
        }
        else if(message.toLowerCase().equals("seafood")){
            kind_string_array_index = R.array.item_seafood_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
            showQuantityLinearLayout = true;
        }
        else if(message.toLowerCase().equals("processed meat")){
            kind_string_array_index = R.array.item_pmeat_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
            showMeatPercentLinearLayout = true;
            showQuantityLinearLayout = true;
        }
        else if(message.toLowerCase().equals("eggs")){
            kind_string_array_index = R.array.item_egg_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
            showQuantityLinearLayout = true;
        }
        else if(message.toLowerCase().equals("wheat")){
            kind_string_array_index = R.array.item_wheat_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("rice")){
            kind_string_array_index = R.array.item_rice_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("pasta")){
            kind_string_array_index = R.array.item_pasta_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("potatoes")){
            kind_string_array_index = R.array.item_potatoes_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("corn")){
            kind_string_array_index = R.array.item_corn_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("oats")){
            kind_string_array_index = R.array.item_oats_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("vegetables")) {
            kind_string_array_index = R.array.item_vegetables_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("fruits")) {
            kind_string_array_index = R.array.item_fruits_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("oils")) {
            kind_string_array_index = R.array.item_oils_kind_string_array;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("legumes")) {
            kind_string_array_index = R.array.item_legumes_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("soup")) {
            kind_string_array_index = R.array.item_soup_kind_string_array;
            showCookingStyleSpinner = true;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("beverage")) {
            kind_string_array_index = R.array.item_beverage_kind_string_array;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("chocolate")){
            kind_string_array_index = R.array.item_chocolate_kind_string_array;
            showKindSpinner = true;
            showQuantityLinearLayout = true;
            leftRadioButtonText = "Brand";
            middleRadioButtonText = "Cooking";
        }
        else if(message.toLowerCase().equals("milk")){
            kind_string_array_index = R.array.item_milk_kind_string_array;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("nuts")){
            kind_string_array_index = R.array.item_nuts_kind_string_array;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("cheese")){
            kind_string_array_index = R.array.item_cheese_kind_string_array;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("fine foods (or similar)")){
            showQuantityLinearLayout = true;
        }
        else if(message.toLowerCase().equals("sauce")){
            kind_string_array_index = R.array.item_sauce_kind_string_array;
            showKindSpinner = true;
        }
        else if(message.toLowerCase().equals("ice cream")){
            showQuantityLinearLayout = true;
        }

        spinner = (Spinner) rootView.findViewById(R.id.item_part_spinner);
        if(showPartSpinner) {
            spinnerAdapter = ArrayAdapter.createFromResource(rootView.getContext(),
                    part_string_array_index,
                    android.R.layout.simple_spinner_item);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
            linearLayout = (LinearLayout) rootView.findViewById(R.id.part_linear_layout);
            linearLayout.setVisibility(View.VISIBLE);
        }

        spinner = (Spinner) rootView.findViewById(R.id.item_cooking_style_spinner);
        if(showCookingStyleSpinner) {
            spinnerAdapter = ArrayAdapter.createFromResource(rootView.getContext(),
                    R.array.item_cooking_style_string_array,
                    android.R.layout.simple_spinner_item);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
            linearLayout = (LinearLayout) rootView.findViewById(R.id.cooking_linear_layout);
            linearLayout.setVisibility(View.VISIBLE);
        }

        spinner = (Spinner) rootView.findViewById(R.id.item_kind_spinner);
        if(showKindSpinner) {
            spinnerAdapter = ArrayAdapter.createFromResource(rootView.getContext(),
                    kind_string_array_index,
                    android.R.layout.simple_spinner_item);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
            linearLayout = (LinearLayout) rootView.findViewById(R.id.kind_linear_layout);
            linearLayout.setVisibility(View.VISIBLE);
        }

        if((leftRadioButtonText.length() +
                middleRadioButtonText.length() +
                rightRadioButtonText.length()) > 0) {
            linearLayout = (LinearLayout) rootView.findViewById(R.id.radio_button_linear_layout);
            linearLayout.setVisibility(View.VISIBLE);
            if(leftRadioButtonText.length() > 0)
                rootView.findViewById(R.id.left_radio_button).setVisibility(View.VISIBLE);
            if(middleRadioButtonText.length() > 0)
                rootView.findViewById(R.id.middle_radio_button).setVisibility(View.VISIBLE);
            if(rightRadioButtonText.length() > 0)
                rootView.findViewById(R.id.right_radio_button).setVisibility(View.VISIBLE);

            ((RadioButton)rootView.findViewById(R.id.left_radio_button)).
                    setText(leftRadioButtonText);
            ((RadioButton)rootView.findViewById(R.id.middle_radio_button)).
                    setText(middleRadioButtonText);
            ((RadioButton)rootView.findViewById(R.id.right_radio_button)).
                    setText(rightRadioButtonText);
        }

        if(showMeatPercentLinearLayout)
            rootView.findViewById(R.id.meat_percent_linear_layout).setVisibility(View.VISIBLE);

        if(showQuantityLinearLayout)
            rootView.findViewById(R.id.quantity_linear_layout).setVisibility(View.VISIBLE);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.commit_item_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Globals g = Globals.getInstance();
                BusinessMenu bM = g.getBusinessMenu();

                Map<String, Plate> plates = bM.getPlates();

                PlateItem newPlateItem = new PlateItem();

                /*******/

                newPlateItem.setMainCategory(getActivity().getIntent().getStringExtra("mainCategory"));

                /*******/

                newPlateItem.setSubCategory(getActivity().getIntent().getStringExtra("item"));

                /*******/

                if(((RadioButton) getActivity().findViewById(R.id.left_radio_button)).isChecked())
                    newPlateItem.setShape(((RadioButton) getActivity().findViewById(R.id.left_radio_button)).getText().toString());
                else if(((RadioButton) getActivity().findViewById(R.id.middle_radio_button)).isChecked())
                    newPlateItem.setShape(((RadioButton) getActivity().findViewById(R.id.middle_radio_button)).getText().toString());
                else if(((RadioButton) getActivity().findViewById(R.id.right_radio_button)).isChecked())
                    newPlateItem.setShape(((RadioButton) getActivity().findViewById(R.id.right_radio_button)).getText().toString());

                /*******/

                if(showKindSpinner) {
                    newPlateItem.setKind(((Spinner) getActivity().findViewById(R.id.item_kind_spinner))
                            .getSelectedItem().toString());
                }

                /*******/

                String meatString = ((EditText) getActivity().findViewById(R.id.meat_percent_textbox))
                        .getText().toString();
                int meatP = 0;
                if(meatString.length() > 0)
                    meatP = Integer.parseInt(meatString);

                String fatString = ((EditText) getActivity().findViewById(R.id.meat_percent_textbox))
                        .getText().toString();
                int fatP = 0;
                if(fatString.length() > 0)
                    fatP = Integer.parseInt(fatString);

                if(meatP !=0)
                   newPlateItem.setMeatPercent(meatP);
                else if(fatP != 0)
                    newPlateItem.setMeatPercent(100-fatP);

                /*******/

                if(showPartSpinner)
                    newPlateItem.setPart(((Spinner) getActivity().findViewById(R.id.item_part_spinner))
                        .getSelectedItem().toString());

                /*******/

                String quantityString = ((EditText) getActivity().findViewById(R.id.quantity_textbox))
                        .getText().toString();
                if(quantityString.length() > 0) {
                    int quantity = Integer.parseInt(quantityString);
                    newPlateItem.setQuantity(quantity);
                }

                /*******/

                String weightString = ((EditText) getActivity().findViewById(R.id.weight_textbox))
                        .getText().toString();
                if(weightString.length() > 0) {
                    double weight = Double.parseDouble(weightString);
                    newPlateItem.setWeight(weight);
                }

                /*******/

                if(showCookingStyleSpinner) {
                    String cookingTypeString = ((Spinner) getActivity().findViewById(R.id.item_cooking_style_spinner))
                            .getSelectedItem().toString();
                    if (cookingTypeString != null && cookingTypeString.length() > 0)
                        newPlateItem.setCookingType(cookingTypeString);
                }

                /*******/

                String saltString = ((EditText) getActivity().findViewById(R.id.salt_textbox))
                        .getText().toString();
                if(saltString.length() > 0) {
                    double salt = Double.parseDouble(saltString);
                    newPlateItem.setSalt(salt);
                }
                /*******/

                newPlateItem.setComments(((EditText) getActivity().findViewById(R.id.comments_textbox))
                        .getText().toString());

                /*******/

                Plate thisPlate;

                if(plates != null && !plates.containsKey(itemName)) {
                    thisPlate = new Plate();
                    plates.put(itemName, thisPlate);
                }
                else
                    thisPlate = plates.get(itemName);

                if(thisPlate.getDietaryRequirements() == null ||
                        ( thisPlate.getDietaryRequirements() != null && thisPlate.getDietaryRequirements().isEmpty()))
                    thisPlate.setDietaryRequirements(Globals.getInstance().getDietaryRequirements());

                List<PlateItem> thisPlateItems = thisPlate.getPlateItems();

                thisPlateItems.add(newPlateItem);

                g.setBusinessMenu(bM);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setMessage("Would you like to add more items to this plate?")
                        .setTitle("Add  more?");

                builder.setPositiveButton("Yes, add more items", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), AddItemActivity.class);
                        intent.putExtra("itemName", itemName);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No, I'm done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        new DynamoDBManagerTask()
                                .execute(DynamoDBManagerType.INSERT_ITEM);

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return rootView;
    }

}
