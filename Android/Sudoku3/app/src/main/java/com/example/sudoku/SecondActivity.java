package com.example.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;

public class SecondActivity extends Activity {
    public static final String extraTable = "com.example.sudoku.EXTRA_TABLE";
    public static final String extraTable2 = "com.example.sudoku.EXTRA_TABLE2";
    int[][] table = new int[9][9];
    int element1 = 11;
    int element = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*for(int k = 0; k < 9; k++){
            for (int j = 0; j < 9; j++) {
                table[k][j] = 0;
            }
        }*/
        Intent intent = getIntent();
        int[][] table4 =(int[][]) intent.getSerializableExtra(MainActivity.extraTable1);
        int[][] table5 =(int[][]) intent.getSerializableExtra(ThirdActivity.extraTable4);
        if(table4 != null){
            table = table4;
        }
        if(table5 != null){
            table = table5;
        }

        Log.d("tag",String.valueOf(table[0][0]));
        final Sudoku sudoku = new Sudoku(table);
        TableLayout tableLayout = new TableLayout(this);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        tableLayout.setLayoutParams(lp);
        for (int i = 1; i <= 9; i++) {
            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            tableRow.setLayoutParams(layoutParams);
            for (int j = 1; j <= 9; j++){
                final LinearLayout linearLayout = new LinearLayout(this);

                linearLayout.setBackgroundResource(R.drawable.background);
                linearLayout.setId(i*10+j);
                linearLayout.setGravity(Gravity.CENTER);
                if(table[i-1][j-1]==0) {
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView textViewOut = (TextView) findViewById(element);
                            textViewOut.setBackgroundColor(getResources().getColor(R.color.white));
                            element1 = linearLayout.getId();
                            LinearLayout linearLayoutIn = (LinearLayout) linearLayout.getChildAt(0);
                            TextView textViewIn = (TextView) linearLayoutIn.getChildAt(0);
                            textViewIn.setBackgroundColor(getResources().getColor(R.color.blue));
                            element = textViewIn.getId();

                            Log.d("tag", String.valueOf(element1));
                        }
                    });
                }

                LinearLayout linearLayout1 = new LinearLayout(this);
                linearLayout1.setOrientation(LinearLayout.VERTICAL);
                final TextView textView1 = new TextView(this);
                if(sudoku.getElement(i-1,j-1)!=0) {
                    textView1.setText(String.format("%s", sudoku.getElement(i - 1, j - 1)));
                } else {
                    textView1.setText(" ");
                }
                textView1.setId((i*10+j)*10+1);
                textView1.setLayoutParams (new ViewGroup.LayoutParams(40, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout1.addView(textView1);

                linearLayout.addView(linearLayout1);

                tableRow.addView(linearLayout);
            }
            tableLayout.addView(tableRow);
        }
        tableLayout.setStretchAllColumns(true);

        TableRow tableRow2 = new TableRow(this);
        tableRow2.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout2 = new LinearLayout(this);
        TextView textView2 = new TextView(this);
        textView2.setLayoutParams (new ViewGroup.LayoutParams(40,60));
        textView2.setText(" ");
        linearLayout2.addView(textView2);
        tableRow2.addView(linearLayout2);
        tableLayout.addView(tableRow2);


        TableRow tableRow1 = new TableRow(this);
        tableRow1.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout0 = new LinearLayout(this);
        linearLayout0.setBackgroundResource(R.drawable.background);
        linearLayout0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(element);
                textView.setText(" ");
            }
        });
        TextView textView0 = new TextView(this);
        textView0.setText(String.format("%s","X"));
        textView0.setLayoutParams (new ViewGroup.LayoutParams(40,60));
        linearLayout0.addView(textView0);
        tableRow1.addView(linearLayout0);

        LinearLayout linearLayout4 = new LinearLayout(this);
        TextView textView4 = new TextView(this);
        textView4.setText(String.format("%s"," "));
        textView4.setLayoutParams (new ViewGroup.LayoutParams(40,60));
        linearLayout4.addView(textView4);
        tableRow1.addView(linearLayout4);
        tableLayout.addView(tableRow1);


        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        for (int j = 1; j <= 9; j++){
            final int u = j;
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setBackgroundResource(R.drawable.background);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = (TextView) findViewById(element);
                    int x = element1 / 10 - 1;
                    int y = element1 % 10 - 1;
                    TextView textViewIn = findViewById(element);
                    if(!sudoku.checkElement(x,y,u)){
                        textViewIn.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        textViewIn.setBackgroundColor(getResources().getColor(R.color.blue));
                    }
                    textView.setText(String.format("%s",u));
                    sudoku.setElement(x,y,u);
                }
            });

            TextView textView1 = new TextView(this);
            textView1.setText(String.format("%s",u));
            textView1.setLayoutParams (new ViewGroup.LayoutParams(40,60));
            linearLayout.addView(textView1);
            tableRow.addView(linearLayout);
        }
        tableLayout.addView(tableRow);

        TableRow tableRow4 = new TableRow(this);
        tableRow4.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundResource(R.drawable.background);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag",String.valueOf(table[0][0]));
                Intent goMain = new Intent(SecondActivity.this,MainActivity.class);
                Log.d("tag",String.valueOf(sudoku.getElement(0,0)));
                goMain.putExtra(extraTable,sudoku.getTable());
                startActivity(goMain);
            }
        });
        TextView textView1 = new TextView(this);
        textView1.setText(String.format("%s","Solve"));
        textView1.setLayoutParams (new ViewGroup.LayoutParams(40,60));
        linearLayout.addView(textView1);
        tableRow4.addView(linearLayout);

        LinearLayout linearLayout5 = new LinearLayout(this);
        linearLayout5.setBackgroundResource(R.drawable.background);
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag",String.valueOf(table[0][0]));
                Intent goThird = new Intent(SecondActivity.this,ThirdActivity.class);
                Log.d("tag",String.valueOf(sudoku.getElement(0,0)));
                goThird.putExtra(extraTable2,sudoku.getTable());
                startActivity(goThird);
            }
        });
        TextView textView5 = new TextView(this);
        textView5.setText(String.format("%s","Done"));
        textView5.setLayoutParams (new ViewGroup.LayoutParams(40,60));
        linearLayout5.addView(textView5);
        tableRow4.addView(linearLayout5);

        tableLayout.addView(tableRow4);
        setContentView(tableLayout);
    }
}
