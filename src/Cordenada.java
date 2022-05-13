public class Cordenada {
    private int x;
    private int y;

    public Cordenada(int Linha, int Coluna) throws Exception{
        if(Linha < 0 || Coluna < 0)
            throw new Exception("Coordenada invalida");

        this.x = Linha;
        this.y = Coluna;
    }

    public int getLinha(){
        return this.x;
    }

    public int getColuna(){
        return this.y;
    }

    @Override
    public String toString() {
        return "Cordenada{" +
                "x=" + this.x +
                ", y=" + this.y +
                '}';
    }
}

