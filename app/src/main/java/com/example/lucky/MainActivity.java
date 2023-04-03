package com.example.lucky;

import ImageViewScroll.IEventEnd;
import ImageViewScroll.ImageViewScrolling;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements IEventEnd {

    ImageView btn_up, btn_down;
    ImageViewScrolling image, image2, image3, imageViewScrolling;
    TextView txt_score;

    int count_done = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        btn_down = findViewById(R.id.btn_down);
        btn_up =  findViewById(R.id.btn_up);

        image =  findViewById(R.id.image);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        txt_score = findViewById(R.id.text_score);


        image.setEventEnd(MainActivity.this);
        image2.setEventEnd(MainActivity.this);
        image3.setEventEnd(MainActivity.this);

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.SCORE >= 50){
                    btn_up.setVisibility(View.GONE);
                    btn_down.setVisibility(View.VISIBLE);

                    image.setValueRandom(new Random().nextInt(6), new Random().nextInt((15-5))+1+5);
                    image2.setValueRandom(new Random().nextInt(6), new Random().nextInt((15-5))+1+5);
                    image3.setValueRandom(new Random().nextInt(6), new Random().nextInt((15-5))+1+5);

                    Common.SCORE -= 50;
                    txt_score.setText(String.valueOf(Common.SCORE));

                }
                else {
                    Toast.makeText(MainActivity.this,"You not enough money", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void eventEnd(int result, int count) {
        if(count_done < 2){
            count_done++;
        }
        else{
            btn_down.setVisibility(View.GONE);
            btn_up.setVisibility(View.VISIBLE);

            count_done = 0;

            if(image.getValue() == image2.getValue() && image2.getValue() == image3.getValue()) {
                Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
                Common.SCORE += 300;
                txt_score.setText(String.valueOf(Common.SCORE));
            } else if (image.getValue() == image2.getValue() || image2.getValue() == image3.getValue() || image.getValue() == image3.getValue()) {
                Toast.makeText(this, "You Win Small Price!", Toast.LENGTH_SHORT).show();
                Common.SCORE += 150;
                txt_score.setText(String.valueOf(Common.SCORE));
            }
            else {
                Toast.makeText(this, "You Lose!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}