import java.util.Date;

public class Transaccion {
    private int idTransaccion;
    private Date fechaHora;
    private int idSucursal;

    public Transaccion(int idTransaccion, Date fechaHora, int idSucursal) {
        this.idTransaccion = idTransaccion;
        this.fechaHora = fechaHora;
        this.idSucursal = idSucursal;
    }

    // Resto de los métodos y propiedades...

    // Método para realizar una compra y venta
    public void realizarCompraVenta(int idProducto, int cantidad, double precioUnitario) {
        // Lógica para realizar la compra
        Compras compra = new Compras();
        compra.setIdProducto(idProducto);
        compra.setCantidad(cantidad);
        compra.setPrecioUnitario(precioUnitario);
        compra.registrarCompra();

        // Lógica para realizar la venta
        Ventas venta = new Ventas(cantidad, cantidad, precioUnitario);
        venta.setIdProducto(idProducto);
        venta.setCantidad(cantidad);
        venta.setPrecioUnitario(precioUnitario);
        venta.registrarVenta();

        System.out.println("Compra y venta realizadas en la transacción.");
        
        
    }
    public static void main(String[] args) {
        System.out.println("Ejecutando la clase Transaccion");

        
        Transaccion transaccion = new Transaccion(1, new Date(), 101);
        transaccion.realizarCompraVenta(1, 3, 29.99);
    }

}
