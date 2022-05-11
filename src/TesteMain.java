import java.nio.file.Files;
import java.nio.file.Paths;

public class TesteMain {
    public static void main(String[] args) {
        try{
            System.out.println("Qual o nome do Arquivo?");
            String arquivo = Teclado.getUmString();
            Labirinto lab = new Labirinto(arquivo);
            lab.testLab();
            System.out.println("VerificandoLab: \n\n" + lab);
        }
        catch (Exception erro){
            System.err.println(erro);
        }
        /*
        System.out.println("Qual o nome do Arquivo?");

        //Pegando o arquivo do usuario no seguinte formato: "nome do arquivo.extensao"
        String arquivo = Teclado.getUmString();

        //Criando um objeto do tipo File
        Arquivo arq = new Arquivo(arquivo);


        String MeuArquivoModelo = "C:\\Users\\Cristian\\Desktop\\C0tuca\\Terceiro Semestre\\TCC\\LabTest.txt"; //só um Modelo

        /*
        //Teste para ver se o arquivo existe e se é possível ler
        System.out.println(new String(Files.readAllBytes(Paths.get(arquivo))));

        //Pego e printo a quantidade de caracteres que o arquivo deve conter por linha
        System.out.println(arq.getUmInt());
        */
        /*Labirinto lab = new Labirinto(arquivo);
        System.out.println(lab.getQtdLinhas());
        System.out.println(lab.getQtdColunas());
        System.out.print(lab);
        */

    }
}
