package ru.herooo.libs.easyconsole.consoletable;

public class ConsoleTableColumn {
    private int width;
    private String name;

    public ConsoleTableColumn(int width, String name) {
        this.width = width;
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

