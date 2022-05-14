import java.util.Arrays;

public class Labirinto {
    private int qtdLinhas;
    private int qtdColunas;
    private String Arquivo;
    private char[][] labirinto;

    private Pilha<Cordenada> caminho;
    private Pilha<Fila<Cordenada>> posibilidades;
    private Fila<Cordenada> filaDeAdjacencia;
    private Cordenada CORD2 = null;
    private Cordenada atual = null;
    //private Fila<Cordenada> posAdj;


    public Labirinto(String Arquivo) throws Exception {
        //Pega o arquivo do labirinto
        Arquivo arqLabirinto = new Arquivo(Arquivo);
        //Pega a quantidade de linhas e colunas do labirinto
        //tenho que ter uma copia do labirinto para não perder o labirinto original
        Arquivo arqLabirinto2 = new Arquivo(Arquivo);
        //Pega o indice da primeira linha do labirinto
        int qtdLinhas = arqLabirinto.getUmInt();
        //System.out.println("Linhas: " + qtdLinhas);
        //Pega a segunda Linha do labirinto
        String str = arqLabirinto.getUmaString();
        //System.out.println("LinhaCompleta: " + str);
        //retorna a quantidade de caracteres para realizar as verificações
        int qtdColunas = str.length();
        //System.out.println("Colunas: " + qtdColunas);

        this.qtdLinhas = qtdLinhas;
        this.qtdColunas = qtdColunas;

        /*if((qtdLinhas != qtdColunas) || (qtdLinhas != qtdColunas)){
            System.out.println("O labirinto não é valido");
            return;
        }*/

        GerarMatrizDoLabirinto(arqLabirinto2);
    }


    public void GerarMatrizDoLabirinto(Arquivo LabCop) {
        try{
            //System.out.println("CopiaIndice: " + LabCop.getUmInt() + "\n" + "Segunda Linha: " + LabCop.getUmaString());
            String srt = null;
            //Pego o indice da primeira linha do labirinto
            LabCop.getUmInt();
            //Crio uma matriz para armazenar o labirinto com os dados
            this.labirinto = new char[this.qtdLinhas][this.qtdColunas];
            //System.out.println("Linhas: " + this.qtdLinhas + " Colunas: " + this.qtdColunas );
            //Percorro o labirinto pegando cada linha dele
            //System.out.println(this.labirinto.length);
            //System.out.println("Linhas: " + this.qtdLinhas + " Colunas: " + this.qtdColunas );
            for(int i = 0; i < this.qtdLinhas; i++){
                srt = LabCop.getUmaString();
                //System.out.println("Linha: "+ i + " " + srt);
                //Percorro a linha pegando cada caracter
                for (int j = 0; j < this.qtdColunas; j++){
                    this.labirinto[i][j] = srt.charAt(j);
                    //System.out.println("Linha: "+ i + " " + "Coluna: " + j + this.labirinto[i][j]);
                    Cordenada cor = new Cordenada(i, j);
                   System.out.println(cor + srt);
                }
            }

        }
        catch (Exception erro)
        { }//Não vai dar erro pois o arquivo é valido
    }





    @Override
    public String toString() {
        String str = "Labirinto: \n";

        if(this.labirinto == null){
            str = "Labirinto invalido";
        }
        else{
            for(int i = 0; i < this.qtdLinhas; i++){
                for(int j = 0; j < this.qtdColunas; j++){
                    str += Character.toString(this.labirinto[i][j]);
                }
                //Quebra a linha para não ficar uma linha só
                str += "\n";
            }
        }
        return str;
    }

    //Vendo o que esta sendo retornado nas linhas e colunas
    public int getQtdLinhas() {
        return qtdLinhas;
    }

    public int getQtdColunas() {
        return qtdColunas;
    }

