package br.com.examechunin.model.interfaces;

public interface EstadoLogInOrOut {
    String getEstadoLog();
}

class LogIn implements EstadoLogInOrOut {
    @Override
    public String getEstadoLog() {
        return "Login";
    }
}

class LogOut implements EstadoLogInOrOut {
    @Override
    public String getEstadoLog() {
        return "Logout";
    }
}
