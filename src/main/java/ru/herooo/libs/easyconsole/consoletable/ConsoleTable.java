package ru.herooo.libs.easyconsole.consoletable;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class ConsoleTable {
    private ConsoleTableColumn[] columns;

    public ConsoleTable(ConsoleTableColumn[] columns) {
        this.columns = columns;
    }

    public void printDelimiter() {
        if (columns != null) {
            int numberOfDashes = 0;
            for (ConsoleTableColumn column: columns) {
                numberOfDashes += column.getWidth() + 1;
            }

            System.out.println("-".repeat(Math.max(0, numberOfDashes)));
        }
    }

    public void printHeader() {
        if (columns != null) {
            StringBuilder tableHeader = new StringBuilder();
            for (ConsoleTableColumn column: columns) {
                int width = column.getWidth();
                String name = column.getName();

                if (name != null) {
                    tableHeader.append(" ".repeat(Math.max(1, width - name.length() + 1)));
                }
                tableHeader.append(name);
            }

            printDelimiter();
            System.out.println(tableHeader);
            printDelimiter();
        }

    }

    public void printTableRow(boolean doNeedToPrintHeader,
                              boolean doNeedToPrintLowerDelimiter, Object ...values) {
        if (values == null || Arrays
                .stream(values)
                .filter(Objects::nonNull)
                .findAny()
                .orElse(null) == null) {
            values = new Object[] { "Пусто" };
        }

        if (columns != null) {
            if (doNeedToPrintHeader) {
                printHeader();
            }

            StringBuilder tableRow = new StringBuilder();
            for (int i = 0; i < columns.length; i++) {
                if (i >= values.length) {
                    break;
                }

                String valueStr = "-";
                Object currentValue = values[i];
                if (currentValue != null) {
                    if (currentValue instanceof Date date) {
                        valueStr = new SimpleDateFormat("dd.MM.yyyy").format(date);
                    } else {
                        valueStr = String.valueOf(currentValue);
                    }
                }

                tableRow.append(" ".repeat(Math.max(1, columns[i].getWidth() - valueStr.length() + 1)));
                tableRow.append(valueStr);
            }

            System.out.println(tableRow);

            if (doNeedToPrintLowerDelimiter) {
                printDelimiter();
            }
        }
    }
}
