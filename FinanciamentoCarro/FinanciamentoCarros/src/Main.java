import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Financiamento de Carros");

        janela.setSize(500, 490);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);



        PainelComImagem painelPrincipal =
                new PainelComImagem("/imagens/fundo.jpg");

        painelPrincipal.setLayout(new BorderLayout());

        janela.add(painelPrincipal);




        JPanel painelVeiculo = new JPanel(new GridBagLayout());
        painelVeiculo.setBorder(
                BorderFactory.createTitledBorder("Dados do Veículo")
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 8, 4); // Espaçamento entre os elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;

// MARCA
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // Não estica o texto
        JLabel labelMarca = new JLabel("Marca:");
        painelVeiculo.add(labelMarca, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // O campo expande para preencher o resto da largura
        JComboBox<String> comboMarca = new JComboBox<>();
        comboMarca.addItem("FIAT");
        comboMarca.addItem("FORD");
        comboMarca.addItem("CHEVROLET");
        comboMarca.addItem("VOLKSWAGEN");
        comboMarca.addItem("TOYOTA");
        painelVeiculo.add(comboMarca, gbc);

// MODELO
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel labelModelo = new JLabel("Modelo:");
        painelVeiculo.add(labelModelo, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField campoModelo = new JTextField();
        painelVeiculo.add(campoModelo, gbc);

// ANO
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        JLabel labelAno = new JLabel("Ano:");
        painelVeiculo.add(labelAno, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JComboBox<Integer> comboAno = new JComboBox<>();
        for (int ano = 2026; ano >= 2000; ano--) {
            comboAno.addItem(ano);
        }
        painelVeiculo.add(comboAno, gbc);

// VALOR
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0;
        JLabel labelValor = new JLabel("Valor:");
        painelVeiculo.add(labelValor, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField campoValor = new JTextField();
        painelVeiculo.add(campoValor, gbc);


        JPanel painelTopo =
                new JPanel(new BorderLayout());


// IMAGEM

        ImageIcon iconeCarro =
                new ImageIcon(
                        Main.class.getResource("/imagens/carro.jpg")
                );

        JLabel imagemCarro =
                new JLabel(iconeCarro);

        painelTopo.add(
                imagemCarro,
                BorderLayout.WEST
        );


// DADOS DO VEÍCULO

        painelTopo.add(
                painelVeiculo,
                BorderLayout.CENTER
        );


        painelPrincipal.add(
                painelTopo,
                BorderLayout.NORTH
        );



        // TIPO DO VEÍCULO


        JRadioButton radioNovo =
                new JRadioButton("Novo");

        JRadioButton radioUsado =
                new JRadioButton("Usado");

        ButtonGroup grupoTipo =
                new ButtonGroup();

        grupoTipo.add(radioNovo);
        grupoTipo.add(radioUsado);

        radioNovo.setSelected(true);


        JPanel painelTipo =
                new JPanel(
                        new FlowLayout(FlowLayout.LEFT)
                );



        painelTipo.add(new JLabel("Tipo:"));
        painelTipo.add(radioNovo);
        painelTipo.add(radioUsado);

        painelTipo.setMaximumSize(
                new Dimension(450, 35)
        );



        // VEÍCULO USADO


        JPanel painelUsado =
                new JPanel(
                        new GridLayout(2, 2, 5, 5)
                );


        painelUsado.setMaximumSize(
                new Dimension(450, 80)
        );
        painelUsado.setPreferredSize(
                new Dimension(450, 80)
        );

        painelUsado.setMaximumSize(
                new Dimension(450, 80)
        );

        painelUsado.setBorder(
                BorderFactory.createTitledBorder(
                        "Dados do Veículo Usado"
                )
        );

        JTextField campoQuilometragem =
                new JTextField();

        JTextField campoProprietarios =
                new JTextField();

        painelUsado.add(
                new JLabel("Quilometragem:")
        );

        painelUsado.add(campoQuilometragem);

        painelUsado.add(
                new JLabel("Proprietários:")
        );

        painelUsado.add(campoProprietarios);

        painelUsado.setVisible(false);


        radioUsado.addActionListener(e -> {
            painelUsado.setVisible(true);
        });

        radioNovo.addActionListener(e -> {
            painelUsado.setVisible(false);
        });



        // FINANCIAMENTO


        JPanel painelFinanciamento =
                new JPanel(new GridLayout(3, 2, 5, 5));


        painelFinanciamento.setPreferredSize(
                new Dimension(450, 100)
        );

        painelFinanciamento.setMaximumSize(
                new Dimension(450, 100)
        );

        painelFinanciamento.setBorder(
                BorderFactory.createTitledBorder("Financiamento")
        );


        // POSSUI ENTRADA

        JCheckBox checkEntrada =
                new JCheckBox("Possui entrada?");

        painelFinanciamento.add(checkEntrada);
        painelFinanciamento.add(new JLabel(""));


        // ENTRADA

        JLabel labelEntrada =
                new JLabel("Entrada:");

        JTextField campoEntrada =
                new JTextField();

        labelEntrada.setVisible(false);
        campoEntrada.setVisible(false);

        painelFinanciamento.add(labelEntrada);
        painelFinanciamento.add(campoEntrada);


        checkEntrada.addActionListener(e -> {

            boolean possuiEntrada = checkEntrada.isSelected();
            labelEntrada.setVisible(possuiEntrada);
            campoEntrada.setVisible(possuiEntrada);

        });


        // PARCELAS

        painelFinanciamento.add(
                new JLabel("Parcelas:")
        );

        JComboBox<Integer> comboParcelas = new JComboBox<>();

        comboParcelas.addItem(12);
        comboParcelas.addItem(24);
        comboParcelas.addItem(36);
        comboParcelas.addItem(48);
        comboParcelas.addItem(60);

        painelFinanciamento.add(comboParcelas);



        // BOTÕES


        JButton botaoCalcular =
                new JButton("CALCULAR");

        JButton botaoLimpar =
                new JButton("LIMPAR");


        JPanel painelBotoes =
                new JPanel(new FlowLayout(FlowLayout.LEFT));


        painelBotoes.add(botaoCalcular);
        painelBotoes.add(botaoLimpar);



        // RESULTADO


        JPanel painelResultado =
                new JPanel(
                        new GridLayout(3, 2, 5, 5));


        painelResultado.setBorder(BorderFactory.createTitledBorder("Resultado")
        );


        JLabel labelValorFinanciado =
                new JLabel("R$ 0,00");

        JLabel labelValorParcela =
                new JLabel("R$ 0,00");

        JLabel labelValorTotal =
                new JLabel("R$ 0,00");


        painelResultado.add(
                new JLabel("Valor financiado:")
        );

        painelResultado.add(
                labelValorFinanciado
        );


        painelResultado.add(
                new JLabel("Valor da parcela:")
        );

        painelResultado.add(labelValorParcela);


        painelResultado.add(new JLabel("Total a pagar:"));

        painelResultado.add(labelValorTotal);


        painelResultado.setVisible(false);



        // PAINEL CENTRAL


        JPanel painelCentro =
                new JPanel();


        painelCentro.setLayout(
                new BoxLayout(painelCentro, BoxLayout.Y_AXIS
                )
        );

        painelCentro.add(painelTipo);
        painelCentro.add(painelUsado);
        painelCentro.add(painelFinanciamento);
        painelCentro.add(painelBotoes);
        painelCentro.add(painelResultado);


        painelPrincipal.add(
                painelCentro,
                BorderLayout.CENTER
        );



        // BOTÃO CALCULAR


        botaoCalcular.addActionListener(e -> {

            try {

                if (campoValor.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(janela, "Informe o valor do veículo."
                    );

                    return;
                }


                double valorVeiculo =
                        Double.parseDouble(campoValor.getText());


                if (valorVeiculo <= 0) {
                    JOptionPane.showMessageDialog(janela, "O valor do veículo deve ser maior que zero."
                    );

                    return;
                }


                double entrada = 0;


                if (checkEntrada.isSelected()) {

                    if (campoEntrada.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(
                                janela,
                                "Informe o valor da entrada."
                        );

                        return;
                    }


                    entrada =
                            Double.parseDouble(
                                    campoEntrada.getText()
                            );


                    if (entrada < 0) {

                        JOptionPane.showMessageDialog(
                                janela,
                                "A entrada não pode ser negativa."
                        );

                        return;
                    }


                    if (entrada >= valorVeiculo) {

                        JOptionPane.showMessageDialog(
                                janela,
                                "A entrada deve ser menor que o valor do veículo."
                        );

                        return;
                    }
                }


                int numeroParcelas =
                        (Integer)
                                comboParcelas
                                        .getSelectedItem();


                // TAXA DE 32%
                double taxa = 0.32;


                // FÓRMULAS

                double valorFinanciado =
                        valorVeiculo - entrada;

                double valorTotal =
                        valorFinanciado *
                                (1 + taxa);

                double valorParcela =
                        valorTotal /
                                numeroParcelas;


                // MOSTRAR RESULTADO

                labelValorFinanciado.setText(
                        String.format(
                                "R$ %.2f",
                                valorFinanciado
                        )
                );


                labelValorParcela.setText(
                        String.format(
                                "R$ %.2f",
                                valorParcela
                        )
                );


                labelValorTotal.setText(
                        String.format(
                                "R$ %.2f",
                                valorTotal
                        )
                );


                painelResultado.setVisible(true);

                janela.revalidate();
                janela.repaint();


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        janela,
                        "Digite somente valores numéricos."
                );

            }

        });


        // ==========================================
        // BOTÃO LIMPAR
        // ==========================================

        botaoLimpar.addActionListener(e -> {

            comboMarca.setSelectedIndex(0);

            campoModelo.setText("");

            comboAno.setSelectedIndex(0);

            campoValor.setText("");


            radioNovo.setSelected(true);

            campoQuilometragem.setText("");
            campoProprietarios.setText("");

            painelUsado.setVisible(false);


            checkEntrada.setSelected(false);

            campoEntrada.setText("");

            labelEntrada.setVisible(false);
            campoEntrada.setVisible(false);


            comboParcelas.setSelectedIndex(0);


            painelResultado.setVisible(false);


            janela.revalidate();
            janela.repaint();

        });


        // ==========================================
        // MOSTRAR JANELA
        // ==========================================

        janela.setVisible(true);
    }
}