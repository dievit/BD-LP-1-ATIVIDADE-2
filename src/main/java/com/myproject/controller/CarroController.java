package com.myproject.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CarroController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private SplitPane splitPane;

    @FXML
    private AnchorPane menuPane;

    @FXML
    void initialize() {
        Platform.runLater(() -> {
            SplitPane.setResizableWithParent(menuPane, false);
        });
    }
}
