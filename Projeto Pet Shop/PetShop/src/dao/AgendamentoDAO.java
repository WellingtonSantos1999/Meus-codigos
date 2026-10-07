package dao;

import database.Conexao;
import model.Agendamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {


    
    // CADASTRAR
    

    public void cadastrar(Agendamento agendamento) {

        String sql = """
                INSERT INTO agendamento
                (data, horario, status,
                 id_pet, id_servico, id_funcionario)
                VALUES (?, ?, ?, ?, ?, ?)
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {


            ps.setDate(
                    1,
                    java.sql.Date.valueOf(
                            agendamento.getData()
                    )
            );


            ps.setTime(
                    2,
                    java.sql.Time.valueOf(
                            agendamento.getHorario()
                    )
            );


            ps.setString(
                    3,
                    agendamento.getStatus()
            );


            ps.setLong(
                    4,
                    agendamento.getIdPet()
            );


            ps.setLong(
                    5,
                    agendamento.getIdServico()
            );


            ps.setLong(
                    6,
                    agendamento.getIdFuncionario()
            );


            ps.executeUpdate();


            System.out.println(
                    "Agendamento cadastrado com sucesso!"
            );


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao cadastrar agendamento!"
            );

            e.printStackTrace();
        }
    }


    
    // LISTAR
    

    public List<Agendamento> listar() {

        List<Agendamento> agendamentos =
                new ArrayList<>();


        String sql = """
                SELECT *
                FROM agendamento
                ORDER BY data, horario
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {

                Agendamento agendamento =
                        new Agendamento();


                agendamento.setIdAgendamento(
                        rs.getLong(
                                "id_agendamento"
                        )
                );


                agendamento.setData(
                        rs.getDate(
                                "data"
                        ).toLocalDate()
                );


                agendamento.setHorario(
                        rs.getTime(
                                "horario"
                        ).toLocalTime()
                );


                agendamento.setStatus(
                        rs.getString(
                                "status"
                        )
                );


                agendamento.setIdPet(
                        rs.getLong(
                                "id_pet"
                        )
                );


                agendamento.setIdServico(
                        rs.getLong(
                                "id_servico"
                        )
                );


                agendamento.setIdFuncionario(
                        rs.getLong(
                                "id_funcionario"
                        )
                );


                agendamentos.add(
                        agendamento
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar agendamentos!"
            );

            e.printStackTrace();
        }


        return agendamentos;
    }


    
    // BUSCAR POR ID
    

    public Agendamento buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM agendamento
                WHERE id_agendamento = ?
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

                    Agendamento agendamento =
                            new Agendamento();


                    agendamento.setIdAgendamento(
                            rs.getLong(
                                    "id_agendamento"
                            )
                    );


                    agendamento.setData(
                            rs.getDate(
                                    "data"
                            ).toLocalDate()
                    );


                    agendamento.setHorario(
                            rs.getTime(
                                    "horario"
                            ).toLocalTime()
                    );


                    agendamento.setStatus(
                            rs.getString(
                                    "status"
                            )
                    );


                    agendamento.setIdPet(
                            rs.getLong(
                                    "id_pet"
                            )
                    );


                    agendamento.setIdServico(
                            rs.getLong(
                                    "id_servico"
                            )
                    );


                    agendamento.setIdFuncionario(
                            rs.getLong(
                                    "id_funcionario"
                            )
                    );


                    return agendamento;
                }
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar agendamento!"
            );

            e.printStackTrace();
        }


        return null;
    }


    
    // ATUALIZAR
    

    public void atualizar(
            Agendamento agendamento
    ) {

        String sql = """
                UPDATE agendamento
                SET data = ?,
                    horario = ?,
                    status = ?,
                    id_pet = ?,
                    id_servico = ?,
                    id_funcionario = ?
                WHERE id_agendamento = ?
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {


            ps.setDate(
                    1,
                    java.sql.Date.valueOf(
                            agendamento.getData()
                    )
            );


            ps.setTime(
                    2,
                    java.sql.Time.valueOf(
                            agendamento.getHorario()
                    )
            );


            ps.setString(
                    3,
                    agendamento.getStatus()
            );


            ps.setLong(
                    4,
                    agendamento.getIdPet()
            );


            ps.setLong(
                    5,
                    agendamento.getIdServico()
            );


            ps.setLong(
                    6,
                    agendamento.getIdFuncionario()
            );


            ps.setLong(
                    7,
                    agendamento.getIdAgendamento()
            );


            int linhasAlteradas =
                    ps.executeUpdate();


            if (linhasAlteradas > 0) {

                System.out.println(
                        "Agendamento atualizado com sucesso!"
                );

            } else {

                System.out.println(
                        "Agendamento não encontrado!"
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar agendamento!"
            );

            e.printStackTrace();
        }
    }


    
    // EXCLUIR
    

    public boolean excluir(Long id) {

        String sql =
                "DELETE FROM agendamento WHERE id_agendamento = ?";

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

            // O agendamento possui algum dado vinculado
            return false;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}