import java.time.LocalTime;

public class Sessao {

    Sala sala;
    Filme filme;
    LocalTime horario;
    

    public Sessao(Sala sala, Filme filme, LocalTime horario, Ingresso ingresso) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public Filme getFilme() {
        return filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }
    public LocalTime getHorario() {
        return horario;
    }
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }


    

}
