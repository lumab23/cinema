public class ControladorFilmes {
    public boolean adicionarFilme(String nome, String genero, String sinopse, String diretor, int duracaoMin) {
        Filme filme = new Filme(nome, genero, sinopse, diretor, duracaoMin);
        GerenteDB db = new GerenteDB();
        return db.salvarFilme(filme);
    }
}