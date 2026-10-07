package view;

import controller.AgendamentoController;
import controller.PagamentoController;

import model.Agendamento;
import model.Pagamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;

public class TelaPagamento extends JFrame {

    
    // CAMPOS
    

    private JComboBox<Agendamento> comboAgendamento;

    private JTextField campoValor;
    private JTextField campoData;

    private JComboBox<String> comboFormaPagamento;
    private JComboBox<String> comboStatus;


    
    // TABELA
    

    private JTable tabelaPagamentos;
    private DefaultTableModel modeloTabela;


    
    // CONTROLLERS
    

    private PagamentoController pagamentoController;
    private AgendamentoController agendamentoController;


    
    // ID SELECIONADO
    

    private Long idPagamentoSelecionado = null;


    
    // FORMATO DA DATA
    

    private final DateTimeFormatter formatoData =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");


    
    // CONSTRUTOR
    

    public TelaPagamento() {

        pagamentoController =
                new PagamentoController();

        agendamentoController =
                new AgendamentoController();


        setTitle("Cadastro de Pagamentos");

        setSize(1000, 600);

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

        carregarAgendamentos();

        carregarPagamentos();


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
                        "Dados do Pagamento"
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
        

        comboAgendamento =
                new JComboBox<>();


        campoValor =
                new JTextField(25);


        campoData =
                new JTextField(25);


        comboFormaPagamento =
                new JComboBox<>();


        comboStatus =
                new JComboBox<>();


        
        // FORMAS DE PAGAMENTO
        

        comboFormaPagamento.addItem("PIX");

        comboFormaPagamento.addItem("Dinheiro");

        comboFormaPagamento.addItem(
                "Cartão de Crédito"
        );

        comboFormaPagamento.addItem(
                "Cartão de Débito"
        );


        
        // STATUS
        

        comboStatus.addItem("Pendente");

        comboStatus.addItem("Pago");

        comboStatus.addItem("Cancelado");


        
        // TAMANHO DOS COMBOS
        

        Dimension tamanhoCampo =
                campoValor.getPreferredSize();


        comboAgendamento.setPreferredSize(
                tamanhoCampo
        );


        comboFormaPagamento.setPreferredSize(
                tamanhoCampo
        );


        comboStatus.setPreferredSize(
                tamanhoCampo
        );


        
        // AGENDAMENTO
        

        gbc.gridx = 0;
        gbc.gridy = 0;


        painelCampos.add(
                new JLabel("Agendamento:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                comboAgendamento,
                gbc
        );


        
        // VALOR
        

        gbc.gridx = 0;
        gbc.gridy = 1;


        painelCampos.add(
                new JLabel("Valor (R$):"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoValor,
                gbc
        );


        
        // DATA
        

        gbc.gridx = 0;
        gbc.gridy = 2;


        painelCampos.add(
                new JLabel("Data (dd/MM/yyyy):"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoData,
                gbc
        );


        
        // FORMA DE PAGAMENTO
        

        gbc.gridx = 0;
        gbc.gridy = 3;


        painelCampos.add(
                new JLabel("Forma de Pagamento:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                comboFormaPagamento,
                gbc
        );


        
        // STATUS
        

        gbc.gridx = 0;
        gbc.gridy = 4;


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
        gbc.gridy = 5;

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


        
        // IMAGEM DO PAGAMENTO
        

        JLabel imagemPagamento =
                criarImagem(
                        "/imagens/pagamentos.png",
                        150,
                        140
                );


        imagemPagamento.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        imagemPagamento.setVerticalAlignment(
                SwingConstants.CENTER
        );


        
        // PAINEL DA IMAGEM
        

        JPanel painelImagem =
                new JPanel(
                        new BorderLayout()
                );


        painelImagem.add(
                imagemPagamento,
                BorderLayout.CENTER
        );


        painelImagem.setPreferredSize(
                new Dimension(
                        380,
                        220
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
                "Agendamento",
                "Valor",
                "Data",
                "Forma de Pagamento",
                "Status"

        };


        modeloTabela =
                new DefaultTableModel(
                        colunas,
                        0
                );


        tabelaPagamentos =
                new JTable(
                        modeloTabela
                );


        tabelaPagamentos.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scroll =
                new JScrollPane(
                        tabelaPagamentos
                );


        tabelaPagamentos
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        selecionarPagamento();

                    }

                });


        add(
                scroll,
                BorderLayout.CENTER
        );
    }


    
    // CARREGAR AGENDAMENTOS
    

    private void carregarAgendamentos() {

        comboAgendamento.removeAllItems();


        List<Agendamento> agendamentos =
                agendamentoController.listar();


        for (Agendamento agendamento : agendamentos) {

            comboAgendamento.addItem(
                    agendamento
            );
        }
    }


    
    // CADASTRAR
    

    private void cadastrar() {

        Agendamento agendamento =
                (Agendamento)
                        comboAgendamento
                                .getSelectedItem();


        if (agendamento == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cadastre um agendamento antes de cadastrar um pagamento!"
            );

            return;
        }


        
        // VALOR
        

        double valor;


        try {

            String textoValor =
                    campoValor
                            .getText()
                            .trim()
                            .replace(",", ".");


            valor =
                    Double.parseDouble(
                            textoValor
                    );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite um valor válido!\nExemplo: 50,00"
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


        String formaPagamento =
                (String)
                        comboFormaPagamento
                                .getSelectedItem();


        String status =
                (String)
                        comboStatus
                                .getSelectedItem();


        
        // CADASTRAR
        

        pagamentoController.cadastrar(

                valor,

                data,

                formaPagamento,

                status,

                agendamento.getIdAgendamento()

        );


        JOptionPane.showMessageDialog(
                this,
                "Pagamento cadastrado com sucesso!"
        );


        limparCampos();

        carregarPagamentos();
    }


    
    // CARREGAR PAGAMENTOS
    

    private void carregarPagamentos() {

        modeloTabela.setRowCount(0);


        List<Pagamento> pagamentos =
                pagamentoController.listar();


        for (Pagamento pagamento : pagamentos) {


            Agendamento agendamento =
                    agendamentoController.buscarPorId(
                            pagamento.getIdAgendamento()
                    );


            String textoAgendamento;


            if (agendamento != null) {

                textoAgendamento =
                        "Agendamento #"
                                + agendamento.getIdAgendamento();

            } else {

                textoAgendamento = "";
            }


            Object[] linha = {

                    pagamento.getIdPagamento(),

                    textoAgendamento,

                    String.format(
                            "R$ %.2f",
                            pagamento.getValor()
                    ),

                    pagamento
                            .getData()
                            .format(formatoData),

                    pagamento.getFormaPagamento(),

                    pagamento.getStatus()

            };


            modeloTabela.addRow(
                    linha
            );
        }
    }


    
    // SELECIONAR PAGAMENTO
    

    private void selecionarPagamento() {

        int linha =
                tabelaPagamentos.getSelectedRow();


        if (linha == -1) {

            return;
        }


        idPagamentoSelecionado =
                Long.parseLong(
                        modeloTabela
                                .getValueAt(
                                        linha,
                                        0
                                )
                                .toString()
                );


        Pagamento pagamento =
                pagamentoController.buscarPorId(
                        idPagamentoSelecionado
                );


        if (pagamento == null) {

            return;
        }


        campoValor.setText(
                String.valueOf(
                        pagamento.getValor()
                )
        );


        campoData.setText(
                pagamento
                        .getData()
                        .format(formatoData)
        );


        comboFormaPagamento.setSelectedItem(
                pagamento.getFormaPagamento()
        );


        comboStatus.setSelectedItem(
                pagamento.getStatus()
        );


        selecionarAgendamento(
                pagamento.getIdAgendamento()
        );
    }


    
    // SELECIONAR AGENDAMENTO
    

    private void selecionarAgendamento(
            Long idAgendamento
    ) {

        for (int i = 0;
             i < comboAgendamento.getItemCount();
             i++) {


            Agendamento agendamento =
                    comboAgendamento.getItemAt(i);


            if (agendamento
                    .getIdAgendamento()
                    .equals(idAgendamento)) {


                comboAgendamento.setSelectedIndex(i);

                break;
            }
        }
    }


    
    // ATUALIZAR
    

    private void atualizar() {

        if (idPagamentoSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um pagamento na tabela!"
            );

            return;
        }


        Agendamento agendamento =
                (Agendamento)
                        comboAgendamento
                                .getSelectedItem();


        if (agendamento == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um agendamento!"
            );

            return;
        }


        
        // VALOR
        

        double valor;


        try {

            valor =
                    Double.parseDouble(
                            campoValor
                                    .getText()
                                    .trim()
                                    .replace(",", ".")
                    );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite um valor válido!"
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


        String formaPagamento =
                (String)
                        comboFormaPagamento
                                .getSelectedItem();


        String status =
                (String)
                        comboStatus
                                .getSelectedItem();


        
        // ATUALIZAR
        

        pagamentoController.atualizar(

                idPagamentoSelecionado,

                valor,

                data,

                formaPagamento,

                status,

                agendamento.getIdAgendamento()

        );


        JOptionPane.showMessageDialog(
                this,
                "Pagamento atualizado com sucesso!"
        );


        limparCampos();

        carregarPagamentos();
    }


    
    // EXCLUIR
    

    private void excluir() {

        if (idPagamentoSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um pagamento na tabela!"
            );

            return;
        }

        int resposta =
                JOptionPane.showConfirmDialog(
                        this,
                        "Deseja realmente excluir este pagamento?",
                        "Confirmar exclusão",
                        JOptionPane.YES_NO_OPTION
                );

        if (resposta == JOptionPane.YES_OPTION) {

            boolean excluiu =
                    pagamentoController.excluir(
                            idPagamentoSelecionado
                    );

            if (excluiu) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pagamento excluído com sucesso!"
                );

                limparCampos();
                carregarPagamentos();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível excluir este pagamento.",
                        "Erro ao excluir",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    
    // LIMPAR CAMPOS
    

    private void limparCampos() {

        campoValor.setText("");

        campoData.setText("");


        if (comboAgendamento.getItemCount() > 0) {

            comboAgendamento.setSelectedIndex(0);
        }


        if (comboFormaPagamento.getItemCount() > 0) {

            comboFormaPagamento.setSelectedIndex(0);
        }


        if (comboStatus.getItemCount() > 0) {

            comboStatus.setSelectedIndex(0);
        }


        idPagamentoSelecionado = null;


        tabelaPagamentos.clearSelection();


        campoValor.requestFocus();
    }
}