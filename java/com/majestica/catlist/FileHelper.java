package com.majestica.catlist;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper
{
    public static String fileName;

    public static void setFileName(String fileName)
    {
        if(fileName !=null && !fileName.isEmpty())
        {
            FileHelper.fileName = fileName;
        }
    }


    public static void writeData(ArrayList<String> items, Context context)
    {
        try
        {

            FileOutputStream outputFile = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            ObjectOutputStream outputObject = new ObjectOutputStream(outputFile);
            outputObject.writeObject(items);
            outputObject.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> readData(Context context)
    {

        ArrayList<String> itemList = new ArrayList<>();
        try
        {

            FileInputStream inputFile = context.openFileInput(fileName);
            ObjectInputStream inputObject = new ObjectInputStream(inputFile);
            itemList = (ArrayList<String>) inputObject.readObject();
        }
        catch(FileNotFoundException e)
        {

            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return itemList;
    }
}
