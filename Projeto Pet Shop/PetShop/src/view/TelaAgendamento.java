package view;

import controller.AgendamentoController;
import controller.FuncionarioController;
import controller.PetController;
import controller.ServicoController;

import model.Agendamento;
import model.Funcionario;
import model.Pet;
import model.Servico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;

public class TelaAgendamento extends JFrame {

    
    // CAMPOS
    

    private JTextField campoData;
    private JTextField campoHorario;

    private JComboBox<String> comboStatus;
    private JComboBox<Pet> comboPet;
    private JComboBox<Servico> comboServico;
    private JComboBox<Funcionario> comboFuncionario;


    
    // TABELA
    

    private JTable tabelaAgendamentos;
    private DefaultTableModel modeloTabela;


    
    // CONTROLLERS
    

    private AgendamentoController agendamentoController;
    private PetController petController;
    private ServicoController servicoController;
    private FuncionarioController funcionarioController;


    
    // ID SELECIONADO
    

    private Long idAgendamentoSelecionado = null;


    
    // FORMATO DATA E HORA
    

    private final DateTimeFormatter formatoData =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final DateTimeFormatter formatoHora =
            DateTimeFormatter.ofPattern("HH:mm");


    
    // CONSTRUTOR
    

    public TelaAgendamento() {

        agendamentoController =
                new AgendamentoController();

        petController =
                new PetController();

        servicoController =
                new ServicoController();

        funcionarioController =
                new FuncionarioController();


        setTitle("Cadastro de Agendamentos");

        setSize(1000, 650);

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

        carregarPets();

        carregarServicos();

        carregarFuncionarios();

        carregarAgendamentos();


        setVisible(true);
    }


    
    // CRIAR FORMULÁRIO
    

