public class Soldado {
    private String nombre;
    private int nivelAtaque;
    private int nivelDefensa;
    private int vidaActual;
    private int velocidad = 0;  
    private String actitud;
    private boolean vive = true;
    private int fila;
    private int columna;

    // Constructores
    public Soldado() {
        nombre = "unknown";
        nivelAtaque = (int)(Math.random() * 5 + 1);
        nivelDefensa = (int)(Math.random() * 5 + 1);
        vidaActual = (int)(Math.random() * 5 + 1);
    }

    public Soldado(String nombre, int fila, int columna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
        this.nivelAtaque = (int)(Math.random() * 5 + 1);
        this.nivelDefensa = (int)(Math.random() * 5 + 1);
        this.vidaActual = (int)(Math.random() * 5 + 1);
        this.actitud = "defensiva"; // Actitud por defecto
    }

    public Soldado(String nombre, int fila, int columna, String actitud) {
        this(nombre, fila, columna);
        this.actitud = actitud;
    }

    // Métodos de acción
    public void avanzar() {
        velocidad++;
    }

    public void atacar() {
        avanzar();
        actitud = "ofensiva";
    }

    public void defender() {
        velocidad = 0;
        actitud = "defensiva";
    }

    public void huir() {
        velocidad += 2;
        actitud = "fuga";
    }

    public void retroceder() {
        if (velocidad > 0) {
            defender();
        } else {
            velocidad--;
        }
    }

    public void serAtacado(int dano) {
        vidaActual -= dano;
        if (vidaActual <= 0) {
            morir();
        }
    }

    public void morir() {
        vidaActual = 0;
        vive = false;
        actitud = "muerto";
    }

    // Getters y setters
    public int getVidaActual() {
        return vidaActual;
    }

    public boolean isVive() {
        return vive;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getNombre() {
        return nombre;
    }
    
    // Agregar método getNivelAtaque()
    public int getNivelAtaque() {
        return nivelAtaque;
    }
    
    // Agregar método getNivelDefensa()
    public int getNivelDefensa() {
        return nivelDefensa;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
}

}
