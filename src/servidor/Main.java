package servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(4000);

        registry.rebind("administrador", Administrador.getInstance());
        System.out.println("Servidor.Main: Esperando ações do cliente . . .");
    }
}