    /* Obtenho o inicio do labirinto percorrendo a matriz e encontrando o char E, por padrão sempre começa em 0-0 e no segundo array na primeira chamada([0])
//    public int[] inicioLabirinto(){
//        int[] volta = {0,0};
//        for (int i = 0; i < labirinto.length; i++){
//            for (int j = 0; j < labirinto[0].length; j++){
//                if (labirinto[i][j] == 'E'){
//                    volta[0] = i;
//                    volta[1] = j;
//                }
//            }
//        }
//        return  volta;
//    }

    //Novamente obtenho o fim do labirinto percorrendo a matriz e encontrando o char S, por padrão sempre começa em 0-0 e no segundo array na primeira chamada([0])
    public int[] fimLabirinto(){
      int[] volta = {0,0};
//        for (int i = 0; i < labirinto.length; i++){
//            for (int j = 0; j < labirinto[0].length; j++){
//                if (labirinto[i][j] == 'S'){
//                    volta[0] = i;
//                    volta[1] = j;
//                }
//            }
//        }
//        return volta ;
}
*/

    //Arrumando os Métodos acima
    public boolean temSaida(){
        for (int i = 0; i < labirinto.length; i++){
            for (int j = 0; j < labirinto.length; j++){
                if (labirinto[i][j] == 'S'){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean temEntrada(){
        for (int i = 0; i < labirinto.length; i++){
            for (int j = 0; j < labirinto.length; j++){
                if(labirinto[i][j] == 'E'){
                    return true;
                }
            }
        }
        return false;
    }

    //Verificar a quantidade de entradas e saidas
    public boolean qtdEntradas(){
        int entradas = 0;
        for (int i = 0; i < labirinto.length; i++){
            for (int j = 0; j < labirinto.length; j++){
                if (labirinto[i][j] == 'E'){
                    entradas++;
                }
            }
        }
        if (entradas != 1){
            return false;
        }
        return true;
    }

    public boolean qtdSaidas() {
        int saidas = 0;
        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto.length; j++) {
                if (labirinto[i][j] == 'S') {
                    saidas++;
                }
            }
        }
        if (saidas != 1) {
            return false;
        }

        return true;
    }

        // Marcar Histórico de passos
        public void passos() {


            // PROVAVEL VERIFICAÇÃO PARA ESPAÇOS EM BRANCO, POSSÍVEL QUE O MÉTODO DEVE CONTER PARAMETRO
            for (int i = 0; i < labirinto.length; i++) {
                for (int j = 0; j < labirinto.length; j++) {
                    if (labirinto[i][j] != 'E' && labirinto[i][j] != 'S' && labirinto[i][j] != '#') {
                        labirinto[i][j] = '*';
                    }
                }
            }

            for (int i = 0; i < labirinto.length-1; i++) {
                for (int j = 0; j < labirinto.length-1; j++) {
                    if (labirinto[i][j] != 'E' && labirinto[i][j] != 'S' && labirinto[i][j] != '#') {
                        labirinto[i][j] = '*';
                    }
                }
            }

        }



    //Traz a loc atual que se encontra no momento
    private char MinhaLocAtual(){
        return labirinto[this.atual.getLinha()][this.atual.getColuna()];
    }

    //Acha a Loc E
    private Cordenada cordE() throws Exception{
        Cordenada cord = null;
        for(int i = 0; i < this.qtdLinhas; i++){
            for(int j = 0; j < this.qtdColunas; j++){
                if(i == 0 || i == this.qtdLinhas-1){
                    //Parede de cima e de baixo
                    if(this.labirinto[i][j] == 'E'){
                        cord = new Cordenada(i,j);

                        // i = Linha = 0 = Linha de cima
                        // j = Coluna = 0 = Coluna da esquerda

                        //this.qtdLinhas = todas as linhas = linha de baixo
                        //this.qtdColunas = todas as colunas = coluna da direita
                    }
                }
                //Parede da esquerda e da direita
                if(j == 0 || j == this.qtdColunas -1){
                    if(this.labirinto[i][j] == 'E'){
                        cord = new Cordenada(i,j);
                    }

                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes");
                    }
                }
            }
        }
         return cord;
    }


    //Acha a Loc S
    private Cordenada cordS() throws Exception{
        Cordenada cord2 = null;
        for(int i = 0; i < this.qtdLinhas; i++){
            for(int j = 0; j < this.qtdColunas; j++){
                if(i == 0 || i == this.qtdLinhas -1){
                    //Parede de cima e de baixo
                    if(this.labirinto[i][j] == 'S'){
                        cord2 = new Cordenada(i,j);

                        // i = Linha = 0 = Linha de cima
                        // j = Coluna = 0 = Coluna da esquerda

                        //this.qtdLinhas = todas as linhas = linha de baixo
                        //this.qtdColunas = todas as colunas = coluna da direita
                    }
                }
                //Parede da esquerda e da direita
                if(j == 0 || j == this.qtdColunas -1){
                    if(this.labirinto[i][j] == 'S'){
                        cord2 = new Cordenada(i,j);
                    }

                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes");
                    }
                }
            }
        }
        return cord2;
    }

