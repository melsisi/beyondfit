package beyondfit.bfpopulator;

import android.os.AsyncTask;

/**
 * Created by melsisi on 4/27/2016.
 */
class DynamoDBManagerTask extends
        AsyncTask<DynamoDBManagerType, Void, RawMenuItem> {

    protected RawMenuItem doInBackground(
            DynamoDBManagerType... types) {

        RawMenuItem toReturn = new RawMenuItem();
        String tableStatus = DynamoDBManager.getTestTableStatus();

        if (types[0] == DynamoDBManagerType.GET_TABLE_STATUS) {
            toReturn.setName(tableStatus);
        } else if (types[0] == DynamoDBManagerType.INSERT_ITEM) {
            if (tableStatus.equals("ACTIVE")) {
                DynamoDBManager.insertMenuItem();
            }
        } else if (types[0] == DynamoDBManagerType.GET_MENU) {
            if (tableStatus.equals("ACTIVE")) {
                toReturn = DynamoDBManager.getMenuForRestaurant("Test Restaurant");
            }
        }

        return toReturn;
    }

    /*protected void onPostExecute(DynamoDBManagerTaskResult result) {

        if (result.getTableStatus().equalsIgnoreCase("ACTIVE")
                && result.getTaskType() == DynamoDBManagerType.INSERT_ITEM) {
            Toast.makeText(UserPreferenceDemoActivity.this,
                    "Users inserted successfully!", Toast.LENGTH_SHORT).show();
        }
    }*/
}
