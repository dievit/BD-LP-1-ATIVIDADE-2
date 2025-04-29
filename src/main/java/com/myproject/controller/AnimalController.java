package com.myproject.controller;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.myproject.model.Animal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AnimalController {
    @FXML
    private ResourceBundle resources;

    @FXML
    void voltarTelaInicial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaInicial.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void fecharAplicacao(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private URL location;

    @FXML
    private ComboBox<Animal> comboBox;

    @FXML
    private TextArea textArea;

    @FXML
    private TextArea textArea2;

    @FXML
    private ImageView imagemField;

    @FXML
    private ImageView imagemField1;

    @FXML
    void initialize() {

        Animal leao = new Animal("Leão", "Africa", "Felino","Rooooaar!", "/images/leao.jpg", "/images/africa.jpg");
        Animal macaco = new Animal("Macaco", "Brasil", "Primata","Uh uh ah ah!", "/images/macaco.jpg", "/images/brasil.jpg");
        Animal hiena = new Animal("Hiena", "Africa", "Hyaenidae","Hehehe!", "/images/hiena.jpg", "/images/africa.jpg");
        Animal papagaio = new Animal("Papagaio", "Brasil", "Ave", "Olá!", "/images/papagaio.jpg", "/images/brasil.jpg");
        Animal cachorro = new Animal("Cachorro", "Qualquer lugar", "Canino", "Au au!", "/images/cachorro.jpg", "/images/mundo.jpg");
        Animal gato = new Animal("Gato", "Qualquer lugar", "Felino", "Miau!", "/images/gato.jpg", "/images/mundo.jpg");

        comboBox.getItems().addAll(leao, macaco, hiena, papagaio, cachorro, gato);

        comboBox.setOnAction(event -> {
            Animal selecionado = comboBox.getValue();

            if (selecionado != null) {
                String info = "Animal: " + selecionado.getNome() +
                        "\nSom: " + selecionado.getSom();
                textArea.setText(info);

                String info2 = "Animal: " + selecionado.getNome() +
                        "\nTipo: " + selecionado.getTipo() +
                        "\nOrigem: " + selecionado.getOrigem();
                textArea2.setText(info2);

                System.out.println("Imagem sendo buscada: " + selecionado.getImagem());

                Image imagem = new Image(getClass().getResourceAsStream(selecionado.getImagem()));
                imagemField.setImage(imagem);

                Image imagem2 = new Image(getClass().getResourceAsStream(selecionado.getImageOrigem()));
                imagemField1.setImage(imagem2);
            }

        });
    }
}