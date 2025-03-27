import java.time.LocalDateTime;

public class Sessao {
    private Sala sala;
    private Filme filme;
    private LocalDateTime horario;
    private int ingressosVendidos;
    private int capacidadeMaxima;

    public Sessao(Sala sala, Filme filme, LocalDateTime horario, int ingressosVendidos, int capacidadeMaxima) {
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
        this.ingressosVendidos = ingressosVendidos;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getIngressosDisponiveis() {
        return capacidadeMaxima - ingressosVendidos;
    }

    public boolean reservarIngressos(int qtd) {
        if (getIngressosDisponiveis() >= qtd) {
            ingressosVendidos += qtd;
            return true;
        }
        return false;
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

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public int getIngressosVendidos() {
        return ingressosVendidos;
    }

    public void setIngressosVendidos(int ingressosVendidos) {
        this.ingressosVendidos = ingressosVendidos;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }
}