/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

import java.io.PrintStream;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class HitsResultsModel
extends AbstractTableModel {
    private Vector col1;
    private Vector col2;
    private int rows = 0;
    private final int cols = 2;
    private String[] columnNames = new String[2];
    private static final boolean DEBUG = true;

    public HitsResultsModel(String col1Name, String col2Name) {
        this.columnNames[0] = col1Name;
        this.columnNames[1] = col2Name;
        this.col1 = new Vector();
        this.col2 = new Vector();
    }

    public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return this.rows;
    }

    public String getColumnName(int col) {
        return this.columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if (col == 0) {
            return this.col1.elementAt(row);
        }
        if (col == 1) {
            return this.col2.elementAt(row);
        }
        return null;
    }

    public void addRow(String name, String size) {
        System.out.println("Inserto " + name + " , " + size + " en " + this.rows);
        this.col1.add(this.rows, name);
        this.col2.add(this.rows, size);
        ++this.rows;
        this.fireTableRowsInserted(this.rows - 1, this.rows - 1);
    }

    public Vector getDataVector() {
        return this.col1;
    }

    public Class getColumnClass(int c) {
        return this.getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void insertRow(int pos, String val1, String val2) {
        System.out.println("Insertando fila " + pos);
        System.out.println("Col1: " + val1);
        System.out.println("Col2: " + val2);
        this.col1.insertElementAt(this.col1, pos);
        this.col2.insertElementAt(this.col2, pos);
        ++this.rows;
        this.fireTableRowsInserted(pos, pos);
    }

    public void removeRow(int pos) {
        this.col1.remove(pos);
        this.col2.remove(pos);
        --this.rows;
        this.fireTableRowsDeleted(pos, pos);
    }

    public void setValueAt(Object value, int row, int col) {
        System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of " + value.getClass() + ")");
        if (col < 1 && col >= 0) {
            if (col == 0) {
                this.col1.remove(row);
                this.col1.add(row, value);
            } else if (col == 1) {
                this.col2.remove(row);
                this.col2.add(row, value);
            }
            this.fireTableCellUpdated(row, col);
        }
        System.out.println("New value of data:");
        this.printDebugData();
    }

    private void printDebugData() {
        int numRows = this.getRowCount();
        int i = 0;
        while (i < numRows) {
            System.out.print(" row " + i + ":");
            System.out.print(" " + this.col1.elementAt(i) + " " + this.col2.elementAt(i));
            System.out.println();
            ++i;
        }
        System.out.println("--------------------------");
    }
}

