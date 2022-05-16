import java.lang.reflect.*;

public class Clonador<X>
{
    public X clone (X x)
    {
        // ASSIM OBTEMOS A CLASSE DA INSTÂNCIA NO OBJETO x,
        // QUE, CONFORME SABEMOS, É A CLASSE X, E A ARMAZENAMOS NO OBJETO CHAMADO CLASSE
        Class<?> classe = x.getClass();

        // NULL PORQUE CHAMAREMOS UM MÉTODO SEM PARÂMETROS
        Class<?>[] tpsParmsForms = null;

        // ASSIM OBTEMOS O MÉTODO CHAMADO CLONE, SEM PARÂMETROS,
        // DA CLASS<?> ARMAZENADA NO OBJETO CLASSSE (SABEMOS QUE
        // A CLASSE É A CLASSE X)
        Method metodo=null;
        try
        {
            metodo = classe.getMethod ("clone",tpsParmsForms);
        }
        catch (NoSuchMethodException erro)
        {}

        // NULL PORQUE CHAMAREMOS UM MÉTODO SEM PARÂMETROS
        Object[] parmsReais = null;

        // ASSIM CHAMAMOS O  armazenado no objeto chamado
        // método, fazendo com que o objeto chamado x seja para
        // ele o objeto chamante (o this) e fazendo com que
        // receba nao receba parâmetros reais (por isso o vetor
        // nulo chamado parmsReais é fornecido; o resultado da
        // chamada, que certamente é da classe X, mas que está
        // sendo retornado como Object, tem seu tipo mudado
        // para X e, então, é atribuido ao objeto chamado ret
        X ret=null;
        try
        {
            ret = (X)metodo.invoke(x,parmsReais);
        }
        catch (InvocationTargetException erro)
        {}
        catch (IllegalAccessException erro)
        {}

        // ret = (X)x.clone();

        return ret;
    }
}