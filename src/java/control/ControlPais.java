/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PaisDAO;
import datos.Pais;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paises.componente.ra6.DAOFactory;

/**
 *
 * @author angel
 */
public class ControlPais extends HttpServlet {

    public DAOFactory bd = null;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num = (int) getServletConfig().getServletContext().getAttribute("tipo");
        bd = DAOFactory.getDAOFactory(num);

        PaisDAO paisDAO = bd.getPaisDAO();
        String op = request.getParameter("accion");

        if (op.equals("alta")) {
            RequestDispatcher rd = request.getRequestDispatcher("/pais/alta.jsp");
            rd.forward(request, response);
        }

        if (op.equals("insertar")) {
            pantalla.Pais pa = (pantalla.Pais) request.getAttribute("pa");

            Pais pais = new Pais(pa.getCodigo(), pa.getNombre());
            int insertar = paisDAO.InsertarPais(pais);
            String mensaje = "";
            if (insertar == 0) {
                mensaje = "Pais " + pa.getCodigo() + " insertado";
            } else if (insertar == 1) {
                mensaje = "Pais " + pa.getCodigo() + " ya existe";
            } else {
                mensaje = "Error al insertar pais " + pa.getCodigo();
            }

            request.setAttribute("boton", "Alta de Paises");
            request.setAttribute("titulo", "INSERCI&Oacute;N DE PAISES");
            request.setAttribute("accion", "alta");
            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/pais/PaisResultado.jsp");
            rd.forward(request, response);

        }

        if (op.equals("listado")) {
            ArrayList lista = paisDAO.TodosPaises();
            request.setAttribute("paises", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/pais/listado.jsp");
            rd.forward(request, response);
        }
        if (op.equals("pantModifBorr")) {
            ArrayList lista = paisDAO.TodosPaises();
            request.setAttribute("paises", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/pais/pantModifBorr.jsp");
            rd.forward(request, response);
        }

        if (op.equals("resModBor")) {
            pantalla.Pais pais = (pantalla.Pais) request.getAttribute("pais");
            if (pais.getModificar() == null) {
                int borrar = paisDAO.EliminarPais(pais.getCodigo());
                String mensaje = "";
                if (borrar == 0) {
                    mensaje = "Pais " + pais.getCodigo()
                            + " eliminado";
                } else if (borrar == 1) {
                    mensaje = "Pais " + pais.getCodigo() + " no puede ser eliminado porque tiene municipios";
                } else {
                    mensaje = "Error al eliminar Pais. "
                            + pais.getCodigo() + " NO SE PUEDE ELIMINAR";
                }
                request.setAttribute("boton", "Eliminaci&oacute;n de Paises");
                request.setAttribute("titulo", "ELIMINACI&Oacute;N DE PAIS");
                request.setAttribute("accion", "pantModifBorr");
                request.setAttribute("mensaje", mensaje);
                RequestDispatcher rd
                        = request.getRequestDispatcher("/pais/PaisResultado.jsp");
                rd.forward(request, response);
            } else {
                Pais pa = paisDAO.ConsultarPais(pais.getCodigo());
                request.setAttribute("paises", pa);
                RequestDispatcher rd
                        = request.getRequestDispatcher("/pais/modificar.jsp");
                rd.forward(request, response);
            }
        }

        if (op.equals("modificacion")) {
            pantalla.Pais p = (pantalla.Pais) request.getAttribute("pais");// obtenerlos
            int modif = paisDAO.ModificarPais(p.getCodigo(), p.getNombre());
            String mensaje = "";
            if (modif == 0) {
                mensaje = "Pais " + p.getCodigo() + " modificado";
            } else if (modif == 1) {
                mensaje = "Pais " + p.getNombre() + " ya existe";
            } else {
                mensaje = "Error al modificar Pais " + p.getCodigo();
            }

            request.setAttribute("boton", "Modificaci&oacute;n de paises");
            request.setAttribute("titulo", "MODIFICACI&Oacute;N DE PAIS");
            request.setAttribute("accion", "pantModifBorr");
            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/pais/PaisResultado.jsp");
            rd.forward(request, response);

        }
    }

    public void destroy() {
        bd = null;
    }
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//    }

}
