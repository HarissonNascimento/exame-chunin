package br.com.examechunin.model.interfaces;

import br.com.examechunin.model.enums.Combustivel;

public class CombustivelFactory {
    public static CombustivelInterface criarCombustivel(Combustivel combustivel) {
        if (combustivel.equals(Combustivel.GASOLINA)) {
            return new Gasolina();
        } else if (combustivel.equals(Combustivel.ALCOOL)) {
            return new Alcool();
        } else if (combustivel.equals(Combustivel.DIESEL)) {
            return new Diesel();
        } else if (combustivel.equals(Combustivel.ELETRICO)) {
            return new Eletrico();
        } else if (combustivel.equals(Combustivel.FLEX)) {
            return new Flex();
        }
        throw new RuntimeException("Tipo de combustivel não encontrado");
    }
}
