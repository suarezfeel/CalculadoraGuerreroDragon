package calculadorav1.modelo;

public class Division extends OperacionMatematica
{
    public Division(double numeroOperadoA, double numeroOperadoB)
    {
        super(numeroOperadoA, numeroOperadoB);
    }

    @Override
    public double ejecutar()
    {
        if (numeroOperadoB != 0) {
            return numeroOperadoA/numeroOperadoB;
        }
        else {
            throw new ArithmeticException("No puede dividirse entre 0.");
        }
    }

    @Override
    public void registrarOperacion() {
        return;
    }
}