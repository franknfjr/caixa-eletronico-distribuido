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
        System.out.println("Servidor recebeu uma chamada para abrirConta de um Administrador\n");
        try {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    return false;
                }
            }
            Conta novaconta = new Conta(nome, conta);
            contas.add(novaconta);
            novaconta.mostrarAdministradores();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean fecharConta(int conta) throws RemoteException {
        System.out.println("Servidor recebeu uma chamada para fecharConta de um Administrador\n");
        try {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    contas.remove(c);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saque(int conta, double valor) throws RemoteException {
        System.out.println("Servidor recebeu uma chamada para saque de um Administrador\n");
        try {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    return c.sacar(valor);
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean transferir(int conta1, int conta2, double valor) throws RemoteException {
        System.out.println("Servidor recebeu uma chamada para transferir de um Administrador\n");
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean depositar(int conta, double valor) throws RemoteException {
        System.out.println("Servidor recebeu uma chamada para depositar de um Administrador\n");
        try {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    c.depositar(valor);
                    c.extrato();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saldo(int conta) throws RemoteException {
        System.out.println("Servidor recebeu uma chamada para saldo de um Administrador\n");
        try {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    c.extrato();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static MensageiroInterface getInstance() throws RemoteException {
        if (instance == null) {
            instance = new Administrador();
        }
        return instance;
    }

}
