package calculadorav1.modelo;

public class Suma extends OperacionMatematica
{
    public Suma(double numeroOperadoA, double numeroOperadoB)
    {
        super(numeroOperadoA, numeroOperadoB);
    }

    @Override
    public double ejecutar()
    {
        return numeroOperadoA + numeroOperadoB;
    }

    @Override
    public void registrarOperacion(){
        return;
    }
}