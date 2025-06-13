package com.myproject.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.myproject.dao.CarroDAO;
import com.myproject.dao.DestinoDAO;
import com.myproject.dao.MotoristaDAO;
import com.myproject.dao.ViagemDAO;
import com.myproject.model.Carro;
import com.myproject.model.Destino;
import com.myproject.model.Motorista;
import com.myproject.model.Viagem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private ComboBox<Destino> cboxDestino;

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
        carregarDestinos();

        cboxVeiculo.valueProperty().addListener((obs, oldVal, newVal) -> atualizarCustoEstimado());
        cboxDestino.valueProperty().addListener((obs, oldVal, newVal) -> atualizarCustoEstimado());

    }

    @FXML
    private void limparCampos() {
        cboxMotorista.setValue(null);
        cboxVeiculo.setValue(null);
        cboxDestino.setValue(null);
        datePickerData.setValue(null);
        fieldDistancia.clear();
        fieldCusto.clear();
    }

    @FXML
    private void onCadastrarViagem() {
        Motorista motorista = cboxMotorista.getValue();
        Carro veiculo = cboxVeiculo.getValue();
        Destino destino = cboxDestino.getValue();
        LocalDate data = datePickerData.getValue();

        String distanciaStr = fieldDistancia.getText().replace(" km", "").trim();
        String custoStr = fieldCusto.getText().replace("R$", "").replace(",", ".").trim();

        if (motorista == null || veiculo == null || destino == null || data == null || distanciaStr.isEmpty() || custoStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos corretamente antes de cadastrar a viagem.");
            alert.showAndWait();
            return;
        }

        try {
            double distancia = Double.parseDouble(distanciaStr);
            double custo = Double.parseDouble(custoStr);

            Viagem viagem = new Viagem(motorista, veiculo, destino, distancia, custo, data);
            viagem.setEmRota(true);
            ViagemDAO viagemDAO = new ViagemDAO();

            boolean sucesso = viagemDAO.cadastrarViagem(viagem); // <- valida se cadastrou

            if (sucesso) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText(null);
                alert.setContentText("Viagem cadastrada com sucesso!");
                alert.showAndWait();
                limparCampos();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de formato");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao converter valores numéricos. Verifique os campos de distância e custo.");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao cadastrar a viagem: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
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
                return carro.getModelo() + " - " + carro.getPlaca() + " (" + carro.getTipo() + ")";
            }

            @Override
            public Carro fromString(String string) {
                return null;
            }
        });
    }

    private void carregarDestinos() {
        List<Destino> destinos = DestinoDAO.listarDestinos();
        cboxDestino.setItems(FXCollections.observableArrayList(destinos));

        cboxDestino.setConverter(new StringConverter<Destino>() {
            @Override
            public String toString(Destino destino) {
                return destino.getNome() + " - " + destino.getCidade() + ", " + destino.getEstado();
            }

            @Override
            public Destino fromString(String string) {
                return null;
            }
        });

        cboxDestino.valueProperty().addListener((observable, oldValue, newValue) -> atualizarCustoEstimado());
    }

    private void atualizarCustoEstimado() {
        Destino destino = cboxDestino.getValue();
        Carro veiculo = cboxVeiculo.getValue();

        if (destino == null || veiculo == null) {
            return;
        }

        double distancia = destino.getDistanciakm();
        double consumo = veiculo.getConsumo();
        double precoCombustivel = 5.89;

        if (consumo > 0) {
            double kmRodado = distancia * 2;
            double custoMotorista = 2.5 * kmRodado;
            double litrosNecessarios = kmRodado / consumo;
            double custo = (litrosNecessarios * precoCombustivel) + custoMotorista;

            fieldDistancia.setText(distancia + " km");
            fieldCusto.setText(String.format("R$ %.2f", custo));
        } else {
            fieldCusto.setText("0.00");
        }
    }

    @FXML
    private void listarViagens() {}





}
