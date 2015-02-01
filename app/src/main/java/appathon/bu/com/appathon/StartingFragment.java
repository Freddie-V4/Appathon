package appathon.bu.com.appathon;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class StartingFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating the layout
        View v = inflater.inflate(R.layout.fragment_starting, container, false);
        Button b = (Button) v.findViewById(R.id.button2);
        b.setOnClickListener(this);


        return v;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button2) {
//            FragmentManager fm = getFragmentManager();
//            FragmentTransaction trans = fm.beginTransaction();
//            Fragment frag = new ContactsFragment();
//
//            trans.replace(R.id.fragment_replace, frag);
//            trans.commit();
            // Send phone number to person
            String messageToSend = "Take back your private time!";
            String number = "2678648593";

            SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
        }
    }
}
