import java.io.*;

public class Arquivo {

    private BufferedReader reader;

    public Arquivo(String nomeDoArquivo) throws Exception{
        try{
            reader = new BufferedReader(new FileReader(nomeDoArquivo));
        }
        catch (IOException erro){
            throw new Exception("Diretório do arquivo não encontrado!!! \n Tente novamente");
        }
    }

    public String getUmaString() {
        String ret = null;
        try {
            ret = reader.readLine();
        }
        catch (IOException erro)
        {  }

        return ret;

    }

    public int getUmInt() throws Exception
    {
        int ret = 0;
        try{
            ret = Integer.parseInt(reader.readLine());
        }
        catch (IOException erro){

        }
        catch (NumberFormatException erro){
            throw new Exception("Não é um número inteiro");
        }
        return ret;
    }
}
