package com.example.medhagupta.quizisprime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private static final String TAG1 = "MainActivity";
    private static final String TAG2 = "MainActivity";
    static int correct = 0;
    static int wrong = 0;
    Button buttonTrue;
    Button buttonFalse;
    Button buttonNext;
    Button buttonHint;
    Button buttonCheat;
    TextView textView_question;
    TextView correctAns;
    TextView inCorrectAns;
    int Number;
    private String questn, correctString, wrongString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        buttonTrue = (Button) findViewById(R.id.TrueButton);
        buttonFalse = (Button) findViewById(R.id.FalseButton);
        buttonNext = (Button) findViewById(R.id.NextButton);
        textView_question = (TextView) findViewById(R.id.QuestionTextView);
        correctAns = (TextView) findViewById(R.id.RightTextView);
        inCorrectAns = (TextView) findViewById(R.id.textView2);
        buttonHint=(Button)findViewById(R.id.HintButton);
        buttonCheat=(Button)findViewById(R.id.CheatButton);

        if (savedInstanceState != null) {
            questn = savedInstanceState.getString(TAG);
            correctString = savedInstanceState.getString(TAG1);
            wrongString = savedInstanceState.getString(TAG2);
            textView_question.setText(questn);
            correctAns.setText(correctString);
            inCorrectAns.setText(wrongString);

        } else {

            Random random = new Random();
            Number = random.nextInt(1000) + 1;
            String number = Integer.toString(Number);
            textView_question.setText("Is " + number + " prime?");
        }

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRandom();
                questn = textView_question.getText().toString();
                //Toast.makeText(getApplicationContext(), num, Toast.LENGTH_LONG).show();
            }
        });


        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = checkPrime(Number);
                if (res == 0) {
                    correct++;

                    String corr = Integer.toString(correct);
                    correctAns.setText(corr);
                    Toast.makeText(getApplicationContext(), "Correct!!", Toast.LENGTH_SHORT).show();
                } else {
                    wrong++;
                    String wr = Integer.toString(wrong);
                    inCorrectAns.setText(wr);
                    Toast.makeText(getApplicationContext(), "InCorrect!!", Toast.LENGTH_SHORT).show();
                }
                createRandom();
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = checkPrime(Number);
                if (res == 1) {
                    correct++;

                    String corr = Integer.toString(correct);
                    correctAns.setText(corr);
                    Toast.makeText(getApplicationContext(), "Correct!!", Toast.LENGTH_SHORT).show();
                } else {

                    wrong++;
                    String wr = Integer.toString(wrong);
                    inCorrectAns.setText(wr);

                    Toast.makeText(getApplicationContext(), "InCorrect!!", Toast.LENGTH_SHORT).show();
                }
                createRandom();
            }

        });

        buttonHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,HintActivity.class);
                startActivityForResult(i,0);
            }
        });

        buttonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num_to_cheat=Integer.toString(Number);
                Intent i=new Intent(MainActivity.this,CheatActivity.class);
                i.putExtra("value",num_to_cheat);
                startActivityForResult(i,1);
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

    void createRandom() {
        Random rand = new Random();
        Number = rand.nextInt(1000) + 1;
        String num = Integer.toString(Number);
        textView_question.setText("Is " + num + " prime?");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onSaveInstanceState(Bundle savedInstanceState) {
        questn = textView_question.getText().toString();
        correctString = correctAns.getText().toString();
        wrongString = inCorrectAns.getText().toString();
        savedInstanceState.putString(TAG, questn);
        savedInstanceState.putString(TAG1, correctString);
        savedInstanceState.putString(TAG2, wrongString);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestcode,int resultcode,Intent data)
    {
        if(requestcode==0)
        {
            int value=data.getIntExtra("hint_seen",0);
            if(value == 1)
            {
                Toast.makeText(this,"You have seen the hint!!",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            int value=data.getIntExtra("cheat_seen",0);
            if(value == 1)
            {
                Toast.makeText(this,"You have cheated!!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
        questn = textView_question.getText().toString();
        correctString = correctAns.getText().toString();
        wrongString = inCorrectAns.getText().toString();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Inside OnPause");
        questn = textView_question.getText().toString();
        correctString = correctAns.getText().toString();
        wrongString = inCorrectAns.getText().toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Inside OnREsume");
        questn = textView_question.getText().toString();
        correctString = correctAns.getText().toString();
        wrongString = inCorrectAns.getText().toString();

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Inside OnSTop");
        questn = textView_question.getText().toString();
        correctString = correctAns.getText().toString();
        wrongString = inCorrectAns.getText().toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }

}
