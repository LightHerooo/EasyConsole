package ru.herooo.libs.easyconsole.consoleframe;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ConsoleFrameAbstract {
    private final String APP_NAME;
    private final String NAME;
    private final String HEADER;

    protected final ConsoleScanner CONSOLE_SCANNER = new ConsoleScanner();

    public ConsoleFrameAbstract(String appName, String name) {
        this.APP_NAME = appName;
        this.NAME = name;
        this.HEADER = String.format("%s. %s", appName, name);
    }

    public String getAppName() {
        return APP_NAME;
    }

    public String getName() {
        return NAME;
    }

    public String getHeader() {
        return HEADER;
    }


    public void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printHeader() {
        printDelimiter();
        System.out.println(HEADER);
        printDelimiter();
    }

    public void printDelimiter() {
        System.out.println("-".repeat(HEADER.length()));
    }

    public void printDelimiter(int numberOfDashes) {
        System.out.println("-".repeat(Math.max(0, numberOfDashes)));
    }


    public void printMenu(Map<Integer, String> menuMap, String exitLabel,
                          boolean doNeedToPrintUpperDelimiter, boolean doNeedToPrintLowerDelimiter) {
        if (doNeedToPrintUpperDelimiter) {
            printDelimiter();
        }

        if (menuMap != null) {
            for (Map.Entry<Integer, String> menuItem: menuMap.entrySet()) {
                Integer key = menuItem.getKey();
                String value = menuItem.getValue();
                if (key != null && value != null) {
                    System.out.printf("%d. %s\n", key, value);
                }
            }
        }
        System.out.printf("0. %s\n", exitLabel);

        if (doNeedToPrintLowerDelimiter) {
            printDelimiter();
        }
    }

    public void printMenu(Map<Integer, String> menuMap, String exitLabel) {
        printMenu(menuMap, exitLabel, false, false);
    }

    public void printMenu(ConsoleFrameAbstract[] consoleFrames, String exitLabel,
                          boolean doNeedToPrintUpperDelimiter, boolean doNeedToPrintLowerDelimiter) {
        Map<Integer, String> menuMap = new HashMap<>();
        if (consoleFrames != null) {
            for (int i = 0; i < consoleFrames.length; i++) {
                ConsoleFrameAbstract current = consoleFrames[i];
                if (current != null) {
                    menuMap.put(i + 1, current.getName());
                }
            }
        }

        printMenu(menuMap, exitLabel, doNeedToPrintUpperDelimiter, doNeedToPrintLowerDelimiter);
    }

    public void printMenu(ConsoleFrameAbstract[] consoleFrames, String exitLabel) {
        printMenu(consoleFrames, exitLabel, false, false);
    }


    public void printWarning(String text) {
        printDelimiter();
        System.out.println(text);
        printDelimiter();

        CONSOLE_SCANNER.waitEnter();
    }

    public void printWarning(String format, Object ...args) {
        printWarning(String.format(format, args));
    }

    public void printError(String text, Throwable e) {
        printWarning("%s (%s)", text, e.getMessage());
    }


    public abstract void printContent();
}
