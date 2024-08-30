package ru.herooo.libs.easyconsole.consoletable;

import java.util.List;

public abstract class ConsoleTableEntityAbstract<T> extends ConsoleTable {
    public ConsoleTableEntityAbstract(ConsoleTableColumn[] columns) {
        super(columns);
    }

    public void printTable(List<T> entities) {
        printHeader();
        if (entities != null && !entities.isEmpty()) {
            for (T entity: entities) {
                printTableRow(entity, false, true);
            }
        } else {
            printTableRow(false, true, (Object) null);
        }
    }

    public void printTableRow(T entity,
                              boolean doNeedToPrintHeader,
                              boolean doNeedToPrintLowerDelimiter) {
        printTableRow(doNeedToPrintHeader, doNeedToPrintLowerDelimiter, getEntityValues(entity));
    }

    public abstract Object[] getEntityValues(T entity);
}