    private void criarFormulario() {

        
        // PAINEL PRINCIPAL
        

        JPanel painelFormulario =
                new JPanel(
                        new BorderLayout(
                                20,
                                5
                        )
                );


        painelFormulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Dados do Agendamento"
                )
        );


        
        // LADO ESQUERDO
        

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


        
        // CRIAR CAMPOS
        

        campoData =
                new JTextField(25);


        campoHorario =
                new JTextField(25);


        comboPet =
                new JComboBox<>();


        comboServico =
                new JComboBox<>();


        comboFuncionario =
                new JComboBox<>();


        comboStatus =
                new JComboBox<>();


        
        // STATUS
        

        comboStatus.addItem("Agendado");

        comboStatus.addItem("Em andamento");

        comboStatus.addItem("Concluído");

        comboStatus.addItem("Cancelado");


        
        // TAMANHO DOS COMBOS
        

        Dimension tamanhoCampo =
                campoData.getPreferredSize();


        comboPet.setPreferredSize(
                tamanhoCampo
        );


        comboServico.setPreferredSize(
                tamanhoCampo
        );


        comboFuncionario.setPreferredSize(
                tamanhoCampo
        );


        comboStatus.setPreferredSize(
                tamanhoCampo
        );


        
        // PET
        

        gbc.gridx = 0;
        gbc.gridy = 0;


        painelCampos.add(
                new JLabel("Pet:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                comboPet,
                gbc
        );


        
        // SERVIÇO
        

        gbc.gridx = 0;
        gbc.gridy = 1;


        painelCampos.add(
                new JLabel("Serviço:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                comboServico,
                gbc
        );


        
        // FUNCIONÁRIO
        

        gbc.gridx = 0;
        gbc.gridy = 2;


        painelCampos.add(
                new JLabel("Funcionário:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                comboFuncionario,
                gbc
        );


        
        // DATA
        

        gbc.gridx = 0;
        gbc.gridy = 3;


        painelCampos.add(
                new JLabel("Data (dd/MM/yyyy):"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoData,
                gbc
        );


        
        // HORÁRIO
        

        gbc.gridx = 0;
        gbc.gridy = 4;


        painelCampos.add(
                new JLabel("Horário (HH:mm):"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoHorario,
                gbc
        );


        
        // STATUS
        

        gbc.gridx = 0;
        gbc.gridy = 5;


        painelCampos.add(
                new JLabel("Status:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                comboStatus,
                gbc
        );


        
        // BOTÕES
        

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


        
        // BOTÕES ABAIXO DOS CAMPOS
        

        gbc.gridx = 0;
        gbc.gridy = 6;

        gbc.gridwidth = 2;


        painelCampos.add(
                painelBotoes,
                gbc
        );


        gbc.gridwidth = 1;


        
        // EVENTOS
        

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


        
        // IMAGEM DO AGENDAMENTO
        

        JLabel imagemAgendamento =
                criarImagem(
                        "/imagens/agendamentos.png",
                        150,
                        140
                );


        imagemAgendamento.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        imagemAgendamento.setVerticalAlignment(
                SwingConstants.CENTER
        );


        
        // PAINEL DA IMAGEM
        

        JPanel painelImagem =
                new JPanel(
                        new BorderLayout()
                );


        painelImagem.add(
                imagemAgendamento,
                BorderLayout.CENTER
        );


        painelImagem.setPreferredSize(
                new Dimension(
                        380,
                        240
                )
        );


        
        // ADICIONAR AO FORMULÁRIO
        

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


    
    // CARREGAR IMAGEM SEM DEFORMAR
    

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


        
        // CALCULAR PROPORÇÃO
        

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


        
        // REDIMENSIONAR
        

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


    
    // CRIAR TABELA
    

    private void criarTabela() {

        String[] colunas = {

                "ID",
                "Data",
                "Horário",
                "Pet",
                "Serviço",
                "Funcionário",
                "Status"

        };


        modeloTabela =
                new DefaultTableModel(
                        colunas,
                        0
                );


        tabelaAgendamentos =
                new JTable(
                        modeloTabela
                );


        tabelaAgendamentos.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scroll =
                new JScrollPane(
                        tabelaAgendamentos
                );


        tabelaAgendamentos
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        selecionarAgendamento();

                    }

                });


        add(
                scroll,
                BorderLayout.CENTER
        );
    }


    
    // CARREGAR PETS
    

    private void carregarPets() {

        comboPet.removeAllItems();


        List<Pet> pets =
                petController.listar();


        for (Pet pet : pets) {

            comboPet.addItem(
                    pet
            );
        }
    }


    
    // CARREGAR SERVIÇOS
    

    private void carregarServicos() {

        comboServico.removeAllItems();


        List<Servico> servicos =
                servicoController.listar();


        for (Servico servico : servicos) {

            comboServico.addItem(
                    servico
            );
        }
    }


    
    // CARREGAR FUNCIONÁRIOS
    

    private void carregarFuncionarios() {

        comboFuncionario.removeAllItems();


        List<Funcionario> funcionarios =
                funcionarioController.listar();


        for (Funcionario funcionario : funcionarios) {

            comboFuncionario.addItem(
                    funcionario
            );
        }
    }


    
    // CADASTRAR
    

    private void cadastrar() {

        Pet pet =
                (Pet)
                        comboPet
                                .getSelectedItem();


        Servico servico =
                (Servico)
                        comboServico
                                .getSelectedItem();


        Funcionario funcionario =
                (Funcionario)
                        comboFuncionario
                                .getSelectedItem();


        if (pet == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cadastre um Pet antes de fazer um agendamento!"
            );

            return;
        }


        if (servico == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cadastre um serviço antes de fazer um agendamento!"
            );

            return;
        }


        if (funcionario == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cadastre um funcionário antes de fazer um agendamento!"
            );

            return;
        }


        
        // DATA
        

        LocalDate data;


        try {

            data =
                    LocalDate.parse(
                            campoData
                                    .getText()
                                    .trim(),
                            formatoData
                    );


        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma data válida!\nExemplo: 10/10/2026"
            );

            return;
        }


        
        // HORÁRIO
        

        LocalTime horario;


        try {

            horario =
                    LocalTime.parse(
                            campoHorario
                                    .getText()
                                    .trim(),
                            formatoHora
                    );


        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite um horário válido!\nExemplo: 14:30"
            );

            return;
        }


        String status =
                (String)
                        comboStatus
                                .getSelectedItem();


        
        // CADASTRAR
        

        agendamentoController.cadastrar(

                data,

                horario,

                status,

                pet.getIdPet(),

                servico.getIdServico(),

                funcionario.getIdFuncionario()

        );


        JOptionPane.showMessageDialog(
                this,
                "Agendamento cadastrado com sucesso!"
        );


        limparCampos();

        carregarAgendamentos();
    }


    
    // CARREGAR AGENDAMENTOS
    

    private void carregarAgendamentos() {

        modeloTabela.setRowCount(0);


        List<Agendamento> agendamentos =
                agendamentoController.listar();


        for (Agendamento agendamento : agendamentos) {


            Pet pet =
                    petController.buscarPorId(
                            agendamento.getIdPet()
                    );


            Servico servico =
                    servicoController.buscarPorId(
                            agendamento.getIdServico()
                    );


            Funcionario funcionario =
                    funcionarioController.buscarPorId(
                            agendamento.getIdFuncionario()
                    );


            String nomePet =
                    pet != null
                            ? pet.getNome()
                            : "";


            String nomeServico =
                    servico != null
                            ? servico.getNome()
                            : "";


            String nomeFuncionario =
                    funcionario != null
                            ? funcionario.getNome()
                            : "";


            Object[] linha = {

                    agendamento.getIdAgendamento(),

                    agendamento
                            .getData()
                            .format(formatoData),

                    agendamento
                            .getHorario()
                            .format(formatoHora),

                    nomePet,

                    nomeServico,

                    nomeFuncionario,

                    agendamento.getStatus()

            };


            modeloTabela.addRow(
                    linha
            );
        }
    }


    
    // SELECIONAR AGENDAMENTO
    

    private void selecionarAgendamento() {

        int linha =
                tabelaAgendamentos.getSelectedRow();


        if (linha == -1) {

            return;
        }


        idAgendamentoSelecionado =
                Long.parseLong(
                        modeloTabela
                                .getValueAt(
                                        linha,
                                        0
                                )
                                .toString()
                );


        Agendamento agendamento =
                agendamentoController.buscarPorId(
                        idAgendamentoSelecionado
                );


        if (agendamento == null) {

            return;
        }


        campoData.setText(
                agendamento
                        .getData()
                        .format(formatoData)
        );


        campoHorario.setText(
                agendamento
                        .getHorario()
                        .format(formatoHora)
        );


        comboStatus.setSelectedItem(
                agendamento.getStatus()
        );


        selecionarPet(
                agendamento.getIdPet()
        );


        selecionarServico(
                agendamento.getIdServico()
        );


        selecionarFuncionario(
                agendamento.getIdFuncionario()
        );
    }


    
    // SELECIONAR PET
    

    private void selecionarPet(
            Long idPet
    ) {

        for (int i = 0;
             i < comboPet.getItemCount();
             i++) {


            Pet pet =
                    comboPet.getItemAt(i);


            if (pet
                    .getIdPet()
                    .equals(idPet)) {


                comboPet.setSelectedIndex(i);

                break;
            }
        }
    }


    
    // SELECIONAR SERVIÇO
    

    private void selecionarServico(
            Long idServico
    ) {

        for (int i = 0;
             i < comboServico.getItemCount();
             i++) {


            Servico servico =
                    comboServico.getItemAt(i);


            if (servico
                    .getIdServico()
                    .equals(idServico)) {


                comboServico.setSelectedIndex(i);

                break;
            }
        }
    }


    
    // SELECIONAR FUNCIONÁRIO
    

    private void selecionarFuncionario(
            Long idFuncionario
    ) {

        for (int i = 0;
             i < comboFuncionario.getItemCount();
             i++) {


            Funcionario funcionario =
                    comboFuncionario.getItemAt(i);


            if (funcionario
                    .getIdFuncionario()
                    .equals(idFuncionario)) {


                comboFuncionario.setSelectedIndex(i);

                break;
            }
        }
    }


    
    // ATUALIZAR
    

    private void atualizar() {

        if (idAgendamentoSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um agendamento na tabela!"
            );

            return;
        }


        Pet pet =
                (Pet)
                        comboPet
                                .getSelectedItem();


        Servico servico =
                (Servico)
                        comboServico
                                .getSelectedItem();


        Funcionario funcionario =
                (Funcionario)
                        comboFuncionario
                                .getSelectedItem();


        if (pet == null ||
                servico == null ||
                funcionario == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pet, serviço e funcionário são obrigatórios!"
            );

            return;
        }


        
        // DATA
        

        LocalDate data;


        try {

            data =
                    LocalDate.parse(
                            campoData
                                    .getText()
                                    .trim(),
                            formatoData
                    );


        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma data válida!\nExemplo: 10/10/2026"
            );

            return;
        }


        
        // HORÁRIO
        

        LocalTime horario;


        try {

            horario =
                    LocalTime.parse(
                            campoHorario
                                    .getText()
                                    .trim(),
                            formatoHora
                    );


        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite um horário válido!\nExemplo: 14:30"
            );

            return;
        }


        String status =
                (String)
                        comboStatus
                                .getSelectedItem();


        
        // ATUALIZAR
        

        agendamentoController.atualizar(

                idAgendamentoSelecionado,

                data,

                horario,

                status,

                pet.getIdPet(),

                servico.getIdServico(),

                funcionario.getIdFuncionario()

        );


        JOptionPane.showMessageDialog(
                this,
                "Agendamento atualizado com sucesso!"
        );


        limparCampos();

        carregarAgendamentos();
    }


    
    // EXCLUIR
    

    private void excluir() {

        if (idAgendamentoSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um agendamento na tabela!"
            );

            return;
        }

        int resposta =
                JOptionPane.showConfirmDialog(
                        this,
                        "Deseja realmente excluir este agendamento?",
                        "Confirmar exclusão",
                        JOptionPane.YES_NO_OPTION
                );

        if (resposta == JOptionPane.YES_OPTION) {

            boolean excluiu =
                    agendamentoController.excluir(
                            idAgendamentoSelecionado
                    );

            if (excluiu) {

                JOptionPane.showMessageDialog(
                        this,
                        "Agendamento excluído com sucesso!"
                );

                limparCampos();
                carregarAgendamentos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Não é possível excluir este agendamento,\n" +
                                "pois existem outros dados vinculados a ele.",
                        "Não foi possível excluir",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }


    
    // LIMPAR CAMPOS
    

    private void limparCampos() {

        campoData.setText("");

        campoHorario.setText("");


        if (comboPet.getItemCount() > 0) {

            comboPet.setSelectedIndex(0);
        }


        if (comboServico.getItemCount() > 0) {

            comboServico.setSelectedIndex(0);
        }


        if (comboFuncionario.getItemCount() > 0) {

            comboFuncionario.setSelectedIndex(0);
        }


        if (comboStatus.getItemCount() > 0) {

            comboStatus.setSelectedIndex(0);
        }


        idAgendamentoSelecionado = null;


        tabelaAgendamentos.clearSelection();


        campoData.requestFocus();
    }
}