    public boolean validarLocalizacao(int colum, int linha) {
        if (colum < 0 || colum >= getQtdColunas() || linha < 0 || linha >= getQtdLinhas()) {
            return false;
        }
        return true;
    }




    public void testLab() throws Exception{
        if(!temSaida()) {
            throw new Exception("Labirinto não tem saida");
        }

        if(!temEntrada()) {
            throw new Exception("Labirinto não tem entrada");
        }

        if(!qtdEntradas()) {
            throw new Exception("Labirinto não tem uma unica entrada");
        }

        if(!qtdSaidas()) {
            throw new Exception("Labirinto não tem uma unica saida");
        }
        //validarLocalizacao(this.qtdColunas, this.qtdLinhas);

        this.caminho = new Pilha<Cordenada>(getQtdLinhas() * getQtdColunas());
        this.posibilidades = new Pilha<Fila<Cordenada>>(getQtdLinhas() * getQtdColunas());

        this.atual = new Cordenada(cordE());
        //this.CORD2 = new Cordenada(cordS());
        System.out.println("Entrada: " + this.atual);
        System.out.println("Saida: " + cordS());

    }

        /*Pilha<Cordenada> caminho = new Pilha<>(this.qtdLinhas * this.qtdColunas);
        Pilha<Fila<Cordenada>> posibilidades = new Pilha<>(this.qtdLinhas * this.qtdColunas);
        Cordenada atual = new Cordenada(0, 0);
        Fila<Cordenada> filaDeAdjacencia = new Fila<>(3);

        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto.length; j++) {
                if (labirinto[i][j] != 'E' && labirinto[i][j] != 'S' && labirinto[i][j] != '#') {
                    caminho.guardeUmItem(new Cordenada(i, j));
                    System.out.println(caminho);
                }
            }
        }*/


    /*
        public boolean passarBranco() throws Exception {
        //atual.getLinha();
        //atual.getColuna();
        //int atual1 = 0;
        for (int i = 0; i < labirinto.length-1; i++) {
            for (int j = 0; j < labirinto.length-1; j++) {
                if (labirinto[i][j] == 'E'){
                    Cordenada atual = new Cordenada(i, j);
                    Fila<Cordenada> filaDeAdjacencia = new Fila<>(3);

                    labirinto[i][j+1] = '*';
                    labirinto[i+1][j+1] = '*';
                    //labirinto[j+1][i+1] = '*';
                    //atual1 = labirinto[i][j];
                    //atual1++;
                    //System.out.println(atual1);
                    System.out.println("pos: " + atual);
                }
            }
        }
        return false;
    }
     */


    @Override
    public int hashCode(){
        int ret = 9;
        ret = ret*7 + Integer.valueOf(this.qtdColunas).hashCode();
        ret = ret*7 + Integer.valueOf(this.qtdLinhas).hashCode();
        // ret = ret*7 + Character.valueOf((char) this.labirinto.length).hashCode();

        for(int i = 0; i < this.qtdLinhas; i++){
            for(int j = 0; j < this.qtdColunas; j++){
                ret = ret * 7 + new Character(this.labirinto[i][j]).hashCode();
            }
        }
        if (ret<0)
            ret=-ret;

        return ret;
    }


    // CONSTRUTOR DE CÓPIA
        public Labirinto (Labirinto lab) throws Exception{
            if (lab == null){
                throw new Exception("Arquivo nulo");
            }
            this.qtdLinhas = lab.qtdLinhas;
            this.qtdColunas = lab.qtdColunas;


            // VERIFICAR
            // this.labirinto = new char[lab.labirinto.length][];
            this.labirinto = lab.labirinto;

    }
}

