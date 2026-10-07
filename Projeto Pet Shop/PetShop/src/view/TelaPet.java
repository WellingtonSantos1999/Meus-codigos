package view;

import controller.ClienteController;
import controller.PetController;
import model.Cliente;
import model.Pet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaPet extends JFrame {

    // ==========================================
    // CAMPOS
    // ==========================================

    private JTextField campoNome;
    private JTextField campoEspecie;
    private JTextField campoRaca;
    private JTextField campoIdade;

    private JComboBox<String> comboSexo;
    private JComboBox<Cliente> comboCliente;


    // ==========================================
    // TABELA
    // ==========================================

    private JTable tabelaPets;
    private DefaultTableModel modeloTabela;


    // ==========================================
    // CONTROLLERS
    // ==========================================

    private PetController petController;
    private ClienteController clienteController;


    // ==========================================
    // ID SELECIONADO
    // ==========================================

    private Long idPetSelecionado = null;


    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public TelaPet() {

        petController =
                new PetController();

        clienteController =
                new ClienteController();


        setTitle("Cadastro de Pets");

        setSize(900, 600);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );


        criarFormulario();

        criarTabela();

        carregarClientes();

        carregarPets();


        setVisible(true);
    }


    // ==========================================
    // CRIAR FORMULÁRIO
    // ==========================================

    private void criarFormulario() {

        // ==========================================
        // PAINEL PRINCIPAL
        // ==========================================

        JPanel painelFormulario =
                new JPanel(
                        new BorderLayout(
                                20,
                                5
                        )
                );


        painelFormulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Dados do Pet"
                )
        );


        // ==========================================
        // LADO ESQUERDO
        // ==========================================

        JPanel painelEsquerdo =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                5,
                                5
                        )
                );


        JPanel painelCampos =
                new JPanel(
                        new GridBagLayout()
                );


        GridBagConstraints gbc =
                new GridBagConstraints();


        gbc.insets =
                new Insets(
                        5,
                        5,
                        5,
                        5
                );


        gbc.anchor =
                GridBagConstraints.WEST;


        // ==========================================
        // CAMPOS
        // ==========================================

        campoNome =
                new JTextField(25);

        campoEspecie =
                new JTextField(25);

        campoRaca =
                new JTextField(25);

        campoIdade =
                new JTextField(25);


        // ==========================================
        // COMBO SEXO
        // ==========================================

        comboSexo =
                new JComboBox<>();


        comboSexo.addItem("Macho");

        comboSexo.addItem("Fêmea");


        // ==========================================
        // COMBO CLIENTE / DONO
        // ==========================================

        comboCliente =
                new JComboBox<>();


        // ==========================================
        // TAMANHO DOS COMBOS
        // ==========================================

        Dimension tamanhoCampo =
                campoNome.getPreferredSize();


        comboSexo.setPreferredSize(
                tamanhoCampo
        );


        comboCliente.setPreferredSize(
                tamanhoCampo
        );


        // ==========================================
        // NOME
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 0;


        painelCampos.add(
                new JLabel("Nome:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoNome,
                gbc
        );


        // ==========================================
        // ESPÉCIE
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 1;


        painelCampos.add(
                new JLabel("Espécie:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoEspecie,
                gbc
        );


        // ==========================================
        // RAÇA
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 2;


        painelCampos.add(
                new JLabel("Raça:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoRaca,
                gbc
        );


        // ==========================================
        // IDADE
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 3;


        painelCampos.add(
                new JLabel("Idade:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoIdade,
                gbc
        );


        // ==========================================
        // SEXO
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 4;


        painelCampos.add(
                new JLabel("Sexo:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                comboSexo,
                gbc
        );


        // ==========================================
        // DONO
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 5;


        painelCampos.add(
                new JLabel("Dono:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                comboCliente,
                gbc
        );


        // ==========================================
        // BOTÕES
        // ==========================================

        JButton botaoCadastrar =
                new JButton("Cadastrar");

        JButton botaoAtualizar =
                new JButton("Atualizar");

        JButton botaoExcluir =
                new JButton("Excluir");

        JButton botaoLimpar =
                new JButton("Limpar");


        JPanel painelBotoes =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                5,
                                5
                        )
                );


        painelBotoes.add(
                botaoCadastrar
        );

        painelBotoes.add(
                botaoAtualizar
        );

        painelBotoes.add(
                botaoExcluir
        );

        painelBotoes.add(
                botaoLimpar
        );


        // ==========================================
        // BOTÕES ABAIXO DOS CAMPOS
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 6;

        gbc.gridwidth = 2;


        painelCampos.add(
                painelBotoes,
                gbc
        );


        gbc.gridwidth = 1;


        // ==========================================
        // EVENTOS
        // ==========================================

        botaoCadastrar.addActionListener(
                e -> cadastrar()
        );


        botaoAtualizar.addActionListener(
                e -> atualizar()
        );


        botaoExcluir.addActionListener(
                e -> excluir()
        );


        botaoLimpar.addActionListener(
                e -> limparCampos()
        );


        painelEsquerdo.add(
                painelCampos
        );


        // ==========================================
        // IMAGEM DO PET
        // ==========================================

        JLabel imagemPet =
                criarImagem(
                        "/imagens/pets.png",
                        300,
                        210
                );


        imagemPet.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        imagemPet.setVerticalAlignment(
                SwingConstants.CENTER
        );


        // ==========================================
        // PAINEL DA IMAGEM
        // ==========================================

        JPanel painelImagem =
                new JPanel(
                        new BorderLayout()
                );


        painelImagem.add(
                imagemPet,
                BorderLayout.CENTER
        );


        painelImagem.setPreferredSize(
                new Dimension(
                        350,
                        230
                )
        );


        // ==========================================
        // ADICIONAR OS DOIS LADOS
        // ==========================================

        painelFormulario.add(
                painelEsquerdo,
                BorderLayout.WEST
        );


        painelFormulario.add(
                painelImagem,
                BorderLayout.CENTER
        );


        add(
                painelFormulario,
                BorderLayout.NORTH
        );
    }


    // ==========================================
    // CARREGAR IMAGEM SEM DEFORMAR
    // ==========================================

    private JLabel criarImagem(
            String caminho,
            int larguraMaxima,
            int alturaMaxima
    ) {

        java.net.URL url =
                getClass().getResource(
                        caminho
                );


        if (url == null) {

            JLabel erro =
                    new JLabel(
                            "Imagem não encontrada"
                    );


            erro.setHorizontalAlignment(
                    SwingConstants.CENTER
            );


            return erro;
        }


        ImageIcon imagemOriginal =
                new ImageIcon(
                        url
                );


        int larguraOriginal =
                imagemOriginal.getIconWidth();


        int alturaOriginal =
                imagemOriginal.getIconHeight();


        // ==========================================
        // CALCULAR PROPORÇÃO
        // ==========================================

        double escala =
                Math.min(

                        (double) larguraMaxima
                                / larguraOriginal,

                        (double) alturaMaxima
                                / alturaOriginal

                );


        int novaLargura =
                (int)
                        (larguraOriginal
                                * escala);


        int novaAltura =
                (int)
                        (alturaOriginal
                                * escala);


        // ==========================================
        // REDIMENSIONAR
        // ==========================================

        Image imagemRedimensionada =
                imagemOriginal
                        .getImage()
                        .getScaledInstance(

                                novaLargura,

                                novaAltura,

                                Image.SCALE_SMOOTH

                        );


        return new JLabel(
                new ImageIcon(
                        imagemRedimensionada
                )
        );
    }


    // ==========================================
    // CRIAR TABELA
    // ==========================================

    private void criarTabela() {

        String[] colunas = {

                "ID",
                "Nome",
                "Espécie",
                "Raça",
                "Idade",
                "Sexo",
                "Dono"

        };


        modeloTabela =
                new DefaultTableModel(
                        colunas,
                        0
                );


        tabelaPets =
                new JTable(
                        modeloTabela
                );


        tabelaPets.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scroll =
                new JScrollPane(
                        tabelaPets
                );


        tabelaPets
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        selecionarPet();

                    }

                });


        add(
                scroll,
                BorderLayout.CENTER
        );
    }


    // ==========================================
    // CARREGAR CLIENTES
    // ==========================================

    private void carregarClientes() {

        comboCliente.removeAllItems();


        List<Cliente> clientes =
                clienteController.listar();


        for (Cliente cliente : clientes) {

            comboCliente.addItem(
                    cliente
            );
        }
    }


    // ==========================================
    // CADASTRAR
    // ==========================================

    private void cadastrar() {

        String nome =
                campoNome
                        .getText()
                        .trim();


        String especie =
                campoEspecie
                        .getText()
                        .trim();


        String raca =
                campoRaca
                        .getText()
                        .trim();


        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do Pet!"
            );

            return;
        }


        // ==========================================
        // IDADE
        // ==========================================

        int idade;


        try {

            idade =
                    Integer.parseInt(
                            campoIdade
                                    .getText()
                                    .trim()
                    );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma idade válida!"
            );

            return;
        }


        String sexo =
                (String)
                        comboSexo
                                .getSelectedItem();


        Cliente cliente =
                (Cliente)
                        comboCliente
                                .getSelectedItem();


        if (cliente == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cadastre um cliente antes de cadastrar o Pet!"
            );

            return;
        }


        petController.cadastrar(

                nome,

                especie,

                raca,

                idade,

                sexo,

                cliente.getIdCliente()

        );


        JOptionPane.showMessageDialog(
                this,
                "Pet cadastrado com sucesso!"
        );


        limparCampos();

        carregarPets();
    }


    // ==========================================
    // CARREGAR PETS
    // ==========================================

    private void carregarPets() {

        modeloTabela.setRowCount(0);


        List<Pet> pets =
                petController.listar();


        for (Pet pet : pets) {


            String nomeCliente =
                    buscarNomeCliente(
                            pet.getIdCliente()
                    );


            Object[] linha = {

                    pet.getIdPet(),

                    pet.getNome(),

                    pet.getEspecie(),

                    pet.getRaca(),

                    pet.getIdade(),

                    pet.getSexo(),

                    nomeCliente

            };


            modeloTabela.addRow(
                    linha
            );
        }
    }


    // ==========================================
    // BUSCAR NOME DO CLIENTE
    // ==========================================

    private String buscarNomeCliente(
            Long idCliente
    ) {

        Cliente cliente =
                clienteController.buscarPorId(
                        idCliente
                );


        if (cliente != null) {

            return cliente.getNome();
        }


        return "";
    }


    // ==========================================
    // SELECIONAR PET
    // ==========================================

    private void selecionarPet() {

        int linha =
                tabelaPets.getSelectedRow();


        if (linha == -1) {

            return;
        }


        idPetSelecionado =
                Long.parseLong(

                        modeloTabela
                                .getValueAt(
                                        linha,
                                        0
                                )
                                .toString()

                );


        Pet pet =
                petController.buscarPorId(
                        idPetSelecionado
                );


        if (pet == null) {

            return;
        }


        campoNome.setText(
                pet.getNome()
        );


        campoEspecie.setText(
                pet.getEspecie()
        );


        campoRaca.setText(
                pet.getRaca()
        );


        campoIdade.setText(
                String.valueOf(
                        pet.getIdade()
                )
        );


        comboSexo.setSelectedItem(
                pet.getSexo()
        );


        selecionarCliente(
                pet.getIdCliente()
        );
    }


    // ==========================================
    // SELECIONAR CLIENTE
    // ==========================================

    private void selecionarCliente(
            Long idCliente
    ) {

        for (int i = 0;
             i < comboCliente.getItemCount();
             i++) {


            Cliente cliente =
                    comboCliente.getItemAt(i);


            if (cliente
                    .getIdCliente()
                    .equals(idCliente)) {


                comboCliente.setSelectedIndex(i);

                break;
            }
        }
    }


    // ==========================================
    // ATUALIZAR
    // ==========================================

    private void atualizar() {

        if (idPetSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um Pet na tabela!"
            );

            return;
        }


        String nome =
                campoNome
                        .getText()
                        .trim();


        String especie =
                campoEspecie
                        .getText()
                        .trim();


        String raca =
                campoRaca
                        .getText()
                        .trim();


        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do Pet!"
            );

            return;
        }


        int idade;


        try {

            idade =
                    Integer.parseInt(
                            campoIdade
                                    .getText()
                                    .trim()
                    );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma idade válida!"
            );

            return;
        }


        String sexo =
                (String)
                        comboSexo
                                .getSelectedItem();


        Cliente cliente =
                (Cliente)
                        comboCliente
                                .getSelectedItem();


        if (cliente == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione o dono do Pet!"
            );

            return;
        }


        petController.atualizar(

                idPetSelecionado,

                nome,

                especie,

                raca,

                idade,

                sexo,

                cliente.getIdCliente()

        );


        JOptionPane.showMessageDialog(
                this,
                "Pet atualizado com sucesso!"
        );


        limparCampos();

        carregarPets();
    }


    // ==========================================
    // EXCLUIR
    // ==========================================

    private void excluir() {

        if (idPetSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um Pet na tabela!"
            );

            return;
        }


        int resposta =
                JOptionPane.showConfirmDialog(

                        this,

                        "Deseja realmente excluir este Pet?",

                        "Confirmar exclusão",

                        JOptionPane.YES_NO_OPTION

                );


        if (resposta ==
                JOptionPane.YES_OPTION) {


            petController.excluir(
                    idPetSelecionado
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Pet excluído com sucesso!"
            );


            limparCampos();

            carregarPets();
        }
    }


    // ==========================================
    // LIMPAR CAMPOS
    // ==========================================

    private void limparCampos() {

        campoNome.setText("");

        campoEspecie.setText("");

        campoRaca.setText("");

        campoIdade.setText("");


        if (comboSexo.getItemCount() > 0) {

            comboSexo.setSelectedIndex(0);
        }


        if (comboCliente.getItemCount() > 0) {

            comboCliente.setSelectedIndex(0);
        }


        idPetSelecionado = null;


        tabelaPets.clearSelection();


        campoNome.requestFocus();
    }
}