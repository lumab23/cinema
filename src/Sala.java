public class Sala {
    private int numero;
    private boolean[][] assentos;

    public Sala(int numero, boolean[][] assentos) {
        this.numero = numero;
        this.assentos = assentos;
    }

    public void exibirAssentos() {
        System.out.println("Mapa de assentos:");
        int contador = 1; // Contador para numerar os assentos disponíveis
    
        for (int i = 0; i < assentos.length; i++) {
            for (int j = 0; j < assentos[i].length; j++) {
                if (assentos[i][j]) {
                    System.out.printf("%2d ", contador); // Exibe número do assento disponível
                } else {
                    System.out.print(" X "); // Exibe "X" para assentos ocupados
                }
                contador++;
            }
            System.out.println(); // Nova linha após cada fileira
        }
    }

    public int getAssentosDisponiveis() {
        int disp = 0;
        for (boolean[] fileira : assentos) {
            for (boolean assento : fileira) {
                if (assento) disp++;
            }
        }
        return disp;
    }

    public boolean isAssentoDisponivel(int fileira, int coluna) {
        return assentos[fileira][coluna];
    }

    public boolean reservarAssento(int fileira, int coluna) {
        if (assentos[fileira][coluna]) {
            assentos[fileira][coluna] = false; // assento ocupado
            return true;
        }
        return false;
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