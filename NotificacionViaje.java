package co.edu.upb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


class Trip {
    String destination;
    String departureTime;
}

public class NotificacionViaje {
    public static void main(String[] args) {
        ArrayList<Trip> trips = new ArrayList<>();

        while (true) {
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Programar un nuevo viaje");
            System.out.println("2. Ver la lista de viajes programados");
            System.out.println("3. Salir");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scheduleTrip(trips);
                    break;
                case 2:
                    displayTrips(trips);
                    break;
                case 3:
                    System.out.println("Salida exitosa.");
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }

           
            Date currentTime = new Date();
            Calendar calendar = Calendar.getInstance();

            for (Trip trip : trips) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
                try {
                    Date tripTime = sdf.parse(trip.departureTime);

                    if (tripTime.before(currentTime)) {
                       
                        continue;
                    }

                    calendar.setTime(tripTime);
                    calendar.add(Calendar.MINUTE, -15);

                    if (calendar.getTime().before(currentTime)) {
                       
                        sendDepartureNotification(trip);
                    }
                } catch (Exception e) {
                    System.out.println("Error al analizar la hora de partida.");
                }
            }

           
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   
    public static void displayTrips(ArrayList<Trip> trips) {
        if (trips.isEmpty()) {
            System.out.println("No se encuentra ningun viaje programado.");
            return;
        }
        
        System.out.println("Viajes programados:");
        for (Trip trip : trips) {
            System.out.println("Destino: " + trip.destination);
            System.out.println("Hora de partida: " + trip.departureTime);
            System.out.println("------------------------------");
        }
    }

    
    public static void scheduleTrip(ArrayList<Trip> trips) {
        Trip newTrip = new Trip();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el destino del viaje: ");
        newTrip.destination = scanner.nextLine();
        System.out.print("Ingrese la hora de partida (HH:mm): ");
        newTrip.departureTime = scanner.nextLine();
        trips.add(newTrip);
        System.out.println("Viaje programado con Exito.");
    }

   
    public static void sendDepartureNotification(Trip trip) {
        System.out.println("Recordatorio: Su viaje a " + trip.destination + " parte a las " + trip.departureTime + ".");
    }
}

//Como usuario, quiero poder planificar viajes futuros y recibir notificaciones recordándome la hora de partida, para una experiencia de viaje más organizada.


