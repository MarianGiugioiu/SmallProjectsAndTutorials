package com.example.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ThirdActivity extends Activity {
    public static final String extraTable4 = "com.example.sudoku.EXTRA_TABLE4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final int[][] table = (int[][]) intent.getSerializableExtra(SecondActivity.extraTable2);
        final Sudoku sudoku = new Sudoku(table);
        sudoku.solve();

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
                linearLayout.setGravity(Gravity.CENTER);

                LinearLayout linearLayout1 = new LinearLayout(this);
                linearLayout1.setOrientation(LinearLayout.VERTICAL);
                final TextView textView1 = new TextView(this);
                if(sudoku.getElement(i-1,j-1)!=0) {
                    textView1.setText(String.format("%s", sudoku.getElement(i - 1, j - 1)));
                } else {
                    textView1.setText(" ");
                }

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


        TableRow tableRow4 = new TableRow(this);
        tableRow4.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundResource(R.drawable.background);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSecond = new Intent(ThirdActivity.this,SecondActivity.class);
                goSecond.putExtra(extraTable4,table);
                startActivity(goSecond);
            }
        });
        TextView textView1 = new TextView(this);
        textView1.setText(String.format("%s","Solve"));
        textView1.setLayoutParams (new ViewGroup.LayoutParams(40,60));
        linearLayout.addView(textView1);
        tableRow4.addView(linearLayout);
        tableLayout.addView(tableRow4);
        setContentView(tableLayout);
    }
}
