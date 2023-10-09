package co.edu.upb;

class Usuario {
    private String nombre;
    private int edad;
    private String preguntaSeguridad;
    private String respuestaSeguridad;

    public Usuario(String nombre, int edad, String preguntaSeguridad, String respuestaSeguridad) {
        this.nombre = nombre;
        this.edad = edad;
        this.preguntaSeguridad = preguntaSeguridad;
        this.respuestaSeguridad = respuestaSeguridad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    public String getRespuestaSeguridad() {
        return respuestaSeguridad;
    }
}