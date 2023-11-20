import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Compras {
    private int idProducto;
    private int cantidad;
    private double precioUnitario;

   

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void registrarCompra() {
        try (Connection connection = Conexion.obtenerConexion()) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese el ID del producto:");
            setIdProducto(scanner.nextInt());

            System.out.println("Ingrese la cantidad de productos:");
            setCantidad(scanner.nextInt());

            System.out.println("Ingrese el precio unitario:");
            setPrecioUnitario(scanner.nextDouble());

            String sql = "INSERT INTO Compras (idProducto, cantidad, precioUnitario) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, getIdProducto());
                statement.setInt(2, getCantidad());
                statement.setDouble(3, getPrecioUnitario());

                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Compra registrada en la base de datos.");
                } else {
                    System.out.println("Error al registrar la compra.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Compras compra = new Compras();
        compra.registrarCompra();
    }
}
