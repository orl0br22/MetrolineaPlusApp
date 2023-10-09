package co.edu.upb;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetrolineaPlusApp {
    public static void main(String[] args) {
        List<Cuenta> cuentas = leerCuentas("cuentas.txt");
        String usuario, contrasena;
        Cuenta cuentaAutenticada = null;

        System.out.println("---------------------------------");
        System.out.println("Bienvenido a la MetrolineaPlus App.");
        System.out.println("---------------------------------");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar una nueva cuenta");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción deseada: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese su usuario: ");
                    usuario = scanner.next();
                    System.out.print("Ingrese su Contraseña: ");
                    contrasena = scanner.next();
                    cuentaAutenticada = autenticarUsuario(cuentas, usuario, contrasena);
                    
                    if (cuentaAutenticada != null) {
                        boolean sesionActiva = true;

                        while (sesionActiva) {
                            System.out.println("1. Ver saldo actual");
                            System.out.println("2. Recargar saldo");
                            System.out.println("3. Realizar un viaje");
                            System.out.println("4. Mostrar historial de transacciones");
                            System.out.println("5. Bloquear/Desbloquear tarjeta");
                            System.out.println("6. Cerrar sesión");
                            System.out.print("Ingrese la opción deseada: ");

                            opcion = scanner.nextInt();

                            switch (opcion) {
                                case 1:
                                    System.out.println("Saldo actual: $" + cuentaAutenticada.obtenerTarjetaAutobus().obtenerSaldo());
                                    break;

                                case 2:
                                    if (cuentaAutenticada.obtenerTarjetaAutobus().estaBloqueada()) {
                                        System.out.println("La tarjeta está bloqueada. No se puede recargar.");
                                    } else {
                                        System.out.println("Seleccione una opción para recargar saldo:");
                                        System.out.println("1. Tarjeta");
                                        System.out.println("2. Efectivo");
                                        System.out.print("Ingrese la opción deseada: ");
                                        int opcionRecarga = scanner.nextInt();

                                        switch (opcionRecarga) {
                                            case 1:
                                                double cantidadRecarga = 0.0;
                                                System.out.print("Ingrese la cantidad a recargar: $");
                                                cantidadRecarga = scanner.nextDouble();
                                                System.out.print("Número de tarjeta: ");
                                                String numeroTarjeta = scanner.next();
                                                System.out.print("Fecha de Vencimiento: ");
                                                String fechaVencimiento = scanner.next();
                                                System.out.print("CVC: ");
                                                String cvc = scanner.next();

                                                cuentaAutenticada.obtenerTarjetaAutobus().recargarSaldo(cantidadRecarga);
                                                String mensaje = "Recarga exitosa. Saldo actual: $" + cuentaAutenticada.obtenerTarjetaAutobus().obtenerSaldo() + " pesos.";
                                                cuentaAutenticada.agregarTransaccion(mensaje, true);
                                                System.out.println(mensaje);
                                                break;

                                            case 2:
                                                System.out.print("Ingrese la cantidad a recargar en efectivo: $");
                                                double cantidadRecargaEfectivo = scanner.nextDouble();
                                                cuentaAutenticada.obtenerTarjetaAutobus().recargarSaldo(cantidadRecargaEfectivo);
                                                mensaje = "Recarga en efectivo exitosa. Saldo actual: $" + cuentaAutenticada.obtenerTarjetaAutobus().obtenerSaldo() + " pesos.";
                                                cuentaAutenticada.agregarTransaccion(mensaje, true);
                                                System.out.println(mensaje);
                                                break;

                                            default:
                                                System.out.println("Opción no válida. Por favor ingrese una opción válida.");
                                        }
                                    }
                                    break;

                                case 3:
                                    double costoViaje;
                                    System.out.print("Ingrese el costo del viaje: $");
                                    costoViaje = scanner.nextDouble();
                                    cuentaAutenticada.realizarViaje(costoViaje);
                                    break;

                                case 4:
                                    System.out.println("Historial de transacciones:");
                                    for (String transaccion : cuentaAutenticada.obtenerHistorial()) {
                                        System.out.println(transaccion);
                                    }
                                    break;

                                case 5:
                                    if (cuentaAutenticada.obtenerTarjetaAutobus().estaBloqueada()) {
                                        System.out.println("La tarjeta está bloqueada.");
                                        System.out.println("1. Desbloquear tarjeta");
                                        System.out.println("2. Volver al menú principal");
                                        System.out.print("Ingrese la opción deseada: ");
                                        int opcionDesbloqueo = scanner.nextInt();

                                        switch (opcionDesbloqueo) {
                                            case 1:
                                                cuentaAutenticada.obtenerTarjetaAutobus().desbloquear();
                                                cuentaAutenticada.agregarTransaccion("Desbloqueo de tarjeta", false);
                                                System.out.println("La tarjeta ha sido desbloqueada.");
                                                break;

                                            case 2:
                                                break;

                                            default:
                                                System.out.println("Opción no válida. Por favor ingrese una opción válida.");
                                        }
                                    } else {
                                        System.out.println("La tarjeta está desbloqueada.");
                                        System.out.println("1. Bloquear tarjeta");
                                        System.out.println("2. Volver al menú principal");
                                        System.out.print("Ingrese la opción deseada: ");
                                        int opcionBloqueo = scanner.nextInt();

                                        switch (opcionBloqueo) {
                                            case 1:
                                                cuentaAutenticada.obtenerTarjetaAutobus().bloquear();
                                                cuentaAutenticada.agregarTransaccion("Bloqueo de tarjeta", false);
                                                System.out.println("La tarjeta ha sido bloqueada.");
                                                break;

                                            case 2:
                                                break;

                                            default:
                                                System.out.println("Opción no válida. Por favor ingrese una opción válida.");
                                        }
                                    }
                                    break;

                                case 6:
                                    System.out.println("Cierre de sesión exitoso.");
                                    cuentaAutenticada = null;
                                    sesionActiva = false;
                                    break;

                                default:
                                    System.out.println("Opción no válida. Por favor ingrese una opción válida.");
                            }
                        }
                    }
                    break;

                case 2:
                    String codigoRuta;
                    System.out.print("Ingrese un nuevo usuario: ");
                    usuario = scanner.next();
                    System.out.print("Ingrese una nueva Contraseña: ");
                    contrasena = scanner.next();
                    System.out.print("Ingrese el código de ruta de la tarjeta de Metrolinea: ");
                    codigoRuta = scanner.next();
                    System.out.print("Ingrese un correo electronico o numero de telefono: ");
                    String telefono = scanner.next();

                    if (esAlfanumerico(usuario) && esAlfanumerico(contrasena) && esAlfanumerico(codigoRuta)) {
                        cuentas.add(new Cuenta(usuario, contrasena, 0.0, codigoRuta));
                        System.out.println("Cuenta registrada exitosamente.");
                    } else {
                        System.out.println("Los campos deben contener solo caracteres alfanuméricos.");
                    }
                    break;

                case 3:
                    System.out.println("Gracias por usar Metrolinea Plus App. Hasta luego.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Por favor ingrese una opción válida.");
            }
        }
    }

    private static Cuenta autenticarUsuario(List<Cuenta> cuentas, String usuario, String contrasena) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.obtenerUsuario().equals(usuario) && cuenta.obtenerContrasena().equals(contrasena)) {
                System.out.println("Inicio exitoso.");
                return cuenta;
            }
        }
        System.out.println("Contraseña y/o Usuario incorrectos. Acceso no admitido!");
        return null;
    }

    private static List<Cuenta> leerCuentas(String archivo) {
        List<Cuenta> cuentas = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 4) {
                    String usuario = parts[0];
                    String contrasena = parts[1];
                    double saldo = Double.parseDouble(parts[2]);
                    String codigoRuta = parts[3];
                    cuentas.add(new Cuenta(usuario, contrasena, saldo, codigoRuta));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cuentas;
    }

    private static boolean esAlfanumerico(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
