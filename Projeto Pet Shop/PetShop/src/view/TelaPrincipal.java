package view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {

        
        // CONFIGURAÇÕES DA JANELA
        

        setTitle("Sistema PetShop");

        setSize(500, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));


        
        // PAINEL SUPERIOR
        

        JPanel painelSuperior = new JPanel();

        painelSuperior.setLayout(
                new BoxLayout(
                        painelSuperior,
                        BoxLayout.Y_AXIS
                )
        );


        
        // IMAGEM
        

        ImageIcon imagemOriginal =
                new ImageIcon(
                        getClass().getResource(
                                "/imagens/logo.png"
                        )
                );


        Image imagemRedimensionada =
                imagemOriginal
                        .getImage()
                        .getScaledInstance(
                                400,
                                200,
                                Image.SCALE_SMOOTH
                        );


        ImageIcon imagemFinal =
                new ImageIcon(
                        imagemRedimensionada
                );


        JLabel labelImagem =
                new JLabel(
                        imagemFinal
                );


        labelImagem.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        painelSuperior.add(
                labelImagem
        );


        
        // TÍTULO
        

        JLabel titulo =
                new JLabel(
                        "Sistema PetShop"
                );


        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );


        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        titulo.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        20,
                        10
                )
        );


        painelSuperior.add(
                titulo
        );


        add(
                painelSuperior,
                BorderLayout.NORTH
        );


        
        // PAINEL DOS BOTÕES
        

        JPanel painelBotoes =
                new JPanel();


        painelBotoes.setLayout(
                new BoxLayout(
                        painelBotoes,
                        BoxLayout.Y_AXIS
                )
        );


        painelBotoes.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        20,
                        20,
                        20
                )
        );


        
        // CRIAR BOTÕES
        

        JButton botaoClientes =
                new JButton("Clientes");

        JButton botaoPets =
                new JButton("Pets");

        JButton botaoFuncionarios =
                new JButton("Funcionários");

        JButton botaoServicos =
                new JButton("Serviços");

        JButton botaoAgendamentos =
                new JButton("Agendamentos");

        JButton botaoPagamentos =
                new JButton("Pagamentos");


        
        // TAMANHO DOS BOTÕES
        

        Dimension tamanhoBotao =
                new Dimension(
                        150,
                        32
                );


        botaoClientes.setMaximumSize(tamanhoBotao);

        botaoPets.setMaximumSize(tamanhoBotao);

        botaoFuncionarios.setMaximumSize(tamanhoBotao);

        botaoServicos.setMaximumSize(tamanhoBotao);

        botaoAgendamentos.setMaximumSize(tamanhoBotao);

        botaoPagamentos.setMaximumSize(tamanhoBotao);


        
        // CENTRALIZAR BOTÕES
        

        botaoClientes.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        botaoPets.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        botaoFuncionarios.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        botaoServicos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        botaoAgendamentos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        botaoPagamentos.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        
        // ADICIONAR BOTÕES
        

        painelBotoes.add(botaoClientes);

        painelBotoes.add(
                Box.createVerticalStrut(8)
        );


        painelBotoes.add(botaoPets);

        painelBotoes.add(
                Box.createVerticalStrut(8)
        );


        painelBotoes.add(botaoFuncionarios);

        painelBotoes.add(
                Box.createVerticalStrut(8)
        );


        painelBotoes.add(botaoServicos);

        painelBotoes.add(
                Box.createVerticalStrut(8)
        );


        painelBotoes.add(botaoAgendamentos);

        painelBotoes.add(
                Box.createVerticalStrut(8)
        );


        painelBotoes.add(botaoPagamentos);


        add(
                painelBotoes,
                BorderLayout.CENTER
        );


        
        // AÇÕES DOS BOTÕES
        

        botaoClientes.addActionListener(e -> {
            new TelaCliente();
        });


        botaoPets.addActionListener(e -> {
            new TelaPet();
        });


        botaoFuncionarios.addActionListener(e -> {
            new TelaFuncionario();
        });


        botaoServicos.addActionListener(e -> {
            new TelaServico();
        });


        botaoAgendamentos.addActionListener(e -> {
            new TelaAgendamento();
        });


        botaoPagamentos.addActionListener(e -> {
            new TelaPagamento();
        });


        
        // MOSTRAR JANELA
        

        setVisible(true);
    }
}