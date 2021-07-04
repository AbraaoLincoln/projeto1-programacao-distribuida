package ProjetoPD;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try{
            //Servidor s = (Servidor) Naming.lookup("ServidorProdutos");
            ServidorImple s = new ServidorImple();
            Supermercado.cadrastraProdutos(s);
            Scanner sc = new Scanner(System.in);
            String input = "0";

            System.out.println("==============================");
            System.out.println(" =   Consulta de Produtos   = ");
            System.out.println("==============================");
            System.out.println("0 - Para finalizar o programa");

            do {
                List<String> produtos = new ArrayList<>();
                System.out.println("Digite a lista de produtos:");
                input = sc.nextLine();

                for(String produto : input.split(",")) {
                    produtos.add(produto);
                }
                Map<String, ArrayList<Produto>> resultadoConsulta = s.consultarProdutos(produtos);
                Cliente.showResultado(produtos, resultadoConsulta);

                System.out.println("Deseja adiconar mais produtos ?");
                System.out.println("0 - nao | 1 - sim");
                input = sc.nextLine();
            }while (!input.equals("0"));

            System.out.println("Programa finalizado");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showResultado(List<String> produtos, Map<String, ArrayList<Produto>> resultado) {
        System.out.println("Produto | Melhor Preco | Supermercados Consultado");
        for(String nomeProduto : produtos) {
            List<Produto> listaProdutos = resultado.get(nomeProduto);
            System.out.print(nomeProduto + " ");
            System.out.print(listaProdutos.get(0).getPreco() + " ");

            for(Produto p : listaProdutos) {
                System.out.print(p.getSupermercado() + ", ");
            }

            System.out.println();
        }
    }

    public static void consultarProdutos(Servidor s) {
        List<String> produtos = new ArrayList<>();
        //Adicionando produtos a lista
        produtos.add("produto-b");
        produtos.add("produto-a");

        try {
            //Imprime o resultado
            System.out.println(s.consultarProdutos(produtos));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
