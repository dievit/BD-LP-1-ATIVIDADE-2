package com.myproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.myproject.dao.ViagemDAO;
import com.myproject.model.Carro;
import com.myproject.model.Viagem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ViagemMainController implements ControladorFilho<CarroController> {
    private CarroController carroController;

    @Override
    public void setControladorPai(CarroController carroController) {
        this.carroController = carroController;
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCadastrarViagem;

    @FXML
    private TableColumn<Viagem, Double> colDistancia;

    @FXML
    private TableView<Viagem> tabelaViagem;

    @FXML
    private TableColumn<Viagem, String> colDataViagem;

    @FXML
    private TableColumn<Viagem, String> colDestino;

    @FXML
    private AnchorPane conteudoPane;

    @FXML
    private TableColumn<Viagem, String> colVeiculo;

    @FXML
    private TableColumn<Viagem, String> colMotorista;

    @FXML
    private Button btnEditarViagem;

    @FXML
    private TableColumn<Viagem, Integer> colId;

    @FXML
    private Button btnListarViagem;

    @FXML
    private TableColumn<Viagem, String> colPlaca;


    @FXML
    void initialize() {
        assert btnCadastrarViagem != null : "fx:id=\"btnCadastrarViagem\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert colDistancia != null : "fx:id=\"colDistancia\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert tabelaViagem != null : "fx:id=\"tabelaViagem\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert colDataViagem != null : "fx:id=\"colDataViagem\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert colDestino != null : "fx:id=\"colDestino\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert conteudoPane != null : "fx:id=\"conteudoPane\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert colVeiculo != null : "fx:id=\"colVeiculo\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert colMotorista != null : "fx:id=\"colMotorista\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert btnEditarViagem != null : "fx:id=\"btnEditarViagem\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert btnListarViagem != null : "fx:id=\"btnListarViagem\" was not injected: check your FXML file 'ViagemMain.fxml'.";
        assert colPlaca != null : "fx:id=\"colStatus\" was not injected: check your FXML file 'ViagemMain.fxml'.";

        // Mapeamento dos dados
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDestino.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestino().getNome()));
        colMotorista.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotorista().getNome()));
        colVeiculo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarro().getModelo()));
        colDistancia.setCellValueFactory(new PropertyValueFactory<>("distanciaKm"));
        colDataViagem.setCellValueFactory(new PropertyValueFactory<>("dataViagem"));
        colPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarro().getPlaca()));
        // Centralização
        centralizarColuna(colId);
        centralizarColuna(colDestino);
        centralizarColuna(colMotorista);
        centralizarColuna(colVeiculo);
        centralizarColuna(colPlaca);
        centralizarColuna(colDistancia);
        centralizarColuna(colDataViagem);

        // Larguras fixas (ajuste conforme sua preferência visual)
        definirLargura(colId, 85);
        definirLargura(colDestino, 150);
        definirLargura(colMotorista, 150);
        definirLargura(colVeiculo, 150);
        definirLargura(colDistancia, 150);
        definirLargura(colDataViagem, 150);
        definirLargura(colPlaca, 150);
    }

    // Método para centralizar o conteúdo da célula
    private void centralizarColuna(TableColumn<?, ?> coluna) {
        coluna.setCellFactory(column -> new javafx.scene.control.cell.TextFieldTableCell() {
            @Override
            public void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });
    }

    // Método para travar largura
    private void definirLargura(TableColumn<?, ?> coluna, double largura) {
        coluna.setPrefWidth(largura);
        coluna.setMinWidth(largura);
        coluna.setMaxWidth(largura);

    }

    @FXML
    void voltar() {
        if (carroController != null) {
            carroController.carregarTela("/view/MainMotoristas.fxml");
        } else {
            System.out.println("CarroController não está definido.");
        }
    }

    @FXML
    void listarViagens() {
        System.out.println("Método listarViagens() chamado.");
        List<Viagem> viagens = ViagemDAO.listarViagens();
        if (viagens.isEmpty()) {
            System.out.println("Nenhuma viagem no banco.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Nenhuma viagem para exibir.");
            alert.showAndWait();
            return;
        }

        viagens.forEach(v -> System.out.println("ID: " + v.getId() + " - Destino: " + v.getDestino().getNome()));

        ObservableList<Viagem> observableViagens = FXCollections.observableArrayList(viagens);
        tabelaViagem.setItems(observableViagens);
    }


    @FXML
    private void abrirTelaCadastroViagem(ActionEvent event) {
        carroController.carregarTela("/view/LancarViagem.fxml");
    }
}
