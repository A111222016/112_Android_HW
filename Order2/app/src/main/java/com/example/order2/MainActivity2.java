package com.example.order2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private TextView main_food, side_food, drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        main_food = findViewById(R.id.main_food);
        side_food = findViewById(R.id.side_food);
        drink = findViewById(R.id.drink);

        // 获取传递的数据
        String selectedMain = getIntent().getStringExtra("selectedMain");
        String selectedSide = getIntent().getStringExtra("selectedSide");
        String selectedDrinks = getIntent().getStringExtra("selectedDrinks");

        // 将数据设置到相应的 TextView
        main_food.setText(selectedMain);
        side_food.setText(selectedSide);
        drink.setText(selectedDrinks);
    }
    // 按钮点击事件处理程序
    public void goBackToMainActivity(View view) {
        // 返回到 MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // 可以选择调用finish()来销毁当前活动，如果不需要返回到MainActivity2，可以调用这个方法
    }
}
