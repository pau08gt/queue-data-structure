import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class PatioAutos {
    private Queue<Auto> listaAutos;

    public PatioAutos() {
        listaAutos=new LinkedList<Auto>();
    }

    public Queue<Auto> getListaAutos() {
        return listaAutos;
    }
    public void encolar(String marca, int anio)
    {
        listaAutos.add(new Auto(marca, anio));
    }
    public Auto desencolar() throws Exception
    {
        if(listaAutos.isEmpty())
        {
            throw new Exception("No hay autos");
        }
        return listaAutos.poll();
    }
    public String listarAutos()
    {
        String acum="";
       for (Auto x: listaAutos)
            {
                acum+=x.toString();
            }
            return acum;
    }
    public String listarMarca(String marca)
    {
        String texto="";
        for (Auto x: listaAutos)
        {
            if(x.getMarca().compareTo(marca)==0)
            {
                texto+=x.toString();
            }
        }
        return texto;
    }
}
