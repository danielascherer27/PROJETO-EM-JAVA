import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;
    private int capacidadeMaxima;

    public Estoque(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.produtos = new ArrayList<>();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public boolean adicionarProduto(Produto produto) {
        if (produtos.size() < capacidadeMaxima) {
            produtos.add(produto);
            return true;
        }
        return false;
    }

    public boolean removerProduto(String nomeProduto) {
        return produtos.removeIf(produto -> produto.getNome().equalsIgnoreCase(nomeProduto));
    }

    public Produto buscarProduto(String nomeProduto) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }
}