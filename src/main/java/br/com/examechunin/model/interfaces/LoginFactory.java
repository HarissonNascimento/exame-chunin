package br.com.examechunin.model.interfaces;

import br.com.examechunin.model.enums.LogInOrOut;


public class LoginFactory {
    public static EstadoLogInOrOut criarEstado(LogInOrOut logInOrOut) {
        if (logInOrOut.equals(LogInOrOut.LOGIN)) {
            return new LogIn();
        } else if (logInOrOut.equals(LogInOrOut.LOGOUT)) {
            return new LogOut();
        }
        throw new IllegalArgumentException("Estado de login não existente");
    }
}
