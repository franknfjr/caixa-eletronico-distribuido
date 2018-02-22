package cliente;

import interfaces.MensageiroInterface;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import servidor.Conta;

public class Main {

    private static void exibirMenu() throws IOException {
        System.out.println("1 - Abrir uma Conta");
        System.out.println("2 - Fechar a Conta");
        System.out.println("3 - Depositar dinheiro na Conta");
        System.out.println("4 - Sacar dinheiro");
        System.out.println("5 - Transferir de uma Conta para outra");
        System.out.println("6 - Saldo na tela");
        System.out.println("0 - Sair");
        System.out.println("Opção: ");
        
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, IOException {
        Registry registry = LocateRegistry.getRegistry(4000);
        MensageiroInterface administrador = (MensageiroInterface) registry.lookup("administrador");
        Scanner scanner = new Scanner(System.in);
        String nome;
        int conta, conta1, conta2;
        double valor;
        String op = "1";
        while (!op.equals("0")) {
            exibirMenu();
            op = scanner.next();
            switch (op) {
                case "1":
                    System.out.println("### Abrir Conta ###");
                    System.out.println("Informe seu nome");
                    nome = scanner.next();
                    System.out.println("Informe a conta");
                    conta = scanner.nextInt();
                    if (administrador.abrirConta(nome, conta)) {
                        System.out.println("Conta criada com sucesso!!!");
                    } else {
                        System.out.println("Já existe uma conta com esse número.");
                    }
                    break;

                case "2":
                    System.out.println("### Fechar Conta ###");
                    System.out.println("Informe a conta");
                    conta = scanner.nextInt();
                    if (administrador.fecharConta(conta)) {
                        System.out.println("Conta fechada com sucesso");
                    } else {
                        System.out.println("Não existe nenhuma conta com esse número");
                    }
                    break;

                case "3":
                    System.out.println("### Depositar dinheiro na Conta ###");
                    System.out.println("Informe a conta");
                    conta = scanner.nextInt();
                    System.out.println("Qual o valor a ser depositado?");
                    valor = scanner.nextInt();
                    if (administrador.depositar(conta, valor)) {
                        System.out.println("Depósito realizado com sucesso");
                    } else {
                        System.out.println("Não existe nenhuma conta para depósito. \n Tente outra conta");
                    }
                    break;

                case "4":
                    System.out.println("### Sacar dinheiro ###");
                    System.out.println("Informe a conta");
                    conta = scanner.nextInt();
                    System.out.println("Qual o valor a ser sacado?");
                    valor = scanner.nextInt();
                    if (administrador.saque(conta, valor)) {
                        System.out.println("Saque realizado com sucesso");
                    } else {
                        System.out.println("Não existe nenhuma conta para depósito. \n Tente outra conta");
                    }
                    break;

                case "5":
                    System.out.println("### Transferir de uma Conta para outra ###");
                    System.out.println("Informe a conta que será retirado o dinheiro: ");
                    conta1 = scanner.nextInt();
                    System.out.println("Informe a conta para transferencia: ");
                    conta2 = scanner.nextInt();
                    System.out.println("Informe o valor a ser transferido: ");
                    valor = scanner.nextInt();
                    if (administrador.transferir(conta1, conta2, valor)) {
                        System.out.println("Transferência realizada com sucesso");
                    } else {
                        System.out.println("Não existe nenhuma conta para depósito. \n Tente outra conta");
                    }
                    break;

                case "6":
                    System.out.println("### Saldo na tela ###");
                    System.out.println("Informe a conta");
                    conta = scanner.nextInt();
                    if (administrador.saldo(conta)) {
                        System.out.println("aguardando a impressão no servidor. . . ");
                    } else {
                        System.out.println("Não existe nenhuma conta para saldo. \n Tente outra conta");
                    }
                    break;
            }
        }
        System.out.println("Sistema encerrado . . . ");
    }
}
