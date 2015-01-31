package appathon.bu.com.appathon;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class ReasonsFragment extends Fragment {
    public static String TEA_TYPE_POS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflating the layout
        View v = inflater.inflate(R.layout.reasons_view, container, false);

        



        return v;
    }
}
