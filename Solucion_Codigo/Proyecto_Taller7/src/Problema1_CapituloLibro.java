import java.util.ArrayList;

public class Problema1_CapituloLibro {
    public static void main(String[] args) {
        Palabra palabra1 = new Palabra("La");
        Palabra palabra2 = new Palabra("celula");
        Palabra palabra3 = new Palabra("es");
        Palabra palabra4 = new Palabra("la");
        Palabra palabra5 = new Palabra("unidad");
        Palabra palabra6 = new Palabra("fundamental.");

        Sentencia sentec1 = new Sentencia();
        sentec1.agregarPalabra(palabra1);
        sentec1.agregarPalabra(palabra2);
        sentec1.agregarPalabra(palabra3);
        sentec1.agregarPalabra(palabra4);
        sentec1.agregarPalabra(palabra5);
        sentec1.agregarPalabra(palabra6);

        Parrafo parrafo1 = new Parrafo();
        parrafo1.agregarSentencia(sentec1);

        Figura figura1 = new Figura("Diagrama de una celula eucariota");
        Tabla tabla1 = new Tabla("Tabla de Diferencias entre celula animal y vegetal");
        Lista lista1 = new Lista("Organelos: Nucleo, Mitocondria, Ribosoma");
        Vineta v1 = new Vineta("+ Toda celula procede de otra celula.");

        Seccion seccion1 = new Seccion("Introduccion a la Biologia Celular");
        seccion1.agregarComponente(parrafo1);
        seccion1.agregarComponente(figura1);
        seccion1.agregarComponente(tabla1);
        seccion1.agregarComponente(lista1);
        seccion1.agregarComponente(v1);

        Capitulo capitulo1 = new Capitulo("Capitulo 1: Fundamentos de la Celula");
        capitulo1.agregarSeccion(seccion1);

        capitulo1.mostrar();
    }
}

class Palabra {
    public String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return this.texto;
    }
}

class Sentencia {
    public ArrayList<Palabra> palabras;

    public Sentencia() {
        this.palabras = new ArrayList<>();
    }

    public void agregarPalabra(Palabra palabra) {
        this.palabras.add(palabra);
    }

    @Override
    public String toString() {
        StringBuilder constructorDeOracion = new StringBuilder();
        for (int i = 0; i < palabras.size(); i++) {
            constructorDeOracion.append(palabras.get(i).toString());
            if (i < palabras.size() - 1) {
                constructorDeOracion.append(" ");
            }
        }
        return constructorDeOracion.toString();
    }

    public void mostrar() {
        System.out.println(this.toString());
    }
}

class Componente {
    public String toString() {
        return "";
    }

    public void mostrar() {
        System.out.println(this.toString());
    }
}

class Parrafo extends Componente {
    public ArrayList<Sentencia> sentencias;

    public Parrafo() {
        this.sentencias = new ArrayList<>();
    }

    public void agregarSentencia(Sentencia sentencia) {
        this.sentencias.add(sentencia);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    Parrafo: ");
        for (Sentencia s : sentencias) {
            sb.append(s.toString());
        }
        return sb.toString();
    }
}

class Figura extends Componente {
    public String descripcion;

    public Figura(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return String.format("    Figura: %s", this.descripcion);
    }
}

class Tabla extends Componente {
    public String titulo;

    public Tabla(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return String.format("    Tabla: %s", this.titulo);
    }
}

class Lista extends Componente {
    public String contenidoLista;

    public Lista(String contenido) {
        this.contenidoLista = contenido;
    }

    @Override
    public String toString() {
        return String.format("    Lista: %s", this.contenidoLista);
    }
}

class Vineta extends Componente {
    public String texto;

    public Vineta(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return String.format("    Vineta: %s", this.texto);
    }
}

class Seccion {
    public String nombre;
    public ArrayList<Componente> componentes;

    public Seccion(String nombre) {
        this.nombre = nombre;
        this.componentes = new ArrayList<>();
    }

    public void agregarComponente(Componente componente) {
        this.componentes.add(componente);
    }

    public void mostrar() {
        System.out.println("  Seccion: " + this.nombre);
        for (Componente c : componentes) {
            c.mostrar();
        }
    }
}

class Capitulo {
    public String titulo;
    public ArrayList<Seccion> secciones;

    public Capitulo(String titulo) {
        this.titulo = titulo;
        this.secciones = new ArrayList<>();
    }

    public void agregarSeccion(Seccion seccion) {
        this.secciones.add(seccion);
    }

    public void mostrar() {
        System.out.println("Titulo del capitulo: " + this.titulo);
        for (Seccion s : secciones) {
            s.mostrar();
        }
    }
}
