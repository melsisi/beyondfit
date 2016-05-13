package beyondfit.bfpopulator;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddItemActivityFragment extends Fragment {

    public AddItemActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_add_item, container, false);

        Intent intent = getActivity().getIntent();
        String prevPlateName = intent.getStringExtra("itemName");
        if(prevPlateName != null && prevPlateName.length() > 0){
            ((EditText) rootView.findViewById(R.id.item_name_text)).setText(prevPlateName);
            ((EditText) rootView.findViewById(R.id.item_name_text)).setEnabled(false);
        }

        LinearLayout itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.meat_linearlayout);
        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("meat", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.carbs_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("carbs", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.veggies_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("vegetables", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.fruitss_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("fruits", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.oils_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("oils", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.legumes_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("legumes", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.soup_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("soup", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.beverage_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("beverage", rootView);
            }
        });

        itemLinearLayout = (LinearLayout)rootView.findViewById(R.id.misc_linearlayout);

        itemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToItemSelectedActivity("misc", rootView);
            }
        });

        return rootView;
    }

    private void goToItemSelectedActivity(String item, View rootView) {
        String plateName = ((EditText) rootView.findViewById(R.id.item_name_text)).getText().toString();
        if(plateName.length() == 0) {
            Snackbar.make(rootView, "Plate name can't be empty!", Snackbar.LENGTH_LONG)
                   .setAction("Action", null).show();
            return;
        }
        Intent intent;
        if(item.equals("meat") || item.equals("carbs") || item.equals("misc"))
            intent = new Intent(rootView.getContext(), ItemSelectedActivity.class);
        else
            intent = new Intent(rootView.getContext(), ItemDetailActivity.class);
        intent.putExtra("item", item);
        intent.putExtra("name", plateName);
        startActivity(intent);
    }
}
