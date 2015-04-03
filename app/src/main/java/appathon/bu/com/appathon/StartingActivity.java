package appathon.bu.com.appathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class StartingActivity extends ActionBarActivity implements View.OnClickListener {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private boolean enabled = true;
    private Button enter;
    private EditText name;
    private EditText phoneNumber;
    private String nameString;
    private String phoneString;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflating the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_starting);
        checkPlayServices();  //check for valid install of Google Play Services

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "y3D6qePfthe7VxIIHdwynl7csbYGoDDOPFXteYHa", "l7QD57x45ILOGQidOtZq7DiD9tRmY3dVe115krz7");

        enter = (Button) findViewById(R.id.enterButton);
        enter.setOnClickListener(this);

        android.support.v7.app.ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setTitle(R.string.app_name);
            ab.setLogo(R.drawable.ic_launcher);
            ab.setDisplayUseLogoEnabled(true);
            ab.setHomeButtonEnabled(enabled);
        }

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

        if (id == R.id.enterButton) {
            // Find EditText views
            name = (EditText) findViewById(R.id.nameText);
            phoneNumber = (EditText) findViewById(R.id.phoneNumberText);

            // Get user input from EditText and add values to strings
            nameString = name.getText().toString();
            phoneString = phoneNumber.getText().toString();

            // Put name and phone number into Parse data table
            ParseObject inputObject = new ParseObject("InputObject");
            inputObject.put("Name", nameString);
            inputObject.put("Phone Number", phoneString);
            inputObject.saveInBackground();

            // Move to next Activity to test it
            Intent i = new Intent(StartingActivity.this, ContactsActivity.class);
            startActivity(i);
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {

                finish();
            }
            return false;
        }
        return true;
    }
}
