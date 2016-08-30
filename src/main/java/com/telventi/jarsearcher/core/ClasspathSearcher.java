/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

import com.telventi.jarsearcher.core.EntryNameVisitor;
import com.telventi.jarsearcher.core.EntryVisitor;
import java.io.File;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class ClasspathSearcher {
    private void search(EntryVisitor visitor) {
        String classpath = System.getProperty("java.class.path");
        String pathSep = System.getProperty("path.separator");
        StringTokenizer pathEls = new StringTokenizer(classpath, pathSep);
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
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("ClasspathSearcher usage: ClasspathSearcher <pattern>\nWhere <pattern> is a regular expression that will be matched against the\nentries in the jars e.g.\n.*class will match all class files;\n.*ObjectPool.*class will return any classes starting with ObjectPool\norg/apache/commons/pool.*class will return all classes in that package");
        } else {
            String pattern = args[0];
            ClasspathSearcher searcher = new ClasspathSearcher();
            EntryNameVisitor visitor = new EntryNameVisitor(pattern);
            searcher.search(visitor);
            System.out.println(visitor.getResults());
        }
        System.out.println("Classpath searcher: http://www.isocra.com");
    }
}

