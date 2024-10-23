package com.webnominas.dao;


import com.webnominas.conexion.Conexion;
import com.webnominas.model.Empleados;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    /**
     * Obtiene un empleado por su DNI.
     *
     * @param dni el DNI del empleado a buscar.
     * @return el objeto Empleado si se encuentra; null en caso contrario.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public Empleados obtenerEmpleadoPorDni(String dni) throws SQLException {
        ResultSet resultSet = null;
        Empleados empleado = null;

        String sql = "SELECT * FROM empleados WHERE dni = ?";
        connection = obtenerConexion();

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                empleado = new Empleados();
                empleado.setId(resultSet.getInt("id"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setDni(resultSet.getString("dni"));
                empleado.setSexo(resultSet.getString("sexo").charAt(0));
                empleado.setCategoria(resultSet.getInt("categoria"));
                empleado.setAnyos(resultSet.getInt("anyos"));
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleado;
    }

    /**
     * Edita un empleado existente en la base de datos.
     *
     * @param empleado el objeto Empleado que contiene la información actualizada.
     * @return true si el empleado se edita correctamente; false en caso contrario.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public boolean actualizarEmpleado(Empleados empleado) throws SQLException {
        String sql = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
        Connection connection = obtenerConexion();
        PreparedStatement statement = null;
        boolean estadoOperacion = false;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, empleado.getNombre());
            statement.setString(2, String.valueOf(empleado.getSexo()));
            statement.setInt(3, empleado.getCategoria());
            statement.setInt(4, empleado.getAnyos());
            statement.setString(5, empleado.getDni());

            // Verifica si se actualizó alguna fila
            int filasActualizadas = statement.executeUpdate();
            estadoOperacion = (filasActualizadas > 0);
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return estadoOperacion;
    }


    /**
     * Elimina un empleado de la base de datos por su DNI.
     *
     * @param dni el DNI del empleado a eliminar.
     * @return true si el empleado se elimina correctamente; false en caso contrario.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public boolean eliminar(int ip) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();
        try {
            connection.setAutoCommit(false);
            sql = "DELETE FROM empleados WHERE ip=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, ip);

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    

    /**
     * 
     * @param sexo
     * @param nombre
     * @param categoria
     * @param anyos
     * @return
     * @throws SQLException
     */
    public List<Empleados> filtrarEmpleados(String sexo, String nombre, Integer categoria, Integer anyos) throws SQLException {
        List<Empleados> listaEmpleados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM empleados WHERE 1=1");

        if (sexo != null && !sexo.isEmpty()) {
            sql.append(" AND sexo = ?");
        }

        if (nombre != null && !nombre.isEmpty()) {
            sql.append(" AND nombre LIKE ?");
        }

        if (categoria != null) {
            sql.append(" AND categoria = ?");
        }

        if (anyos != null) {
            sql.append(" AND anyos = ?");
        }

        connection = obtenerConexion();
        try {
            statement = connection.prepareStatement(sql.toString());
            int index = 1;

            if (sexo != null && !sexo.isEmpty()) {
                statement.setString(index++, sexo);
            }

            if (nombre != null && !nombre.isEmpty()) {
                statement.setString(index++, "%" + nombre + "%");
            }

            if (categoria != null) {
                statement.setInt(index++, categoria);
            }

            if (anyos != null) {
                statement.setInt(index++, anyos);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Empleados empleado = new Empleados();
                empleado.setId(resultSet.getInt("id"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setDni(resultSet.getString("dni"));
                empleado.setSexo(resultSet.getString("sexo").charAt(0));
                empleado.setCategoria(resultSet.getInt("categoria"));
                empleado.setAnyos(resultSet.getInt("anyos"));
                listaEmpleados.add(empleado);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEmpleados;
    }
    /**
     * 
     * @param empleado
     * @throws SQLException
     */
    public void crearEmpleado(Empleados empleado) throws SQLException {
        String sql = "INSERT INTO empleados (nombre, dni, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
        connection = obtenerConexion();

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getDni());
            statement.setString(3, String.valueOf(empleado.getSexo()));
            statement.setInt(4, empleado.getCategoria());
            statement.setInt(5, empleado.getAnyos());

            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    /**
     * Obtiene una lista de todos los empleados en la base de datos.
     *
     * @return una lista de objetos Empleado.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public List<Empleados> obtenerEmpleados() throws SQLException {
        ResultSet resultSet = null;
        List<Empleados> listaEmpleados = new ArrayList<>();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM empleados";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Empleados empleado = new Empleados();
                empleado.setId(resultSet.getInt("id"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setDni(resultSet.getString("dni"));
                empleado.setSexo(resultSet.getString("sexo").charAt(0));
                empleado.setCategoria(resultSet.getInt("categoria"));
                empleado.setAnyos(resultSet.getInt("anyos"));
                listaEmpleados.add(empleado);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEmpleados;
    }

    /**
     * 
     * @return
     * @throws SQLException
     */
    private Connection obtenerConexion() throws SQLException {
        // Asegúrate de ajustar esta línea para usar tu propia clase de conexión o detalles
        return Conexion.getConnection();
    }
}