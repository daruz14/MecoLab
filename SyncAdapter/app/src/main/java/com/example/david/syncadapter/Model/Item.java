package com.example.david.syncadapter.Model;

/**
 * Created by david on 25/05/16.
 */
public class Item {
    public final static int STUDENT_CELL = 1;
    public final static int LETTER_CELL = 0;

    private String mText;
    private int mCellType;

    public Item(String text, int celType){
        mText = text;
        mCellType = celType;
    }

    public int getCellType(){
        return mCellType;
    }

    public String getText(){
        return mText;
    }

}
