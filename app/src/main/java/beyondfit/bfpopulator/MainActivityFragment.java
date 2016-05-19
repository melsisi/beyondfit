package beyondfit.bfpopulator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

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

        Map<String, Plate> plates = Globals.getInstance().getBusinessMenu().getPlates();

        if(plates != null && plates.size() > 0) {
            TextView emptyTextView = (TextView) rootView.findViewById(R.id.empty_textview);
            emptyTextView.setVisibility(View.GONE);

            ListView menuItemsListView = (ListView) rootView.findViewById(R.id.menu_items_listview);
            menuItemsListView.setVisibility(View.VISIBLE);

            Set<String> plateNames = plates.keySet();

            ListMenuItemAdapter customAdapter = new ListMenuItemAdapter(getContext(),
                    R.layout.menu_item_list,
                    R.id.menu_items_listview,
                    new ArrayList<String>(plateNames));

            menuItemsListView.setAdapter(customAdapter);
        }

        return rootView;
    }
}
