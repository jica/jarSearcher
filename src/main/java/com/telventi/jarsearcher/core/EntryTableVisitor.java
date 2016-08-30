/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

import com.telventi.jarsearcher.core.ClassMatcher;
import com.telventi.jarsearcher.core.EntryVisitor;
import java.io.File;
import java.io.PrintStream;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class EntryTableVisitor
extends EntryVisitor {
    private ClassMatcher matcher;
    private Vector classes;
    private Vector files;
    private int _count = 0;

    public EntryTableVisitor(String patternStr) {
        patternStr = this.preparePattern(patternStr);
        this.matcher = new ClassMatcher(patternStr);
        this.classes = new Vector();
        this.files = new Vector();
    }

    private String preparePattern(String pattern) {
        System.out.println("Preparo " + pattern);
        if (pattern.contains(".")) {
            pattern = pattern.replaceAll("\\.", "/");
        }
        System.out.println("Lo pongo " + pattern);
        return pattern;
    }

    public void visit(JarFile jar, JarEntry entry) {
        if (this.matcher.matches(entry.getName())) {
            System.out.println("Encontrado " + entry.getName() + " en " + jar.getName());
            this.classes.add(this._count, entry.getName());
            this.files.add(this._count, jar.getName());
            ++this._count;
        }
    }

    public void visit(File file) {
        if (this.matcher.matches(file.getName())) {
            this.classes.add(this._count, file.getName());
            this.files.add(this._count, file.getPath());
            ++this._count;
        }
    }

    public String getResults() {
        if (this._count == 0) {
            return "No entries found matching \"" + this.matcher.getPattern() + "\"";
        }
        return "";
    }

    public Vector getClassesResults() {
        return this.classes;
    }

    public Vector getFilesResults() {
        return this.files;
    }
}

