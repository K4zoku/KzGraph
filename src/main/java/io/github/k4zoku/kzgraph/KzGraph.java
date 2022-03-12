package io.github.k4zoku.kzgraph;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import io.github.k4zoku.kzgraph.view.KzGraphWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class KzGraph implements Runnable {

    public static void main(String[] args) {
        new KzGraph().run();
    }

    private Logger getLogger() {
        return Logger.getLogger(KzGraph.class.getName());
    }

    @Override
    public void run() {
        // Enable anti-aliasing
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        // Set the look and feel
        boolean isDark = System.getenv().get("DARK_MODE") != null;
        boolean success = isDark ? FlatDarculaLaf.setup() : FlatIntelliJLaf.setup();
        if (success) {
            // Customize FlatLaf
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        } else {
            getLogger().warning("Failed to set the Flatlaf look and feel");
            getLogger().warning("Fallback to the Nimbus look and feel");
            Arrays.stream(UIManager.getInstalledLookAndFeels())
                    .filter(laf -> laf.getName().contains("Nimbus"))
                    .findFirst()
                    .ifPresent(laf -> {
                        try {
                            UIManager.setLookAndFeel(laf.getClassName());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                            getLogger().log(Level.SEVERE, "Failed to set the Nimbus look and feel", e);
                        }
                    });
        }
        // Create the main window
        SwingUtilities.invokeLater(new KzGraphWindow("KzGraph"));
    }
}
