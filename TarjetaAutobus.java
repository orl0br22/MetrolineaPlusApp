package co.edu.upb;

class TarjetaAutobus {
    private double saldo;
    private boolean bloqueada;

    public TarjetaAutobus(String ruta) {
        saldo = 0.0;
        bloqueada = false;
    }

	public void recargarSaldo(double monto) {
        if (!bloqueada) {
            saldo += monto;
            System.out.println("Saldo recargado con éxito. Saldo actual: $" + saldo + " pesos.");
        } else {
            System.out.println("La tarjeta está bloqueada. No se puede recargar.");
        }
    }

    public double obtenerSaldo() {
        return saldo;
    }

    public void descontarSaldo(double monto) {
        if (!bloqueada && saldo >= monto) {
            saldo -= monto;
            System.out.println("Descuento exitoso. Saldo actual: $" + saldo + " pesos.");
        } else {
            System.out.println("No se puede realizar el descuento. La tarjeta está bloqueada o no tiene saldo suficiente.");
        }
    }

    public boolean estaBloqueada() {
        return bloqueada;
    }

    public void bloquear() {
        bloqueada = true;
    }

    public void desbloquear() {
        bloqueada = false;
    }
}
