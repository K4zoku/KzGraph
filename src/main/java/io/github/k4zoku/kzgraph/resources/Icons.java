package io.github.k4zoku.kzgraph.resources;

import io.github.k4zoku.kzgraph.ResourcesGetter;

import java.net.URL;

public class Icons {
    private static final String ICONS_DIR = "icons/";
    public static final URL APP_ICON = ResourcesGetter.getResource(ICONS_DIR + "app_icon.png");
    public static final URL CLEAR_ICON = ResourcesGetter.getResource(ICONS_DIR + "clear.png");
    public static final URL EXPORT_AS_IMAGE_ICON = ResourcesGetter.getResource(ICONS_DIR + "export_as_image.png");
    public static final URL EXIT_ICON = ResourcesGetter.getResource(ICONS_DIR + "exit.png");
    public static final URL LIST_ICON = ResourcesGetter.getResource(ICONS_DIR + "list.png");
    public static final URL MATRIX_ICON = ResourcesGetter.getResource(ICONS_DIR + "matrix.png");
    public static final URL OPEN_ICON = ResourcesGetter.getResource(ICONS_DIR + "open.png");
    public static final URL SAVE_ICON = ResourcesGetter.getResource(ICONS_DIR + "save.png");
    public static final URL SAVE_AS_ICON = ResourcesGetter.getResource(ICONS_DIR + "save_as.png");

    private Icons() {
        // no instance
    }
}
