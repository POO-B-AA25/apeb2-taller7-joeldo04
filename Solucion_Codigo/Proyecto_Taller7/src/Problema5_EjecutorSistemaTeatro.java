import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Problema5_EjecutorSistemaTeatro {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Random generadorAleatorio = new Random();

        ArrayList<Zona> listaDeZonas = new ArrayList<>();
        ArrayList<Entrada> listaDeEntradasVendidas = new ArrayList<>();
    
        listaDeZonas.add(new Zona("Principal", 200, 25.0, 17.5));
        listaDeZonas.add(new Zona("PalcoB", 40, 70.0, 40.0));
        listaDeZonas.add(new Zona("Central", 400, 20.0, 14.0));
        listaDeZonas.add(new Zona("Lateral", 100, 15.5, 10.0));

        String[] nombresPosibles = {"Carlos Lopez", "Maria Moreno", "Alejandro Carrion",
            "Melanie Martinez","Sheyla Ochoa","Diana Suarez","Sebastian Arias"};
        
        int proximoId = 1;
        String continuarVenta = "S";

        System.out.println("SISTEMA DE VENTA DE ENTRADAS - TEATRO");

        do {
            System.out.println(">> NUEVA VENTA");
            System.out.println("Por favor, seleccione la zona deseada:");
            for (int i = 0; i < listaDeZonas.size(); i++) {
                Zona zonaActual = listaDeZonas.get(i);
                int disponibles = zonaActual.capacidad - zonaActual.ocupadas;
                System.out.println((i + 1) + ". " + zonaActual.nombre + " (Disponibles: " + disponibles + ")");
            }
            System.out.print("Opcion de Zona: ");
            int opcionZona = teclado.nextInt();
            teclado.nextLine(); 
            if (opcionZona < 1 || opcionZona > listaDeZonas.size()) {
                System.out.println("ERROR: La zona seleccionada no es valida.");
                continue; 
            }

            Zona zonaElegida = listaDeZonas.get(opcionZona - 1);

            if (!zonaElegida.hayDisponibilidad()) {
                System.out.println("ERROR: La zona '" + zonaElegida.nombre + "' ya no tiene localidades disponibles.");
                continue; 
            }

            String comprador = nombresPosibles[generadorAleatorio.nextInt(nombresPosibles.length)];
            System.out.println("Nombre del comprador asignado: " + comprador);

            System.out.println("\nSeleccione el tipo de entrada:");
            System.out.println("1. Normal");
            System.out.println("2. Reducida");
            System.out.println("3. Abonado");
            System.out.print("Opcion de Tipo: ");
            int opcion = teclado.nextInt();
            teclado.nextLine(); 

            Entrada nuevaEntrada = null;
            
            switch (opcion) {
                case 1:
                    nuevaEntrada = new EntradaNormal(proximoId, comprador, zonaElegida);
                    break;
                case 2:
                    nuevaEntrada = new EntradaReducida(proximoId, comprador, zonaElegida);
                    break;
                case 3:
                    nuevaEntrada = new EntradaAbonado(proximoId, comprador, zonaElegida);
                    break;
                default:
                    System.out.println("ERROR: El tipo de entrada no es valido.");
            }
            if (nuevaEntrada != null) {
                zonaElegida.venderLocalidad();
                listaDeEntradasVendidas.add(nuevaEntrada);
                
                System.out.println("\n--- VENTA COMPLETADA ---");
                System.out.println("ID de la Entrada: " + nuevaEntrada.id);
                System.out.println("Precio a Pagar: $" + nuevaEntrada.calcularPrecio());
                proximoId++; 
            }
            System.out.print("Vender mas entradas? (S/N): ");
            continuarVenta = teclado.nextLine();

        } while (continuarVenta.equalsIgnoreCase("S"));

        System.out.println("\n--- SISTEMA CERRADO. RESUMEN DE VENTAS ---");
        if (listaDeEntradasVendidas.isEmpty()) {
            System.out.println("No se vendió ninguna entrada.");
        } else {
            for (Entrada entrada : listaDeEntradasVendidas) {
                System.out.println("ID: " + entrada.id + ", Comprador: " + entrada.comprador + 
                                   ", Zona: " + entrada.zona.nombre + ", Precio: $" + entrada.calcularPrecio());
            }
        }
    }
}

class Zona {
    public String nombre;
    public int capacidad;
    public int ocupadas;
    public double precioNormal;
    public double precioAbonado;

    public Zona(String nombre, int capacidad, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.ocupadas = 0;
    }

    public boolean hayDisponibilidad() {
        return this.ocupadas < this.capacidad;
    }

    public void venderLocalidad() {
        this.ocupadas++;
    }
}

class Entrada {
    public int id;
    public String comprador;
    public Zona zona;
   
    public Entrada(int id, String comprador, Zona zona) {
        this.id = id;
        this.comprador = comprador;
        this.zona = zona;
    }
    public double calcularPrecio() {
        return 0.0;
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(int id, String comprador, Zona zona) {
        super(id, comprador, zona);
    }
    
    @Override
    public double calcularPrecio() {
        return this.zona.precioNormal;
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, String comprador, Zona zona) {
        super(id, comprador, zona);
    }
    
    @Override
    public double calcularPrecio() {
        return this.zona.precioNormal * 0.85;
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, String comprador, Zona zona) {
        super(id, comprador, zona);
    }
    
    @Override
    public double calcularPrecio() {
        return this.zona.precioAbonado;
    }
}