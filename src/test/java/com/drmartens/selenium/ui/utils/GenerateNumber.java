package com.drmartens.selenium.ui.utils;
import java.util.Random;

public class GenerateNumber {
    public static int randomNumber(int size){
        Random random = new Random();
        int randomNum = random.nextInt(size);
        return randomNum;
    }
}
