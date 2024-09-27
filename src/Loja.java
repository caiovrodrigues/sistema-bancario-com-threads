public class Loja {

    private String nome;
    private Double balance;
    private Funcionario funcionario;

    public Loja(String nome, Funcionario funcionario){
        balance = 0.0;
        this.nome = nome;
        this.funcionario = funcionario;
    }

    public void realizarVenda(Double valorCompra){
        synchronized (balance){
            balance += valorCompra;
            if(balance >= 1400 && !funcionario.pagamentoRecebido()){
                funcionario.receberPagamento(1400.0);
                balance -= 1400;
            }
        }
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Loja{" +
                "nome='" + nome + '\'' +
                ", balance=" + balance +
                ", funcionario=" + funcionario +
                '}';
    }
}
