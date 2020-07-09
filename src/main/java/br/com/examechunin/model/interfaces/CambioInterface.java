package br.com.examechunin.model.interfaces;

public interface CambioInterface {
    String getTipoCambio();
}

class Automatico implements CambioInterface {
    @Override
    public String getTipoCambio() {
        return "Automático";
    }
}

class Manual implements CambioInterface {
    @Override
    public String getTipoCambio() {
        return "Manual";
    }
}
