import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InterfaceEstoque extends JFrame {
    private Estoque estoque;
    private JTextArea areaTexto;

    public InterfaceEstoque(Estoque estoque) {
        this.estoque = estoque;
        setTitle("Gerenciamento de Estoque");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar Produto");
        JButton btnAtualizar = new JButton("Atualizar Produto");
        JButton btnRemover = new JButton("Remover Produto");
        JButton btnSair = new JButton("Sair");
        JButton btnVisualizar = new JButton("Visualizar Produtos");

        areaTexto = new JTextArea(10, 30);
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        btnAdicionar.addActionListener(e -> adicionarProduto());
        btnAtualizar.addActionListener(e -> atualizarProduto());
        btnRemover.addActionListener(e -> removerProduto());
        btnSair.addActionListener(e -> System.exit(0));
        btnVisualizar.addActionListener(e -> visualizarProdutos());

        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnSair);
        panelBotoes.add(btnVisualizar);

        add(panelBotoes, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);


        try {
            BufferedImage logoImage = ImageIO.read(new File("logo.jpg"));
            int newWidth = logoImage.getWidth() / 4;
            int newHeight = logoImage.getHeight() / 4;
            Image scaledLogo = logoImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon logoIcon = new ImageIcon(scaledLogo);

            JLabel logoLabel = new JLabel(logoIcon);
            JPanel panelLogo = new JPanel();
            panelLogo.setLayout(new FlowLayout(FlowLayout.RIGHT));
            panelLogo.add(logoLabel);
            add(panelLogo, BorderLayout.EAST);
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
        }
    }


    private void adicionarProduto() {
        String nomeProduto = JOptionPane.showInputDialog(this, "Digite o nome do produto:");
        if (nomeProduto != null && !nomeProduto.isEmpty()) {
            try {
                int quantidade = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite a quantidade do produto:"));
                double valor = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite o valor do produto:"));

                Produto produto = new Produto(nomeProduto, quantidade, valor);
                boolean sucesso = estoque.adicionarProduto(produto);

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
                    visualizarProdutos();
                } else {
                    JOptionPane.showMessageDialog(this, "Estoque cheio. Não é possível adicionar mais produtos.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade ou valor inválido.");
            }
        }
    }


    private void atualizarProduto() {
        String nomeProduto = JOptionPane.showInputDialog(this, "Digite o nome do produto a ser atualizado:");
        if (nomeProduto != null && !nomeProduto.isEmpty()) {
            Produto produto = estoque.buscarProduto(nomeProduto);
            if (produto != null) {
                try {
                    int novaQuantidade = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite a nova quantidade:"));
                    double novoValor = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite o novo valor:"));
                    produto.setQuantidade(novaQuantidade);
                    produto.setValor(novoValor);
                    JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
                    visualizarProdutos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Valor inválido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            }
        }
    }

    private void removerProduto() {
        String nomeProduto = JOptionPane.showInputDialog(this, "Digite o nome do produto a ser removido:");
        if (nomeProduto != null && !nomeProduto.isEmpty()) {
            boolean sucesso = estoque.removerProduto(nomeProduto);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
                visualizarProdutos();
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            }
        }
    }

    private void visualizarProdutos() {
        areaTexto.setText("");
        for (Produto produto : estoque.getProdutos()) {
            areaTexto.append("Nome: " + produto.getNome() + ", Quantidade: " + produto.getQuantidade() + ", Valor: " + produto.getValor() + "\n");
        }
    }
    public static void main(String[] args) {
        Estoque estoque = new Estoque(100);
        InterfaceEstoque interfaceEstoque = new InterfaceEstoque(estoque);
        interfaceEstoque.setVisible(true);
    }
}