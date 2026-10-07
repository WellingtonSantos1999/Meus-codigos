package dao;

import database.Conexao;
import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

public class FuncionarioDAO {

    
    // CADASTRAR
    

    public void cadastrar(Funcionario funcionario) {

        String sql = """
                INSERT INTO funcionario
                (nome, cpf, telefone, cargo)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getTelefone());
            ps.setString(4, funcionario.getCargo());

            ps.executeUpdate();

            System.out.println(
                    "Funcionário cadastrado com sucesso!"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao cadastrar funcionário!"
            );

            e.printStackTrace();
        }
    }


    
    // LISTAR
    

    public List<Funcionario> listar() {

        List<Funcionario> funcionarios =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM funcionario
                ORDER BY id_funcionario
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Funcionario funcionario =
                        new Funcionario();

                funcionario.setIdFuncionario(
                        rs.getLong("id_funcionario")
                );

                funcionario.setNome(
                        rs.getString("nome")
                );

                funcionario.setCpf(
                        rs.getString("cpf")
                );

                funcionario.setTelefone(
                        rs.getString("telefone")
                );

                funcionario.setCargo(
                        rs.getString("cargo")
                );

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar funcionários!"
            );

            e.printStackTrace();
        }

        return funcionarios;
    }


    
    // BUSCAR POR ID
    

    public Funcionario buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM funcionario
                WHERE id_funcionario = ?
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Funcionario funcionario =
                            new Funcionario();

                    funcionario.setIdFuncionario(
                            rs.getLong("id_funcionario")
                    );

                    funcionario.setNome(
                            rs.getString("nome")
                    );

                    funcionario.setCpf(
                            rs.getString("cpf")
                    );

                    funcionario.setTelefone(
                            rs.getString("telefone")
                    );

                    funcionario.setCargo(
                            rs.getString("cargo")
                    );

                    return funcionario;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar funcionário!"
            );

            e.printStackTrace();
        }

        return null;
    }


    
    // ATUALIZAR
    

    public void atualizar(Funcionario funcionario) {

        String sql = """
                UPDATE funcionario
                SET nome = ?,
                    cpf = ?,
                    telefone = ?,
                    cargo = ?
                WHERE id_funcionario = ?
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getTelefone());
            ps.setString(4, funcionario.getCargo());

            ps.setLong(
                    5,
                    funcionario.getIdFuncionario()
            );

            int linhasAlteradas =
                    ps.executeUpdate();

            if (linhasAlteradas > 0) {

                System.out.println(
                        "Funcionário atualizado com sucesso!"
                );

            } else {

                System.out.println(
                        "Funcionário não encontrado!"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar funcionário!"
            );

            e.printStackTrace();
        }
    }


    
    // EXCLUIR
    

    public boolean excluir(Long id) {

        String sql =
                "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setLong(1, id);

            int linhasAfetadas =
                    stmt.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLIntegrityConstraintViolationException e) {

            // O funcionário possui algum dado vinculado
            return false;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}