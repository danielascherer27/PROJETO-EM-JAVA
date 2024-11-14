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
            BufferedImage logoImage = ImageIO.read(new File("logo.png"));
            int newWidth = logoImage.getWidth() / 4;
            int newHeight = logoImage.getHeight() / 4;
            Image scaledLogo = logoImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon logoIcon = new ImageIcon(scaledLogo);

            JLabel logoLabel = new JLabel(logoIcon);
            JPanel panelLogo = new JPanel();
            panelLogo.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Posiciona a logo à direita
            panelLogo.add(logoLabel);
            add(panelLogo, BorderLayout.EAST);
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
        }
    }


