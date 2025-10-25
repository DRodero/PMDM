package com.example.botonesylistas;

public class ElementoLista {

    private String texto1, texto2;

    public ElementoLista(String texto1, String texto2) {
        this.texto1 = texto1;
        this.texto2 = texto2;
    }

    public String getTexto1() {
        return texto1;
    }

    public String getTexto2() {
        return texto2;
    }

    public void setTexto1(String texto1) {
        this.texto1 = texto1;
    }

    public void setTexto2(String texto2) {
        this.texto2 = texto2;
    }
}
