package com.webnominas.controlador;

import com.webnominas.dao.EmpleadoDao;
import com.webnominas.dao.NominaDao;
import com.webnominas.model.Empleados;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class EmpleadoController
 * <p>
 * Este servlet se encarga de administrar las peticiones relacionadas con la tabla de empleados.
 * Permite realizar operaciones como crear, listar, editar y eliminar empleados.
 * </p>
 */
@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = { "/empleados" })
public class EmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmpleadoController() {
        super();
    }

    /**
     * Método que maneja las peticiones HTTP GET.
     * <p>
     * Dependiendo del parámetro "opcion", este método puede crear, listar, editar o eliminar empleados.
     * </p>
     *
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en la servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        switch (opcion) {

            case "listar":
                listarEmpleados(request, response);
                break;
            
                
            case "buscarSalario":
            	buscarSalarioPorDni(request, response);
                break;
                
            case "actualizarEmpleado":
            	buscarEmpleadoPorDni(request, response);
                break;
            
            case "filtrar":
            	filtrarEmpleados(request, response);
            	break;
            	
            case "crear":
            	crearEmpleado(request, response);
            	break;
            	
                
            default:
                ;
        }
    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpleadoDao empleadoDAO = new EmpleadoDao();
        List<Empleados> lista = new ArrayList<>();
        try {
            lista = empleadoDAO.obtenerEmpleados();
            for (Empleados empleado : lista) {
                System.out.println(empleado);
            }

            request.setAttribute("listaEmpleados", lista);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listarEmpleados.jsp");
            requestDispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void buscarSalarioPorDni(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        int salario = 0;
        NominaDao nominaDAO = new NominaDao();

        try {
            // Llamar al método obtenerSalarioPorDni del DAO
            salario = nominaDAO.obtenerSalarioPorDni(dni);
            if (salario == 0) {
                request.setAttribute("error", "Salario no encontrado para el empleado con ese DNI.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al buscar el salario.");
        }

        // Pasar el salario al JSP
        request.setAttribute("dni", dni);
        request.setAttribute("salario", salario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/buscarEmpleado.jsp");
        dispatcher.forward(request, response);
    }
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void buscarEmpleadoPorDni(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        Empleados empleado = null;
        EmpleadoDao empleadoDAO = new EmpleadoDao();

        try {
            empleado = empleadoDAO.obtenerEmpleadoPorDni(dni);
            if (empleado == null) {
                request.setAttribute("error", "Empleado no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al buscar el empleado.");
        }

        request.setAttribute("empleado", empleado);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/modificarEmpleado.jsp");
        dispatcher.forward(request, response);
    }
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni"); // Capturamos el DNI del empleado
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anyos = Integer.parseInt(request.getParameter("anyos"));

        Empleados empleadoActualizado = new Empleados();
        empleadoActualizado.setDni(dni); // No se cambia
        empleadoActualizado.setNombre(nombre);
        empleadoActualizado.setSexo(sexo.charAt(0));
        empleadoActualizado.setCategoria(categoria);
        empleadoActualizado.setAnyos(anyos);

        EmpleadoDao empleadoDAO = new EmpleadoDao();
        try {
            boolean resultado = empleadoDAO.actualizarEmpleado(empleadoActualizado);
            int nuevoSueldo = empleadoActualizado.calcularSueldo();
            NominaDao nominaDao = new NominaDao();
			nominaDao.actualizarSueldo(dni, nuevoSueldo);
            if (resultado) {
            	
                request.setAttribute("mensaje", "Empleado actualizado correctamente.");
            } else {
                request.setAttribute("error", "Error al actualizar el empleado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al acceder a la base de datos.");
        }

        // Redirigir a la vista correspondiente
        
        empleadoDAO = new EmpleadoDao();
        List<Empleados> lista = new ArrayList<>();
            try {
				lista = empleadoDAO.obtenerEmpleados();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            for (Empleados empleado : lista) {
                System.out.println(empleado);
            }
        
        request.setAttribute("listaEmpleados", lista);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listarEmpleados.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void filtrarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sexo = request.getParameter("sexo");
        String nombre = request.getParameter("nombre");
        String categoriaStr = request.getParameter("categoria");
        String anyosStr = request.getParameter("anyos");
        Integer categoria = null;
        Integer anyos = null;

        // Convertir la categoría y años a Integer si están presentes
        if (categoriaStr != null && !categoriaStr.isEmpty()) {
            categoria = Integer.parseInt(categoriaStr);
        }

        if (anyosStr != null && !anyosStr.isEmpty()) {
            anyos = Integer.parseInt(anyosStr);
        }

        EmpleadoDao empleadoDAO = new EmpleadoDao();
        List<Empleados> listaEmpleadosFiltrados = new ArrayList<>();

        try {
            listaEmpleadosFiltrados = empleadoDAO.filtrarEmpleados(sexo, nombre, categoria, anyos); 
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al filtrar los empleados.");
        }

        request.setAttribute("empleados", listaEmpleadosFiltrados);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/filtrar.jsp");
        dispatcher.forward(request, response);
    }
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void crearEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/views/crear.jsp");
        dispatcher.forward(request, response);
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        String sexo = request.getParameter("sexo");
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        int anyos = Integer.parseInt(request.getParameter("anyos"));

        EmpleadoDao empleadoDAO = new EmpleadoDao();
        NominaDao nominaDAO = new NominaDao();

        try {
            // Crear el empleado y obtener su ID generado
            Empleados nuevoEmpleado = new Empleados();
            nuevoEmpleado.setNombre(nombre);
            nuevoEmpleado.setDni(dni);
            nuevoEmpleado.setSexo(sexo.charAt(0));
            nuevoEmpleado.setCategoria(categoria);
            nuevoEmpleado.setAnyos(anyos);

            // Guardar el nuevo empleado
            empleadoDAO.crearEmpleado(nuevoEmpleado);

            // Calcular el sueldo del nuevo empleado
            int sueldo = nuevoEmpleado.calcularSueldo();

            // Guardar la nómina del nuevo empleado
            nominaDAO.crearNomina(dni, sueldo);
            
            request.setAttribute("success", "Empleado añadido correctamente.");
            
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al crear el empleado.");
            dispatcher = request.getRequestDispatcher("/views/crear.jsp");
            dispatcher.forward(request, response);
        }
    }

    
    /**
     * Método que maneja las peticiones HTTP POST.
     * <p>
     * Dependiendo del parámetro "opcion", este método puede guardar o editar un empleado.
     * </p>
     *
     * @param request  la solicitud HTTP
     * @param response la respuesta HTTP
     * @throws ServletException si ocurre un error en la servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        if ("actualizarEmpleado".equals(opcion)) {
        	actualizarEmpleado(request, response);
        } else if ("crear".equals(opcion)) {
        	crearEmpleado(request, response);
        }
    }

    /**
     * Método que guarda un nuevo empleado.
     */
   
}

