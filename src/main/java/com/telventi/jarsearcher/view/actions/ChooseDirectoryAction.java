/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.view.actions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ChooseDirectoryAction
extends AbstractAction {
    private JFileChooser chooser;
    private JFrame frame;
    private String choosertitle;
    private JTextField ruta;

    public ChooseDirectoryAction(JFrame frame, JFileChooser chooser, String title, JTextField ruta) {
        super(title);
        this.chooser = chooser;
        this.frame = frame;
        this.ruta = ruta;
    }

    public void actionPerformed(ActionEvent e) {
        this.chooser.setDialogTitle(this.choosertitle);
        if (this.ruta != null && !this.ruta.getText().equals("")) {
            this.chooser.setCurrentDirectory(new File(this.ruta.getText()));
            System.out.println("Creo con " + this.ruta.getText());
        } else {
            this.chooser.setCurrentDirectory(new File("."));
            System.out.println("Creo con . porque lo que hay es " + this.ruta.getText());
        }
        this.chooser.setDialogTitle(this.choosertitle);
        this.chooser.setFileSelectionMode(1);
        this.chooser.setAcceptAllFileFilterUsed(false);
        if (this.chooser.showOpenDialog(this.frame) == 0) {
            this.ruta.setText(this.chooser.getSelectedFile().getAbsolutePath());
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}

