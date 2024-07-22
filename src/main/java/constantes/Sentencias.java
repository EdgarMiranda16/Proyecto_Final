package constantes;

public class Sentencias {

    // Sentencia SQL para insertar un usuario
    public static final String INSERT_USUARIO = "INSERT INTO usuarios (perfil_id, usuario, password, email) VALUES (?, ?, ?, ?)";

    // Sentencia SQL para actualizar un usuario
    public static final String UPDATE_USUARIO = "UPDATE usuarios SET perfil_id = ?, usuario = ?, password = ?, email = ? WHERE id = ?";

    // Sentencia SQL para eliminar un usuario
    public static final String DELETE_USUARIO = "DELETE FROM usuarios WHERE id = ?";

    // Sentencia SQL para listar todos los usuarios
    public static final String LIST_USUARIO = """
            SELECT usuarios.id AS usuario_id, 
                   usuarios.usuario, 
                   usuarios.password, 
                   usuarios.email, 
                   usuarios.fecha_creacion, 
                   perfiles.id AS perfil_id, 
                   perfiles.nombre_perfil, 
                   perfiles.descripcion 
            FROM usuarios 
            INNER JOIN perfiles ON usuarios.perfil_id = perfiles.id
            """;

    // Sentencia SQL para obtener un usuario por su id
    public static final String GET_USUARIO = """
            SELECT usuarios.id AS usuario_id, 
                   usuarios.usuario,
                   usuarios.password, 
                   usuarios.email, 
                   usuarios.fecha_creacion, 
                   perfiles.id AS perfil_id, 
                   perfiles.nombre_perfil, 
                   perfiles.descripcion 
            FROM usuarios 
            INNER JOIN perfiles ON usuarios.perfil_id = perfiles.id
            WHERE usuarios.id = ?
            """;

    // Sentencia SQL para iniciar sesión
    public static final String LOGIN_USUARIO = """
            SELECT usuarios.id AS usuario_id, 
                   usuarios.usuario,
                   usuarios.password, 
                   usuarios.email, 
                   usuarios.fecha_creacion, 
                   perfiles.id AS perfil_id, 
                   perfiles.nombre_perfil, 
                   perfiles.descripcion 
            FROM usuarios 
            INNER JOIN perfiles ON usuarios.perfil_id = perfiles.id 
            WHERE usuario = ? AND password = ?
            """;

    // Sentencia SQL para listar todos perfiles
    public static final String LIST_PERFIL = """
            SELECT perfiles.id, 
                   perfiles.nombre_perfil, 
                   perfiles.descripcion
            FROM perfiles
            """;

    // Sentencia SQL para obtener un perfil por su id
    public static final String GET_PERFIL = "SELECT * FROM perfiles WHERE id = ?";

    // Sentencia SQL para insertar un cliente
    public static final String INSERT_CLIENTE = "INSERT INTO clientes (nombres, apellidos, direccion, telefono, email) VALUES (?, ?, ?, ?, ?)";

    // Sentencia SQL para actualizar un cliente
    public static final String UPDATE_CLIENTE = "UPDATE clientes SET nombres = ?, apellidos = ?, direccion = ?, telefono = ?, email = ? WHERE id = ?";

    // Sentencia SQL para eliminar un cliente
    public static final String DELETE_CLIENTE = "DELETE FROM clientes WHERE id = ?";

    // Sentencia SQL para listar todos los clientes
    public static final String LIST_CLIENTE = "SELECT * FROM clientes";

    // Sentencia SQL para actualizar el stock de un producto
    public static final String UPDATE_STOCK_PRODUCTO = "UPDATE productos SET stock = ? WHERE id = ?";

    // Sentencia SQL para obtener un cliente por su id
    public static final String GET_CLIENTE = "SELECT * FROM clientes WHERE id = ?";

    // Sentencia SQL para insertar un producto
    public static final String INSERT_PRODUCTO = "INSERT INTO productos (nombre_producto, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

