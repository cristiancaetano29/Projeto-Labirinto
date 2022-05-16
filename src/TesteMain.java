import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TesteMain {
    public static void main(String[] args) {
        try {
            System.out.println("Qual o nome do Arquivo?");
            String arquivo = Teclado.getUmString();

            Labirinto lab = new Labirinto(arquivo);
            System.out.println(lab);

            lab.testLab();
            System.out.println("LabirintoResolvido: \n" + lab);
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
        /*
        System.out.println("Qual o nome do Arquivo?");

        //Pegando o arquivo do usuario no seguinte formato: "nome do arquivo.extensao"
        String arquivo = Teclado.getUmString();

        //Criando um objeto do tipo Files
        Arquivo arq = new Arquivo(arquivo);


        String MeuArquivoModelo = "C:\\Users\\Cristian\\Desktop\\C0tuca\\Terceiro Semestre\\TCC\\LabTest.txt"; //só um Modelo
         C:\Users\diogo\OneDrive\Área de Trabalho\labs\LabTest.txt
        */
    }
}
