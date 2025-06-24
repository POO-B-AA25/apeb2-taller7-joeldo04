import java.util.ArrayList;
import java.util.Scanner;

public class Problema6_EjecutorSistemaCuentaBanco {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<CuentaBancaria> cuentas = new ArrayList<>();

        cuentas.add(new CuentaCheques("111-222", "Juan Perez"));
        cuentas.add(new CuentaAhorros("333-444", "Maria Garcia", 0.05));
        cuentas.add(new CuentaPlatino("555-666", "Carlos Lopez"));
        
        cuentas.get(0).depositar(500);
        cuentas.get(1).depositar(1000);
        cuentas.get(2).depositar(2000);
        System.out.println("Cuentas creadas");


        int opcion = 0;
        while (opcion != 5) {
            System.out.println("--- MENU PRINCIPAL - UN BANCO ---");
            System.out.println("1. Realizar Deposito");
            System.out.println("2. Realizar Retiro");
            System.out.println("3. Calcular Intereses del Mes");
            System.out.println("4. Mostrar Saldos de Todas las Cuentas");
            System.out.println("5. Salir");
            System.out.print("Elija una opcion: ");

            opcion = teclado.nextInt();
            teclado.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Seleccione la cuenta para depositar (1-" + cuentas.size() + "): ");
                    int cuentaDepositoIdx = teclado.nextInt() - 1;
                    teclado.nextLine();
                    
                    if (cuentaDepositoIdx >= 0 && cuentaDepositoIdx < cuentas.size()) {
                        CuentaBancaria cuentaADepositar = cuentas.get(cuentaDepositoIdx);
                        
                        System.out.print("Ingrese el monto a depositar en la cuenta de " + cuentaADepositar.nombreCliente + ": ");
                        double montoDeposito = teclado.nextDouble();
                        teclado.nextLine();
                        
                        cuentaADepositar.depositar(montoDeposito);
                        System.out.println("Deposito realizado con exito.");
                    } else {
                        System.out.println("Numero de cuenta invalido.");
                    }
                    break;

                case 2:
                    System.out.print("Seleccione la cuenta para retirar (1-" + cuentas.size() + "): ");
                    int cuentaRetiroIdx = teclado.nextInt() - 1;
                    teclado.nextLine();

                    if (cuentaRetiroIdx >= 0 && cuentaRetiroIdx < cuentas.size()) {
                        CuentaBancaria cuentaSeleccionada = cuentas.get(cuentaRetiroIdx);
                        
                        System.out.print("Ingrese el monto a retirar de la cuenta de " + cuentaSeleccionada.nombreCliente + ": ");
                        double montoRetiro = teclado.nextDouble();
                        teclado.nextLine();

                        if (cuentaSeleccionada instanceof CuentaCheques) {
                            ((CuentaCheques) cuentaSeleccionada).retirar(montoRetiro);
                        } else if (cuentaSeleccionada instanceof CuentaAhorros) {
                            ((CuentaAhorros) cuentaSeleccionada).retirar(montoRetiro);
                        } else if (cuentaSeleccionada instanceof CuentaPlatino) {
                           ((CuentaPlatino) cuentaSeleccionada).retirar(montoRetiro);
                        }
                    
                    } else {
                        System.out.println("Numero de cuenta invalido.");
                    }
                    break;

                case 3:
                    for (CuentaBancaria cuenta : cuentas) {
                        if (cuenta instanceof CuentaAhorros) {
                            ((CuentaAhorros) cuenta).calcularInteres();
                        } else if (cuenta instanceof CuentaPlatino) {
                            ((CuentaPlatino) cuenta).calcularInteres();
                        }
                    }
                    System.out.println("Intereses calculados y agregados.");
                    break;

                case 4:
                    System.out.println("\n--- ESTADO DE CUENTAS ---");
                    for (int i = 0; i < cuentas.size(); i++) {
                        System.out.println((i + 1) + ". " + cuentas.get(i));
                    }
                    break;

                case 5:
                    System.out.println("Gracias por usar los servicios de UN BANCO.");
                    break;
                    
                default:
                    System.out.println("Opcion no valida.");
            }
            System.out.println("---------------------------------");
        }
        teclado.close();
    }
}

class CuentaBancaria {
    protected String numeroCuenta;
    protected String nombreCliente;
    protected double saldo;

    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.saldo = 0.0;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
        }
    }
    
    public void retirar(double monto) {
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
        } else {
            System.out.println("AVISO: Saldo insuficiente. Operacion denegada.");
        }
    }

    @Override
    public String toString() {
        return "Cuenta: " + this.numeroCuenta + " | Cliente: " + this.nombreCliente + 
               " | Saldo: $" + String.format("%.2f", this.saldo);
    }
}


class CuentaCheques extends CuentaBancaria {
    public CuentaCheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            this.saldo -= monto;
            System.out.println("Retiro de $" + monto + " realizado. Nuevo saldo: $" + String.format("%.2f", this.saldo));
        }
    }

    public boolean estaSobregirada() {
        return this.saldo < 0;
    }

    @Override
    public String toString() {
        String estado = super.toString() + " | Tipo: Cheques";
        if (estaSobregirada()) {
            estado += " (SOBREGIRADA)";
        }
        return estado;
    }
}


class CuentaAhorros extends CuentaBancaria {
    protected double tasaInteres;

    public CuentaAhorros(String numeroCuenta, String nombreCliente, double tasaInteres) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
            System.out.println("Retiro de $" + monto + " realizado. Nuevo saldo: $" + String.format("%.2f", this.saldo));
        } else {
            System.out.println("Retiro denegado. Saldo insuficiente.");
        }
    }

    public void calcularInteres() {
        double interesGenerado = this.saldo * this.tasaInteres;
        this.saldo += interesGenerado;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Tipo: Ahorros";
    }
}


class CuentaPlatino extends CuentaBancaria {
    protected double tasaInteres = 0.10;

    public CuentaPlatino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            this.saldo -= monto;
            System.out.println("Retiro de $" + monto + " realizado. Nuevo saldo: $" + String.format("%.2f", this.saldo));
        }
    }

    public void calcularInteres() {
        if (this.saldo > 0) {
            double interesGenerado = this.saldo * this.tasaInteres;
            this.saldo += interesGenerado;
        }
    }

    public boolean estaSobregirada() {
        return this.saldo < 0;
    }
    
    @Override
    public String toString() {
        String estado = super.toString() + " | Tipo: Platino";
        if (estaSobregirada()) {
            estado += " (SOBREGIRADA)";
        }
        return estado;
    }
}