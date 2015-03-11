package appathon.bu.com.appathon;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class CardsActivity extends Card {

    public CardsActivity(Context context) {
        this(context, R.layout.activity_view);
    }

    public CardsActivity(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }


    private void init() {
//        CardView card = (CardView) findViewById(R.id.card_view);

        //Set click listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                CardExpand expand = new CardExpand(getContext());
                expand.setTitle("First Card");
                card.addCardExpand(expand);
            }
        });

        //Set swipe on
        setSwipeable(true);

    }
}
