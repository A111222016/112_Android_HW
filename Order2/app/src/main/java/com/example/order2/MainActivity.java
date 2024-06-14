package com.example.order2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView main_food, side_food, drink;
    private ListView listView;
    private Spinner spinner;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);
        main_food = findViewById(R.id.main_food);
        side_food = findViewById(R.id.side_food);
        drink = findViewById(R.id.drink);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.meal_options, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(listAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                switch (spinner.getSelectedItemPosition()) {
                    case 0:
                        main_food.setText(selectedItem);
                        break;
                    case 1:
                        side_food.setText(selectedItem);
                        break;
                    case 2:
                        drink.setText(selectedItem);
                        break;
                    default:
                        // Do nothing
                        break;
                }
            }
        });
    }

    private void updateListView(int position) {
        String[] items;
        switch (position) {
            case 0:
                items = getResources().getStringArray(R.array.main_meals);
                break;
            case 1:
                items = getResources().getStringArray(R.array.side_meals);
                break;
            case 2:
                items = getResources().getStringArray(R.array.drinks);
                break;
            default:
                items = new String[]{};
                break;
        }
        listAdapter.clear();
        listAdapter.addAll(items);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.finish) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("selectedMain", main_food.getText().toString());
            intent.putExtra("selectedSide", side_food.getText().toString());
            intent.putExtra("selectedDrinks", drink.getText().toString());
            startActivity(intent);
            return true;
        }
        else if (item.getItemId() == R.id.clear) {
                main_food.setText("請選擇");
                side_food.setText("請選擇");
                drink.setText("請選擇");
                return true;
            }
            else {
            return super.onOptionsItemSelected(item);
            }

    }


}
