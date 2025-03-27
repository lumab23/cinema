public class Ingresso {
    private float preco;
    private Sessao sessao;
    private TipoIngresso tipo;
    private String numeroCadeira;

    

    public Ingresso(float preco, Sessao sessao, TipoIngresso tipo, String numeroCadeira) {
        this.preco = preco;
        this.sessao = sessao;
        this.tipo = tipo;
        this.numeroCadeira = numeroCadeira;
    }

    public float calcularPreco() {
        // Implementação (ex: baseado no tipo e sessão)
        return preco;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngresso tipo) {
        this.tipo = tipo;
    }

    public String getNumeroCadeira() {
        return numeroCadeira;
    }

    public void setNumeroCadeira(String numeroCadeira) {
        this.numeroCadeira = numeroCadeira;
    }

    
}