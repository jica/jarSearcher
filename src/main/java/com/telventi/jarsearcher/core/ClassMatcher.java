/*
 * Decompiled with CFR 0_115.
 */
package com.telventi.jarsearcher.core;

public class ClassMatcher {
    private String pattern;

    public ClassMatcher(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }

    public boolean matches(String hit) {
        if (hit.toLowerCase().contains(this.pattern.toLowerCase())) {
            return true;
        }
        return false;
    }
}

