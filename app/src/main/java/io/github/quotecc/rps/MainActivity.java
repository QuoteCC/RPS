package io.github.quotecc.rps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button p1, p2;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1 = (Button) findViewById(R.id.oneP);
        p2 = (Button) findViewById(R.id.twoP);


        //two buttons to determine 1 or 2 players, handled through boolean bundle
        //creates the intent in the onclick so that different game types can be swapped
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(MainActivity.this, GameAct.class);
                i.putExtra("2Play", false);
                startActivity(i);

            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this,GameAct.class);
                i.putExtra("2Play", true);
                startActivity(i);
            }
        });


    }


}
