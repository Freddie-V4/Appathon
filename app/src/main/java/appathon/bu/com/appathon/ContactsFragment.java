package appathon.bu.com.appathon;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class ContactsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating the layout
        View v = inflater.inflate(R.layout.contacts_view, container, false);





        return v;
    }

}
