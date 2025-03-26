public class Sala {

    private int numero;
    private boolean assentos[][];
    private int assentosDisponiveis;
    
    public Sala(int numero, boolean[][] assentos, int assentosDisponiveis) {
        this.numero = numero;
        this.assentos = assentos;
        this.assentosDisponiveis = assentosDisponiveis;
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

    public int getAssentosDisponiveis() {
        int count = 0;
        for (boolean[] linha : assentos) {
            for (boolean assento : linha) {
                if (!assento) count++; 
            }
        }

        return count;
    }


    // método para imprimir a matriz de assentos
    public void imprimirAssentos() {
        for (boolean[] linha : assentos) {
            for (boolean assento : linha) {
                System.out.print(assento ? "O " : "X ");
            }
            System.out.println();
        }
    }

    public void setAssentosDisponiveis(int assentosDisponiveis) {
        this.assentosDisponiveis = assentosDisponiveis;
    }


}
