package appathon.bu.com.appathon;

import android.content.Context;
import android.view.View;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;

/**
 * Created by FreddieV4 on 1/31/2015.
 */
public class CardView extends Card {

    public CardView(Context context) {
        this(context, R.layout.activity_view);
    }

    public CardView(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }


    private void init() {

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
