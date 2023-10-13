/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author LENOVO
 */
public class Utils {
    public static String getDate(String welcome, String dateFormat) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        boolean check;
        String result = "";
        do {
            check = true;
            result = getString(welcome);
            try {
                sdf.parse(result);
            } catch (ParseException e) {
                check = false;
                System.err.println("Incorrect date ! Please enter again !");
            }

        } while (!check);
        return result;
    }

    public static String getDate(String welcome, String oldData, String dateFormat) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        boolean check;
        String result = "";
        do {
            check = true;
            result = getString(welcome, oldData);
            try {
                sdf.parse(result);
            } catch (ParseException e) {
                check = false;
                System.err.println("Incorrect date ! Please enter again !");
            }

        } while (!check);
        return result;
    }

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
}
