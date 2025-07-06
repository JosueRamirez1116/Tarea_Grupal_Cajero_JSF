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
    private Double  monto;
    private String mensaje="";
    private Double nuevoSaldo;
    private String value;
    private String text;
    private String base64;

    public CajeroBean() {
        clientes = new ArrayList<>();
        clientes.add(new Cliente("1001", "1234", 1000, "Ana García"));
        clientes.add(new Cliente("1002", "5678", 1500,"Julio Lopez"));
        clientes.add(new Cliente("1003", "1111", 200,"Diana Pavon"));
        clientes.add(new Cliente("1004", "2222", 3500,"Saul Guardado"));
        clientes.add(new Cliente("1005", "3333", 500,"Jose Deras"));
    }

    public String irAConsulta() {
        mensaje = null;
        cuenta = null;
        pin = null;
        return "consultar.xhtml?faces-redirect=true";
    }


    public String retirar() {
        this.mensaje = null;
        Cliente c = buscarCliente();
        if (c == null || !c.getPin().equals(pin)) {
            mensaje = "PIN o cuenta incorrecta.";
            return "resultado";
        }
        if (monto == null || monto <= 0) {
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
        monto = null;
        pin = "";
        text="";
        value="";
        base64="";

        return "resultado";
    }

    public void consultar() {
        mensaje="";
        Cliente cliente = buscarCliente(); // tu lógica
        if (cliente != null && cliente.getPin().equals(pin)) {
            mensaje = "Saldo disponible: L " + cliente.getSaldo();
        } else {
            mensaje = "❌ Cuenta o PIN incorrectos.";
        }

        cuenta = "";
        pin = "";
    }


    public String depositar() {
        this.mensaje = null;
        Cliente c = buscarCliente();

        if (c == null || !c.getPin().equals(pin)) {
            mensaje = "❌ PIN o cuenta incorrecta.";
            return "resultado";
        }

        if (monto == null || monto <= 0) {
            mensaje = "❌ El monto debe ser mayor que cero.";
            return "resultado";
        }

        c.setSaldo(c.getSaldo() + monto);
        nuevoSaldo = c.getSaldo();
        mensaje = "✅ Depósito realizado con éxito. Nuevo saldo: L " + nuevoSaldo;

        cuenta = "";
        monto = null;
        pin = "";
        text = "";
        value = "";
        base64 = "";

        return "resultado";
    }


    private Cliente buscarCliente() {
        this.mensaje=null;
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

    public Double  getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Double getNuevoSaldo() {
        return nuevoSaldo;
    }

    public void setNuevoSaldo(Double nuevoSaldo) {
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
