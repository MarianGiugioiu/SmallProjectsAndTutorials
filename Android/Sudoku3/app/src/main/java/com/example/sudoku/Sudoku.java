package com.example.sudoku;

public class Sudoku {
    private int[][] table = new int[9][9];
    private int[][] table2 = new int[9][9];
    private int[][] table1 = new int[9][9];

    private void copy(int[][] table1, int[][] table2){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                table1[i][j] = table2[i][j];
            }
        }
    }
    public Sudoku(int[][] Table) {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.table[i][j] = Table[i][j];
            }
        }
    }

    public Sudoku(Sudoku sudoku) {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.table[i][j] = sudoku.table[i][j];
            }
        }
    }

    public int[][] getTable() {
        int[][] Table = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                Table[i][j] = this.table[i][j];
            }
        }
        return Table;
    }
    public int[][] getTableUnsolved(){
        solve();
        int[][] Table = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                Table[i][j] = this.table1[i][j];
            }
        }
        return Table;
    }

    public int getElement(int i, int j) {
        return table[i][j];
    }

    public void setElement(int i, int j, int x) {
        this.table[i][j] = x;
    }

    private boolean checkElement1(int i, int j, int x, int[][] table){
        for (int k = 0; k < 9; k++){
            if(x == table[i][k] || x == table[k][j]) {
                return false;
            }
        }

        for (int k = (i / 3) * 3; k < (i / 3) * 3 + 3; k++){
            for (int l = (j / 3) * 3; l < (j / 3) * 3 + 3; l++){
                if(x == table[k][l]){
                    return false;
                }
            }
        }

        return true;
    }
    public boolean checkElement(int i, int j, int x){
        return checkElement1(i,j,x,this.table);
    }

    public void solve(){
        copy(table2,table);
        copy(table1,table);
        bkt(0,0);
    }

    private int u = 1;
    private void bkt(int x, int y){
        if(x==9 && this.u==1){
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    this.table[i][j]=this.table2[i][j];
                }
            }
            this.u=0;
        }
        else
        if(this.u==1)
        {
            int i,j;
            if(y==8){
                j=0;
                i=x+1;
            } else {
                i=x;
                j=y+1;
            }
            if(this.table1[x][y]==0){
                for(int k=1;k<=9;k++){
                    if(checkElement1(x,y,k,this.table2)){
                        this.table2[x][y]=k;
                        bkt(i,j);
                        this.table2[x][y]=0;
                    }
                    else{
                        //cout<<x<<" "<<y<<" "<<k<<"\n";
                    }
                }
            }
            else bkt(i,j);
        }
    }

}

