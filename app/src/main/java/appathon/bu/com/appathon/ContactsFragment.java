package appathon.bu.com.appathon;

import android.app.DownloadManager;
import android.app.Fragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
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
public class ContactsFragment extends Fragment {

    //private final static String[] FROM_COLUMNS = new String[];
    public static String[] names;
    public static String[] phoneNumbers;
    ListView lv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating the layout
        View v = inflater.inflate(R.layout.contacts_view, container, false);
        lv = (ListView) v.findViewById(R.id.listView);


        String name = null;
        String phoneNumber = null;

        ContentResolver cr = getActivity().getContentResolver();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext()) {
            name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        }
        phones.close();


        // Takes strings of names and puts them into an array of names
        names = name.split("\\s+");

        // Takes phone numbers from contacts and puts them into an array of phone numbers
        phoneNumbers = phoneNumber.split("\\s+");

//        Button butt =
//        Toast toast = Toast.makeText(getActivity(),Arrays.toString(phoneNumbers), Toast.LENGTH_LONG);


        ArrayAdapter<String> aA = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);

        for (int i = 0; i < names.length; i++) {
            lv.setAdapter(aA);
        }


        return v;
    }


}
