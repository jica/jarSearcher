/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

import com.telventi.jarsearcher.core.EntryTableVisitor;
import com.telventi.jarsearcher.core.HitsResultsModel;
import java.io.File;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LaunchClasspathSearch {
    private JTextField query;
    private Vector classesResults;
    private Vector filesResults;

    public LaunchClasspathSearch(JTextField query) {
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
        String classpath = System.getProperty("java.class.path");
        String pathSep = System.getProperty("path.separator");
        StringTokenizer pathEls = new StringTokenizer(classpath, pathSep);
        EntryTableVisitor visitor = new EntryTableVisitor(this.query.getText());
        while (pathEls.hasMoreTokens()) {
            String element = pathEls.nextToken();
            File file = new File(element);
            if (visitor.isJar(file)) {
                visitor.searchJar(file);
                continue;
            }
            if (file.isDirectory()) {
                visitor.searchDir(file);
                continue;
            }
            visitor.visit(file);
        }
        this.classesResults = visitor.getClassesResults();
        this.filesResults = visitor.getFilesResults();
        return this.classesResults.size();
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

