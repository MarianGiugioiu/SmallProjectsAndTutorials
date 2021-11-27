package com.example.sudoku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.prefs.BackingStoreException;

public class MainActivity extends AppCompatActivity {

    int element1 = 11;
    int element = 111;
    public static final String extraTable1 = "com.example.sudoku.EXTRA_TABLE1";
    /*int[][] table = {{9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0}};*/
    //Sudoku sudoku = new Sudoku(table);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("tag","3");

        Intent intent = getIntent();
        final int[][] table = (int[][]) intent.getSerializableExtra(SecondActivity.extraTable);
        Log.d("tag",String.valueOf(table[0][0]));
        final Sudoku sudoku = new Sudoku(table);
        Log.d("tag",String.valueOf(sudoku.getElement(0,0)));

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
                if(table[i-1][j-1]==0) {
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView textViewOut = (TextView) findViewById(element);
                            textViewOut.setBackgroundColor(getResources().getColor(R.color.white));
                            element1 = linearLayout.getId();
                            LinearLayout linearLayoutIn = (LinearLayout) linearLayout.getChildAt(1);
                            TextView textViewIn = (TextView) linearLayoutIn.getChildAt(0);
                            textViewIn.setBackgroundColor(getResources().getColor(R.color.blue));
                            element = textViewIn.getId();

                            Log.d("tag", String.valueOf(element1));
                        }
                    });
                }
                //linearLayout.setPadding(0,0,5,0);
                TableLayout tableLayout1 = new TableLayout(this);
                tableLayout1.setLayoutParams(new ViewGroup.LayoutParams(20,ViewGroup.LayoutParams.MATCH_PARENT));
                for (int k = 1; k <= 4; k++){
                    TableRow tableRow1 = new TableRow(this);
                   // tableRow1.setBackgroundColor(R.color.blue);
                    tableRow1.setLayoutParams(new ViewGroup.LayoutParams(20,ViewGroup.LayoutParams.MATCH_PARENT));

                    final TextView textView = new TextView(this);
                    textView.setText(String.format("%s"," "));
                    textView.setId((i*10+j)*100+k);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,6);
                    textView.setHeight(20);
                    tableRow1.addView(textView);

                    tableLayout1.addView(tableRow1);
                }
                linearLayout.addView(tableLayout1);

                LinearLayout linearLayout1 = new LinearLayout(this);
                linearLayout1.setOrientation(LinearLayout.VERTICAL);


                final TextView textView1 = new TextView(this);
                if(sudoku.getElement(i-1,j-1)!=0) {
                    textView1.setText(String.format("%s", sudoku.getElement(i - 1, j - 1)));
                } else {
                    textView1.setText(" ");
                }
                textView1.setId((i*10+j)*10+1);
                textView1.setLayoutParams (new ViewGroup.LayoutParams(40, 100));
                linearLayout1.addView(textView1);
                //textView1.setPadding(0,0,0,0);
                //textView1.setBackgroundColor(R.color.green);


                linearLayout.addView(linearLayout1);
                TableLayout tableLayout2 = new TableLayout(this);
                tableLayout2.setLayoutParams(new ViewGroup.LayoutParams(10,ViewGroup.LayoutParams.MATCH_PARENT));
                for (int k = 1; k <= 5; k++){
                    TableRow tableRow1 = new TableRow(this);
                    tableRow1.setLayoutParams(new ViewGroup.LayoutParams(10,ViewGroup.LayoutParams.MATCH_PARENT));
                    //tableRow1.setBackgroundColor(R.color.red);
                    final TextView textView = new TextView(this);
                    textView.setText(String.format("%s"," "));
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,6);
                    textView.setHeight(20);
                    tableRow1.addView(textView);

                    tableLayout2.addView(tableRow1);
                }
                linearLayout.addView(tableLayout2);
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
        for(int j = 0; j < 3; j++){
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setBackgroundResource(R.drawable.background);
            if(j == 1){
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        element = element1 * 10 + 1;
                    }
                });
            }
            TextView textView = new TextView(this);
            if(j == 1){
                textView.setText(String.format("%s","O"));
            } else {
                if(j == 0){
                    textView.setText(String.format("%s","<-"));
                } else {
                    textView.setText(String.format("%s","->"));
                }
            }
            textView.setLayoutParams (new ViewGroup.LayoutParams(40,60));
            linearLayout.addView(textView);
            tableRow1.addView(linearLayout);
        }
        tableLayout.addView(tableRow1);

        /*for(int k = 1;k <= 4; k++){
            LinearLayout linearLayout8 = new LinearLayout(this);
            linearLayout8.setBackgroundResource(R.drawable.background);
            final int element2 = element1*100+k;
            linearLayout8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    element = element2;
                }
            });
            TextView textView8 = new TextView(this);
            //textView8.setText(((TextView) findViewById(element2)).getText());
            textView8.setLayoutParams (new ViewGroup.LayoutParams(40,60));
            linearLayout8.addView(textView8);
            tableRow1.addView(linearLayout8);
        }*/

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
            //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            //params.gravity  = Gravity.CENTER;
            //linearLayout.setLayoutParams(params);
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
                Intent goSecond = new Intent(MainActivity.this,SecondActivity.class);
                goSecond.putExtra(extraTable1,table);
                startActivity(goSecond);
            }
        });
        TextView textView1 = new TextView(this);
        textView1.setText(String.format("%s","Create"));
        textView1.setLayoutParams (new ViewGroup.LayoutParams(40,60));
        linearLayout.addView(textView1);
        tableRow4.addView(linearLayout);
        tableLayout.addView(tableRow4);

        setContentView(tableLayout);
    }
}
