package co.edu.upb;
import java.text.SimpleDateFormat;
import java.util.Date;


class Transaccion {
    private Date fecha;
    private String descripcion;
    private double monto;

    public Transaccion(String descripcion, double monto) {
        fecha = new Date();
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public String getFechaFormateada() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateFormat.format(fecha);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }
}