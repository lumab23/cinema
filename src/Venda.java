public class Venda {
    private int qtdIngressos;
    private float precoTotal;
    private Cliente cliente;
    private Sessao sessao;
    private Pagamento pagamento;

    

    public Venda(int qtdIngressos, float precoTotal, Cliente cliente, Sessao sessao, Pagamento pagamento) {
        this.qtdIngressos = qtdIngressos;
        this.precoTotal = precoTotal;
        this.cliente = cliente;
        this.sessao = sessao;
        this.pagamento = pagamento;
    }

    public Venda(Sessao sessao, Cliente cliente, int qtdIngressos) {
        this.sessao = sessao;
        this.cliente = cliente;
        this.qtdIngressos = qtdIngressos;
        this.precoTotal = calcularPreco();
    }

    public float calcularPreco() {
        // Implementação (ex: soma dos preços dos ingressos)
        return precoTotal;
    }

    public void efetuarPagamento() {
        this.pagamento.processar();
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