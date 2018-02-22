package servidor;

import interfaces.MensageiroInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public final class Administrador extends UnicastRemoteObject implements MensageiroInterface {

    private static MensageiroInterface instance = null;
    private static final long serialVersionUID = 1L;
    private List<Conta> contas;

    private Administrador() throws RemoteException {
        this.contas = new ArrayList<Conta>();
    }

    @Override
    public boolean abrirConta(String nome, int conta) throws RemoteException {
        for (Conta c : contas) {
            if (c.getConta() == conta) {
                return false;
            }
        }
        Conta novaconta = new Conta(nome, conta);
        contas.add(novaconta);
        return true;
    }

    @Override
    public boolean fecharConta(int conta) throws RemoteException {
        for (Conta c : contas) {
            if (c.getConta() == conta) {
                contas.remove(c);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean saque(int conta, double valor) throws RemoteException {
        for (Conta c : contas) {
            if (c.getConta() == conta) {
                return c.sacar(valor);
            }
        }
        return false;
    }

    @Override
    public boolean transferir(int conta1, int conta2, double valor) throws RemoteException {
        for (Conta c : contas) {
            if (c.getConta() == conta1) {
                for (Conta c2 : contas) {
                    if (c2.getConta() == conta2) {
                        return c.transferir(valor, c2);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean depositar(int conta, double valor) throws RemoteException {
        for (Conta c : contas) {
            if (c.getConta() == conta) {
                c.depositar(valor);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean saldo(int conta) throws RemoteException {
        for (Conta c : contas) {
            if (c.getConta() == conta) {
                c.extrato();
                return true;
            }
        }
        return false;
    }

    public static MensageiroInterface getInstance() throws RemoteException {
        if (instance == null) {
            instance = new Administrador();
        }
        return instance;
    }

}