    // Sentencia SQL para actualizar un producto
    public static final String UPDATE_PRODUCTO = "UPDATE productos SET nombre_producto = ?, descripcion = ?, precio = ?, stock = ? WHERE id = ?";

    // Sentencia SQL para eliminar un producto
    public static final String DELETE_PRODUCTO = "DELETE FROM productos WHERE id = ?";

    // Sentencia SQL para listar todos los productos
    public static final String LIST_PRODUCTO = "SELECT * FROM productos";

    // Sentencia SQL para obtener un producto por su id
    public static final String GET_PRODUCTO = "SELECT * FROM productos WHERE id = ?";

    // Sentencia SQL para insertar una venta
    public static final String INSERT_VENTA = "INSERT INTO ventas (cliente_id, usuario_id, total) VALUES (?, ?, ?)";

    // Sentencia SQL para listar todos las ventas
    public static final String LIST_VENTA = """
            SELECT ventas.id AS venta_id,
                ventas.fecha_venta,
                ventas.total,
                clientes.id AS cliente_id,
                clientes.nombres AS cliente_nombres,
                clientes.apellidos AS cliente_apellidos,
                clientes.direccion AS cliente_direccion,
                clientes.telefono AS cliente_telefono,
                clientes.email AS cliente_email
            FROM ventas
            INNER JOIN clientes ON ventas.cliente_id = clientes.id;
            """;

    // Sentencia SQL para obtener una venta por su id
    public static final String GET_VENTA = """
            SELECT ventas.id AS venta_id,
                ventas.fecha_venta,
                ventas.total,
                clientes.id AS cliente_id,
                CONCAT(clientes.nombres, ' ', clientes.apellidos) AS cliente_nombre,
                clientes.direccion AS cliente_direccion,
                clientes.telefono AS cliente_telefono,
                clientes.email AS cliente_email,
                detalle_ventas.id AS detalle_id,
                productos.id AS producto_id,
                productos.nombre_producto,
                productos.descripcion AS producto_descripcion,
                detalle_ventas.cantidad,
                detalle_ventas.precio_unitario,
                detalle_ventas.subtotal
            FROM ventas
            INNER JOIN detalle_ventas ON ventas.id = detalle_ventas.venta_id
            INNER JOIN clientes ON ventas.cliente_id = clientes.id
            INNER JOIN productos ON detalle_ventas.producto_id = productos.id
            WHERE ventas.id = ?;
            """;

    // Sentencia SQL para insertar un detalle de venta
    public static final String INSERT_DETALLE_VENTA = "INSERT INTO detalle_ventas (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

    // Sentencia SQL para listar todos los detalles de venta
    public static final String LIST_DETALLE_VENTA = """
            SELECT ventas.id AS venta_id,
                                                    ventas.fecha_venta,
                                                    ventas.total,
                                                    clientes.id AS cliente_id,
                                                    clientes.nombres AS cliente_nombres,
                                                    clientes.apellidos AS cliente_apellidos,
                                                    clientes.direccion AS cliente_direccion,
                                                    clientes.telefono AS cliente_telefono,
                                                    clientes.email AS cliente_email,
                                                    productos.id AS producto_id,
                                                    productos.nombre_producto,
                                                    productos.descripcion AS producto_descripcion,
                                                    productos.precio as precio_producto,
                                                    productos.stock as stock_producto,
                                                    detalle_ventas.id AS detalle_id,
                                                    detalle_ventas.cantidad,
                                                    detalle_ventas.precio_unitario,
                                                    detalle_ventas.subtotal,
                                                    usuarios.id AS usuario_id,
                                                    usuarios.usuario AS usuario_registro
                                                FROM ventas
                                                INNER JOIN detalle_ventas ON ventas.id = detalle_ventas.venta_id
                                                INNER JOIN clientes ON ventas.cliente_id = clientes.id
                                                INNER JOIN productos ON detalle_ventas.producto_id = productos.id
                                                INNER JOIN usuarios ON ventas.usuario_id = usuarios.id
                                                ORDER BY ventas.fecha_venta DESC;
            """;

}
