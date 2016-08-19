package beyondfit.bfpopulator;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ItemSelectedActivityFragment extends Fragment {

    private static String message;
    private static String itemName;

    public ItemSelectedActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_item_selected, container, false);

        ListView itemDetailsListView = (ListView) rootView.findViewById(R.id.item_details_listview);

        Intent prevIntent = getActivity().getIntent();
        if(prevIntent.getStringExtra("item") != null)
            message = prevIntent.getStringExtra("item");

        itemName = prevIntent.getStringExtra("name");

        if(message == null)
            return rootView;

        List<String> itemDetailsArrayList = null;

        if(message.equals("meat"))
            itemDetailsArrayList = new ArrayList<>(Arrays.asList(
                    getResources().getStringArray(R.array.item_meat_string_array)));
        else if(message.equals("carbs"))
            itemDetailsArrayList = new ArrayList<>(Arrays.asList(
                    getResources().getStringArray(R.array.item_carbs_string_array)));
        else if(message.equals("misc"))
            itemDetailsArrayList = new ArrayList<>(Arrays.asList(
                    getResources().getStringArray(R.array.item_misc_string_array)));

        ArrayAdapter<String> itemDetailsArrayAdapter = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_list_item_1,
                itemDetailsArrayList);

        itemDetailsListView.setAdapter(itemDetailsArrayAdapter);

        itemDetailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);

                Intent intent = new Intent(view.getContext(), ItemDetailActivity.class);
                intent.putExtra("item", item);
                intent.putExtra("mainCategory", message);
                intent.putExtra("name", itemName);
                startActivity(intent);
            }
        }
        );

        return rootView;
    }
}
