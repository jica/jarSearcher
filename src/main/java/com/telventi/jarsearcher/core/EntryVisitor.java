/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class EntryVisitor {
    public abstract void visit(JarFile var1, JarEntry var2);

    public abstract void visit(File var1);

    public abstract String getResults();

    public abstract Vector getClassesResults();

    public abstract Vector getFilesResults();

    public boolean isJar(File file) {
        return file.getName().toLowerCase().endsWith(".jar");
    }

    public void searchJar(File jarFile) {
        try {
            JarFile jar = new JarFile(jarFile, false, 1);
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                this.visit(jar, entry);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchDir(File directory) {
        File[] files = directory.listFiles();
        int i = 0;
        while (i < files.length) {
            File file = files[i];
            if (this.isJar(file)) {
                this.searchJar(file);
            } else if (file.isDirectory()) {
                this.searchDir(file);
            } else {
                this.visit(file);
            }
            ++i;
        }
    }
}

