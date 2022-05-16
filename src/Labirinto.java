import java.util.Arrays;

public class Labirinto implements Cloneable {
    private int qtdLinhas;
    private int qtdColunas;
    private String Arquivo;
    private char[][] labirinto;

    private Pilha<Cordenada> caminho;
    private Pilha<Fila<Cordenada>> posibilidades;
    private Fila<Cordenada> filaDeAdjacencia;
    private Cordenada atual = null;
    private Pilha<Cordenada> inverso;


    public Labirinto(String Arquivo) throws Exception {
        // PEGA O ARQUIVO DO LABIRINTO
        Arquivo arqLabirinto = new Arquivo(Arquivo);

        // PEGA A QUANTIDADE DE LINHAS E COLUNAS DO LABIRINTO
        // !! TENHO QUE TER UMA CÓPIA DO LABIRINTO PARA NÃO PERDER O LABIRINTO ORIGINAL
        Arquivo arqLabirinto2 = new Arquivo(Arquivo);

        // PEGA O INDICE DA PRIMEIRA LINHA DO LABIRINTO
        int qtdLinhas = arqLabirinto.getUmInt();

        //Pega a segunda Linha do labirinto
        String str = arqLabirinto.getUmaString();

        // RETORNA A QUANTIDADE DE CARACTERES PARA REALIZAR AS VERIFICAÇÕES
        int qtdColunas = str.length();
        this.qtdLinhas = qtdLinhas;
        this.qtdColunas = qtdColunas;
        GerarMatrizDoLabirinto(arqLabirinto2);
    }

    public void GerarMatrizDoLabirinto(Arquivo LabCop) {
        try{
            String srt = null;

            // É PEGO O INDICE DA PRIMEIRA LINHA DO LABIRINTO
            LabCop.getUmInt();

            // É CRIADO UMA MATRIZ PARA ARMAZENAR  O LABIRINTO COM OS DADOS
            this.labirinto = new char[this.qtdLinhas][this.qtdColunas];

            // É PERCORRIDO O LABIRINTO PEGANDO CADA LINHA
            for(int i = 0; i < this.qtdLinhas; i++){
                srt = LabCop.getUmaString();

                // É PERCORRIDO O LABIRINTO PEGANDO CADA CARACTER
                for (int j = 0; j < this.qtdColunas; j++){
                    this.labirinto[i][j] = srt.charAt(j);
                    Cordenada cor = new Cordenada(i, j);
                }
            }
        }
        catch (Exception erro)
        {
            // NÃO VAI DAR ERRO POIS O ARQUIVO É VALIDO
        }
    }

    // VERIFCA SE EXISTE UMA SAIDA
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

    // VERIFCA SE EXISTE UMA ENTRADA
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

    // VERIFCA SE EXISTE MAIS DE UMA ENTRADA
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

    // VERIFCA SE EXISTE MAIS DE UMA SAIDA
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

    //  VERIFCA CARACTER DIFERENTE AOS QUE PEDE, NO CASO 'E' - 'S' - '#' - ' '
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

    // TRAZ A POSIÇÃO ATUAL QUE SE ENCONTRA NO MOMENTO
    private char MinhaLocAtual(){
        return labirinto[this.atual.getLinha()][this.atual.getColuna()];
    }

