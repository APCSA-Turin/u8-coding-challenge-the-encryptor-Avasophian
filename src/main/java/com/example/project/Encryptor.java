package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor 
{
    
    public static int determineColumns(int messageLen, int rows)
    {   
        if (messageLen == 0)
        {
            return 1;
        }
        return (int)((double)messageLen/rows + 0.999);
    }
    
    public static String[][] generateEncryptArray(String message, int rows)
    {


        String[][] arr = new String[rows][determineColumns(message.length(), rows)];
        if (message.equals(""))
        {
            for (int row = 0; row < arr.length; row++)
            {
                for (int col = 0; col < arr[0].length; col++)
                {
                    if (arr[row][col] == null)
                    {
                        arr[row][col] = "=";
                    }
                }
            }
            return arr;
        }
        
        int i = 0;
        int r = 0;
        int c = 0;
        for (int row = 0; row < arr.length; row++)
        {
            for (int col = 0; col < arr[0].length; col++)
            {
                if (i + 1 < message.length())
                {
                    r = row;
                    c = col;
                    arr[row][col] = message.substring(i, i+1);
                    i++;
                }
            }
        }   
        if (!(c + 1 >= arr[0].length))
        {
            arr[r][c + 1] = message.substring(i);
        }
        else 
        {
            arr[r + 1][0] = message.substring(i);
        }
        for (int row = 0; row < arr.length; row++)
        {
            for (int col = 0; col < arr[0].length; col++)
            {
                if (arr[row][col] == null)
                {
                    arr[row][col] = "=";
                }
            }
        }
        return arr;
    }

    public static String encryptMessage(String message, int rows)
    {
        String encrypted = "";
        String[][] arr = generateEncryptArray(message, rows);
        for (int col = arr[0].length - 1; col >= 0; col--)
        {
            for (int row = 0; row < arr.length; row++)
            {
               encrypted += arr[row][col];
            }
        }
        return encrypted;
    }

    public static String decryptMessage(String encryptedMessage, int rows) 
    {
        int cols = determineColumns(encryptedMessage.length(), rows);
        String[][] decryptedArr = new String[rows][cols];
        int c = 0;
        for (int col = cols - 1; col >= 0; col--) 
        {
            for (int row = 0; row < rows; row++) 
            {
                if (c < encryptedMessage.length()) 
                {
                    decryptedArr[row][col] = encryptedMessage.substring(c, c + 1);
                    c++;
                }
            }
        }
        String decrypted = "";
        for (int row = 0; row < rows; row++) 
        {
            for (int col = 0; col < cols; col++)
             {
                if (decryptedArr[row][col] != null && !decryptedArr[row][col].equals("=")) 
                {
                    decrypted += decryptedArr[row][col];
                }
            }
        }
        return decrypted;
    }

    public static void main(String[] args) 
    {
        System.out.println(decryptMessage("  t=wsf=owa?no tkldn leauamlomapyhn  s adre iarrDmae", 4));        
    }
}