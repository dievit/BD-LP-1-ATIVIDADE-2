package com.myproject.controller;

public interface ControladorFilho<T> {
    void setControladorPai(Object controller);

    void setControladorPai(FrotaController controller);

    void setControladorPai(CarroController controller);

    void setCarroController(CarroController carroController);
}
