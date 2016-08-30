/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

import com.telventi.jarsearcher.core.EntryTableVisitor;
import com.telventi.jarsearcher.core.HitsResultsModel;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LaunchDirSearch {
    private JTextField query;
    private JTextField path;
    private Vector classesResults;
    private Vector filesResults;

    public LaunchDirSearch(JTextField path, JTextField query) {
        this.path = path;
        this.query = query;
    }

    public HitsResultsModel launchSearch() {
        int num = this.performSearch();
        if (num > 0) {
            return this.fillModel();
        }
        JOptionPane.showMessageDialog(null, "No se encontraron resultados...", "Na de na...", 0);
        return new HitsResultsModel("Clase", "Archivo");
    }

    private int performSearch() {
        File directory = new File(this.path.getText());
        if (directory.isDirectory()) {
            EntryTableVisitor visitor = new EntryTableVisitor(this.query.getText());
            visitor.searchDir(directory);
            this.classesResults = visitor.getClassesResults();
            this.filesResults = visitor.getFilesResults();
            return this.classesResults.size();
        }
        JOptionPane.showMessageDialog(null, "Debe seleccionar un directorio v\u00e1lido para la b\u00fasqueda", "No ha seleccionado ning\u00fan directorio", 0);
        return 0;
    }

    private HitsResultsModel fillModel() {
        int row = 0;
        HitsResultsModel model = new HitsResultsModel("Clase", "Archivo");
        Iterator it = this.classesResults.iterator();
        while (it.hasNext()) {
            String clase = (String)it.next();
            String file = (String)this.filesResults.elementAt(row);
            model.addRow(clase, file);
            ++row;
        }
        return model;
    }
}

