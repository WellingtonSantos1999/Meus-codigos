package dao;

import database.Conexao;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // ==========================================
    // CADASTRAR CLIENTE
    // ==========================================

    public void cadastrar(Cliente cliente) {

        String sql = """
                INSERT INTO cliente
                (nome, cpf, telefone, email, endereco)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getEndereco());

            ps.executeUpdate();

            System.out.println("Cliente cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente!");
            e.printStackTrace();
        }
    }


    // ==========================================
    // LISTAR CLIENTES
    // ==========================================

    public List<Cliente> listar() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = """
                SELECT *
                FROM cliente
                ORDER BY id_cliente
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes!");
            e.printStackTrace();
        }

        return clientes;
    }

    // ==========================================
    // BUSCAR CLIENTE POR ID
    // ==========================================

    public Cliente buscarPorId(Long id) {

        String sql = """
            SELECT *
            FROM cliente
            WHERE id_cliente = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));

                return cliente;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente!");
            e.printStackTrace();
        }

        return null;
    }


    // ==========================================
    // ATUALIZAR CLIENTE
    // ==========================================

    public void atualizar(Cliente cliente) {

        String sql = """
            UPDATE cliente
            SET nome = ?,
                cpf = ?,
                telefone = ?,
                email = ?,
                endereco = ?
            WHERE id_cliente = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getEndereco());

            ps.setLong(6, cliente.getIdCliente());

            int linhasAlteradas = ps.executeUpdate();

            if (linhasAlteradas > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente!");
            e.printStackTrace();
        }
    }


    // ==========================================
    // EXCLUIR CLIENTE
    // ==========================================

    public void excluir(Long id) {

        String sql = """
            DELETE FROM cliente
            WHERE id_cliente = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setLong(1, id);

            int linhasExcluidas = ps.executeUpdate();

            if (linhasExcluidas > 0) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente!");
            e.printStackTrace();
        }
    }
}