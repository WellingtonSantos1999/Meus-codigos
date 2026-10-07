package view;

import controller.FuncionarioController;
import model.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaFuncionario extends JFrame {

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private JTextField campoCargo;

    private JTable tabelaFuncionarios;
    private DefaultTableModel modeloTabela;

    private FuncionarioController funcionarioController;

    private Long idFuncionarioSelecionado = null;


    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public TelaFuncionario() {

        funcionarioController =
                new FuncionarioController();

        setTitle("Cadastro de Funcionários");

        setSize(900, 550);

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

        carregarFuncionarios();

        setVisible(true);
    }


    // ==========================================
    // CRIAR FORMULÁRIO
    // ==========================================

    private void criarFormulario() {

        // Painel principal
        JPanel painelFormulario =
                new JPanel(
                        new BorderLayout(
                                20,
                                5
                        )
                );

        painelFormulario.setBorder(
                BorderFactory.createTitledBorder(
                        "Dados do Funcionário"
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

        campoCpf =
                new JTextField(25);

        campoTelefone =
                new JTextField(25);

        campoCargo =
                new JTextField(25);


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
        // CPF
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 1;

        painelCampos.add(
                new JLabel("CPF:"),
                gbc
        );

        gbc.gridx = 1;

        painelCampos.add(
                campoCpf,
                gbc
        );


        // ==========================================
        // TELEFONE
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 2;

        painelCampos.add(
                new JLabel("Telefone:"),
                gbc
        );

        gbc.gridx = 1;

        painelCampos.add(
                campoTelefone,
                gbc
        );


        // ==========================================
        // CARGO
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 3;

        painelCampos.add(
                new JLabel("Cargo:"),
                gbc
        );

        gbc.gridx = 1;

        painelCampos.add(
                campoCargo,
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
        // COLOCAR BOTÕES ABAIXO DOS CAMPOS
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 4;

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
        // IMAGEM DO FUNCIONÁRIO
        // ==========================================

        JLabel imagemFuncionario =
                criarImagem(
                        "/imagens/funcionarios.png",
                        140,
                        180
                );


        imagemFuncionario.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        imagemFuncionario.setVerticalAlignment(
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
                imagemFuncionario,
                BorderLayout.CENTER
        );


        painelImagem.setPreferredSize(
                new Dimension(
                        350,
                        200
                )
        );


        // ==========================================
        // ADICIONAR AO FORMULÁRIO
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
        // CALCULAR ESCALA
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
                "CPF",
                "Telefone",
                "Cargo"

        };


        modeloTabela =
                new DefaultTableModel(
                        colunas,
                        0
                );


        tabelaFuncionarios =
                new JTable(
                        modeloTabela
                );


        tabelaFuncionarios.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scroll =
                new JScrollPane(
                        tabelaFuncionarios
                );


        tabelaFuncionarios
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        selecionarFuncionario();

                    }

                });


        add(
                scroll,
                BorderLayout.CENTER
        );
    }


    // ==========================================
    // CADASTRAR
    // ==========================================

    private void cadastrar() {

        String nome =
                campoNome
                        .getText()
                        .trim();


        String cpf =
                campoCpf
                        .getText()
                        .trim();


        String telefone =
                campoTelefone
                        .getText()
                        .trim();


        String cargo =
                campoCargo
                        .getText()
                        .trim();


        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do funcionário!"
            );

            return;
        }


        funcionarioController.cadastrar(

                nome,

                cpf,

                telefone,

                cargo

        );


        JOptionPane.showMessageDialog(
                this,
                "Funcionário cadastrado com sucesso!"
        );


        limparCampos();

        carregarFuncionarios();
    }


    // ==========================================
    // CARREGAR FUNCIONÁRIOS
    // ==========================================

    private void carregarFuncionarios() {

        modeloTabela.setRowCount(0);


        List<Funcionario> funcionarios =
                funcionarioController.listar();


        for (Funcionario funcionario : funcionarios) {


            Object[] linha = {

                    funcionario.getIdFuncionario(),

                    funcionario.getNome(),

                    funcionario.getCpf(),

                    funcionario.getTelefone(),

                    funcionario.getCargo()

            };


            modeloTabela.addRow(
                    linha
            );
        }
    }


    // ==========================================
    // SELECIONAR FUNCIONÁRIO
    // ==========================================

    private void selecionarFuncionario() {

        int linha =
                tabelaFuncionarios.getSelectedRow();


        if (linha == -1) {

            return;
        }


        idFuncionarioSelecionado =
                Long.parseLong(

                        modeloTabela
                                .getValueAt(
                                        linha,
                                        0
                                )
                                .toString()

                );


        Funcionario funcionario =
                funcionarioController.buscarPorId(
                        idFuncionarioSelecionado
                );


        if (funcionario == null) {

            return;
        }


        campoNome.setText(
                funcionario.getNome()
        );


        campoCpf.setText(
                funcionario.getCpf()
        );


        campoTelefone.setText(
                funcionario.getTelefone()
        );


        campoCargo.setText(
                funcionario.getCargo()
        );
    }


    // ==========================================
    // ATUALIZAR
    // ==========================================

    private void atualizar() {

        if (idFuncionarioSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um funcionário na tabela!"
            );

            return;
        }


        String nome =
                campoNome
                        .getText()
                        .trim();


        String cpf =
                campoCpf
                        .getText()
                        .trim();


        String telefone =
                campoTelefone
                        .getText()
                        .trim();


        String cargo =
                campoCargo
                        .getText()
                        .trim();


        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do funcionário!"
            );

            return;
        }


        funcionarioController.atualizar(

                idFuncionarioSelecionado,

                nome,

                cpf,

                telefone,

                cargo

        );


        JOptionPane.showMessageDialog(
                this,
                "Funcionário atualizado com sucesso!"
        );


        limparCampos();

        carregarFuncionarios();
    }


    // ==========================================
    // EXCLUIR
    // ==========================================

    private void excluir() {

        if (idFuncionarioSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um funcionário na tabela!"
            );

            return;
        }


        int resposta =
                JOptionPane.showConfirmDialog(

                        this,

                        "Deseja realmente excluir este funcionário?",

                        "Confirmar exclusão",

                        JOptionPane.YES_NO_OPTION

                );


        if (resposta ==
                JOptionPane.YES_OPTION) {


            funcionarioController.excluir(
                    idFuncionarioSelecionado
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Funcionário excluído com sucesso!"
            );


            limparCampos();

            carregarFuncionarios();
        }
    }


    // ==========================================
    // LIMPAR CAMPOS
    // ==========================================

    private void limparCampos() {

        campoNome.setText("");

        campoCpf.setText("");

        campoTelefone.setText("");

        campoCargo.setText("");


        idFuncionarioSelecionado = null;


        tabelaFuncionarios.clearSelection();


        campoNome.requestFocus();
    }
}