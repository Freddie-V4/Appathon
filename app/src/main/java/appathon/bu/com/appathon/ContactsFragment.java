package appathon.bu.com.appathon;

import android.app.DownloadManager;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class ContactsFragment extends Fragment implements View.OnClickListener {

    //private final static String[] FROM_COLUMNS = new String[];
    public static String[] names;
    public static String[] phoneNumbers;
    ListView lv;
    String LOG_TAG = "Monitor";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating the layout
        View v = inflater.inflate(R.layout.contacts_view, container, false);
        lv = (ListView) v.findViewById(R.id.listView);


        String name = " ";
        String phoneNumber = "";

        ContentResolver cr = getActivity().getContentResolver();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext()) {
            name += phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) + "_";
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        }
        phones.close();

        Log.v(name, LOG_TAG);

        // Takes strings of names and puts them into an array of names
        names = name.split("\\_");

        // Takes phone numbers from contacts and puts them into an array of phone numbers
        phoneNumbers = phoneNumber.split("\\s+");


        ArrayAdapter<String> aA = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);
        //ArrayAdapter<String> aB = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, phoneNumbers);

        lv.setAdapter(aA);

        Button butt = (Button)v.findViewById(R.id.button_add);

        butt.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        // Creates a new Intent to insert a contact
        Intent intent = new Intent(Contacts.Intents.Insert.ACTION);
// Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
    }
}
