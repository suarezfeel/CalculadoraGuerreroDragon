package calculadorav1.modelo;

public abstract class OperacionMatematica
{
    protected double numeroOperadoA;
    protected double numeroOperadoB;
    public abstract double ejecutar();
    public abstract void registrarOperacion();

    public OperacionMatematica(double numeroOperadoA, double numeroOperadoB)
    {
        this.numeroOperadoA = numeroOperadoA;
        this.numeroOperadoB = numeroOperadoB;
    }
}