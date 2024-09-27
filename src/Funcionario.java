public class Funcionario extends Thread{

    private String nome;
    private Double balance;

    public Funcionario(String nome) {
        this.nome = nome;
        this.balance = 0.0;
    }

    @Override
    public void run() {
        synchronized (balance){
            while(!pagamentoRecebido()){
                try {
                    balance.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(nome + " recebeu o pagamento; balance = " + balance);
        }
    }

    public boolean pagamentoRecebido(){
        return balance.equals(1400.0);
    }

    public void receberPagamento(Double valor){
        synchronized (balance){
            balance += valor;
            balance.notify();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", balance=" + balance +
                '}';
    }
}
