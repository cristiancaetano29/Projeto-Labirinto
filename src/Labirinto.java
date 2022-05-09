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
}
