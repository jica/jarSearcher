/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

import com.telventi.jarsearcher.core.EntryVisitor;
import java.io.File;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntryNameVisitor
extends EntryVisitor {
    private Pattern _pattern;
    private StringBuffer _output;
    private int _count = 0;

    public EntryNameVisitor(String patternStr) {
        this._pattern = Pattern.compile(patternStr);
        this._output = new StringBuffer();
    }

    public void visit(JarFile jar, JarEntry entry) {
        Matcher matcher = this._pattern.matcher(entry.getName());
        if (matcher.matches()) {
            this._output.append(String.valueOf(jar.getName()) + ": " + entry.getName() + "\n");
            ++this._count;
        }
    }

    public void visit(File file) {
        Matcher matcher = this._pattern.matcher(file.getName());
        if (matcher.matches()) {
            this._output.append(String.valueOf(file.getAbsolutePath()) + "\n");
            ++this._count;
        }
    }

    public String getResults() {
        if (this._count == 0) {
            return "No entries found matching \"" + this._pattern.pattern() + "\"";
        }
        this._output.append(String.valueOf(this._count) + " entries found.");
        return this._output.toString();
    }

    public Vector getFilesResults() {
        throw new Error("Unresolved compilation problem: \n\tThe type EntryNameVisitor must implement the inherited abstract method EntryVisitor.getFilesResults()\n");
    }

    public Vector getClassesResults() {
        throw new Error("Unresolved compilation problem: \n\tThe type EntryNameVisitor must implement the inherited abstract method EntryVisitor.getClassesResults()\n");
    }
}

