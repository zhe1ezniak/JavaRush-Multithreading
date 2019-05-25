package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Charset windows = Charset.forName("Windows-1251");
        Charset utf = Charset.forName("UTF-8");

        FileInputStream input = new FileInputStream(args[0]);
        FileOutputStream output = new FileOutputStream(args[1]);

        byte[] buffer = new byte[1000];
        input.read(buffer);
        String s = new String(buffer, windows);
        buffer = s.getBytes(utf);
        output.write(buffer);
    }
}
