public class Filme {

    private String nome;
    private String genero;
    private int duracaoMin;
    private String sinopse;
    private String diretor;
    private int classificacao;

    public Filme(String nome, String genero, int duracaoMin, String sinopse, String diretor, int classificacao) {
        this.nome = nome;
        this.genero = genero;
        this.duracaoMin = duracaoMin;
        this.sinopse = sinopse;
        this.diretor = diretor;
        this.classificacao = classificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(int duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    
    



}
