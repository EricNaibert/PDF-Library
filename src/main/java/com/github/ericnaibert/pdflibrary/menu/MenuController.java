package com.github.ericnaibert.pdflibrary.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    protected Button button;

    @FXML
    protected Button refreshButton;

    @FXML
    protected void onClick() {

        SwingElements.showTextFieldPopUp();

    }

    @FXML
    protected void refresh() {

        Refresh.refreshAndCreateUI();
    }

}