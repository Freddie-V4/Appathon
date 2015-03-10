package appathon.bu.com.appathon;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class ContactsActivity extends ActionBarActivity implements View.OnClickListener {

    //private final static String[] FROM_COLUMNS = new String[];
    public static String[] names;
    public static String[] phoneNumbers;
    public static String[] name_phone;
    ListView lv;
    String LOG_TAG = "Monitor";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflating the layout
        setContentView(R.layout.contacts_view);
        lv = (ListView) findViewById(R.id.listView);


        String name = " ";
        String phoneNumber = "";

        ContentResolver cr = getContentResolver();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext()) {
            name += phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) + "_";
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + "_";

        }
        phones.close();

        Log.v(name, LOG_TAG);

        // Takes strings of names and puts them into an array of names
        names = name.split("\\_");

        // Takes phone numbers from contacts and puts them into an array of phone numbers
        phoneNumbers = phoneNumber.split("\\_");

        Log.d(LOG_TAG, "Here is the list of phone numbers BEFORE: " + Arrays.toString(phoneNumbers));
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < phoneNumbers.length; j++) {
                if (names[i] == phoneNumbers[j]) {
                    name_phone[i] = phoneNumbers[j];
                }
            }
        }
        Log.d(LOG_TAG, "Here is the list of phone numbers AFTER: " + Arrays.toString(phoneNumbers));

        ArrayAdapter<String> aA = new ArrayAdapter<String>(ContactsActivity.this, android.R.layout.simple_list_item_1, names);
        ArrayAdapter<String> aB = new ArrayAdapter<String>(ContactsActivity.this, android.R.layout.simple_list_item_1, phoneNumbers);

        lv.setAdapter(aA);
//        lv.setAdapter(aB);

        Button addButton = (Button) findViewById(R.id.button_add);
        addButton.bringToFront();
        addButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
//        // Creates a new Intent to insert a contact
//        Intent intent = new Intent(Contacts.Intents.Insert.ACTION);
//// Sets the MIME type to match the Contacts Provider
//        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
//        Send message to person
        if(id == R.id.button_add){
//            String messageToSend = "Take back your private time!";
            String messageToSend = "Hi. Hope you don't mind this text. Just testing out a feature of an app I'm working on!";
            String number = "7813086904";

            SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null, null);

            Intent i = new Intent(ContactsActivity.this, ReasonsActivity.class);
            startActivity(i);
        }
    }
}
