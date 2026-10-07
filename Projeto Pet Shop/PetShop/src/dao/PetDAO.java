package dao;

import database.Conexao;
import model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

public class PetDAO {


    
    // CADASTRAR PET
    

    public void cadastrar(Pet pet) {

        String sql = """
                INSERT INTO pet
                (nome, especie, raca, idade, sexo, id_cliente)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, pet.getNome());
            ps.setString(2, pet.getEspecie());
            ps.setString(3, pet.getRaca());
            ps.setInt(4, pet.getIdade());
            ps.setString(5, pet.getSexo());
            ps.setLong(6, pet.getIdCliente());

            ps.executeUpdate();

            System.out.println("Pet cadastrado com sucesso!");

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar pet!");
            e.printStackTrace();
        }
    }


    
    // LISTAR PETS
    

    public List<Pet> listar() {

        List<Pet> pets = new ArrayList<>();

        String sql = """
                SELECT *
                FROM pet
                ORDER BY id_pet
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Pet pet = new Pet();

                pet.setIdPet(
                        rs.getLong("id_pet")
                );

                pet.setNome(
                        rs.getString("nome")
                );

                pet.setEspecie(
                        rs.getString("especie")
                );

                pet.setRaca(
                        rs.getString("raca")
                );

                pet.setIdade(
                        rs.getInt("idade")
                );

                pet.setSexo(
                        rs.getString("sexo")
                );

                pet.setIdCliente(
                        rs.getLong("id_cliente")
                );

                pets.add(pet);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar pets!");
            e.printStackTrace();
        }

        return pets;
    }


    
    // BUSCAR PET POR ID
    

    public Pet buscarPorId(Long id) {

        String sql = """
                SELECT *
                FROM pet
                WHERE id_pet = ?
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Pet pet = new Pet();

                pet.setIdPet(
                        rs.getLong("id_pet")
                );

                pet.setNome(
                        rs.getString("nome")
                );

                pet.setEspecie(
                        rs.getString("especie")
                );

                pet.setRaca(
                        rs.getString("raca")
                );

                pet.setIdade(
                        rs.getInt("idade")
                );

                pet.setSexo(
                        rs.getString("sexo")
                );

                pet.setIdCliente(
                        rs.getLong("id_cliente")
                );

                return pet;
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar pet!");
            e.printStackTrace();
        }

        return null;
    }


    
    // ATUALIZAR PET
    

    public void atualizar(Pet pet) {

        String sql = """
                UPDATE pet
                SET nome = ?,
                    especie = ?,
                    raca = ?,
                    idade = ?,
                    sexo = ?,
                    id_cliente = ?
                WHERE id_pet = ?
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, pet.getNome());
            ps.setString(2, pet.getEspecie());
            ps.setString(3, pet.getRaca());
            ps.setInt(4, pet.getIdade());
            ps.setString(5, pet.getSexo());
            ps.setLong(6, pet.getIdCliente());

            ps.setLong(7, pet.getIdPet());

            int linhasAlteradas =
                    ps.executeUpdate();

            if (linhasAlteradas > 0) {

                System.out.println(
                        "Pet atualizado com sucesso!"
                );

            } else {

                System.out.println(
                        "Pet não encontrado!"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar pet!"
            );

            e.printStackTrace();
        }
    }


    
    // EXCLUIR PET
    

    public boolean excluir(Long id) {

        String sql =
                "DELETE FROM pet WHERE id_pet = ?";

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

            // O Pet possui algum dado vinculado
            return false;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }
}