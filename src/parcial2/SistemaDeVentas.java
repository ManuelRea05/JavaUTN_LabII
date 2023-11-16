package parcial2;
import java.sql.*;

public class SistemaDeVentas {
    public static void main(String[] args) {
        Comerciales comercial = new Comerciales();
        Vendedor vendedorBuscado = new Vendedor("SELECT * FROM `vendedores` WHERE `vendedor_id` = 3");
        Producto productoMasVendido = Productos.obtenerProductoMasVendido();

        //datos del vendedor con consulta
        System.out.println("Nombre: " + vendedorBuscado.getNombre());
        System.out.println("");

        // obtener vendedor por id
        Vendedor vendedor = Comerciales.obtenerVendedorPorID(2);
        if (vendedor != null) {
            System.out.println("Detalles del vendedor:");
            System.out.println(vendedor);
        } else {
            System.out.println("No se encontró ningún vendedor con ese ID");
    }
        System.out.println("");

        // informe
        comercial.generarInforme();
        System.out.println("");

        //productos
        Producto producto = Productos.obtenerProducto(1);
        if (producto != null){
            System.out.println("Detalles del producto:");
            System.out.println(producto);
        }

        System.out.println("");
        if (productoMasVendido != null) {
            System.out.println("Detalles del producto mas vendido:");
            System.out.println(productoMasVendido);
        } else {
            System.out.println("No se encontró el producto mas vendido.");
        }

    }
}
class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:33061/ventas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para ejecutar una consulta sin devolver resultados
    public static void ejecutarConsulta(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la declaración
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                // Ejecutar la consulta
                statement.executeUpdate();
            }
            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar una consulta y devolver un conjunto de resultados
    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Crear la declaración
            PreparedStatement statement = connection.prepareStatement(consulta);

            // Ejecutar la consulta y devolver el conjunto de resultados
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class Producto{
    private int idProduc;
    private String nombre;
    private Double precioPorU;
    private int stock;

    public Producto(int idProduc, String nombre, Double precioPorU, int stock) {
        this.idProduc = idProduc;
        this.nombre = nombre;
        this.precioPorU = precioPorU;
        this.stock = stock;
    }

    public int getIdProduc() {
        return idProduc;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecioPorU() {
        return precioPorU;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id= " + idProduc +
                ", nombre=' " + nombre + '\'' +
                ", precioPorU =" + precioPorU +
                ", stock =" + stock +
                '}';
    }
}

class Vendedor{
    private int idVendedor;
    private String nombre;
    private String apellido;
    private int dni;
    private Date fechaNacimiento;
    private Date fechaContratacion;

    public Vendedor(int idVendedor, String nombre, String apellido, int dni, Date fechaNacimiento, Date fechaContratacion) {
        this.idVendedor = idVendedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContratacion = fechaContratacion;
    }

    public Vendedor(String consultaBusqueda) {
        try {
            // DBHelper
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consultaBusqueda);

            // Si se encuentra un vendedor con el ID, inicializa los datos del vendedor
            if (resultado != null && resultado.next()) {
                this.idVendedor = resultado.getInt("vendedor_id");
                this.nombre = resultado.getString("nombre");
                this.apellido = resultado.getString("apellido");
                this.dni = resultado.getInt("dni");
                this.fechaNacimiento = resultado.getDate("fecha_nacimiento");
                this.fechaContratacion = resultado.getDate("fecha_contratacion");
            } else {
                System.out.println("No se encontró ningún vendedor.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vendedor() {

    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "id= " + idVendedor +
                ", nombre= '"+nombre + '\'' +
                ", apellido= '"+ apellido + '\'' +
                ", dni= "+ dni +
                ", fechaNacimiento= " + fechaNacimiento +
                ", fechaContratacion =" + fechaContratacion +
                '}';
    }
}

class Comerciales {
    public static Vendedor obtenerVendedorPorID(int vendedorID) {
        // Utiliza la clase DBHelper para ejecutar la consulta SQL correspondiente.
        String consulta = "SELECT * FROM vendedores WHERE vendedor_id = '" + vendedorID + "'";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        try {
            if (resultado != null && resultado.next()) {
                int id = resultado.getInt("vendedor_id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                int dni = resultado.getInt("dni");
                Date fechaNac = resultado.getDate("fecha_nacimiento");
                Date fechaCont = resultado.getDate("fecha_contratacion");

                // Crear objeto
                Vendedor vendedor = new Vendedor(id, nombre, apellido, dni, fechaNac, fechaCont);

                return vendedor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void generarInforme() {
        String consulta = "SELECT * FROM productos";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        double valorTotalProductos = 0.0;

        System.out.println("Informe de Productos en Stock:");
        System.out.printf("%-32s %-8s %-10s %-10s\n", "Producto", "Stock", "Precio", "Total");
        System.out.println("------------------------------------------");

        try {
            while (resultado.next()) {
                String nombreProducto = resultado.getString("nombre");
                int stock = resultado.getInt("stock");
                double precioPorUnidad = resultado.getDouble("precio_por_unidad");
                double valorTotalProducto = precioPorUnidad * stock;

                System.out.printf("%-32s %-8d %-10.2f %-10.2f\n", nombreProducto, stock, precioPorUnidad, valorTotalProducto);

                // valor total
                valorTotalProductos += valorTotalProducto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------------------");
        System.out.printf("%50s%.2f\n", "Total:", valorTotalProductos);
    }
}

class Productos {

    public static Producto obtenerProducto(int productoID) {
        String consulta = "SELECT * FROM productos WHERE producto_id = '" + productoID + "'";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        try {
            if (resultado != null && resultado.next()) {
                int id = resultado.getInt("producto_id");
                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio_por_unidad");
                int stock = resultado.getInt("stock");

                Producto producto = new Producto(id, nombre, precio, stock);

                return producto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Producto obtenerProductoMasVendido() {
        String consulta = "SELECT producto_id, SUM(cantidad_vendida) as total_vendido " +
                "FROM ventas " +
                "GROUP BY producto_id " +
                "ORDER BY total_vendido DESC " +
                "LIMIT 1";

        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        try {
            if (resultado != null && resultado.next()) {
                int productoID = resultado.getInt("producto_id");
                return obtenerProducto(productoID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




}









