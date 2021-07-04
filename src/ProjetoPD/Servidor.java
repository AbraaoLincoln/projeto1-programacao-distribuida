package ProjetoPD;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface Servidor extends Remote {
    public void cadastrarProduto(Produto produto) throws RemoteException;

    public void cadastrarProdutos(List<Produto> produtos) throws RemoteException;

    public void removerProduto(Produto produto) throws RemoteException;

    public void removerProdutos(List<Produto> produtos) throws RemoteException;

    public Map consultarProdutos(List<String> nomesProdutos) throws RemoteException;
}
