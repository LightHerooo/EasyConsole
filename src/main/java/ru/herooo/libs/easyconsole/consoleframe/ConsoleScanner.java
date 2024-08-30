package ru.herooo.libs.easyconsole.consoleframe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleScanner {
    private Scanner scanner;

    public ConsoleScanner() {
        refreshScanner();
    }

    private void refreshScanner() {
        if (scanner != null) {
            scanner.close();
        }

        scanner = new Scanner(System.in, "cp866");
    }


    public int waitChoice() {
        return waitInt("Выберите значение из списка");
    }


    public int waitInt() {
        return waitInt(null);
    }

    public int waitInt(String label) {
        int value = -1;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите число";
        }

        System.out.printf("%s: ", label);
        if (scanner.hasNextInt()) {
            value = scanner.nextInt();
            scanner.nextLine();
        } else {
            scanner.next();
            scanner.nextLine();
        }

        return value;
    }

    public int waitInt(String label, boolean isNullPossible) {
        int value = -1;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите число";
        }

        if (isNullPossible) {
            System.out.printf("%s (%s): ", label, "null, если пусто");
        } else {
            System.out.printf("%s: ", label);
        }

        String str = scanner.nextLine();
        if (isNullPossible && str.equals("null")) {
            value = 0;
        } else {
            try {
                value = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                value = -1;
            }
        }
        return value;
    }

    public int waitInt(String label, int oldValue, boolean isNullPossible) {
        int value = -1;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите число";
        }

        String extraStr = "-, если без изменений";
        if (isNullPossible) {
            extraStr += " | null, если пусто";
        }

        System.out.printf("%s (%s): ", label, extraStr);
        String str = scanner.nextLine();
        if (str.equals("-")) {
            value = oldValue;
        } else if (isNullPossible && str.equals("null")) {
            value = 0;
        } else {
            try {
                value = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                value = -1;
            }
        }

        return value;
    }


    public String waitString() {
        return waitString(null);
    }

    public String waitString(String label) {
        String value = null;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите строку";
        }

        System.out.printf("%s: ", label);
        value = scanner.nextLine();

        return value;
    }

    public String waitString(String label, boolean isNullPossible) {
        String value = null;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите строку";
        }

        if (isNullPossible) {
            System.out.printf("%s (%s): ", label, "null, если пусто");
        } else {
            System.out.printf("%s: ", label);
        }

        value = scanner.nextLine();
        if (isNullPossible && value.equals("null")) {
            value = null;
        }

        return value;
    }

    public String waitString(String label, String oldValue, boolean isNullPossible) {
        String value = null;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите строку";
        }

        String extraStr = "-, если без изменений";
        if (isNullPossible) {
            extraStr += " | null, если пусто";
        }

        System.out.printf("%s (%s): ", label, extraStr);
        value = scanner.nextLine();

        if (value.equals("-")) {
            value = oldValue;
        } else if (isNullPossible && value.equals("null")) {
            value = null;
        }

        return value;
    }


    public boolean waitBoolean() {
        return waitBoolean(null);
    }

    public boolean waitBoolean(String label) {
        boolean value = false;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Выберите";
        }

        System.out.printf("%s (y/n): ", label);
        String choice = scanner.nextLine();
        value = choice.equalsIgnoreCase("y");

        return value;
    }

    public boolean waitBoolean(String label, boolean oldValue) {
        boolean value = false;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Выберите";
        }

        String extraStr = "-, если без изменений";

        System.out.printf("%s (y/n) (%s): ", label, extraStr);
        String choice = scanner.nextLine();
        if (choice.equals("-")) {
            value = oldValue;
        } else {
            value = choice.equalsIgnoreCase("y");
        }

        return value;
    }


    public Date waitDate() {
        return waitDate(null);
    }

    public Date waitDate(String label) {
        Date value = null;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите дату";
        }

        System.out.printf("%s (например: 01.01.2000): ", label);
        try {
            value = new SimpleDateFormat("dd.MM.yyyy").parse(scanner.nextLine());
        } catch (ParseException e) {
            value = null;
        }

        return value;
    }

    public Date waitDate(String label, boolean isNullPossible) {
        Date value = null;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите дату";
        }

        if (isNullPossible) {
            System.out.printf("%s (например: 01.01.2000) (%s): ", label, "null, если пусто");
        } else {
            System.out.printf("%s (например: 01.01.2000): ", label);
        }

        String str = scanner.nextLine();
        if (isNullPossible && str.equals("null")) {
            value = null;
        } else {
            try {
                value = new SimpleDateFormat("dd.MM.yyyy").parse(str);
            } catch (ParseException e) {
                value = null;
            }
        }

        return value;
    }

    public Date waitDate(String label, Date oldValue, boolean isNullPossible) {
        Date value = null;

        if (label == null || label.isEmpty() || label.isBlank()) {
            label = "Введите дату";
        }

        String extraStr = "-, если без изменений";
        if (isNullPossible) {
            extraStr += " | null, если пусто";
        }

        System.out.printf("%s (например: 01.01.2000) (%s): ", label, extraStr);
        String str = scanner.nextLine();
        if (str.equals("-")) {
            value = oldValue;
        } else if (isNullPossible && str.equals("null")) {
            value = null;
        } else {
            try {
                value = new SimpleDateFormat("dd.MM.yyyy").parse(str);
            } catch (ParseException e) {
                value = null;
            }
        }

        return value;
    }


    public void waitEnter() {
        System.out.println("Нажмите ENTER, чтобы продолжить...");
        scanner.nextLine();
    }
}
