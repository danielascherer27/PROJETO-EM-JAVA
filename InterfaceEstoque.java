import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDateTime;

public class InterfaceEstoque extends JFrame {
    private Estoque estoque;
    private JTextArea areaTexto;
    private JTextArea areaHistorico;
    private JTextArea areaResultadoOtimizacao;

    public InterfaceEstoque(Estoque estoque) {
        this.estoque = estoque;

        // Configuração básica da janela
        setTitle("Gerenciamento de Estoque");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        painelPrincipal.setBackground(new Color(200, 230, 255)); // Azul claro
        add(painelPrincipal);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Painel de botões
        JPanel painelBotoes = criarPainelBotoes();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        painelPrincipal.add(painelBotoes, gbc);

        // Área de texto para produtos no estoque
        areaTexto = criarAreaTexto("Produtos no Estoque");
        JScrollPane scrollPaneProdutos = new JScrollPane(areaTexto);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.9;
        painelPrincipal.add(scrollPaneProdutos, gbc);

        // Área de histórico de alterações
        areaHistorico = criarAreaTexto("Histórico de Alterações");
        JScrollPane scrollPaneHistorico = new JScrollPane(areaHistorico);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        painelPrincipal.add(scrollPaneHistorico, gbc);

        // Área de resultado da otimização
        areaResultadoOtimizacao = criarAreaTexto("Resultado da Otimização");
        JScrollPane scrollPaneResultado = new JScrollPane(areaResultadoOtimizacao);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        painelPrincipal.add(scrollPaneResultado, gbc);
    }

    private JPanel criarPainelBotoes() {
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.setBackground(new Color(100, 149, 237)); // Azul cadete
        painelBotoes.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180)), // Azul aço
                "Ações",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
        ));

        JButton btnAdicionar = criarBotao("Adicionar", "Adicionar Produto");
        JButton btnAtualizar = criarBotao("Atualizar", "Atualizar Produto");
        JButton btnRemover = criarBotao("Remover", "Remover Produto");
        JButton btnSair = criarBotao("Sair", "Encerrar Sistema");
        JButton btnOtimizar = criarBotao("Otimizar", "Otimizar Mochila");

        btnAdicionar.addActionListener(e -> adicionarProduto());
        btnAtualizar.addActionListener(e -> atualizarProduto());
        btnRemover.addActionListener(e -> removerProduto());
        btnSair.addActionListener(e -> System.exit(0));
        btnOtimizar.addActionListener(e -> otimizarMochila());

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnOtimizar);
        painelBotoes.add(btnSair);

        return painelBotoes;
    }

    private JButton criarBotao(String texto, String tooltip) {
        JButton botao = new JButton(texto);
        botao.setToolTipText(tooltip);
        botao.setFont(new Font("Arial", Font.PLAIN, 14));
        botao.setBackground(new Color(70, 130, 180)); // Azul aço
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return botao;
    }

    private JTextArea criarAreaTexto(String titulo) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180)), // Azul aço
                titulo,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
        ));
        area.setBackground(new Color(240, 248, 255)); // Azul Alice
        area.setForeground(Color.BLACK);
        return area;
    }

    // Métodos de ações dos botões
    private void adicionarProduto() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do produto:");
        if (nome != null && !nome.isEmpty()) {
            double comprimento = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite o comprimento do produto (cm):"));
            double altura = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite a altura do produto (cm):"));
            double largura = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite a largura do produto (cm):"));
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite a quantidade do produto:"));
            double valor = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite o valor do produto:"));

            Produto produto = new Produto(nome, comprimento, altura, largura, quantidade, valor);
            if (estoque.adicionarProduto(produto)) {
                areaTexto.append(produto.toString() + "\n");
                areaHistorico.append("[" + LocalDateTime.now() + "] Produto adicionado: " + nome + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível adicionar o produto. Estoque cheio.");
            }
        }
    }

    private void atualizarProduto() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do produto a ser atualizado:");
        if (nome != null && !nome.isEmpty()) {
            Produto produto = estoque.buscarProduto(nome);
            if (produto != null) {
                int novaQuantidade = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite a nova quantidade do produto:"));
                produto.setQuantidade(novaQuantidade);
                areaHistorico.append("[" + LocalDateTime.now() + "] Produto atualizado: " + nome + " - Nova quantidade: " + novaQuantidade + "\n");
                areaTexto.append("Produto atualizado: " + nome + " - Nova quantidade: " + novaQuantidade + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            }
        }
    }

    private void removerProduto() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do produto a ser removido:");
        if (nome != null && !nome.isEmpty()) {
            if (estoque.removerProduto(nome)) {
                areaTexto.append("Produto removido: " + nome + "\n");
                areaHistorico.append("[" + LocalDateTime.now() + "] Produto removido: " + nome + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            }
        }
    }

    private void otimizarMochila() {
        // Aqui você pode implementar a lógica de otimização da mochila
        areaResultadoOtimizacao.append("Otimização da mochila realizada com sucesso.\n");
    }

    public static void main(String[] args) {
        Estoque estoque = new Estoque(100); // Supondo que Estoque tenha um construtor que aceita capacidade
        SwingUtilities.invokeLater(() -> {
            InterfaceEstoque interfaceEstoque = new InterfaceEstoque(estoque);
            interfaceEstoque.setVisible(true);
        });
    }
}
