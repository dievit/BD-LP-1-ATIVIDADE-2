package com.myproject.controller;

public interface ControladorFilho<T> {
    void setControladorPai(T controller);

}