package co.edu.upb;

class TarjetaMetrolinea {
    private double saldo;
    private int viajesDisponibles;
    private boolean bloqueada;

    public TarjetaMetrolinea(double saldoInicial) {
        saldo = saldoInicial;
        viajesDisponibles = calcularViajesDisponibles(saldo);
        bloqueada = false;
    }

    private int calcularViajesDisponibles(double saldo) {
        // Suponemos que 1 viaje cuesta $2500
        return (int) (saldo / 2500);
    }

    public double getSaldo() {
        return saldo;
    }

    public int getViajesDisponibles() {
        return viajesDisponibles;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void recargarSaldo(double cantidad) {
        saldo += cantidad;
        viajesDisponibles = calcularViajesDisponibles(saldo);
    }

    public void realizarViaje() {
        if (!bloqueada) {
            if (viajesDisponibles > 0) {
                viajesDisponibles--;
                saldo -= 2500; // Costo de un viaje
                System.out.println("Viaje realizado. Saldo restante: " + saldo);
            } else {
                System.out.println("No tienes suficientes viajes disponibles. Por favor, recarga tu tarjeta.");
            }
        } else {
            System.out.println("La tarjeta está bloqueada. Debes desbloquearla antes de viajar.");
        }
    }

    public void bloquear() {
        bloqueada = true;
    }

    public void desbloquear() {
        bloqueada = false;
    }
}