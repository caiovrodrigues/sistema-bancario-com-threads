import java.text.NumberFormat;

public abstract class Conta {

    private String nome;
    private Double saldo;

    public Conta(String nome, Double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void depositar(Double valor){
        saldo += valor;
    }

    public void descontar(Double valor){
        saldo -= valor;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "nome='" + nome + '\'' +
                ", saldo=" + NumberFormat.getCurrencyInstance().format(saldo) +
                '}';
    }
}
