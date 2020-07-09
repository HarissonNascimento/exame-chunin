package br.com.examechunin.model.interfaces;

public interface TipoVeiculoInterface {
    String getTipoVeiculo();
}

class CarrosEUltilitarios implements TipoVeiculoInterface {
    @Override
    public String getTipoVeiculo() {
        return "Carros e Ultilitários";
    }
}

class MotosEQuadriciclos implements TipoVeiculoInterface {
    @Override
    public String getTipoVeiculo() {
        return "Motos e Quadriciclos";
    }
}

class Caminhoes implements TipoVeiculoInterface {

    @Override
    public String getTipoVeiculo() {
        return "Caminhões";
    }
}

class CarretasECarrocerias implements TipoVeiculoInterface {

    @Override
    public String getTipoVeiculo() {
        return "Carretas e Carrocerias";
    }
}

class OnibusEVans implements TipoVeiculoInterface {

    @Override
    public String getTipoVeiculo() {
        return "Ônibus e Vans";
    }
}

class Nauticos implements TipoVeiculoInterface {

    @Override
    public String getTipoVeiculo() {
        return "Náuticos";
    }
}

class Outros implements TipoVeiculoInterface {

    @Override
    public String getTipoVeiculo() {
        return "Outros";
    }
}
