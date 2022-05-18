public class Cordenada {
    private int x;
    private int y;

    public Cordenada(int Linha, int Coluna) throws Exception {
        if (Linha < 0 || Coluna < 0)
            throw new Exception("Coordenada invalida");

        this.x = Linha;
        this.y = Coluna;
    }

    //Retornar a linha da cordenada
    public int getLinha() {
        return this.x;
    }

    //Retornar a coluna da cordenada
    public int getColuna() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Cordenada(" +
                "x=" + this.x +
                ",y=" + this.y +
                ')';
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Cordenada cord = (Cordenada) obj;

        if (this.x != cord.x)
            return false;

        return this.y == cord.y;

    }


    public int hashCode() {
        int ret = 9;
        ret = ret * 7 + Integer.valueOf(this.x).hashCode();
        ret = ret * 7 + Integer.valueOf(this.y).hashCode();

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    // construtor de copia
    public Cordenada(Cordenada modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        this.x = modelo.x;
        this.y = modelo.y;
    }

    @Override
    public Object clone() {
        Cordenada ret = null;

        try {
            ret = new Cordenada(this);
        } catch (Exception erro) {
        }

        return ret;
    }

}

