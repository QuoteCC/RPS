package io.github.quotecc.rps;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameAct extends AppCompatActivity {

    boolean playMode;
    int[][] lazy = {{0, 2, 1}, {1, 0, 2}, {2, 1, 0}}; //lazy[player 1 choice][player 2 choice] gives the winning player number, or 0 for a tie
    String title2;
    String title1 = "P1: %d";
    ImageView p1Img, p2Img;
    Button r,p,s;
    TextView p1s,p2s, result;
    boolean turn1 = true;
    int[] pics = {R.drawable.rock, R.drawable.paper, R.drawable.scissors};

    int score1 = 0, score2=0;
    int p1act,p2act;
    int p1Score, p2Score;
    String p1Str = "P1: %d";

    Animator anim,anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playMode = getIntent().getBooleanExtra("2Play", false);
        p1Img = (ImageView) findViewById(R.id.p1);
        p2Img = (ImageView) findViewById(R.id.p2);
        //p1Img.setPivotX(p1Img.getX());
        //p1Img.setPivotY(p1Img.getY()-10);


        p1s = (TextView) findViewById(R.id.p1score);
        p2s = (TextView) findViewById(R.id.p2score);
        result= (TextView) findViewById(R.id.result);

        r = (Button) findViewById(R.id.rock);
        p = (Button) findViewById(R.id.paper);
        s = (Button) findViewById(R.id.scissors);
        anim = AnimatorInflater.loadAnimator(this, R.animator.anim );
        anim2 = AnimatorInflater.loadAnimator(this,R.animator.anim2);
        anim.setTarget(p1Img);
        anim2.setTarget(p2Img);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                round(p1act, p2act);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        if (playMode){
            getSupportActionBar().setTitle("Player v. Player!");
            title2 = "P2: %d";

        }
        else{
            getSupportActionBar().setTitle("Player v. AI");
            title2 = "AI: %d";

        }

        turn1 = true;



        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playMode){ //two players
                    if (result.getVisibility() == View.VISIBLE){
                        result.setVisibility(View.GONE);
                        p1Img.setImageResource(pics[0]);
                        p2Img.setImageResource(pics[0]);
                    }
                    if(turn1){
                        turn1 = false;
                        p1act = 0;
                        p2Turn();
                    }
                    else{
                        turn1 = true;
                        p2act = 0;
                        //round(p1act,p2act);
                        anim.start();
                        anim2.start();
                    }
                }
                else{ //make ai choose second
                    Random r = new Random();
                    p1act = 0;
                    p2act = r.nextInt(3);
                    //round(p1act, p2act);
                    anim.start();
                    anim2.start();
                }
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playMode){ //two players
                    if (result.getVisibility() == View.VISIBLE){
                        result.setVisibility(View.GONE);
                        p1Img.setImageResource(pics[0]);
                        p2Img.setImageResource(pics[0]);
                    }
                    if(turn1){
                        turn1 = false;
                        p1act = 1;
                        p2Turn();
                    }
                    else{
                        turn1 = true;
                        p2act = 1;
                        //round(p1act,p2act);
                        anim.start();
                        anim2.start();
                    }
                }
                else{ //make ai choose second
                    Random r = new Random();
                    p1act = 1;
                    p2act = r.nextInt(3);
                    //round(p1act, p2act);
                    anim.start();
                    anim2.start();
                }

            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playMode){ //two players
                    if (result.getVisibility() == View.VISIBLE){
                        result.setVisibility(View.GONE);
                        p1Img.setImageResource(pics[0]);
                        p2Img.setImageResource(pics[0]);
                    }
                    if(turn1){
                        turn1 = false;
                        p1act = 2;
                        p2Turn();
                    }
                    else{
                        turn1 = true;
                        p2act = 2;
                        //round(p1act,p2act);
                        anim.start();
                        anim2.start();
                    }
                }
                else{ //make ai choose second
                    Random r = new Random();
                    p1act = 2;
                    p2act = r.nextInt(3);
                    //round(p1act, p2act);
                    anim.start();
                    anim2.start();
                }


            }
        });
        p1s.setText(String.format(title1, p1Score));
        p2s.setText(String.format(title2, p2Score));




    }

    void p2Turn(){
        r.setBackgroundResource(R.drawable.bttnr);
        p.setBackgroundResource(R.drawable.bttnr);
        s.setBackgroundResource(R.drawable.bttnr);
    }



    void round (int p1, int p2){

        p1Img.setImageResource(pics[p1]);
        p2Img.setImageResource(pics[p2]);


        r.setBackgroundResource(R.drawable.bttn);
        p.setBackgroundResource(R.drawable.bttn);
        s.setBackgroundResource(R.drawable.bttn);

        switch(lazy[p1][p2]){
            case 0:
                result.setText("Tie!");
                break;
            case 1:
                result.setText("P1 Win!");
                p1Score++;
                p1s.setText(String.format(title1, p1Score));
                break;
            case 2:
                result.setText("P2 Win!");
                p2Score++;
                p2s.setText(String.format(title2, p2Score));
                break;
            default:
                break;
        }
        result.setVisibility(View.VISIBLE);


    }
}
