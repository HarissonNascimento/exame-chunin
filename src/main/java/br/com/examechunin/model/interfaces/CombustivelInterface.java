package br.com.examechunin.model.interfaces;

public interface CombustivelInterface {
    String getTipoCombustivel();
}

class Gasolina implements CombustivelInterface {

    @Override
    public String getTipoCombustivel() {
        return "Gasolina";
    }
}

class Alcool implements CombustivelInterface {

    @Override
    public String getTipoCombustivel() {
        return "Alcool";
    }
}

class Diesel implements CombustivelInterface {

    @Override
    public String getTipoCombustivel() {
        return "Diesel";
    }
}

class Eletrico implements CombustivelInterface {

    @Override
    public String getTipoCombustivel() {
        return "Elétrico";
    }
}

class Flex implements CombustivelInterface {

    @Override
    public String getTipoCombustivel() {
        return "Flex";
    }
}

class Hibrido implements CombustivelInterface{

    @Override
    public String getTipoCombustivel() {
        return "Híbrido";
    }
}

class OutrosCombustivel implements CombustivelInterface{

    @Override
    public String getTipoCombustivel() {
        return "Outros";
    }
}
