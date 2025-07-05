package uth.hn.cajero_grupo8.beans;


import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import uth.hn.cajero_grupo8.model.Cliente;
import java.util.ArrayList;
import java.util.List;


@Named("cajero")
@SessionScoped
public class CajeroBean implements Serializable {

    private List<Cliente> clientes;
    private String cuenta="";
    private String pin="";
    private double monto;
    private String mensaje="";
    private double nuevoSaldo=0.0;
    private String value;
    private String text;
    private String base64;
    private String prueba;

    public CajeroBean() {
        clientes = new ArrayList<>();
        clientes.add(new Cliente("1001", "1234", 1000, "Ana García"));
        clientes.add(new Cliente("1002", "5678", 1500,"Julio Lopez"));
        clientes.add(new Cliente("1003", "1111", 200,"Diana Pavon"));
        clientes.add(new Cliente("1004", "2222", 3500,"Saul Guardado"));
        clientes.add(new Cliente("1005", "3333", 500,"Jose Deras"));
    }

    public String consultar() {
        Cliente c = buscarCliente();
        if (c == null || !c.getPin().equals(pin)) {
            mensaje = "Cuenta o PIN incorrecto.";
        } else {
            mensaje = "Bienvenido " + c.getNombre() + ", su saldo es: L " + c.getSaldo();
        }
        cuenta = "";
        pin = "";
        text="";
        value="";
        base64="";
        mensaje="";
        return null; // quedarse en la misma página
    }


    public String retirar() {
        Cliente c = buscarCliente();
        if (c == null || !c.getPin().equals(pin)) {
            mensaje = "PIN o cuenta incorrecta.";
            return "resultado";
        }
        if (monto <= 0) {
            mensaje = "El monto debe ser mayor que cero.";
            return "resultado";
        }
        if (monto > c.getSaldo()) {
            mensaje = "Saldo insuficiente.";
            return "resultado";
        }
        c.setSaldo(c.getSaldo() - monto);
        nuevoSaldo = c.getSaldo();
        mensaje = "Retiro exitoso. Nuevo saldo: L " + nuevoSaldo;
        cuenta = "";
        pin = "";
        monto = 0;
        text="";
        value="";
        base64="";
        mensaje="";
        return "resultado";
    }

    public String depositar() {
        Cliente c = buscarCliente();
        if (c == null || !c.getPin().equals(pin)) {
            mensaje = "PIN o cuenta incorrecta.";
            return "resultado";
        }
        if (monto <= 0) {
            mensaje = "El monto debe ser mayor que cero.";
            return "resultado";
        }
        c.setSaldo(c.getSaldo() + monto);
        nuevoSaldo = c.getSaldo();
        mensaje = "Depósito exitoso. Nuevo saldo: L " + nuevoSaldo;
        cuenta = "";
        pin = "";
        monto = 0;
        text="";
        value="";
        base64="";
        mensaje="";
        return "resultado";
    }

    private Cliente buscarCliente() {
        for (Cliente c : clientes) {
            if (c.getNumeroCuenta().equals(cuenta)) return c;
        }
        return null;
    }

    // Getters y Setters para cuenta, pin, monto, mensaje, nuevoSaldo

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public double getNuevoSaldo() {
        return nuevoSaldo;
    }

    public void setNuevoSaldo(double nuevoSaldo) {
        this.nuevoSaldo = nuevoSaldo;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
