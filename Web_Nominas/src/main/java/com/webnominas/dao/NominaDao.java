package com.webnominas.dao;

import com.webnominas.conexion.Conexion;
import java.sql.*;


public class NominaDao {

	 	private Connection connection;
	    private PreparedStatement statement;

	    /**
	     * 
	     * @param dni
	     * @return
	     * @throws SQLException
	     */
	    public int obtenerSalarioPorDni(String dni) throws SQLException {
	        ResultSet resultSet = null;
	        int salario = 0;

	        String sql = "SELECT sueldo FROM nominas WHERE dni = ?";
	        connection = obtenerConexion();

	        try {
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, dni);
	            resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                salario = resultSet.getInt("sueldo");
	            }

	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return salario;
	    }
	    /**
	     * 
	     * @param dni
	     * @param salario
	     * @throws SQLException
	     */
	    public void crearNomina(String dni, int salario) throws SQLException {
	        String sql = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";
	        connection = obtenerConexion();

	        try {
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, dni);
	            statement.setInt(2, salario);

	            statement.executeUpdate();
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    
	    /**
	     * 
	     * @param dni
	     * @param nuevoSueldo
	     * @return
	     * @throws SQLException
	     */
	    public boolean actualizarSueldo(String dni, int nuevoSueldo) throws SQLException {
	        String sql = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
	        boolean actualizado = false;
	        connection = obtenerConexion();

	        try {
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, nuevoSueldo);
	            statement.setString(2, dni);
	            int filasActualizadas = statement.executeUpdate();
	            
	            // Verifica si se actualizó alguna fila
	            actualizado = filasActualizadas > 0;
	            
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return actualizado;
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
