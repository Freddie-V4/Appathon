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
/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class ContactsFragment extends Fragment {

    //private final static String[] FROM_COLUMNS = new String[];



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating the layout
        View v = inflater.inflate(R.layout.contacts_view, container, false);


        readContacts();


        return v;
    }

    private void readContacts(){
        ContentResolver cr = this.getActivity().getContentResolver();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        }
        phones.close();

    }

}
