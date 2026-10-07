package controller;

import dao.PetDAO;
import model.Pet;

import java.util.List;

public class PetController {

    private PetDAO petDAO;


    
    // CONSTRUTOR
    

    public PetController() {

        petDAO = new PetDAO();
    }


    
    // CADASTRAR
    

    public void cadastrar(String nome,
                          String especie,
                          String raca,
                          int idade,
                          String sexo,
                          Long idCliente) {

        Pet pet = new Pet(
                nome,
                especie,
                raca,
                idade,
                sexo,
                idCliente
        );

        petDAO.cadastrar(pet);
    }


    
    // LISTAR
    

    public List<Pet> listar() {

        return petDAO.listar();
    }


    
    // BUSCAR POR ID
    

    public Pet buscarPorId(Long id) {

        return petDAO.buscarPorId(id);
    }


    
    // ATUALIZAR
    

    public void atualizar(Long idPet,
                          String nome,
                          String especie,
                          String raca,
                          int idade,
                          String sexo,
                          Long idCliente) {

        Pet pet = new Pet();

        pet.setIdPet(idPet);
        pet.setNome(nome);
        pet.setEspecie(especie);
        pet.setRaca(raca);
        pet.setIdade(idade);
        pet.setSexo(sexo);
        pet.setIdCliente(idCliente);

        petDAO.atualizar(pet);
    }


    
    // EXCLUIR
    

    public boolean excluir(Long id) {
        return petDAO.excluir(id);
    }
}