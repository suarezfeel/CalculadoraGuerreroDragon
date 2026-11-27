package calculadorav1.modelo;

public class Multiplicacion extends OperacionMatematica
{
    @Override
    public double ejecutar()
    {
        return numeroOperadoA * numeroOperadoB;
    }
    @Override
    public void registrarOperacion() {
        return;
    }
    public Multiplicacion(double numeroOperadoA, double numeroOperadoB){
        super(numeroOperadoA, numeroOperadoB);
    }
}