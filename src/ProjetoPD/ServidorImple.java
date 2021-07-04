package ProjetoPD;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ServidorImple extends UnicastRemoteObject implements Servidor {
    private HashMap<String, TreeMap<Float, ArrayList<Produto>>> produtos;

    protected ServidorImple() throws RemoteException {
        produtos = new HashMap<>();
    }

    @Override
    public void cadastrarProduto(Produto produto) throws RemoteException {
        if(produtos.containsKey(produto.getNome())) {
            TreeMap<Float, ArrayList<Produto>> produtosPorPreco = produtos.get(produto.getNome());
            if(!produtosPorPreco.containsKey(produto.getPreco())) {
                produtosPorPreco.put(produto.getPreco(), new ArrayList<>());
            }
            produtosPorPreco.get(produto.getPreco()).add(produto);
        }else {
            TreeMap<Float, ArrayList<Produto>> produtosPorPreco = new TreeMap<>();
            produtosPorPreco.put(produto.getPreco(), new ArrayList<>());
            produtosPorPreco.get(produto.getPreco()).add(produto);
            produtos.put(produto.getNome(), produtosPorPreco);
        }

        //System.out.println(produtos);
    }

    @Override
    public void cadastrarProdutos(List<Produto> produtos) throws RemoteException {

    }

    @Override
    public void removerProduto(Produto produto) throws RemoteException {
        if(produtos.containsKey(produto.getNome())) {

            if(!produtos.get(produto.getNome()).get(produto.getPreco()).isEmpty()) {
                produtos.get(produto.getNome()).get(produto.getPreco()).remove(produto);
            }

            if(produtos.get(produto.getNome()).get(produto.getPreco()).isEmpty()) {
                produtos.get(produto.getNome()).remove(produto.getPreco());
            }

            if(produtos.get(produto.getNome()).isEmpty()) {
                produtos.remove(produto.getNome());
            }
        }
        //System.out.println(produtos);
    }

    @Override
    public void removerProdutos(List<Produto> produtos) throws RemoteException {

    }

    @Override
    public Map consultarProdutos(List<String> nomesProdutos) throws RemoteException {
        HashMap<String, ArrayList<Produto>> produtosEncontrados = new HashMap<>();

        for(String nomeProduto : nomesProdutos) {
            if(produtos.containsKey(nomeProduto)) {
                ArrayList<Produto> listaProdutosEncontrados = new ArrayList<>();
                for(Float preco : produtos.get(nomeProduto).keySet()) {
                    listaProdutosEncontrados.addAll(produtos.get(nomeProduto).get(preco));
                }
                produtosEncontrados.put(nomeProduto, listaProdutosEncontrados);
            }
        }

        return produtosEncontrados;
    }

    public static void main(String[] args) {
        try {
            Servidor s = new ServidorImple();
            Naming.rebind("ServidorProdutos", s);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
