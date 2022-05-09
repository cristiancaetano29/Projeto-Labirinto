import java.io.*;

public class Arquivo {

    private BufferedReader reader;

    public Arquivo(String nomeDoArquivo){
        try{
            reader = new BufferedReader(new FileReader(nomeDoArquivo));
        }
        catch (IOException erro){
            System.err.println("Arquivo não encontrado");
        }
    }

    public String getUmaString() {
        String ret = null;
        try {
            ret = reader.readLine();
            /*

            for(int i=0; i < reader.toString().length(); i++){
                ret += reader.readLine();

             */
        }
        catch (IOException erro)
        { }

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
