public class Sala {
    private int numero;
    private boolean[][] assentos;

    public Sala(int numero, boolean[][] assentos) {
        this.numero = numero;
        this.assentos = assentos;
    }

    public int getAssentosDisponiveis() {
        return 0;
    }

    public boolean isAssentoDisponivel(int fileira, int coluna) {
        return assentos[fileira][coluna];
    }

    public boolean reservarAssento(int fileira, int coluna) {
        return true;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean[][] getAssentos() {
        return assentos;
    }

    public void setAssentos(boolean[][] assentos) {
        this.assentos = assentos;
    }

    
}