    // ACHA A POSIÇÃO DO 'E'
    private Cordenada cordE() throws Exception{
        Cordenada cord = null;
        for(int i = 0; i < this.qtdLinhas; i++){
            for(int j = 0; j < this.qtdColunas; j++){
                if(i == 0 || i == this.qtdLinhas-1){
                    // PAREDE DE CIMA E DE BAIXO
                    if(this.labirinto[i][j] == 'E'){
                        cord = new Cordenada(i,j);
                    }
                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes nas bordas");
                    }
                }
                // PAREDE DA ESQUERDA E DA DIREITA
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


    // ACHA A POSIÇÃO DO 'S'
    private Cordenada cordS() throws Exception{
        Cordenada cord2 = null;
        for(int i = 0; i < this.qtdLinhas; i++){
            for(int j = 0; j < this.qtdColunas; j++){
                if(i == 0 || i == this.qtdLinhas -1){
                    // PAREDE DE CIMA E DE BAIXO
                    if(this.labirinto[i][j] == 'S'){
                        cord2 = new Cordenada(i,j);
                    }
                    if(this.labirinto[i][j] == ' '){
                        throw new Exception("Não existe Paredes nas bordas");
                    }
                }
                // PAREDE DA ESQUERDA E DA DIREITA
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


    // MÉTODO PARA TESTE
    public void testLab() throws Exception{
        if(!temEntrada()) {
            throw new Exception("Labirinto não tem entrada");
        }

        if(!temSaida()) {
            throw new Exception("Labirinto não tem saida");
        }
        this.atual = new Cordenada(cordE());

        if(!qtdEntradas()) {
            throw new Exception("Labirinto não tem uma unica entrada");
        }

        if(!qtdSaidas()) {
            throw new Exception("Labirinto não tem uma unica saida");
        }

        if(!carterDiferente()){
            throw new Exception("Labirinto tem carter diferente de: 'E' -- 'S' -- ' ' ");
        }

        // ESTANCIANDO OBJETO DA CLASSE PILHA E GUARDANDO EM PILHA DE CORDENADAS
        this.caminho = new Pilha<Cordenada>(getQtdLinhas() * getQtdColunas());

        // ESTANCIANDO OBJETO DA CLASSE PILHA E GUARDANDO EM FILHA DE PILHA DE CORDENADAS
        this.posibilidades = new Pilha<Fila<Cordenada>>(getQtdLinhas() * getQtdColunas());

//        System.out.println("Entrada: " + this.atual);
//        System.out.println("Saida: " + cordS());
//        System.out.println("Cord E: " + cordE());
//        System.out.println("Loc Atual: " + MinhaLocAtual());

        // WHILE PARA SEMPRE RETORNAR(ENTRAR EM LOOP) ENQUANTO OS CARACTERES FOREM DIFERENTES DE S
        while(MinhaLocAtual() != 'S'){
            CordLabADJ();
            while(this.filaDeAdjacencia.isVazia()){
                this.atual = caminho.recupereUmItem();
                this.caminho.removaUmItem();
                this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = ' ';
                this.filaDeAdjacencia = posibilidades.recupereUmItem();
                this.posibilidades.removaUmItem();
            }
            this.atual = this.filaDeAdjacencia.recupereUmItem();
            if(MinhaLocAtual() != 'S'){
                this.filaDeAdjacencia.removaUmItem();
                this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = '*';
                this.caminho.guardeUmItem(this.atual);
                this.posibilidades.guardeUmItem(this.filaDeAdjacencia);
            }
        }
        RecuperaCordenadaInversa();
    }

    // MÉTOD0 PARA
    private void RecuperaCordenadaInversa() throws Exception{
        inverso = new Pilha<Cordenada>(caminho.getUltimo());
        while(!caminho.isVazia()){
            inverso.guardeUmItem(caminho.recupereUmItem());
            caminho.removaUmItem();
        }
        System.out.println("Esssa são as cordenadas que levam a saida");
        while(!inverso.isVazia()){
            System.out.println(inverso.recupereUmItem());
            inverso.removaUmItem();
        }
        System.out.println("\n");
    }

    // MÉTODO PARA VERIFICAR A COORDENADA ADJCENTE DA FILA DE COORDENADAS
    private void CordLabADJ() throws Exception{
        // FAÇO UMA FILA DE CORDENADASD CABENDO 3
        this.filaDeAdjacencia = new Fila<Cordenada>(3);
        // DECLARA UMA CORDENADA E USA O MÉTODO cordCima() PARA RETORNAR A CORDENADA DE CIMA SALVANDO-A NA VARIAVEL
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

    // MÉTODOS PARA VERIFICAR POSIÇÕES REFERENTE AS CORDENADAS ACEITAS
    private Cordenada cordCima() throws Exception{
        Cordenada cord = null;
        try{
            cord = new Cordenada(this.atual.getLinha() - 1, this.atual.getColuna());
        }
        catch (Exception erro){
            return null;
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
        }
        return cord;
    }

    private Cordenada cordDireita() throws Exception{
        Cordenada cord = null;
        try{
            cord = new Cordenada(this.atual.getLinha(), this.atual.getColuna() + 1);
        }
        catch (Exception erro){
            return null;
        }
        return cord;
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
                str += "\n";
            }
        }
        return str;
    }

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

    // MÉTODOS PARA PEGAR O QUE TEM EM CADA POSIÇÃO
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

