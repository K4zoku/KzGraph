package io.github.k4zoku.kzgraph;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.util.logging.Logger;

public final class KzGraph {
    public static void main(String[] args) {
        // Set the look and feel
        boolean dark = Boolean.parseBoolean(System.getProperty("dark-mode", "false"));
        if (dark && FlatDarkLaf.setup() || !dark && FlatLightLaf.setup()) {
            Logger.getLogger(KzGraph.class.getName()).severe("Failed to set the look and feel.");
            return;
        }
        // Enable anti-aliasing
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

    }
}
