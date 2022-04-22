package com.androidttt;

public class Bitboard {
    // Constants
    // Lines
    public static final int LINE_1 = 448;   // 111000000
    public static final int LINE_2 = 56;    // 000111000
    public static final int LINE_3 = 7;     // 000000111

    // Rows
    public static final int ROW_1 = 292;    // 100100100
    public static final int ROW_2 = 146;    // 010010010
    public static final int ROW_3 = 73;     // 001001001

    // Diags
    public static final int DIAG_1 = 273;   // 100010001
    public static final int DIAG_2 = 84;    // 001010100

    enum Side
    {
        UP_LEFT,
        UP_MID,
        UP_RIGHT,
        MID_LEFT,
        MID_MID,
        MID_RIGHT,
        LOW_LEFT,
        LOW_MID,
        LOW_RIGHT
    }

    public int sideToBb(Side side)
    {
        switch(side)
        {
            case UP_LEFT:   return 256;
            case UP_MID:    return 128;
            case UP_RIGHT:  return  64;
            case MID_LEFT:  return  32;
            case MID_MID:   return  16;
            case MID_RIGHT: return   8;
            case LOW_LEFT:  return   4;
            case LOW_MID:   return   2;
            case LOW_RIGHT: return   1;
            default:        return  -1;
        }
    }

    private int bitboard;
    static final int winningPositions [] =
            {   LINE_1, LINE_2, LINE_3,
                ROW_1, ROW_2, ROW_3,
                DIAG_1, DIAG_2 };

    public Bitboard() { bitboard = 0; };

    public Bitboard(Side side)
    {
        bitboard = sideToBb(side);
    }

    public void Add(Side side)
    {
        bitboard += sideToBb(side);
    }

    public boolean IsWinning()
    {
        for (int bb : winningPositions)
        {
            if ((bb & bitboard) == bb)
                return true;
        }

        return false;
    }

    public boolean isSideFree(Side side)
    {
        return (bitboard & sideToBb(side)) == 0 ? false : true;
    }

    public static Side idToSide(int id)
    {
        switch (id)
        {
            case 1: return Side.UP_LEFT;
            case 2: return Side.UP_MID;
            case 3: return Side.UP_RIGHT;
            case 4: return Side.MID_LEFT;
            case 5: return Side.MID_MID;
            case 6: return Side.MID_RIGHT;
            case 7: return Side.LOW_LEFT;
            case 8: return Side.LOW_MID;
            case 9: return Side.LOW_RIGHT;
            default: return Side.MID_MID;
        }
    }

    public void clear()
    {
        bitboard = 0;
    }
}
