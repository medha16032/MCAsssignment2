package com.example.medhagupta.quizisprime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HintActivity extends ActionBarActivity {
    TextView textView_hint;
    Button button_showHint;
    Button button_back;
    private int hint_clicked=0;
    private String hint="A number greater than 1 is called a prime number, if it has only two factors, namely 1 and the number itself. Test whether the number is divisible by any prime number less than the square root of the number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        getSupportActionBar().hide();

        textView_hint=(TextView)findViewById(R.id.HintTextView);
        button_showHint=(Button)findViewById(R.id.ShowHintButton);
        button_back=(Button)findViewById(R.id.BackButton);

        button_showHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hint_clicked=1;
                textView_hint.setText(hint);
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(HintActivity.this,MainActivity.class);
                if(hint_clicked==1)
                {
                    i.putExtra("hint_seen",1);
                }
                else
                {
                    i.putExtra("hint_seen",0);
                }
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hint, menu);
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
