public class HistoricoCompra {

    private Cliente cliente;
    private Loja loja;
    private Double valor;

    public HistoricoCompra(Cliente cliente, Loja loja, Double valor) {
        this.cliente = cliente;
        this.loja = loja;
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Loja getLoja() {
        return loja;
    }

    public Double getValor() {
        return valor;
    }
}
