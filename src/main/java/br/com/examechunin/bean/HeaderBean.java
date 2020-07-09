package br.com.examechunin.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class HeaderBean implements Serializable {
    private String busca;

    public String buscar() {
        return "/resultbusca.xhtml?faces-redirect=true&busca=" + busca;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
}
