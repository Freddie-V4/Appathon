package appathon.bu.com.appathon;

import android.app.DownloadManager;
import android.app.Fragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class ContactsFragment extends Fragment {

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

        names = name.split("\\_");
        phoneNumbers = phoneNumber.split("\\s+");


        ArrayAdapter<String> aA = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);
        //ArrayAdapter<String> aB = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, phoneNumbers);

        lv.setAdapter(aA);


        return v;
    }


}
