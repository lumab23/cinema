public class Venda {
    private int qtdIngressos;
    private float precoTotal;
    private Cliente cliente;
    private Sessao sessao;
    private Pagamento pagamento;

    public Venda(Sessao sessao, Cliente cliente, int qtdIngressos) {
        this.sessao = sessao;
        this.cliente = cliente;
        this.qtdIngressos = qtdIngressos;
        this.precoTotal = qtdIngressos * 10.0f; 
    }

    public float calcularPreco() {
        return this.precoTotal;
    }

    public void efetuarPagamento() {
        if (pagamento != null) {
            pagamento.precoTotal = this.precoTotal;
            pagamento.processar();
        }
    }

    public int getQtdIngressos() {
        return qtdIngressos;
    }

    public void setQtdIngressos(int qtdIngressos) {
        this.qtdIngressos = qtdIngressos;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}