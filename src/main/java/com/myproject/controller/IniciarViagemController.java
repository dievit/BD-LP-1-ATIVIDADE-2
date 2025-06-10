package com.myproject.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.myproject.dao.CarroDAO;
import com.myproject.dao.MotoristaDAO;
import com.myproject.model.Carro;
import com.myproject.model.Destino;
import com.myproject.model.Motorista;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class IniciarViagemController implements ControladorFilho<CarroController> {
    private CarroController carroController;

    @Override
    public void setControladorPai(CarroController carroController) {
        this.carroController = carroController;
    }

    @FXML
    void voltar() {
        if (carroController != null) {
            carroController.carregarTela("/view/ViagemMain.fxml");
        } else {
            System.out.println("CarroController não está definido.");
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldDistancia;

    @FXML
    private ComboBox<?> cboxDestino;

    @FXML
    private DatePicker datePickerData;

    @FXML
    private TextField fieldCusto;

    @FXML
    private Button btnIniciarViagem;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<?> cboxPartida;

    @FXML
    private ComboBox<Carro> cboxVeiculo;

    @FXML
    private ComboBox<Motorista> cboxMotorista;

    @FXML
    private Button btnLimpar;


    @FXML
    private ComboBox<Motorista> comboMotorista;



    @FXML
    void initialize() {
        assert fieldDistancia != null : "fx:id=\"fieldDistancia\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert cboxDestino != null : "fx:id=\"cboxDestino\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert datePickerData != null : "fx:id=\"datePickerData\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert fieldCusto != null : "fx:id=\"fieldCusto\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert btnIniciarViagem != null : "fx:id=\"btnIniciarViagem\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert btnVoltar != null : "fx:id=\"btnVoltar\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert cboxPartida != null : "fx:id=\"cboxPartida\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert cboxVeiculo != null : "fx:id=\"cboxVeiculo\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert cboxMotorista != null : "fx:id=\"cboxMotorista\" was not injected: check your FXML file 'LancarViagem.fxml'.";
        assert btnLimpar != null : "fx:id=\"btnLimpar\" was not injected: check your FXML file 'LancarViagem.fxml'.";

        carregarMotoristasDisponiveis();
        carregarCarrosDisponiveis();
        atualizarCustoEstimado();

    }

    @FXML
    void limparCampos(ActionEvent event) {
        datePickerData.setValue(null);
        fieldDistancia.clear();
        fieldCusto.clear();
        cboxMotorista.getSelectionModel().clearSelection();
        cboxVeiculo.getSelectionModel().clearSelection();
        cboxDestino.getSelectionModel().clearSelection();
        cboxPartida.getSelectionModel().clearSelection();
    }

    private void carregarMotoristasDisponiveis() {
        List<Motorista> motoristasDisponiveis = MotoristaDAO.listarMotoristasAptos();
        cboxMotorista.setItems(FXCollections.observableArrayList(motoristasDisponiveis));

        cboxMotorista.setConverter(new StringConverter<Motorista>() {
            @Override
            public String toString(Motorista motorista) {
                return motorista.getNome();
            }

            @Override
            public Motorista fromString(String string) {
                return null;
            }
        });
    }

    private void carregarCarrosDisponiveis() {
        List<Carro> carrosDisponiveis = CarroDAO.listarCarrosDisponiveis();
        cboxVeiculo.setItems(FXCollections.observableArrayList(carrosDisponiveis));

        cboxVeiculo.setConverter(new StringConverter<Carro>() {
            @Override
            public String toString(Carro carro) {
                return carro.getModelo();
            }

            @Override
            public Carro fromString(String string) {
                return null;
            }
        });
    }

    private void atualizarCustoEstimado() {
        Destino destino = (Destino) cboxDestino.getValue();
        Carro veiculo = cboxVeiculo.getValue();

        if (destino != null && veiculo != null) {
            double distancia = destino.getDistanciakm();
            double consumo = veiculo.getConsumo();
            double precoCombustivel = 5.89;

            if (consumo > 0) {
                double litrosNecessarios = distancia / consumo;
                double custo = litrosNecessarios * precoCombustivel;

                fieldCusto.setText(String.format("%.2f", custo));
            } else {
                fieldCusto.setText("0.00");
            }
        }
    }


}
