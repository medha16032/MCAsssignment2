package com.example.medhagupta.quizisprime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends ActionBarActivity {

    TextView textView_cheat;
    Button button_showCheat;
    Button button_back;
    private int cheat_clicked=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        getSupportActionBar().hide();

        textView_cheat=(TextView)findViewById(R.id.cheat_text_view);
        button_showCheat=(Button)findViewById(R.id.cheat_button);
        button_back=(Button)findViewById(R.id.back_button);

        button_showCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=0;
                int result=0;
                cheat_clicked = 1;
                String numstring = "";
                Bundle passed_value = getIntent().getExtras();
                if (passed_value != null) {
                    numstring = passed_value.getString("value");
                    num = Integer.parseInt(numstring);
                    result = checkPrime(num);
                    if (result == 0) {
                       textView_cheat.setText( num + " is prime");
                    }
                    else {
                        textView_cheat.setText( num + " is not prime");
                    }

                }
                else {
                    System.out.println("Problem loading number");
                }
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(CheatActivity.this, MainActivity.class);
                if (cheat_clicked == 1) {
                    i.putExtra("cheat_seen", 1);
                } else {
                    i.putExtra("cheat_seen", 0);
                }
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    int checkPrime(int n) {

        if (n == 1)
            return 1;

        else if (n == 2 || n == 3 || n == 5)
            return 0;
        else {
            if (n % 2 == 0)
                return 1;
            else {
                for (int i = 3; i <= n / 2; i += 2) {
                    if (n % i == 0) {
                        return 1;
                    }
                }
            }
        }
        return 0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cheat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
