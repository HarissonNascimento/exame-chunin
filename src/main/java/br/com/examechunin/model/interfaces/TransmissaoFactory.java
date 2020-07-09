package br.com.examechunin.model.interfaces;

import br.com.examechunin.model.enums.Transmissao;

public class TransmissaoFactory {
    public static CambioInterface criarCambio(Transmissao transmissao) {
        if (transmissao.equals(Transmissao.AUTOMATICO)) {
            return new Automatico();
        } else if (transmissao.equals(Transmissao.MANUAL)) {
            return new Manual();
        }
        throw new RuntimeException("Tipo de câmbio não encontrado");
    }
}
