import java.util.ArrayList;

public class Problema2_EjecutorSistemaAlquilerPeliculas {
    public static void main(String[] args) {
        Pelicula pelicula1 = new Pelicula("Stich", "Diego", 2025);
        Pelicula pelicula2 = new Pelicula("La monja", "Marcelo", 2024);
        VHS vhs1 = new VHS("EN", pelicula1, 10);
        System.out.println(vhs1);
    }
}
class SoportePelicula{
    public double precioAlquiler;
    public SoportePelicula(double precioAlq) {
        this.precioAlquiler = precioAlquiler;
    }
    public String toString() {
        return "\nSoportePelicula\n" + "precio Alquiler =" + precioAlquiler;
    }
}
class DVD extends SoportePelicula{
    public String idiomas[];
    public ArrayList<Pelicula> listaPeliculas;
    public DVD(String[] idiomas, ArrayList<Pelicula> listaPeliculas, double precioAlq) {
        super(precioAlq);
        this.idiomas = idiomas;
        this.listaPeliculas = listaPeliculas;
    }
    public void calcularCostoAlq(){
        this.precioAlquiler += this.precioAlquiler * 0.1;
    }
    public String toString() {
        return "DVD\n" + "idiomas = " + idiomas + "\nlistaPeliculas = " + listaPeliculas;
    }
}
class VHS extends SoportePelicula{
    public String idioma;
    public Pelicula pelicula;
    public VHS(String idioma, Pelicula pelicula, double precioAlq) {
        super(precioAlq);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }
    public String toString() {
        return "VHS\n" + "idioma = " + idioma + "\n pelicula = " + pelicula + super.toString();
    }
}
class Pelicula{
    public String titulo, autor;
    public int anioEdicion;
    public Pelicula(String titulo, String autor, int anioEdicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
    }
    public String toString() {
        return "Pelicula\n" + "titulo = " + titulo + "\nautor = " + autor + 
                "\nanioEdicion=" + anioEdicion;
    }
}