package br.com.examechunin.bean;

import br.com.examechunin.model.Usuario;
import br.com.examechunin.model.enums.LogInOrOut;
import br.com.examechunin.model.interfaces.EstadoLogInOrOut;
import br.com.examechunin.model.interfaces.LoginFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    private Usuario usuario;
    private String nome;
    private String password;
    private EstadoLogInOrOut estadoLogInOrOut = LoginFactory.criarEstado(LogInOrOut.LOGIN);


    public String logar() {

        if (nome.equals("root") && password.equals("password")) {
            estadoLogInOrOut = LoginFactory.criarEstado(LogInOrOut.LOGOUT);
            usuario = new Usuario();
            return "/restricted/homeadm.xhtml?faces-redirect=true";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e/ou senha incorretos", ""));
            return null;
        }
    }

    public String logout() {
        if (usuario != null) {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            usuario = null;
        }
        return "/login?faces-redirect=true";
    }

    public boolean isLogged() {
        return usuario != null;
    }

    public String estadoLogin() {
        return estadoLogInOrOut.getEstadoLog();
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
