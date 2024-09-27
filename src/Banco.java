public class Banco {

    private String nome;
    private static final Object transacaoLock = new Object();

    public static void realizarTransacaoClienteParaLoja(Loja loja, Cliente cliente, Double valorCompra){
        synchronized (transacaoLock){
            loja.realizarVenda(valorCompra);
            cliente.diminuirSaldo(valorCompra);
        }
    }

    public static void realizarTransacaoLojaParaFuncionario(Loja loja, Funcionario funcionario, Double valorPagamento){
        synchronized (transacaoLock){
            loja.diminuirSaldo(valorPagamento);
            funcionario.receberPagamento(valorPagamento);
        }
    }

}
