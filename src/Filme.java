public class Filme {
    private String nome;
    private String genero;
    private String sinopse;
    private String diretor;
    private int duracaoMin;

    public Filme(String nome, String genero, String sinopse, String diretor, int duracaoMin) {
        this.nome = nome;
        this.genero = genero;
        this.sinopse = sinopse;
        this.diretor = diretor;
        this.duracaoMin = duracaoMin;
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

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(int duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    

    
}