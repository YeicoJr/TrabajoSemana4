import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ventas {
    private int idProducto;
    private int cantidad;
    private double precioUnitario;


    public Ventas(int idProducto, int cantidad, double precioUnitario) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }


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

    // Método para registrar una venta en la base de datos
    public void registrarVenta() {
        try (Connection connection = Conexion.obtenerConexion()) {
            String sql = "INSERT INTO Ventas (idProducto, cantidad, precioUnitario) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, getIdProducto());
                statement.setInt(2, getCantidad());
                statement.setDouble(3, getPrecioUnitario());

                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Venta registrada en la base de datos.");
                } else {
                    System.out.println("Error al registrar la venta.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
