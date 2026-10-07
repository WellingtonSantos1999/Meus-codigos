package dao;

import database.Conexao;
import model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

public class ServicoDAO {


    
    // CADASTRAR
    

    public void cadastrar(Servico servico) {

        String sql = """
                INSERT INTO servico
                (nome, descricao, valor, duracao)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(
                    1,
                    servico.getNome()
            );

            ps.setString(
                    2,
                    servico.getDescricao()
            );

            ps.setDouble(
                    3,
                    servico.getValor()
            );

            ps.setInt(
                    4,
                    servico.getDuracao()
            );


            ps.executeUpdate();


            System.out.println(
                    "Serviço cadastrado com sucesso!"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao cadastrar serviço!"
            );

            e.printStackTrace();
        }
    }


    
    // LISTAR
    

    public List<Servico> listar() {

        List<Servico> servicos =
                new ArrayList<>();


        String sql = """
                SELECT *
                FROM servico
                ORDER BY id_servico
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {

                Servico servico =
                        new Servico();


                servico.setIdServico(
                        rs.getLong(
                                "id_servico"
                        )
                );


                servico.setNome(
                        rs.getString(
                                "nome"
                        )
                );


                servico.setDescricao(
                        rs.getString(
                                "descricao"
                        )
                );


                servico.setValor(
                        rs.getDouble(
                                "valor"
                        )
                );


                servico.setDuracao(
                        rs.getInt(
                                "duracao"
                        )
                );


                servicos.add(
                        servico
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar serviços!"
            );

            e.printStackTrace();
        }


        return servicos;
    }


    
    // BUSCAR POR ID
    

    public Servico buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM servico
                WHERE id_servico = ?
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {


            ps.setLong(
                    1,
                    id
            );


            try (ResultSet rs =
                         ps.executeQuery()) {


                if (rs.next()) {

                    Servico servico =
                            new Servico();


                    servico.setIdServico(
                            rs.getLong(
                                    "id_servico"
                            )
                    );


                    servico.setNome(
                            rs.getString(
                                    "nome"
                            )
                    );


                    servico.setDescricao(
                            rs.getString(
                                    "descricao"
                            )
                    );


                    servico.setValor(
                            rs.getDouble(
                                    "valor"
                            )
                    );


                    servico.setDuracao(
                            rs.getInt(
                                    "duracao"
                            )
                    );


                    return servico;
                }
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar serviço!"
            );

            e.printStackTrace();
        }


        return null;
    }


    
    // ATUALIZAR
    

    public void atualizar(Servico servico) {

        String sql = """
                UPDATE servico
                SET nome = ?,
                    descricao = ?,
                    valor = ?,
                    duracao = ?
                WHERE id_servico = ?
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {


            ps.setString(
                    1,
                    servico.getNome()
            );


            ps.setString(
                    2,
                    servico.getDescricao()
            );


            ps.setDouble(
                    3,
                    servico.getValor()
            );


            ps.setInt(
                    4,
                    servico.getDuracao()
            );


            ps.setLong(
                    5,
                    servico.getIdServico()
            );


            int linhasAlteradas =
                    ps.executeUpdate();


            if (linhasAlteradas > 0) {

                System.out.println(
                        "Serviço atualizado com sucesso!"
                );

            } else {

                System.out.println(
                        "Serviço não encontrado!"
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar serviço!"
            );

            e.printStackTrace();
        }
    }


    
    // EXCLUIR
    

    public boolean excluir(Long id) {

        String sql =
                "DELETE FROM servico WHERE id_servico = ?";

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

            // O serviço possui algum dado vinculado
            return false;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}