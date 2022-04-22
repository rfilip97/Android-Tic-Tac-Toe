package com.androidttt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView pos[] = new ImageView[9];
    ImageView status;
    Symbol sideToMove;
    Bitboard PlayerXBB;
    Bitboard PlayerYBB;
    Bitboard GameBB;

    enum Symbol
    {
        X,
        O,
        NONE
    }

    /* Getter for side to mode field */
    private Symbol GetSideToMove()
    {
        return sideToMove;
    }

    /* Return the pos object. Pos1 has index 0 */
    private ImageView GetPosByID(int id)
    {
        try
        {
            return pos[id - 1];
        } catch (Exception e)
        {
            return pos[0];
        }
    }

    /* Get the ID of the image associated with the given symbol */
    private int GetSymbolId(Symbol symbol)
    {
        switch (symbol)
        {
            case O : return R.drawable.o;
            case X: return R.drawable.x;
            default : return -1;
        }
    }

    /* Change the symbol placed on specified position */
    private void ChangeImage(Symbol symbol, int pos)
    {
        int symbolId = GetSymbolId(symbol);
        GetPosByID(pos).setImageResource(symbolId);
        GetPosByID(pos).setAlpha(1.0f);
        Log.i("MainActivity", "Changing pos " + pos + " with symbol " + (symbol == Symbol.X ? "X" : "O"));
    }

    /* Method called after the restart button has been clicked */
    public void Restart(View view)
    {
        Log.i("MainActivity", "Entered Restart");
        for (int i = 1; i <= 9; i++)
            GetPosByID(i).setAlpha(0.0f);

        sideToMove = Symbol.X;
        status.setImageResource(R.drawable.x);
    }

    /* Basic move */
    private void Move(int pos)
    {
        Bitboard.Side side = Bitboard.idToSide(pos);

        // Ignore move is square is already used
        if (GameBB.isSideFree(side) == true)
            return;

        // Add move to GameBB
        GameBB.Add(side);

        // Add move to currently moving player
        if (sideToMove == Symbol.X)
        {
            PlayerXBB.Add(side);
        } else {
            PlayerYBB.Add(side);
        }

        // Add symbol to the main view
        ChangeImage(GetSideToMove(), pos);

        // Switch player
        SwitchSideToMove();
    }

    /* Switch side to move internal variable */
    private void SwitchSideToMove()
    {
        switch(sideToMove) {
            case O:
                sideToMove = Symbol.X;
                status.setImageResource(R.drawable.x);
                break;
            case X:
                sideToMove = Symbol.O;
                status.setImageResource(R.drawable.o);
                break;
        }
    }

    /* Generic position clicked */
    private void PosNClicked(int n)
    {
        Log.i("MainActivity", "Entered PosNClicked with id " + n);
        Move(n);
    }

    /* Method called if pos1 has been clicked */
    public void Pos1Clicked(View view)
    {
        PosNClicked(1);
    }

    /* Method called if pos2 has been clicked */
    public void Pos2Clicked(View view)
    {
        PosNClicked(2);
    }

    /* Method called if pos3 has been clicked */
    public void Pos3Clicked(View view)
    {
        PosNClicked(3);
    }

    /* Method called if pos4 has been clicked */
    public void Pos4Clicked(View view)
    {
        PosNClicked(4);
    }

    /* Method called if pos5 has been clicked */
    public void Pos5Clicked(View view)
    {
        PosNClicked(5);
    }

    /* Method called if pos6 has been clicked */
    public void Pos6Clicked(View view)
    {
        PosNClicked(6);
    }

    /* Method called if pos7 has been clicked */
    public void Pos7Clicked(View view)
    {
        PosNClicked(7);
    }

    /* Method called if pos8 has been clicked */
    public void Pos8Clicked(View view)
    {
        PosNClicked(8);
    }

    /* Method called if pos9 has been clicked */
    public void Pos9Clicked(View view)
    {
        PosNClicked(9);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get positions
        pos[0] = findViewById(R.id.pos1);
        pos[1] = findViewById(R.id.pos2);
        pos[2] = findViewById(R.id.pos3);
        pos[3] = findViewById(R.id.pos4);
        pos[4] = findViewById(R.id.pos5);
        pos[7] = findViewById(R.id.pos8);
        pos[5] = findViewById(R.id.pos6);
        pos[6] = findViewById(R.id.pos7);
        pos[8] = findViewById(R.id.pos9);
        status = findViewById(R.id.statusIcon);

        // Initialize variables
        sideToMove = Symbol.X;
        status.setImageResource(R.drawable.x);
        GameBB = new Bitboard();
        PlayerXBB = new Bitboard();
        PlayerYBB = new Bitboard();
    }
}