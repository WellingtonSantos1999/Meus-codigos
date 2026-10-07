package view;

import controller.ServicoController;
import model.Servico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaServico extends JFrame {

    
    // CAMPOS
    

    private JTextField campoNome;
    private JTextField campoDescricao;
    private JTextField campoValor;
    private JTextField campoDuracao;


    
    // TABELA
    

    private JTable tabelaServicos;
    private DefaultTableModel modeloTabela;


    
    // CONTROLLER
    

    private ServicoController servicoController;


    
    // ID SELECIONADO
    

    private Long idServicoSelecionado = null;


    
    // CONSTRUTOR
    

    public TelaServico() {

        servicoController =
                new ServicoController();


        setTitle("Cadastro de Serviços");

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

        carregarServicos();


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
                        "Dados do Serviço"
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
        

        campoNome =
                new JTextField(25);


        campoDescricao =
                new JTextField(25);


        campoValor =
                new JTextField(25);


        campoDuracao =
                new JTextField(25);


        
        // NOME
        

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


        
        // DESCRIÇÃO
        

        gbc.gridx = 0;
        gbc.gridy = 1;


        painelCampos.add(
                new JLabel("Descrição:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoDescricao,
                gbc
        );


        
        // VALOR
        

        gbc.gridx = 0;
        gbc.gridy = 2;


        painelCampos.add(
                new JLabel("Valor (R$):"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoValor,
                gbc
        );


        
        // DURAÇÃO
        

        gbc.gridx = 0;
        gbc.gridy = 3;


        painelCampos.add(
                new JLabel("Duração:"),
                gbc
        );


        gbc.gridx = 1;


        painelCampos.add(
                campoDuracao,
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
        gbc.gridy = 4;

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


        
        // IMAGEM DO SERVIÇO
        

        JLabel imagemServico =
                criarImagem(
                        "/imagens/servicos.png",
                        150,
                        140
                );


        imagemServico.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        imagemServico.setVerticalAlignment(
                SwingConstants.CENTER
        );


        
        // PAINEL DA IMAGEM
        

        JPanel painelImagem =
                new JPanel(
                        new BorderLayout()
                );


        painelImagem.add(
                imagemServico,
                BorderLayout.CENTER
        );


        painelImagem.setPreferredSize(
                new Dimension(
                        350,
                        200
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
                "Nome",
                "Descrição",
                "Valor",
                "Duração"

        };


        modeloTabela =
                new DefaultTableModel(
                        colunas,
                        0
                );


        tabelaServicos =
                new JTable(
                        modeloTabela
                );


        tabelaServicos.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scroll =
                new JScrollPane(
                        tabelaServicos
                );


        tabelaServicos
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        selecionarServico();

                    }

                });


        add(
                scroll,
                BorderLayout.CENTER
        );
    }


    
    // CADASTRAR
    

    private void cadastrar() {

        String nome =
                campoNome
                        .getText()
                        .trim();


        String descricao =
                campoDescricao
                        .getText()
                        .trim();


        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do serviço!"
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


        
        // DURAÇÃO
        

        int duracao;


        try {

            duracao =
                    Integer.parseInt(
                            campoDuracao
                                    .getText()
                                    .trim()
                    );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma duração válida em minutos!\nExemplo: 30"
            );

            return;
        }


        
        // CADASTRAR
        

        servicoController.cadastrar(

                nome,

                descricao,

                valor,

                duracao

        );


        JOptionPane.showMessageDialog(
                this,
                "Serviço cadastrado com sucesso!"
        );


        limparCampos();

        carregarServicos();
    }


    
    // CARREGAR SERVIÇOS
    

    private void carregarServicos() {

        modeloTabela.setRowCount(0);


        List<Servico> servicos =
                servicoController.listar();


        for (Servico servico : servicos) {


            Object[] linha = {

                    servico.getIdServico(),

                    servico.getNome(),

                    servico.getDescricao(),

                    String.format(
                            "R$ %.2f",
                            servico.getValor()
                    ),

                    servico.getDuracao()
                            + " min"

            };


            modeloTabela.addRow(
                    linha
            );
        }
    }


    
    // SELECIONAR SERVIÇO
    

    private void selecionarServico() {

        int linha =
                tabelaServicos.getSelectedRow();


        if (linha == -1) {

            return;
        }


        idServicoSelecionado =
                Long.parseLong(
                        modeloTabela
                                .getValueAt(
                                        linha,
                                        0
                                )
                                .toString()
                );


        Servico servico =
                servicoController.buscarPorId(
                        idServicoSelecionado
                );


        if (servico == null) {

            return;
        }


        campoNome.setText(
                servico.getNome()
        );


        campoDescricao.setText(
                servico.getDescricao()
        );


        campoValor.setText(
                String.valueOf(
                        servico.getValor()
                )
        );


        campoDuracao.setText(
                String.valueOf(
                        servico.getDuracao()
                )
        );
    }


    
    // ATUALIZAR
    

    private void atualizar() {

        if (idServicoSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um serviço na tabela!"
            );

            return;
        }


        String nome =
                campoNome
                        .getText()
                        .trim();


        String descricao =
                campoDescricao
                        .getText()
                        .trim();


        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do serviço!"
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


        
        // DURAÇÃO
        

        int duracao;


        try {

            duracao =
                    Integer.parseInt(
                            campoDuracao
                                    .getText()
                                    .trim()
                    );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma duração válida em minutos!"
            );

            return;
        }


        
        // ATUALIZAR
        

        servicoController.atualizar(

                idServicoSelecionado,

                nome,

                descricao,

                valor,

                duracao

        );


        JOptionPane.showMessageDialog(
                this,
                "Serviço atualizado com sucesso!"
        );


        limparCampos();

        carregarServicos();
    }


    
    // EXCLUIR
    

    private void excluir() {

        if (idServicoSelecionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um serviço na tabela!"
            );

            return;
        }


        int resposta =
                JOptionPane.showConfirmDialog(

                        this,

                        "Deseja realmente excluir este serviço?",

                        "Confirmar exclusão",

                        JOptionPane.YES_NO_OPTION

                );


        if (resposta == JOptionPane.YES_OPTION) {

            boolean excluiu =
                    servicoController.excluir(
                            idServicoSelecionado
                    );


            if (excluiu) {

                JOptionPane.showMessageDialog(
                        this,
                        "Serviço excluído com sucesso!"
                );


                limparCampos();

                carregarServicos();

            } else {

                JOptionPane.showMessageDialog(
                        this,

                        "Não é possível excluir este serviço,\n" +
                                "pois existem outros dados vinculados a ele.",

                        "Não foi possível excluir",

                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }


    
    // LIMPAR CAMPOS
    

    private void limparCampos() {

        campoNome.setText("");

        campoDescricao.setText("");

        campoValor.setText("");

        campoDuracao.setText("");


        idServicoSelecionado = null;


        tabelaServicos.clearSelection();


        campoNome.requestFocus();
    }
}