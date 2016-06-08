package com.example.david.myapplication.Model;

/**
 * Created by david on 1/06/16.
 */
public class Item {
    public final static int STUDENT_CELL = 1;
    public final static int LETTER_CELL = 0;

    private String mText;
    private String mTextNumber;
    private int mCellType;

    public Item(String text,String numero, int celType){
        mText = text;
        mTextNumber = numero;
        mCellType = celType;
    }

    public int getCellType(){
        return mCellType;
    }

    public String getText(){
        return mText;
    }
    public String getNumber(){
        return mTextNumber;
    }

}