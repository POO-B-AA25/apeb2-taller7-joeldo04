import java.util.ArrayList;
import java.util.Random;

public class Problema3_EjecutorSistemaMensajes {
    public static void main(String[] args) {
        Random random = new Random();

        String[] nombres = {"Maria", "Pedro", "Ruth", "Carlos", "Sofia", "Jose", 
            "Laura", "Diego"};
        String[] textosSMS = {
            "Hola, como estas?", "Nos vemos a las 6 de la tarde", 
            "No te olvides de comprar el pan",
            "Llamame cuando puedas","Ven vamos a la piscina",
            "Feliz cumpleanios!!","Pasame el deber" };
        String[] nombresFicherosMMS = {"fotoPlaya.jpg", "vacaciones.png", 
            "memeGato.gif", "memeTungTung.gif", "documento.pdf","fotoDeber.jpg"};

        ArrayList<Movil> contactos = new ArrayList<>();
        System.out.println("Contactos");
        for (int i = 0; i < 5; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            String numero = "09" + (10000000 + random.nextInt(90000000));
            contactos.add(new Movil(numero, nombre));
            System.out.println(contactos.get(i));
        }
        System.out.println();

        ArrayList<Mensaje> buzonDeSalida = new ArrayList<>();
        System.out.println("Mensajes enviados");
        System.out.println(); 

        for (int i = 0; i < 3; i++) {
            Movil remitente = contactos.get(random.nextInt(contactos.size()));
            Movil destinatario;
            do {
                destinatario = contactos.get(random.nextInt(contactos.size()));
            } while (remitente == destinatario);

            Mensaje nuevoMensaje = random.nextBoolean()
                ? new MensajeSMS(remitente, destinatario, textosSMS[random.nextInt(textosSMS.length)])
                : new MensajeMMS(remitente, destinatario, nombresFicherosMMS[random.nextInt(nombresFicherosMMS.length)]);
            
            buzonDeSalida.add(nuevoMensaje);
        }

        for (Mensaje msg : buzonDeSalida) {
            msg.verMensaje();
            msg.enviarMensaje();
            System.out.println("====================================="); 
        }
    }
}

class Movil {
    private String numero;
    private String nombre;

    public Movil(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return this.nombre + " (" + this.numero + ")";
    }
}

class Mensaje {
    protected Movil remitente;
    protected Movil destinatario;

    public Mensaje(Movil remitente, Movil destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public void enviarMensaje() {
        System.out.println("Enviando Mensaje");
    }

    public void verMensaje() {
        System.out.println("--- MENSAJE ---");
        System.out.println("De: " + remitente);
        System.out.println("Para: " + destinatario);
        System.out.println("------------------------");
    }
}

class MensajeSMS extends Mensaje {
    private String textoMensaje;

    public MensajeSMS(Movil remitente, Movil destinatario, String textoMensaje) {
        super(remitente, destinatario);
        this.textoMensaje = textoMensaje;
    }
    public void enviarMensaje() {
        System.out.println("Enviando SMS de [" + remitente.getNombre() + "] a [" 
                + destinatario.getNombre() + "]");
    }
    public void verMensaje() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return """
               --- MENSAJE SMS ---
               De: %s  Para: %s
               Mensaje: "%s"
               -------------------
               """.formatted(remitente, destinatario, textoMensaje);
    }
}

class MensajeMMS extends Mensaje {
    private String nombreFichero;

    public MensajeMMS(Movil remitente, Movil destinatario, String nombreFichero) {
        super(remitente, destinatario);
        this.nombreFichero = nombreFichero;
    }
    public void enviarMensaje() {
        System.out.println("Enviando MMS (Imagen) de [" + remitente.getNombre() 
                + "] a [" + destinatario.getNombre() + "");
    }
    public void verMensaje() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return """
               --- MENSAJE MMS ---
               De: %s  Para: %s
               Fichero de Imagen: [%s]
               -------------------
               """.formatted(remitente, destinatario, nombreFichero);
    }
}