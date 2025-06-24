import java.util.ArrayList;
import java.util.List;

public class Problema4_EjecutorSistemaTrabajador {

    public static void main(String[] args) {
        Jefe director = new Jefe("Ana", "Paredes", "Av. Principal 123", "11001", 3000.0);

        TrabajadorFijo fijo1 = new TrabajadorFijo("Juan", "Solano", "Calle Secundaria 456", "11002", 1200.0);
        TrabajadorComision comisionista1 = new TrabajadorComision("Carla", "Mora", "Av. Norte 789", "11003", 0.10);
        TrabajadorHoras porHoras1 = new TrabajadorHoras("Pedro", "Ramirez", "Calle Sur 321", "11004", 10.0, 15.0);

        fijo1.jefe = director;
        comisionista1.jefe = director;
        porHoras1.jefe = director;
        
        comisionista1.ventasRealizadas = 15000;
        porHoras1.cantidadHoras = 50;

        List<Trabajador> nomina = new ArrayList<>();
        nomina.add(director);
        nomina.add(fijo1);
        nomina.add(comisionista1);
        nomina.add(porHoras1);

        System.out.println("--- CALCULO DE NOMINA MENSUAL ---");
        for (Trabajador t : nomina) {
            
            System.out.println("Empleado: " + t.nombres + " " + t.apellidos +
                    " (" + t.getClass().getSimpleName() + ") | Sueldo a Pagar: $" + t.calcularSueldo());
        }

        System.out.println("\n--- Verificacion de Jerarquia ---");
        System.out.println("El jefe de " + fijo1.nombres + " " + fijo1.apellidos 
                + " es: " + fijo1.jefe.nombres + " " + fijo1.jefe.apellidos);
    }
}

class Trabajador {
    protected String nombres;
    protected String apellidos;
    protected String direccion;
    protected String dni;
    protected Trabajador jefe;

    public Trabajador() {}

    public Trabajador(String nombres, String apellidos, String direccion, String dni) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
    }

    public double calcularSueldo() {
        return 0.0;
    }
}

class Jefe extends Trabajador {
    protected double sueldoFijo;

    public Jefe(String nombres, String apellidos, String direccion, String dni, 
            double sueldoFijo) {
        super(nombres, apellidos, direccion, dni);
        this.sueldoFijo = sueldoFijo;
    }

    @Override
    public double calcularSueldo() {
        return sueldoFijo;
    }
}

class TrabajadorFijo extends Trabajador {
    protected double sueldoMensual;

    public TrabajadorFijo(String nombres, String apellidos, String direccion,
            String dni, double sueldoMensual) {
        super(nombres, apellidos, direccion, dni);
        this.sueldoMensual = sueldoMensual;
    }



    @Override
    public double calcularSueldo() {
        return sueldoMensual;
    }
}

class TrabajadorComision extends Trabajador {
    protected int ventasRealizadas;
    protected double porcentajeComision;

    public TrabajadorComision(String nombres, String apellidos, String direccion,
            String dni, double porcentajeComision) {
        super(nombres, apellidos, direccion, dni);
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public double calcularSueldo() {
        return ventasRealizadas * porcentajeComision;
    }
}

class TrabajadorHoras extends Trabajador {
    protected int cantidadHoras;
    protected double precioPorHora;
    protected double precioPorHoraExtra;

    public TrabajadorHoras(String nombres, String apellidos, String direccion, 
            String dni, double precioPorHora, double precioPorHoraExtra) {
        super(nombres, apellidos, direccion, dni);
        this.precioPorHora = precioPorHora;
        this.precioPorHoraExtra = precioPorHoraExtra;
    }

    @Override
    public double calcularSueldo() {
        if (cantidadHoras <= 40) {
            return cantidadHoras * precioPorHora;
        } else {
            int horasExtra = cantidadHoras - 40;
            return (40 * precioPorHora) + (horasExtra * precioPorHoraExtra);
        }
    }
}