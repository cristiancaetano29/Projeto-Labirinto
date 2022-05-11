import java.util.Arrays;

public class Labirinto {
    private int qtdLinhas;
    private int qtdColunas;
    private String Arquivo;
    private char[][] labirinto;

    public Labirinto(String Arquivo) throws Exception {
        //Pega o arquivo do labirinto
        Arquivo arqLabirinto = new Arquivo(Arquivo);
        //tenho que ter uma copia do labirinto para não perder o labirinto original
        Arquivo arqLabirinto2 = new Arquivo(Arquivo);
        //Pega o indice da primeira linha do labirinto
        int qtdLinhas = arqLabirinto.getUmInt();
        System.out.println("Linhas: " + qtdLinhas);
        //Pega a segunda Linha do labirinto
        String str = arqLabirinto.getUmaString();
        System.out.println("LinhaCompleta: " + str);
        //retorna a quantidade de caracteres para realizar as verificações
        int qtdColunas = str.length();
        System.out.println("Colunas: " + qtdColunas);

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
            //Pego o indece da primeira linha do labirinto
            LabCop.getUmInt();
            //Crio uma matriz para armazenar o labirinto com os dados
            this.labirinto = new char[this.qtdLinhas][this.qtdColunas];
            //System.out.println("Linhas: " + this.qtdLinhas + " Colunas: " + this.qtdColunas );
            //Percorro o labirinto pegando cada linha dele
            //System.out.println(this.labirinto.length);
            System.out.println("Linhas: " + this.qtdLinhas + " Colunas: " + this.qtdColunas );
            for(int i = 0; i < this.qtdLinhas; i++){
                srt = LabCop.getUmaString();
                //System.out.println("Linha: "+ i + " " + srt);
                //Percorro a linha pegando cada caracter
                for (int j = 0; j < this.qtdColunas; j++){
                    this.labirinto[i][j] = srt.charAt(j);
                    System.out.println("Linha: "+ i + " " + "Coluna: " + j + this.labirinto[i][j]);
                }
            }

        }
        catch (Exception erro)
        { }//Não vai dar erro pois o arquivo é valido
    }

    //making a toSring for return all elements of the labyrinth



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

    // Obtenho o inicio do labirinto percorrendo a matriz e encontrando o char E, por padrão sempre começa em 0-0 e no segundo array na primeira chamada([0])
    public int[] inicioLabirinto(){
        int[] volta = {0,0};
        for (int i = 0; i < labirinto.length; i++){
            for (int j = 0; j < labirinto[0].length; j++){
                if (labirinto[i][j] == 'E'){
                    volta[0] = i;
                    volta[1] = j;
                }
            }
        }
        return  volta;
    }

    // Novamente obtenho o fim do labirinto percorrendo a matriz e encontrando o char S, por padrão sempre começa em 0-0 e no segundo array na primeira chamada([0])
    public int[] fimLabirinto(){
        int[] volta = {0,0};
        for (int i = 0; i < labirinto.length; i++){
            for (int j = 0; j < labirinto[0].length; j++){
                if (labirinto[i][j] == 'S'){
                    volta[0] = i;
                    volta[1] = j;
                }
            }
        }
        return volta ;
    }

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
                if (labirinto[i][j] == 'E'){
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
    }
}
