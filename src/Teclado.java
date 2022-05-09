import java.io.*;

public class Teclado {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getUmString(){
        String str = null;

        try{
            str = reader.readLine();
        }
        catch (IOException erro)
        { }
        return str;
    }

    public static int getUmInt() throws Exception{
        int num = 0;
        try{
            num = Integer.parseInt(getUmString());
        }
        /*catch (IOException erro)
        { }*/
        catch (NumberFormatException erro){
            throw new Exception("Número inválido");
        }

        return num;
    }
}
