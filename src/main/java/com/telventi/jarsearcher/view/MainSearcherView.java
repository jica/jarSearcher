/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.view;

import com.telventi.jarsearcher.core.HitsResultsModel;
import com.telventi.jarsearcher.core.LaunchClasspathSearch;
import com.telventi.jarsearcher.core.LaunchDirSearch;
import com.telventi.jarsearcher.view.actions.ChooseDirectoryAction;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

public class MainSearcherView
extends JFrame
implements ActionListener {
    private JTable table;
    private JTextField dirquery;
    private JTextField classquery;
    private JTextField ruta;
    private JFileChooser chooser;
    private JButton dirButton;
    private JLabel dirlabel;
    private JPanel directoryPanel;
    private JPanel classpathPanel;
    private JTabbedPane tabbedPane;
    private JLabel buslabel;
    private JButton searchButton;
    private JScrollPane directoryScrollPane;
    private HitsResultsModel model;

    public void actionPerformed(ActionEvent evt) {
    }

    public static void main(String[] args) {
        try {
            MainSearcherView inst = new MainSearcherView();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            inst.setLocation(screenSize.width / 2 - inst.getWidth() / 2, screenSize.height / 2 - inst.getHeight() / 2);
            inst.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MainSearcherView() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.initialize();
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setBounds(10, 10, 772, 95);
        this.getContentPane().add(this.tabbedPane);
        this.directoryPanel = new JPanel();
        this.classpathPanel = new JPanel();
        this.directoryPanel.setLayout(null);
        this.classpathPanel.setLayout(null);
        this.tabbedPane.addTab("Directorio", null, this.directoryPanel, "B\u00fasqueda por directorios");
        this.tabbedPane.addTab("Classpath", null, this.classpathPanel, "Busqueda por classpath");
        this.fillDirectoryPanel();
        this.fillClasspathPanel();
        this.directoryScrollPane = new JScrollPane();
        this.directoryScrollPane.setBounds(11, 111, 771, 407);
        this.getContentPane().add(this.directoryScrollPane);
        this.table = new JTable();
        this.model = new HitsResultsModel("Clase", "Archivo");
        this.table.setModel(this.model);
        this.directoryScrollPane.setViewportView(this.table);
    }

    private void fillDirectoryPanel() {
        this.dirlabel = new JLabel();
        this.dirlabel.setText("Directorio:");
        this.dirlabel.setBounds(10, 42, 61, 14);
        this.directoryPanel.add(this.dirlabel);
        this.ruta = new JTextField();
        this.ruta.setBackground(Color.WHITE);
        this.ruta.setEditable(false);
        this.ruta.setBounds(77, 39, 609, 20);
        this.directoryPanel.add(this.ruta);
        this.chooser = new JFileChooser();
        this.chooser.removeChoosableFileFilter(this.chooser.getChoosableFileFilters()[0]);
        this.chooser.addChoosableFileFilter(new FolderFilter());
        this.dirButton = new JButton(new ChooseDirectoryAction(this, this.chooser, "Abris carpeta...", this.ruta));
        this.dirButton.setMargin(new Insets(2, 2, 2, 2));
        this.dirButton.setText("Examinar...");
        this.dirButton.setBounds(692, 38, 67, 23);
        this.directoryPanel.add(this.dirButton);
        this.buslabel = new JLabel();
        this.buslabel.setText("Buscar:");
        this.buslabel.setBounds(10, 10, 54, 14);
        this.directoryPanel.add(this.buslabel);
        this.dirquery = new JTextField();
        this.dirquery.setBounds(77, 7, 609, 20);
        this.directoryPanel.add(this.dirquery);
        this.searchButton = new JButton();
        this.searchButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                if (!MainSearcherView.this.dirquery.getText().equals("")) {
                    LaunchDirSearch search = new LaunchDirSearch(MainSearcherView.this.ruta, MainSearcherView.this.dirquery);
                    MainSearcherView.access$2(MainSearcherView.this, search.launchSearch());
                    MainSearcherView.this.table.setModel(MainSearcherView.this.model);
                } else {
                    JOptionPane.showMessageDialog(null, "Indique un criterio de b\u00fasqueda", "Na que buscar", 0);
                }
            }
        });
        this.searchButton.setText("Buscar");
        this.searchButton.setBounds(692, 6, 67, 23);
        this.directoryPanel.add(this.searchButton);
    }

    private void fillClasspathPanel() {
        this.buslabel = new JLabel();
        this.buslabel.setText("Buscar:");
        this.buslabel.setBounds(10, 10, 54, 14);
        this.classpathPanel.add(this.buslabel);
        this.classquery = new JTextField();
        this.classquery.setBounds(77, 7, 609, 20);
        this.classpathPanel.add(this.classquery);
        this.searchButton = new JButton();
        this.searchButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                if (!MainSearcherView.this.classquery.getText().equals("")) {
                    LaunchClasspathSearch search = new LaunchClasspathSearch(MainSearcherView.this.classquery);
                    MainSearcherView.access$2(MainSearcherView.this, search.launchSearch());
                    MainSearcherView.this.table.setModel(MainSearcherView.this.model);
                } else {
                    JOptionPane.showMessageDialog(null, "Indique un criterio de b\u00fasqueda", "Na que buscar", 0);
                }
            }
        });
        this.searchButton.setText("Buscar");
        this.searchButton.setBounds(692, 6, 67, 23);
        this.classpathPanel.add(this.searchButton);
    }

    private void initialize() {
        this.setSize(800, 555);
        this.setDefaultCloseOperation(3);
        this.getContentPane().setBackground(new Color(212, 208, 200));
        this.setBackground(new Color(212, 208, 200));
        this.setResizable(false);
        this.setTitle("TiJarSearcher");
        this.getContentPane().setLayout(null);
    }

    static /* synthetic */ void access$2(MainSearcherView mainSearcherView, HitsResultsModel hitsResultsModel) {
        mainSearcherView.model = hitsResultsModel;
    }

    class FolderFilter
    extends FileFilter {
        FolderFilter() {
        }

        public boolean accept(File f) {
            if (!f.isDirectory() && !f.getName().toLowerCase().endsWith(".jar")) {
                return false;
            }
            return true;
        }

        public String getDescription() {
            return "JAR files";
        }
    }

}

