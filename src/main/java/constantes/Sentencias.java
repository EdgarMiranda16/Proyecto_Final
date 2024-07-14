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

}
