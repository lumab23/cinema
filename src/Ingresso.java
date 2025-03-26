public class Ingresso {
    float preco;
    Sessao sessao;
    private boolean meiaEntrada;

    public Ingresso(Sessao sessao) {
        if (meiaEntrada == true) {
            this.preco = (float) (28.0 / 2);
        } else {
            this.preco = (float) 28.0;
        }
        this.sessao = sessao;
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

    
}
