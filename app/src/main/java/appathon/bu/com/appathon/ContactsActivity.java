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
import java.util.Comparator;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class ContactsActivity extends ActionBarActivity implements View.OnClickListener {

    //private final static String[] FROM_COLUMNS = new String[];
    private static String[] names;
    private static String[] phoneNumbers;
    private static String[] name_phone;
    private ListView lv;
    private String TAG = "DEBUG";
    private boolean debug = false;
    private String name = "";
    private String phoneNumber = "";

    // debugging method
    private void db(String s) {
        if (debug)
            Log.d(TAG, s);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_view);
        lv = (ListView) findViewById(R.id.listView);

        ContentResolver cr = getContentResolver();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext()) {
            name += phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) + "_";
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + "_";

        }
        phones.close();

        // Takes strings of names and puts them into an array of names
        names = name.split("\\_");

        // Sorts name alphabetically by first and last name
        Arrays.sort(names, new AlphaCompare());

        // Takes phone numbers from contacts and puts them into an array of phone numbers
        phoneNumbers = phoneNumber.split("\\_");

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < phoneNumbers.length; j++) {
                if (names[i] == phoneNumbers[j]) {
                    name_phone[i] = phoneNumbers[j];
                }
            }
        }

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
        if (id == R.id.button_add) {
//            String messageToSend = "Take back your private time!";
            String messageToSend = "Hi. Hope you don't mind this text. Just testing out a feature of an app I'm working on!";
            String number = "7813086904";

//            SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null, null);
            Intent i = new Intent(ContactsActivity.this, ReasonsActivity.class);
            startActivity(i);
        }
    }

    private static class AlphaCompare implements Comparator<String> {
        public int compare(String s1, String s2) {
            return (s1.toLowerCase().compareTo(s2.toLowerCase()));
        }
    }

}
