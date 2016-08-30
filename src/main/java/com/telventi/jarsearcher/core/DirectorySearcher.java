/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

import com.telventi.jarsearcher.core.EntryNameVisitor;
import java.io.File;
import java.io.PrintStream;

public class DirectorySearcher {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("DirectorySearcher usage: DirectorySearcher <directory> <pattern>\nWhere <pattern> is a regular expression that will be matched against the\nentries in the jars e.g.\n.*class will match all class files;\n.*ObjectPool.*class will return any classes starting with ObjectPool\norg/apache/commons/pool.*class will return all classes in that package");
        } else {
            File directory = new File(args[0]);
            String pattern = args[1];
            if (directory.isDirectory()) {
                EntryNameVisitor visitor = new EntryNameVisitor(pattern);
                visitor.searchDir(directory);
                System.out.println(visitor.getResults());
            } else {
                System.out.println("DirectorySearcher: " + directory + " must be a directory");
            }
        }
        System.out.println("Directory searcher: http://www.isocra.com");
    }
}

