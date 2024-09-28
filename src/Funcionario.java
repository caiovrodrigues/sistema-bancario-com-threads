import java.text.NumberFormat;

public class Funcionario extends Thread{

    private String nome;
    private Conta contaSalario;
    private Conta contaInvestimento;
    private final Object salarioLock = new Object();

    public Funcionario(String nome) {
        this.nome = nome;
        this.contaSalario = new ContaSalario();
        this.contaInvestimento = new ContaInvestimento();
    }

    @Override
    public void run() {
        synchronized (salarioLock){
            while(!pagamentoRecebido()){
                try {
                    System.out.println(nome + " ainda não recebeu o pagamento, vou dormir... saldo = " + getSaldo());
                    salarioLock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(nome + " recebeu o pagamento; saldo = " + getSaldo());
        }
    }

    public boolean pagamentoRecebido(){
        return getSaldo() == 1400;
    }

    public void receberPagamento(Double salario){
        synchronized (salarioLock){
            double valorParaInvestimento = salario * 0.2;
            double salarioLiquido = salario * 0.8;
            contaInvestimento.depositar(valorParaInvestimento);
            contaSalario.depositar(salarioLiquido);
            salarioLock.notifyAll();
        }
    }

    public String getNome() {
        return nome;
    }

    public Double getSaldo() {
        return contaSalario.getSaldo() + contaInvestimento.getSaldo();
    }

    @Override
    public String toString() {
        return "%-20s%-27s%-30s%-13s%n".formatted(nome, NumberFormat.getCurrencyInstance().format(contaSalario.getSaldo()), NumberFormat.getCurrencyInstance().format(contaInvestimento.getSaldo()), NumberFormat.getCurrencyInstance().format(getSaldo()));
    }
}
