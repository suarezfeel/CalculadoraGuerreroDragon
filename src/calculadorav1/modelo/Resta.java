package calculadorav1.modelo;

public class Resta extends OperacionMatematica
{
    public Resta(double numeroOperadoA, double numeroOperadoB)
    {
        super(numeroOperadoA, numeroOperadoB);
    }
    @Override
    public double ejecutar()
    {
        return numeroOperadoA - numeroOperadoB;
    }

    @Override
    public void registrarOperacion() {
        return;
    }
}