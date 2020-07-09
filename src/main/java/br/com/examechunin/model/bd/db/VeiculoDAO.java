package br.com.examechunin.model.bd.db;

import br.com.examechunin.model.Comprador;
import br.com.examechunin.model.Veiculo;
import br.com.examechunin.model.bd.conn.ConexaoFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class VeiculoDAO {

    public VeiculoDAO(Veiculo veiculo) {
    }

    public static void delete(Veiculo veiculo) {
        if (veiculo == null || veiculo.getId() == null) {
            throw new RuntimeException("Impossivel deletar o veiculo selecionado");
        }
        CompradorDAO.deleteByIdVeiculo(veiculo.getId());
        String sql = "DELETE FROM `examechunindb`.`carro` WHERE (`id` = ?)";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, veiculo.getId());
            ps.executeUpdate();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Veiculo deletado com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ConexaoFactory.close(conn, ps);
            File file = new File(veiculo.recuperarDiretorio());
            boolean dir = file.exists();
            if (dir) {
                List<File> fileList = asList(file.listFiles());
                for (File f : fileList) {
                    f.delete();
                }
                file.delete();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void update(Veiculo veiculo) {
        if (veiculo == null || veiculo.getId() == null) {
            throw new RuntimeException("Impossivel atualizar dados do veiculo selecionado");
        }
        String sql;
        if (veiculo.getComprador() == null) {
            sql = "UPDATE `examechunindb`.`carro` SET `tipo_veiculo` = ?,`combustivel` = ?,`cambio` = ?,`marca` = ?," +
                    "`modelo` = ?,`ano` = ?,`descricao` = ?,`preco` = ?, `status_boolean` = ? WHERE(`id` = ?);";
        } else {
            sql = "UPDATE `examechunindb`.`carro` SET `tipo_veiculo` = ?,`combustivel` = ?,`cambio` = ?,`marca` = ?," +
                    "`modelo` = ?,`ano` = ?,`descricao` = ?,`preco` = ?, `status_boolean` = ?, `comprador_id` = ? WHERE(`id` = ?);";
        }
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, veiculo.getTipoVeiculo());
            ps.setString(2, veiculo.getCombustivel());
            ps.setString(3, veiculo.getTransmissao());
            ps.setString(4, veiculo.getMarca());
            ps.setString(5, veiculo.getModelo());
            ps.setInt(6, veiculo.getAno());
            ps.setString(7, veiculo.getDescricao());
            ps.setDouble(8, veiculo.getPreco());
            ps.setBoolean(9, veiculo.isStatusVendido());
            if (veiculo.getComprador() == null) {
                ps.setInt(10, veiculo.getId());
            } else {
                ps.setInt(10, veiculo.getComprador().getId());
                ps.setInt(11, veiculo.getId());
            }
            ps.executeUpdate();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Veiculo editado com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ConexaoFactory.close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void save(Veiculo veiculo) {
        String sql = "INSERT INTO `examechunindb`.`carro`(`tipo_veiculo`,`combustivel`, `cambio`, `marca`, `modelo`, `ano`, `descricao`," +
                " `preco`,`pasta_imagens`) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, veiculo.getTipoVeiculo());
            ps.setString(2, veiculo.getCombustivel());
            ps.setString(3, veiculo.getTransmissao());
            ps.setString(4, veiculo.getMarca());
            ps.setString(5, veiculo.getModelo());
            ps.setInt(6, veiculo.getAno());
            ps.setString(7, veiculo.getDescricao());
            ps.setDouble(8, veiculo.getPreco());
            ps.setString(9, veiculo.getPastaImagens());
            ps.executeUpdate();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Veiculo adicionado com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ConexaoFactory.close(conn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Veiculo> selectAll() {
        String sql = "SELECT id,tipo_veiculo,combustivel,cambio,marca,modelo,ano,descricao,preco,pasta_imagens, status_boolean, comprador_id FROM examechunindb.carro; ";
        List<Veiculo> veiculoList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                if (rs.getInt("comprador_id") == 0) {
                    veiculoList.add(new Veiculo(rs.getInt("id"), rs.getString("tipo_veiculo"), rs.getString("combustivel"), rs.getString("cambio"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"), rs.getString("descricao"), rs.getDouble("preco"), rs.getString("pasta_imagens"), rs.getBoolean("status_boolean")));
                } else {
                    Comprador comprador = CompradorDAO.searchById(rs.getInt("comprador_id"));
                    veiculoList.add(new Veiculo(rs.getInt("id"), rs.getString("tipo_veiculo"), rs.getString("combustivel"), rs.getString("cambio"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"), rs.getString("descricao"), rs.getDouble("preco"), rs.getString("pasta_imagens"), rs.getBoolean("status_boolean"), comprador));
                }
            }
            ConexaoFactory.close(conn, ps, rs);
            return veiculoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Veiculo> searchByModel(String nome) {
        String sql = "SELECT id,tipo_veiculo,combustivel,cambio,marca,modelo,ano,descricao,preco,pasta_imagens, status_boolean FROM examechunindb.carro WHERE modelo LIKE ?; ";
        List<Veiculo> veiculoList = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                veiculoList.add(new Veiculo(rs.getInt("id"), rs.getString("tipo_veiculo"), rs.getString("combustivel"), rs.getString("cambio"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"), rs.getString("descricao"), rs.getDouble("preco"), rs.getString("pasta_imagens"), rs.getBoolean("status_boolean")));
            }
            ConexaoFactory.close(conn, ps, rs);
            return veiculoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Veiculo searchById(Integer id) {
        String sql = "SELECT id,tipo_veiculo,combustivel,cambio,marca,modelo,ano,descricao,preco,pasta_imagens, status_boolean FROM examechunindb.carro WHERE id=?; ";
        Veiculo veiculo = null;
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                veiculo = new Veiculo(rs.getInt("id"), rs.getString("tipo_veiculo"), rs.getString("combustivel"), rs.getString("cambio"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"), rs.getString("descricao"), rs.getDouble("preco"), rs.getString("pasta_imagens"), rs.getBoolean("status_boolean"));
            }
            ConexaoFactory.close(conn, ps, rs);
            return veiculo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
