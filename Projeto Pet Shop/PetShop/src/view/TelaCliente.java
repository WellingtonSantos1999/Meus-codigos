package view;

import controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaCliente extends JFrame {

    // ==========================================
    // CAMPOS
    // ==========================================

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextField campoEndereco;


    // ==========================================
    // TABELA
    // ==========================================

    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;


    // ==========================================
    // CONTROLLER
    // ==========================================

    private ClienteController clienteController;


    // ==========================================
    // ID DO CLIENTE SELECIONADO
    // ==========================================

    private Long idClienteSelecionado = null;


    // ==========================================
    // CONSTRUTOR
    // ==========================================

    public TelaCliente() {

        clienteController =
                new ClienteController();


        setTitle("Cadastro de Clientes");

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

        carregarClientes();


        setVisible(true);
    }


    // ==========================================
    // CRIAR FORMULÁRIO
    // ==========================================

    private void criarFormulario() {

        // ==========================================
        // PAINEL PRINCIPAL DO FORMULÁRIO
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
                        "Dados do Cliente"
                )
        );


        // ==========================================
        // PAINEL ESQUERDO
        // CAMPOS + BOTÕES
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

        campoEmail =
                new JTextField(25);

        campoEndereco =
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
        // E-MAIL
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 3;


        painelCampos.add(
                new JLabel("E-mail:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoEmail,
                gbc
        );


        // ==========================================
        // ENDEREÇO
        // ==========================================

        gbc.gridx = 0;
        gbc.gridy = 4;


        painelCampos.add(
                new JLabel("Endereço:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoEndereco,
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
        gbc.gridy = 5;

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
        // IMAGEM DO LADO DIREITO
        // ==========================================

        JLabel imagemCliente =
                criarImagem(
                        "/imagens/clientes.png",
                        300,
                        180
                );


        imagemCliente.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        imagemCliente.setVerticalAlignment(
                SwingConstants.CENTER
        );


        JPanel painelImagem =
                new JPanel(
                        new BorderLayout()
                );


        painelImagem.add(
                imagemCliente,
                BorderLayout.CENTER
        );


        painelImagem.setPreferredSize(
                new Dimension(
                        350,
                        200
                )
        );


        // ==========================================
        // ADICIONAR ESQUERDA E DIREITA
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
    // FUNÇÃO PARA CARREGAR IMAGEM
    // SEM DEFORMAR
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


        // Caso a imagem não seja encontrada
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


        ImageIcon imagemFinal =
                new ImageIcon(
                        imagemRedimensionada
                );


        return new JLabel(
                imagemFinal
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
                "E-mail",
                "Endereço"

        };


        modeloTabela =
                new DefaultTableModel(
                        colunas,
                        0
                );


        tabelaClientes =
                new JTable(
                        modeloTabela
                );


        tabelaClientes.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scroll =
                new JScrollPane(
                        tabelaClientes
                );


        tabelaClientes
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        selecionarCliente();

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


        String email =
                campoEmail
                        .getText()
                        .trim();


        String endereco =
                campoEndereco
                        .getText()
                        .trim();


        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do cliente!"
            );

            return;
        }


        clienteController.cadastrar(

                nome,

                cpf,

                telefone,

                email,

                endereco

        );


        JOptionPane.showMessageDialog(
                this,
                "Cliente cadastrado com sucesso!"
        );


        limparCampos();

        carregarClientes();
    }


    // ==========================================
    // CARREGAR CLIENTES
    // ==========================================

    private void carregarClientes() {

        modeloTabela.setRowCount(0);


        List<Cliente> clientes =
                clienteController.listar();


        for (Cliente cliente : clientes) {


            Object[] linha = {

                    cliente.getIdCliente(),

                    cliente.getNome(),

                    cliente.getCpf(),

                    cliente.getTelefone(),

                    cliente.getEmail(),

                    cliente.getEndereco()

            };


            modeloTabela.addRow(
                    linha
            );
        }
    }


    // ==========================================
    // SELECIONAR CLIENTE
    // ==========================================

    private void selecionarCliente() {

        int linha =
                tabelaClientes.getSelectedRow();


        if (linha == -1) {

            return;
        }


        idClienteSelecionado =
                Long.parseLong(

                        modeloTabela
                                .getValueAt(
                                        linha,
                                        0
                                )
                                .toString()

                );


        Cliente cliente =
                clienteController.buscarPorId(
                        idClienteSelecionado
                );


        if (cliente == null) {

            return;
        }


        campoNome.setText(
                cliente.getNome()
        );


        campoCpf.setText(
                cliente.getCpf()
        );


        campoTelefone.setText(
                cliente.getTelefone()
        );


        campoEmail.setText(
                cliente.getEmail()
        );


        campoEndereco.setText(
                cliente.getEndereco()
        );
    }


    // ==========================================
    // ATUALIZAR
    // ==========================================

    private void atualizar() {

        if (idClienteSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um cliente na tabela!"
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


        String email =
                campoEmail
                        .getText()
                        .trim();


        String endereco =
                campoEndereco
                        .getText()
                        .trim();


        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do cliente!"
            );

            return;
        }


        clienteController.atualizar(

                idClienteSelecionado,

                nome,

                cpf,

                telefone,

                email,

                endereco

        );


        JOptionPane.showMessageDialog(
                this,
                "Cliente atualizado com sucesso!"
        );


        limparCampos();

        carregarClientes();
    }


    // ==========================================
    // EXCLUIR
    // ==========================================

    private void excluir() {

        if (idClienteSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um cliente na tabela!"
            );

            return;
        }


        int resposta =
                JOptionPane.showConfirmDialog(

                        this,

                        "Deseja realmente excluir este cliente?",

                        "Confirmar exclusão",

                        JOptionPane.YES_NO_OPTION

                );


        if (resposta ==
                JOptionPane.YES_OPTION) {


            clienteController.excluir(
                    idClienteSelecionado
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Cliente excluído com sucesso!"
            );


            limparCampos();

            carregarClientes();
        }
    }


    // ==========================================
    // LIMPAR CAMPOS
    // ==========================================

    private void limparCampos() {

        campoNome.setText("");

        campoCpf.setText("");

        campoTelefone.setText("");

        campoEmail.setText("");

        campoEndereco.setText("");


        idClienteSelecionado = null;


        tabelaClientes.clearSelection();


        campoNome.requestFocus();
    }
}