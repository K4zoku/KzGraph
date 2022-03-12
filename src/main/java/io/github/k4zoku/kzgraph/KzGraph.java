package io.github.k4zoku.kzgraph;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import io.github.k4zoku.kzgraph.view.KzGraphWindow;

import javax.swing.*;
import java.util.logging.Logger;

public final class KzGraph {
    public static void main(String[] args) {
        // Enable anti-aliasing
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        // Set the look and feel
        boolean isDark = System.getenv().get("DARK_MODE") != null;
        boolean success = isDark ? FlatDarculaLaf.setup() : FlatIntelliJLaf.setup();
        if (!success) {
            Logger.getLogger(KzGraph.class.getName()).warning("Failed to set the Flatlaf look and feel");
            Logger.getLogger(KzGraph.class.getName()).warning("Fallback to the Nimbus look and feel");
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (Exception e) {
                        Logger.getLogger(KzGraph.class.getName()).warning("Failed to set the look and feel");
                    }
                    break;
                }
            }
        }
        // Create the main window
        SwingUtilities.invokeLater(new KzGraphWindow("KzGraph"));
    }
}
