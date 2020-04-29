package com.company.util;

import java.io.File;
import java.io.IOException;

import com.company.App;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.paint.Color;

public class PreferencesData {
    private String font;
    private String fontSize;
    private String defaultPath;

    public PreferencesData() {

    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }
}
