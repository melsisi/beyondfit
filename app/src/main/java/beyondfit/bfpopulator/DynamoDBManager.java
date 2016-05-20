package beyondfit.bfpopulator;

import android.util.Log;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by melsisi on 4/27/2016.
 */
public class DynamoDBManager {

    private static final String TAG = "DynamoDBManager";

    /*
     * Retrieves the table description and returns the table status as a string.
     */
    public static String getTestTableStatus() {

        try {
            AmazonDynamoDBClient ddb = SplashActivity.clientManager
                    .ddb();

            DescribeTableRequest request = new DescribeTableRequest()
                    .withTableName(SplashActivity.
                            clientManager.getContext().getString(R.string.raw_menu_items_table));
            DescribeTableResult result = ddb.describeTable(request);

            String status = result.getTable().getTableStatus();
            return status == null ? "" : status;

        } catch (ResourceNotFoundException e) {
        } catch (AmazonServiceException ex) {
            SplashActivity.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return "";
    }

    /*
     * Insert
     */
    public static void insertMenuItem() {
        AmazonDynamoDBClient ddb = SplashActivity.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            BusinessMenu menuToAdd = Globals.getInstance().getBusinessMenu();
            ByteBuffer byteArrayMenuToAdd = Serializer.serialize(menuToAdd);
            RawMenuItem toAdd = new RawMenuItem();
            toAdd.setMenu(byteArrayMenuToAdd);
            toAdd.setName(Globals.getInstance().getBusinessName());

            mapper.save(toAdd);

        } catch (AmazonServiceException ex) {
            Log.e(TAG, "Error inserting users");
            SplashActivity.clientManager
                    .wipeCredentialsOnAuthError(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Retrieves all of the attribute/value pairs for the specified restaurant.
     */
    public static RawMenuItem getMenuForRestaurant() {

        AmazonDynamoDBClient ddb = SplashActivity.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            RawMenuItem menuItem = mapper.load(RawMenuItem.class,
                    Globals.getInstance().getBusinessName());

            return menuItem;

        } catch (AmazonServiceException ex) {
            SplashActivity.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return null;
    }

    public static RawMenuItem getBusinessName() {
        AmazonDynamoDBClient ddb = SplashActivity.clientManager
                .ddb();

        DescribeTableRequest request = new DescribeTableRequest()
                .withTableName(SplashActivity.
                        clientManager.getContext().getString(R.string.business_names_table));
        ddb.describeTable(request);
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            RestaurantsIDs businessID = mapper.load(RestaurantsIDs.class,
                    Globals.getInstance().getBusinessID());

            RawMenuItem toReturn = new RawMenuItem();
            if(businessID != null &&
                    businessID.getName() != null &&
                    businessID.getName().length() > 0)
                toReturn.setName(businessID.getName());
            return toReturn;

        } catch (AmazonServiceException ex) {
            SplashActivity.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return null;
    }
}