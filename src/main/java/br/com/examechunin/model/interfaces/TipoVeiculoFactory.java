package br.com.examechunin.model.interfaces;

import br.com.examechunin.model.enums.TipoVeiculo;

public class TipoVeiculoFactory {
    public static TipoVeiculoInterface criarTipoVeiculo(TipoVeiculo tipoVeiculo) {
        if (tipoVeiculo.equals(TipoVeiculo.CARROS_E_ULTILITARIOS)) {
            return new CarrosEUltilitarios();
        } else if (tipoVeiculo.equals(TipoVeiculo.MOTOS_E_QUADRICICLO)) {
            return new MotosEQuadriciclos();
        } else if (tipoVeiculo.equals(TipoVeiculo.CAMINHOES)) {
            return new Caminhoes();
        } else if (tipoVeiculo.equals(TipoVeiculo.CARRETAS_E_CARROCERIAS)) {
            return new CarretasECarrocerias();
        } else if (tipoVeiculo.equals(TipoVeiculo.ONIBUS_E_VANS)) {
            return new OnibusEVans();
        } else if (tipoVeiculo.equals(TipoVeiculo.NAUTICOS)) {
            return new Nauticos();
        } else if (tipoVeiculo.equals(TipoVeiculo.OUTROS)) {
            return new Outros();
        }
        throw new IllegalArgumentException("Tipo de veículo não encontrado");
    }
}
