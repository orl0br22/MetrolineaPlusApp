package co.edu.upb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

class Cuenta {
    private String usuario;
    private String contrasena;
    private double saldo;
    private TarjetaAutobus tarjetaAutobus;
    private List<String> historial;

    public Cuenta(String u, String c, double s, String ruta) {
        usuario = u;
        contrasena = c;
        saldo = s;
        tarjetaAutobus = new TarjetaAutobus(ruta);
        historial = new ArrayList<>();
    }

    public void agregarTransaccion(String mensaje, boolean esRecarga) {
        String tipoTransaccion = esRecarga ? "Recarga" : "Descuento";
        String transaccion = obtenerFechaHoraActual() + " - " + tipoTransaccion + ": " + mensaje;
        historial.add(transaccion);
    }

    public void realizarViaje(double costo) {
        if (tarjetaAutobus.obtenerSaldo() >= costo) {
            tarjetaAutobus.descontarSaldo(costo);
            String mensaje = "Viaje realizado exitosamente. Costo del viaje: $" + costo;
            agregarTransaccion(mensaje, false);
            System.out.println(mensaje);
        } else {
            System.out.println("Saldo insuficiente para realizar el viaje.");
        }
    }

    public String obtenerUsuario() {
        return usuario;
    }

    public String obtenerContrasena() {
        return contrasena;
    }

    public double obtenerSaldo() {
        return saldo;
    }

    public List<String> obtenerHistorial() {
        return historial;
    }

    public TarjetaAutobus obtenerTarjetaAutobus() {
        return tarjetaAutobus;
    }

    private String obtenerFechaHoraActual() {
        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date fechaHoraActual = new Date();
        return formatoFechaHora.format(fechaHoraActual);
    }
}