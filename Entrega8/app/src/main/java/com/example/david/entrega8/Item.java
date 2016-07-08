package com.example.david.entrega8;

/**
 * Created by david on 4/07/16.
 */
public class Item {
    public final static int I_CELL = 1;
    public final static int SERVER_CELL = 0;

    private String mText;
    private String mFrom;
    private String mTo;
    private int mCellType;//Esto puede ser de externo o interno
    //1 para Uno el que lo mando
    //0 si lo mando el servidor

    public Item(String text, int cellType, String para, String de) {
        mText = text;
        mCellType = cellType;
        mTo=para;
        mFrom=de;
    }

    public int getCellType() {
        return mCellType;
    }

    public String getText() {
        return mText;
    }
    public String getmTo() {
        return mTo;
    }
    public String getmFrom() {
        return mFrom;
    }
}