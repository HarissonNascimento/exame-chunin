package br.com.examechunin.model.bd.db;

import br.com.examechunin.model.Comprador;
import br.com.examechunin.model.Veiculo;
import br.com.examechunin.model.bd.conn.ConexaoFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompradorDAO {

    public CompradorDAO(Comprador comprador) {
    }

    public static void delete(Comprador comprador) {
        if (comprador == null || comprador.getId() == null) {
            throw new RuntimeException("Comprador não encontrado");
        }
        String sql = "DELETE FROM `examechunindb`.`comprador` WHERE (`id` = ?)";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, comprador.getId());
            ps.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador deletado com sucesso", ""));
            ConexaoFactory.close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteByIdVeiculo(Integer id) {
        String sql = "DELETE FROM `examechunindb`.`comprador` WHERE (`carro_id` = ?);";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador deletado com sucesso", ""));
            ConexaoFactory.close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void update(Comprador comprador) {
        if (comprador == null || comprador.getId() == null) {
            throw new RuntimeException("Comprador não encontrado");
        }
        String sql = "UPDATE `examechunindb`.`comprador` SET `nome` = ?, `telefone` = ?, `carro_id` = ?, `contatado` = ? WHERE (`id` = ?);";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, comprador.getNome());
            ps.setString(2, comprador.getTelefone());
            ps.setInt(3, comprador.getVeiculo().getId());
            ps.setBoolean(4, comprador.isContatado());
            ps.setInt(5, comprador.getId());
            ps.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador atualizado com sucesso", ""));
            ConexaoFactory.close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void save(Comprador comprador) {
        String sql = "INSERT INTO `examechunindb`.`comprador` (`nome`,`telefone`,`carro_id`) VALUES (?,?,?)";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, comprador.getNome());
            ps.setString(2, comprador.getTelefone());
            ps.setInt(3, comprador.getVeiculo().getId());
            ps.executeUpdate();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comprador salvo com sucesso", ""));
            ConexaoFactory.close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Comprador> selectAll() {
        String sql = "SELECT id,nome,telefone,carro_id, contatado FROM examechunindb.comprador";
        List<Comprador> compradorList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                Veiculo c = VeiculoDAO.searchById(rs.getInt("carro_id"));
                compradorList.add(new Comprador(rs.getString("nome"), rs.getString("telefone"), rs.getInt("id"), rs.getBoolean("contatado"), c));
            }
            ConexaoFactory.close(conn, ps, rs);
            return compradorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Comprador> searchByName(String nome) {
        String sql = "SELECT id,nome,telefone,carro_id, contatado FROM examechunindb.comprador WHERE nome LIKE ?";
        List<Comprador> compradorList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Veiculo c = VeiculoDAO.searchById(rs.getInt("carro_id"));
                compradorList.add(new Comprador(rs.getString("nome"), rs.getString("telefone"), rs.getInt("id"), rs.getBoolean("contatado"), c));
            }
            ConexaoFactory.close(conn, ps, rs);
            return compradorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Comprador searchById(Integer id) {
        String sql = "SELECT `id`, `nome`, `telefone`, `carro_id` FROM examechunindb.comprador WHERE id=?;";
        Comprador comprador = null;
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Veiculo veiculo = VeiculoDAO.searchById(rs.getInt("carro_id"));
                comprador = new Comprador(rs.getString("nome"), rs.getString("telefone"), rs.getInt("id"), veiculo);
            }
            ConexaoFactory.close(conn, ps, rs);
            return comprador;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
