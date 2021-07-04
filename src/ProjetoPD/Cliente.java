package ProjetoPD;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        List<String> produtos = new ArrayList<>();

        try{
            Servidor s = (Servidor) Naming.lookup("ServidorProdutos");
            //Adicionando produtos a lista
            produtos.add("produto-b");
            produtos.add("produto-a");
            //Imprime o resultado
            System.out.println(s.consultarProdutos(produtos));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
