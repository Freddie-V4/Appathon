package appathon.bu.com.appathon;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.view.CardView;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by FreddieV4 on 1/31/2015.
 */


public class CardsActivity extends Activity {
    TextView pgraph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        setUpCards();
    }

    private void setUpCards() {

        final Card card = new Card(getApplicationContext());
        //Create a CardHeader
        card.setTitle("Title of Card!");

        CardHeader header = new CardHeader(getApplicationContext());
        header.setTitle("Header!");
        //Add Header to card
        card.addCardHeader(header);

        CardExpand expand = new CardExpand(getApplicationContext());
        expand.setTitle("Expand!");
        card.addCardExpand(expand);


        //Set card in the cardView
        CardViewNative cardView = (CardViewNative) findViewById(R.id.carddemo);

        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(cardView);
        card.setViewToClickToExpand(viewToClickToExpand);

        cardView.setCard(card);
    }
}
