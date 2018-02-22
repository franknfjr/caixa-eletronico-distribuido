package servidor;

public class Conta {

    private int conta;
    private double saldo;
    private double saque;
    private double limite;
    private String nome;

    public Conta() {
    }

    public Conta(String nome, int conta) {
        this.conta = conta;
        this.nome = nome;
        saldo = 0;
    }

    public void extrato() {
        System.out.println("### AZBANK_EXTRATO ###");
        System.out.println("Nome: " + this.nome);
        System.out.println("Número da conta: " + this.conta);
        System.out.printf("Saldo atual: %.2f\n", this.saldo);
    }

    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            saque++;
            return true;
        }
        return false;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean transferir(double valor, Conta conta) {
        if (valor > 0) {
            this.sacar(valor);
            conta.depositar(valor);
            return true;
        }
        return false;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaque() {
        return saque;
    }

    public void setSaque(double saque) {
        this.saque = saque;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
