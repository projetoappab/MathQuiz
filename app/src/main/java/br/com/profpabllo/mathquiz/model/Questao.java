package br.com.profpabllo.mathquiz.model;

public class Questao {
    private String texto;
    private double respostaCorreta;
    private double respostaIncorreta;

    public Questao(String texto, double respostaCorreta, double respostaIncorreta) {
        this.texto = texto;
        this.respostaCorreta = respostaCorreta;
        this.respostaIncorreta = respostaIncorreta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public double getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(double respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public double getRespostaIncorreta() {
        return respostaIncorreta;
    }

    public void setRespostaIncorreta(double respostaIncorreta) {
        this.respostaIncorreta = respostaIncorreta;
    }
}
