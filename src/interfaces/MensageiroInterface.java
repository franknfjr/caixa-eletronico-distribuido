package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MensageiroInterface extends Remote {

    boolean abrirConta(String nome, int conta) throws RemoteException;
    boolean fecharConta(int conta) throws RemoteException;
    boolean saque(int conta, double valor) throws RemoteException;
    boolean saldo(int conta) throws RemoteException;
    boolean transferir(int conta1, int conta2, double valor) throws RemoteException;
    boolean depositar(int conta, double valor) throws RemoteException;
}
