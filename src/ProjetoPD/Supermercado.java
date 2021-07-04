package ProjetoPD;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Supermercado {
    public static void main(String[] args) {

        try{
            //Servidor s = (Servidor) Naming.lookup("ServidorProdutos");
            ServidorImple s = new ServidorImple();
            //Supermercado.cadrastraProdutos(s);
            Scanner sc = new Scanner(System.in);
            String input = "0";
            float preco = 0;
            System.out.println("==============================");
            System.out.println(" =   Cadastro de Produtos   =");
            System.out.println("==============================");
            System.out.println("0 - Para finalizar o programa");
            do {
                Produto produtoParaCadastrar = new Produto("", "", 0);

                System.out.println("Digite o nome do supermercado:");
                input = sc.nextLine();
                produtoParaCadastrar.setSupermercado(input);

                System.out.println("Digite o nome do produto:");
                input = sc.nextLine();
                produtoParaCadastrar.setNome(input);

                System.out.println("Digite o preco do produto:");
                preco = sc.nextFloat();
                produtoParaCadastrar.setPreco(preco);

                sc.nextLine();

                s.cadastrarProduto(produtoParaCadastrar);
                System.out.println("Produto cadastrado com sucesso!");

                System.out.println("Deseja adiconar mais produtos ?");
                System.out.println("0 - nao | 1 - sim");
                input = sc.nextLine();
            }while (!input.equals("0"));

            System.out.println("Programa finalizado");
        }catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error ao cadastrar o produto!");
        }
    }

    public static void cadrastraProdutos(Servidor s) {
        Produto p1 = new Produto("S-A", "produto-a", 2);
        Produto p2 = new Produto("S-B", "produto-b", 1);
        Produto p3 = new Produto("S-C", "produto-b", 3);

        try {
            s.cadastrarProduto(p1);
            s.cadastrarProduto(p2);
            s.cadastrarProduto(p3);
            System.out.println("==================");
            //s.removerProduto(p3);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
