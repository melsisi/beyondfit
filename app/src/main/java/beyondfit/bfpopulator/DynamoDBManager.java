package beyondfit.bfpopulator;

import android.util.Log;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

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
            AmazonDynamoDBClient ddb = MainActivityFragment.clientManager
                    .ddb();

            DescribeTableRequest request = new DescribeTableRequest()
                    .withTableName(MainActivityFragment.
                            clientManager.getContext().getString(R.string.raw_menu_items_table));
            DescribeTableResult result = ddb.describeTable(request);

            String status = result.getTable().getTableStatus();
            return status == null ? "" : status;

        } catch (ResourceNotFoundException e) {
        } catch (AmazonServiceException ex) {
            MainActivityFragment.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return "";
    }

    /*
     * Insert
     */
    public static void insertMenuItem() {
        AmazonDynamoDBClient ddb = MainActivityFragment.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            BusinessMenu menuToAdd = Globals.getInstance().getBusinessMenu();
            ByteBuffer byteArrayMenuToAdd = Serializer.serialize(menuToAdd);
            RawMenuItem toAdd = new RawMenuItem();
            toAdd.setMenu(byteArrayMenuToAdd);
            toAdd.setName("Test Restaurant");

            mapper.save(toAdd);

        } catch (AmazonServiceException ex) {
            Log.e(TAG, "Error inserting users");
            MainActivityFragment.clientManager
                    .wipeCredentialsOnAuthError(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Retrieves all of the attribute/value pairs for the specified restaurant.
     */
    public static RawMenuItem getMenuForRestaurant(String restaurantName) {

        AmazonDynamoDBClient ddb = MainActivityFragment.clientManager
                .ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb);

        try {
            RawMenuItem menuItem = mapper.load(RawMenuItem.class,
                    restaurantName);

            return menuItem;

        } catch (AmazonServiceException ex) {
            MainActivityFragment.clientManager
                    .wipeCredentialsOnAuthError(ex);
        }

        return null;
    }
}