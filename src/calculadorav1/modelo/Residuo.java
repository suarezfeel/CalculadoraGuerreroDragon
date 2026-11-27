package calculadorav1.modelo;

public class Residuo  extends OperacionMatematica {
    public Residuo(double numeroOperadoA, double numeroOperadoB)
    {
        super(numeroOperadoA, numeroOperadoB);
    }
    @Override
    public double ejecutar()
    {
        if (numeroOperadoB != 0) {
            return  numeroOperadoA % numeroOperadoB;
        }
        else {
            throw new ArithmeticException("No puede sacarse el residuo entre 0.");
        }
    }

    @Override
    public void registrarOperacion(){
        return;
    }
}