import java.util.Arrays;

public class Labirinto implements Cloneable {
    private int qtdLinhas;
    private int qtdColunas;
    private String Arquivo;
    private char[][] labirinto;

    private Pilha<Cordenada> caminho;
    private Pilha<Fila<Cordenada>> posibilidades;
    private Fila<Cordenada> filaDeAdjacencia;
    //private Cordenada CORD2 = null;
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

        for(int i = 0; i < this.qtdLinhas; i++) {
            for (int j = 0; j < this.qtdColunas; j++) {
                if(this.qtdLinhas != this.qtdColunas){;
                    //System.out.println("O labirinto não é valido");
                    throw new Exception("O labirinto não é valido");
                }
            }
        }

        GerarMatrizDoLabirinto(arqLabirinto2);
    }
//Teste1.txt
    //C:\Users\Cristian\Desktop\C0tuca\Terceiro Semestre\TCC\Labirintos corretos para teste\

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
                   //System.out.println(cor + srt);
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
        for (int i = 0; i < this.qtdLinhas; i++) {
            for (int j = 0; j < this.qtdColunas; j++) {
                if (labirinto[i][j] == 'S'){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean temEntrada(){
        for (int i = 0; i < this.qtdLinhas; i++) {
            for (int j = 0; j < this.qtdColunas; j++) {
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
        for (int i = 0; i < this.qtdLinhas; i++) {
            for (int j = 0; j < this.qtdColunas; j++) {
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
        for (int i = 0; i < this.qtdLinhas; i++) {
            for (int j = 0; j < this.qtdColunas; j++) {
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

    public boolean carterDiferente(){
        for (int i = 0; i < this.qtdLinhas; i++) {
            for (int j = 0; j < this.qtdColunas; j++) {
                if (labirinto[i][j] != 'E' && labirinto[i][j] != 'S' && labirinto[i][j] != '#' && labirinto[i][j] != ' '){
                    return false;
                }
            }
        }
        return true;
    }

        // Marcar Histórico de passos
        public void passos() {


            // PROVAVEL VERIFICAÇÃO PARA ESPAÇOS EM BRANCO, POSSÍVEL QUE O MÉTODO DEVE CONTER PARAMETRO
            for (int i = 0; i < this.qtdLinhas; i++) {
                for (int j = 0; j < this.qtdColunas; j++) {
                    if (labirinto[i][j] != 'E' && labirinto[i][j] != 'S' && labirinto[i][j] != '#') {
                        labirinto[i][j] = '*';
                    }
                }
            }

            for (int i = 0; i < this.qtdLinhas -1; i++) {
                for (int j = 0; j < this.qtdColunas -1; j++) {
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
                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes nas bordas");
                    }
                }
                //Parede da esquerda e da direita
                if(j == 0 || j == this.qtdColunas -1){
                    if(this.labirinto[i][j] == 'E'){
                        cord = new Cordenada(i,j);
                    }

                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes nas bordas");
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
                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes nas bordas");
                    }
                }
                //Parede da esquerda e da direita
                if(j == 0 || j == this.qtdColunas -1){
                    if(this.labirinto[i][j] == 'S'){
                        cord2 = new Cordenada(i,j);
                    }

                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes nas bordas");
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
/*
    public void testLab() throws Exception {
        if (!temSaida()) {
            throw new Exception("Labirinto não tem saida");
        }

        this.atual = new Cordenada(cordE());

        if (!temEntrada()) {
            throw new Exception("Labirinto não tem entrada");
        }

        if (!qtdEntradas()) {
            throw new Exception("Labirinto não tem uma unica entrada");
        }

        if (!qtdSaidas()) {
            throw new Exception("Labirinto não tem uma unica saida");
        }
        //validarLocalizacao(this.qtdColunas, this.qtdLinhas);

        if (!carterDiferente()) {
            throw new Exception("Labirinto tem carter diferente de: 'E' -- 'S' -- ' ' ");
        }
        //cordE();


        this.caminho = new Pilha<Cordenada>(getQtdLinhas() * getQtdColunas());
        this.posibilidades = new Pilha<Fila<Cordenada>>(getQtdLinhas() * getQtdColunas());


        System.out.println("Entrada: " + this.atual);
        System.out.println("Saida: " + cordS());
        System.out.println("Cord E: " + cordE());
        System.out.println("Loc Atual: " + MinhaLocAtual());

        CordLabADJ();
        while (MinhaLocAtual() == 'E' && MinhaLocAtual() != 'S') {
            this.atual = this.filaDeAdjacencia.recupereUmItem();
            if (MinhaLocAtual() == ' ' && MinhaLocAtual() != 'S') {
                this.filaDeAdjacencia.removaUmItem();
                this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = '*';
                this.posibilidades.guardeUmItem(this.filaDeAdjacencia);
            }
        }
    }*/

    public void testLab() throws Exception{
        if(!temSaida()) {
            throw new Exception("Labirinto não tem saida");
        }
        this.atual = new Cordenada(cordE());

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

        if(!carterDiferente()){
            throw new Exception("Labirinto tem carter diferente de: 'E' -- 'S' -- ' ' ");
        }
        //cordE();

        this.caminho = new Pilha<Cordenada>(getQtdLinhas() * getQtdColunas());
        this.posibilidades = new Pilha<Fila<Cordenada>>(getQtdLinhas() * getQtdColunas());
        //this.atual = filaDeAdjacencia.recupereUmItem();
        //this.atual = new Cordenada(cordE());
        //this.atual = caminho.recupereUmItem();
        //System.out.println("Atual: " + this.atual);

        while(MinhaLocAtual() != 'S'){
            CordLabADJ();
            /*while(this.filaDeAdjacencia.isVazia()){
                this.atual = caminho.recupereUmItem();
                this.caminho.removaUmItem();
                this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = ' ';
                this.posibilidades.removaUmItem();
            }*/
            this.atual = this.filaDeAdjacencia.recupereUmItem();
            if(MinhaLocAtual() != 'S'){
                this.filaDeAdjacencia.removaUmItem();
                this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = '*';
                this.caminho.guardeUmItem(this.atual);
                this.posibilidades.guardeUmItem(this.filaDeAdjacencia);
            }
            //CordLabADJ()
        }

    }

    public void CordLabADJ() throws Exception{
        //Faço uma Fila de Cordenadas cabendo 3
        this.filaDeAdjacencia = new Fila<Cordenada>(3);
        //Declara uma Cordenada e usa o Método cordCima() para retornar a cordenada de cima salvando ela na variavel
        Cordenada cord;
        cord = cordCima();
        // VERIFICA CHAR NA POSIÇÃO ACIMA
        if(cord != null) {
            if (getPosCima() == ' ' || getPosCima() == 'S') {
                this.filaDeAdjacencia.guardeUmItem(cord);
            }
        }
        // VERIFICA CHAR NA POSIÇÃO ABAIXO
        cord = cordBaixo();
        if(cord != null) {
            if (getPosBaixo() == ' ' || getPosBaixo() == 'S') {
                this.filaDeAdjacencia.guardeUmItem(cord);
            }
        }

        // VERIFICA CHAR NA POSIÇÃO A DIREITA
        cord = cordDireita();
        if(cord != null) {
            if (getPosDireita() == ' ' || getPosDireita() == 'S') {
                this.filaDeAdjacencia.guardeUmItem(cord);
            }
        }

        // VERIFICA CHAR NA POSIÇÃO A ESQUERDA
        cord = cordEsquerda();
        if(cord != null) {
            if (getPosEsquerda() == ' ' || getPosEsquerda() == 'S') {
                this.filaDeAdjacencia.guardeUmItem(cord);
            }
        }

    }

    private Cordenada cordCima() throws Exception{
        Cordenada cord = null;
        try{
            cord = new Cordenada(this.atual.getLinha() - 1, this.atual.getColuna());
        }
        catch (Exception erro){
            return null;
            //throw new Exception("Erro ao tentar pegar cordenada");
        }
        return cord;
    }

    private Cordenada cordBaixo() throws Exception{
        Cordenada cord = null;
        try{
            cord = new Cordenada(this.atual.getLinha() + 1, this.atual.getColuna());
        }
        catch (Exception erro){
            return null;
            //throw new Exception("Erro ao tentar pegar cordenada");
        }
        return cord;
    }

    private Cordenada cordEsquerda() throws Exception{
        Cordenada cord = null;
        try{
            cord = new Cordenada(this.atual.getLinha(), this.atual.getColuna() - 1);
        }
        catch (Exception erro){
            return null;
            //throw new Exception("Erro ao tentar pegar cordenada");
        }
        return cord;
    }

    private Cordenada cordDireita() throws Exception{
        Cordenada cord = null;
        try{
            cord = new Cordenada(this.atual.getLinha(), this.atual.getColuna() + 1);
        }
        catch (Exception erro){
            //throw new Exception("Erro ao tentar pegar cordenada");
            return null;
        }
        return cord;
    }



    // VERIFICA LINHA ACIMAaa

     /*       if()
     Cordenada cord;
     cord = cordCima();
     if(getPosCima() == 'S' || getPosCima() == ' '){

        this.filaDeAdjacencia.guardeUmItem(cord;

      */

    /*
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
                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes nas bordas");
                    }
                }
         */

        /*
              if (linhaAtual - 1 >= 0) // verifica se não estoura o array na parte de cima
            {
                if (labirinto[linhaAtual - 1][colunaAtual] == '.') // verifica se o passo de cima é '.'
                {
                    completaLab(linhaAtual - 1, colunaAtual);
                }
            }







         */



        /*
              for (int i = 0; i < this.qtdLinhas -1; i++) {
                for (int j = 0; j < this.qtdColunas -1; j++) {
                    if (labirinto[i][j] != 'E' && labirinto[i][j] != 'S' && labirinto[i][j] != '#') {
                        labirinto[i][j] = '*';
                    }
                }
            }
         */

        /*
        Pilha<Cordenada> caminho = new Pilha<>(this.qtdLinhas * this.qtdColunas);
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
        }
       */



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

    @Override
    public Object clone(){

        Labirinto lab = null;
        try {
            lab = new Labirinto(this);

        } catch (Exception erro){}

        return lab;
    }

/*    @Override
        public  boolean equals(Object obj){
            if (this == obj) return true;
            if (obj == null) return false;

            if (obj.getClass() != Labirinto.class) return false;
            Labirinto lab = (Labirinto) obj;
           return   ;

}
*/
    public int getQtdLinhas() {
        return this.qtdLinhas;
    }

    public int getQtdColunas() {
        return this.qtdColunas;
    }

    //Métodos para pegar o que tem em cada posição
    private char getPosCima(){
        return this.labirinto[this.atual.getLinha()-1][this.atual.getColuna()];
}

    private char getPosBaixo(){
        return this.labirinto[this.atual.getLinha()+1][this.atual.getColuna()];
    }

    private char getPosEsquerda(){
        return this.labirinto[this.atual.getLinha()][this.atual.getColuna()-1];
    }

    private char getPosDireita(){
        return this.labirinto[this.atual.getLinha()][this.atual.getColuna()+1];
    }

}

