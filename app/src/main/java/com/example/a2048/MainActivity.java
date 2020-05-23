package com.example.a2048;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2048.model.Matrix2048;

public class MainActivity extends AppCompatActivity {
    private SudokuBoard board;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Matrix2048 matrix2048 = new Matrix2048(4);
        board = findViewById(R.id.sudokuBoard);
        board.initGrid(matrix2048);
    }

    public void directions(View view) {
        String s = String.valueOf(((TextView) view).getText());
        String UP = "↑";
        String DOWN = "↓";
        String LEFT = "←";
        String RIGHT = "→";
        if (UP.equals(s)) {
            board.up();
        }
        if (DOWN.equals(s)) {
            board.down();
        }
        if (LEFT.equals(s)) {
            board.left();
        }
        if (RIGHT.equals(s)) {
            board.right();
        }
    }
}