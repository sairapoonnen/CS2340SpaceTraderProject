package cs2340.gatech.edu.cs2340spacetraderproject.views;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import cs2340.gatech.edu.cs2340spacetraderproject.R;
import cs2340.gatech.edu.cs2340spacetraderproject.model.SolarSystem;

public class MarketActivity extends AppCompatActivity {
    private SolarSystem ss;
    private ImageView background;
    private Button buy;
    private Button sell;
    private Button leave;
    private TextView name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        ss = (SolarSystem) getIntent().getSerializableExtra("PLANET");

        buy = findViewById(R.id.to_buy);
        sell = findViewById(R.id.to_sell);
        leave = findViewById(R.id.to_leave);
        name = findViewById(R.id.planet_name);
        background = findViewById(R.id.background);

        if (ss.getTech() <= 3) {
            background.setImageResource(R.drawable.market2);
        } else if (ss.getTech() <= 6) {
            background.setImageResource(R.drawable.market1);
        }

        move(background);

        buy.setVisibility(View.INVISIBLE);
        sell.setVisibility(View.INVISIBLE);
        leave.setVisibility(View.INVISIBLE);
        name.setVisibility(View.INVISIBLE);

        buy.postDelayed(new Runnable() {
            public void run() {
                buy.setVisibility(View.VISIBLE);
                buy.startAnimation(AnimationUtils.loadAnimation(MarketActivity.this, R.anim.fade_in));
            }
        }, 1250);
        sell.postDelayed(new Runnable() {
            public void run() {
                sell.setVisibility(View.VISIBLE);
                sell.startAnimation(AnimationUtils.loadAnimation(MarketActivity.this, R.anim.fade_in));
            }
        }, 1750);
        leave.postDelayed(new Runnable() {
            public void run() {
                leave.setVisibility(View.VISIBLE);
                leave.startAnimation(AnimationUtils.loadAnimation(MarketActivity.this, R.anim.fade_in));
            }
        }, 2250);
        name.postDelayed(new Runnable() {
            public void run() {
                name.setVisibility(View.VISIBLE);
                name.startAnimation(AnimationUtils.loadAnimation(MarketActivity.this, R.anim.fade_in));
            }
        }, 875);
    }

    //button handler to go to Buy
    public void onBuyPressed(View view) {
        Intent intent = new Intent(MarketActivity.this, MarketBuyActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

    //button handler to go to Sell
    public void onSellPressed(View view) {
        Intent intent = new Intent(MarketActivity.this, MarketSellActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

    //button handler to go back to planet surface
    public void onLeavePressed(View view) {
        Intent intent = new Intent(MarketActivity.this, PlanetSurfaceActivity.class);
        intent.putExtra("PLANET", ss);
        startActivity(intent);
    }

    public static void move(final ImageView view){
        ValueAnimator va = ValueAnimator.ofFloat(0f, -300f);
        int mDuration = 4500; //in millis
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((float)animation.getAnimatedValue());
            }
        });
        va.start();
    }
}
