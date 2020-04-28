package com.company.util;

import com.company.App;
import javafx.scene.control.*;

import java.util.ArrayList;

public class searchEngine {
    private int index = 0;
    private int i = 0;
    private int matches = 0;
    private ArrayList<Integer> indexArray = new ArrayList<Integer>();

    public searchEngine (TextField searchField, TextArea textPanel) {
        if (searchField.getText() != null && !searchField.getText().isEmpty()) {
            index = textPanel.getText().indexOf(searchField.getText());
            if (index == -1) {
                App.errorBox("Search key Not in the text");
            } else {
                indexArray.add(index);
                i = index;
                while (i < textPanel.getLength()) {
                    index = textPanel.getText().indexOf(searchField.getText(), i + searchField.getLength());
                    if (index < 0 ) { break; }
                    indexArray.add(index);
                    i = index;
                }
                setIndex(i);
                setMatches(indexArray.size());
            }
        } else {
        App.errorBox("Missing search key");
        }
    }

    public void setIndex(int a) { this.index = a; }

    public int getIndex() { return this.index; }

    public void setMatches(int a) { this.matches = a; }

    public int getMatches() { return this.matches; }

    public void searchNext (TextField searchField, TextArea textPanel, int request) {
        index = getIndex();
        if (index == -1) {
            App.errorBox("Search key Not in the text");
        } else {
            if (this.getMatches() == 0) {
                App.errorBox("No matches");
            } else {
                index = indexArray.get(request);
                textPanel.selectRange(index, index + searchField.getLength());
            }
        }
    }
    public void searchPrev (TextField searchField, TextArea textPanel, int request) {
        index = getIndex();
        if (index == -1) {
            App.errorBox("Search key Not in the text");
        } else {
            if (this.getMatches() == 0){
                App.errorBox("No matches");
            } else {
                index = indexArray.get(request);
                textPanel.selectRange(index, index + searchField.getLength());
            }
        }
    }
}
