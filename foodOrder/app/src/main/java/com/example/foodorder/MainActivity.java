package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CheckBox.OnCheckedChangeListener {


    private int count=0;
    private ImageView img, img2, img3, img4,img5;
    private CheckBox chk1, chk2, chk3, chk4,chk5;
    private int[] chkID={R.id.chk1,R.id.chk2,R.id.chk3, R.id.chk4,R.id.chk5};

    private TextView show;
    private int[] imgID={R.id.img1,R.id.img2,R.id.img3, R.id.img4,R.id.img5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show=(TextView) findViewById(R.id.showOrder);
        img=(ImageView) findViewById(R.id.img1);
        img2=(ImageView) findViewById(R.id.img2);
        img3=(ImageView) findViewById(R.id.img3);
        img4=(ImageView) findViewById(R.id.img4);
        chk1=(CheckBox) findViewById(R.id.chk1);
        chk2=(CheckBox) findViewById(R.id.chk2);
        chk3=(CheckBox) findViewById(R.id.chk3);
        chk4=(CheckBox) findViewById(R.id.chk4);
        chk5=(CheckBox) findViewById(R.id.chk5);
        chk1.setOnCheckedChangeListener(this);
        chk2.setOnCheckedChangeListener(this);
        chk3.setOnCheckedChangeListener(this);
        chk4.setOnCheckedChangeListener(this);
        chk5.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        count=0;
        for (int i=0;i<chkID.length;i++){
            CheckBox chk=(CheckBox) findViewById(chkID[i]);
            ImageView imgobj= (ImageView) findViewById(imgID[i]);
            if(chk.isChecked()) {
                count++;
                imgobj.setVisibility(View.VISIBLE);
            }
            else{
                imgobj.setVisibility(View.GONE);
            }


        }
        if(count!=0){
            show.setText("您點的餐點如下:");
        }
        else{
            show.setText("請點餐");
        }
    }
}