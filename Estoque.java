import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;
    private List<HistoricoAlteracao> historico;
    private int capacidadeMaxima;

    public Estoque(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.produtos = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<HistoricoAlteracao> getHistorico() {
        return historico;
    }

    public boolean adicionarProduto(Produto produto) {
        if (produtos.size() < capacidadeMaxima) {
            produtos.add(produto);
            adicionarHistorico("Adição", produto.getNome(), "Produto adicionado ao estoque");
            return true;
        }
        return false;
    }

    public boolean atualizarProduto(String nomeProduto, Produto novoProduto) {
        Produto produtoExistente = buscarProduto(nomeProduto);
        if (produtoExistente != null) {
            int index = produtos.indexOf(produtoExistente);
            produtos.set(index, novoProduto);
            adicionarHistorico("Atualização", nomeProduto, "Produto atualizado no estoque");
            return true;
        }
        return false;
    }

    public boolean removerProduto(String nomeProduto) {
        Produto produto = buscarProduto(nomeProduto);
        if (produto != null) {
            produtos.remove(produto);
            adicionarHistorico("Remoção", produto.getNome(), "Produto removido do estoque");
            return true;
        }
        return false;
    }

    public Produto buscarProduto(String nomeProduto) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }

    public void adicionarHistorico(String operacao, String nomeProduto, String descricao) {
        historico.add(new HistoricoAlteracao(operacao, nomeProduto, descricao));
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }
}
