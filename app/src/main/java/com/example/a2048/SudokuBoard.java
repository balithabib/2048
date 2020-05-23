package com.example.a2048;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.a2048.model.Matrix2048;

public class SudokuBoard extends View {
    private float width, height, textWidth, textHeight;
    private Paint linePaint, textPaint, sameCell;
    private int size = 4;
    private float cellSize;

    private Matrix2048 grid;

    public SudokuBoard(Context context, AttributeSet attrs) {
        super(context, attrs);

        sameCell = new Paint();
        sameCell.setStyle(Paint.Style.FILL_AND_STROKE);
        sameCell.setColor(Color.rgb(52, 0, 0));


        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.rgb(127, 140, 141));
        linePaint.setStrokeWidth(4F);


        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setColor(Color.rgb(255, 255, 255));
        textPaint.setTextSize(80F);

        Rect textBounds = new Rect();
        textWidth = textPaint.measureText("0");
        textHeight = textBounds.height();
    }

    public void initGrid(Matrix2048 grid) {
        this.grid = grid;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizePixels = Math.min(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(sizePixels, sizePixels);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = (float) getWidth();
        height = (float) getHeight();
        cellSize = width / (float) size;

        fillCells(canvas);
        textGrid(canvas);
        drawLine(canvas);
    }

    public void fillCells(Canvas canvas) {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                fillCell(canvas, r, c);
            }
        }
    }

    public void fillCell(Canvas canvas, int r, int c) {
        int value = grid.get(r, c);

        switch (value) {
            case 2:
                sameCell.setColor(Color.rgb(255, 0, 255));
                break;
            case 4:
                sameCell.setColor(Color.rgb(255, 0, 0));
                break;
            case 8:
                sameCell.setColor(Color.rgb(0, 255, 255));
                break;
            case 16:
                sameCell.setColor(Color.rgb(0, 255, 0));
                break;
            case 32:
                sameCell.setColor(Color.rgb(0, 0, 255));
                break;
            case 64:
                sameCell.setColor(Color.rgb(255, 128, 128));
                break;
            case 128:
                sameCell.setColor(Color.rgb(255, 0, 128));
                break;
            case 256:
                sameCell.setColor(Color.rgb(128, 0, 255));
                break;
            case 512:
                sameCell.setColor(Color.rgb(128, 128, 255));
                break;
            case 1024:
                sameCell.setColor(Color.rgb(128, 255, 0));
                break;
            case 2048:
                sameCell.setColor(Color.rgb(128, 255, 128));
                break;
            default:
                sameCell.setColor(Color.parseColor("#1c2833"));
        }
        canvas.drawRect(c * cellSize, r * cellSize, (c + 1) * cellSize, (r + 1) * cellSize, sameCell);
    }

    public void textGrid(Canvas canvas) {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                String str = String.format("%d", grid.get(r, c));
                if (!"0".equals(str)) {
                    canvas.drawText(str, (c * cellSize - textWidth / 2) + cellSize / 2, (r * cellSize + textHeight / 2) + cellSize / 2, textPaint);
                }
            }
        }
    }

    public void drawLine(Canvas canvas) {
        canvas.drawRect(0F, 0F, width, height, linePaint);
        for (int i = 0; i < size; i++) {
            canvas.drawLine(i * cellSize, 0F, i * cellSize, height, linePaint);
            canvas.drawLine(0F, i * cellSize, width, i * cellSize, linePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            invalidate();
            return true;
        }
        return false;
    }

    public void down() {
        grid.down();
        grid.randOneElement();
        invalidate();
    }

    public void up() {
        grid.up();
        grid.randOneElement();
        invalidate();
    }


    public void left() {
        grid.left();
        grid.randOneElement();
        invalidate();
    }


    public void right() {
        grid.right();
        grid.randOneElement();
        invalidate();
    }

}
