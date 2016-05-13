package beyondfit.bfpopulator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by melsisi on 4/21/2016.
 */
public class ListMenuItemAdapter extends ArrayAdapter<String> {

    public ListMenuItemAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListMenuItemAdapter(Context context, int resource, int textViewResourceId, List<String> plates) {
        super(context, resource, textViewResourceId, plates);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.menu_item_list, null);
        }

        String plate = getItem(position);

        if (plate != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.menu_item_text_view);
            tt1.setTextSize(20);

            if (tt1 != null) {
                tt1.setText(plate);
            }
        }

        return v;
    }

}
