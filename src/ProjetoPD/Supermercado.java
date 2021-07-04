package ProjetoPD;

import java.rmi.Naming;

public class Supermercado {
    public static void main(String[] args) {
        Produto p1 = new Produto("S-A", "produto-a", 2);
        Produto p2 = new Produto("S-B", "produto-b", 1);
        Produto p3 = new Produto("S-C", "produto-b", 3);

        try{
            Servidor s = (Servidor) Naming.lookup("ServidorProdutos");
            //ServidorImple s = new ServidorImple();
            s.cadastrarProduto(p1);
            s.cadastrarProduto(p2);
            s.cadastrarProduto(p3);
            System.out.println("==================");
            s.removerProduto(p3);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
