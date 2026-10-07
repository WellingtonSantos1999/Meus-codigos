package dao;

import database.Conexao;
import model.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {


    // ==========================================
    // CADASTRAR
    // ==========================================

    public void cadastrar(Pagamento pagamento) {

        String sql = """
                INSERT INTO pagamento
                (valor, data, forma_pagamento,
                 status, id_agendamento)
                VALUES (?, ?, ?, ?, ?)
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps =
                     conexao.prepareStatement(sql)) {


            ps.setDouble(
                    1,
                    pagamento.getValor()
            );


            ps.setDate(
                    2,
                    java.sql.Date.valueOf(
                            pagamento.getData()
                    )
            );


            ps.setString(
                    3,
                    pagamento.getFormaPagamento()
            );


            ps.setString(
                    4,
                    pagamento.getStatus()
            );


            ps.setLong(
                    5,
                    pagamento.getIdAgendamento()
            );


            ps.executeUpdate();


            System.out.println(
                    "Pagamento cadastrado com sucesso!"
            );


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao cadastrar pagamento!"
            );

            e.printStackTrace();
        }
    }


    // ==========================================
    // LISTAR
    // ==========================================

    public List<Pagamento> listar() {

        List<Pagamento> pagamentos =
                new ArrayList<>();


        String sql = """
                SELECT *
                FROM pagamento
                ORDER BY id_pagamento
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps =
                     conexao.prepareStatement(sql);
             ResultSet rs =
                     ps.executeQuery()) {


            while (rs.next()) {

                Pagamento pagamento =
                        new Pagamento();


                pagamento.setIdPagamento(
                        rs.getLong(
                                "id_pagamento"
                        )
                );


                pagamento.setValor(
                        rs.getDouble(
                                "valor"
                        )
                );


                pagamento.setData(
                        rs.getDate(
                                "data"
                        ).toLocalDate()
                );


                pagamento.setFormaPagamento(
                        rs.getString(
                                "forma_pagamento"
                        )
                );


                pagamento.setStatus(
                        rs.getString(
                                "status"
                        )
                );


                pagamento.setIdAgendamento(
                        rs.getLong(
                                "id_agendamento"
                        )
                );


                pagamentos.add(
                        pagamento
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar pagamentos!"
            );

            e.printStackTrace();
        }


        return pagamentos;
    }


    // ==========================================
    // BUSCAR POR ID
    // ==========================================

    public Pagamento buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM pagamento
                WHERE id_pagamento = ?
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps =
                     conexao.prepareStatement(sql)) {


            ps.setLong(
                    1,
                    id
            );


            try (ResultSet rs =
                         ps.executeQuery()) {


                if (rs.next()) {

                    Pagamento pagamento =
                            new Pagamento();


                    pagamento.setIdPagamento(
                            rs.getLong(
                                    "id_pagamento"
                            )
                    );


                    pagamento.setValor(
                            rs.getDouble(
                                    "valor"
                            )
                    );


                    pagamento.setData(
                            rs.getDate(
                                    "data"
                            ).toLocalDate()
                    );


                    pagamento.setFormaPagamento(
                            rs.getString(
                                    "forma_pagamento"
                            )
                    );


                    pagamento.setStatus(
                            rs.getString(
                                    "status"
                            )
                    );


                    pagamento.setIdAgendamento(
                            rs.getLong(
                                    "id_agendamento"
                            )
                    );


                    return pagamento;
                }
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar pagamento!"
            );

            e.printStackTrace();
        }


        return null;
    }


    // ==========================================
    // ATUALIZAR
    // ==========================================

    public void atualizar(
            Pagamento pagamento
    ) {

        String sql = """
                UPDATE pagamento
                SET valor = ?,
                    data = ?,
                    forma_pagamento = ?,
                    status = ?,
                    id_agendamento = ?
                WHERE id_pagamento = ?
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps =
                     conexao.prepareStatement(sql)) {


            ps.setDouble(
                    1,
                    pagamento.getValor()
            );


            ps.setDate(
                    2,
                    java.sql.Date.valueOf(
                            pagamento.getData()
                    )
            );


            ps.setString(
                    3,
                    pagamento.getFormaPagamento()
            );


            ps.setString(
                    4,
                    pagamento.getStatus()
            );


            ps.setLong(
                    5,
                    pagamento.getIdAgendamento()
            );


            ps.setLong(
                    6,
                    pagamento.getIdPagamento()
            );


            int linhasAlteradas =
                    ps.executeUpdate();


            if (linhasAlteradas > 0) {

                System.out.println(
                        "Pagamento atualizado com sucesso!"
                );

            } else {

                System.out.println(
                        "Pagamento não encontrado!"
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar pagamento!"
            );

            e.printStackTrace();
        }
    }


    // ==========================================
    // EXCLUIR
    // ==========================================

    public void excluir(Long id) {

        String sql = """
                DELETE FROM pagamento
                WHERE id_pagamento = ?
                """;


        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps =
                     conexao.prepareStatement(sql)) {


            ps.setLong(
                    1,
                    id
            );


            int linhasExcluidas =
                    ps.executeUpdate();


            if (linhasExcluidas > 0) {

                System.out.println(
                        "Pagamento excluído com sucesso!"
                );

            } else {

                System.out.println(
                        "Pagamento não encontrado!"
                );
            }


        } catch (SQLException e) {

            System.out.println(
                    "Erro ao excluir pagamento!"
            );

            e.printStackTrace();
        }
    }
}