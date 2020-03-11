/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.MunicipioDAO;
import dao.ViviendaDAO;
import datos.*;
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
public class ControlVivienda extends HttpServlet {

    public DAOFactory bd = null;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num = (int) getServletConfig().getServletContext().getAttribute("tipo");
        bd = DAOFactory.getDAOFactory(num);

        //bd=(DAOFactory) request.getAttribute("database");
        MunicipioDAO municipioDAO = bd.getMunicipioDAO();
        ViviendaDAO viviDAO = bd.getViviendaDAO();
        String op = request.getParameter("accion");

        if (op.equals("alta")) {
            ArrayList lista = municipioDAO.TodosMunicipios();
            request.setAttribute("municipios", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/vivienda/alta.jsp");
            rd.forward(request, response);
        }

        if (op.equals("insertar")) {
            pantalla.Vivienda vi = (pantalla.Vivienda) request.getAttribute("vivi");

            Vivienda vivienda = new Vivienda(vi.getCodigo(), vi.getNombremunicipio(), vi.getDireccion(), vi.getMetroscuadrados(), vi.getAnyo(), vi.getImpuesto(), vi.getNumeropersonas());
            int insertar = viviDAO.InsertarVivienda(vivienda);
            String mensaje = "";
            if (insertar == 0) {
                mensaje = "Vivienda " + vi.getCodigo() + " insertada";
            } else if (insertar == 1) {
                mensaje = "Vivienda " + vi.getCodigo() + " ya existe";
            } else {
                mensaje = "Error al insertar vivienda " + vi.getCodigo();
            }

            request.setAttribute("boton", "Alta de Viviendas");
            request.setAttribute("titulo", "INSERCI&Oacute;N DE VIVIENDAS");
            request.setAttribute("accion", "alta");
            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/vivienda/ViviendaResultado.jsp");
            rd.forward(request, response);

        }

        if (op.equals("listado")) {
            ArrayList lista = viviDAO.TodasViviendas();
            request.setAttribute("viviendas", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/vivienda/listado.jsp");
            rd.forward(request, response);
        }
        if (op.equals("pantModifBorr")) {
            ArrayList lista = viviDAO.TodasViviendas();
            request.setAttribute("viviendas", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/vivienda/pantModifBorr.jsp");
            rd.forward(request, response);
        }

        if (op.equals("resModBor")) {
            pantalla.Vivienda vivienda = (pantalla.Vivienda) request.getAttribute("vivienda");
            if (vivienda.getModificar() == null) {
                int borrar = viviDAO.EliminarVivienda(vivienda.getCodigo());
                String mensaje = "";
                if (borrar == 0) {
                    mensaje = "Vivienda " + vivienda.getCodigo()
                            + " eliminada";
                } else if (borrar == 1) {
                    mensaje = "Vivienda " + vivienda.getCodigo() + " no puede ser eliminada porque tiene personas";
                } else {
                    mensaje = "Error al eliminar Vivienda. "
                            + vivienda.getCodigo() + " NO SE PUEDE ELIMINAR";
                }
                request.setAttribute("boton", "Eliminaci&oacute;n de Viviendas");
                request.setAttribute("titulo", "ELIMINACI&Oacute;N DE VIVIENDA");
                request.setAttribute("accion", "pantModifBorr");
                request.setAttribute("mensaje", mensaje);
                RequestDispatcher rd
                        = request.getRequestDispatcher("/vivienda/ViviendaResultado.jsp");
                rd.forward(request, response);
            } else {
                Vivienda vi = viviDAO.ConsultarVivienda(vivienda.getCodigo());
                request.setAttribute("viviendas", vi);
                request.setAttribute("muniviv", vi.getNombremunicipio());
                ArrayList lista = municipioDAO.TodosMunicipios();
                request.setAttribute("municipios", lista);
                RequestDispatcher rd
                        = request.getRequestDispatcher("/vivienda/modificar.jsp");
                rd.forward(request, response);
            }
        }

        if (op.equals("modificacion")) {
            pantalla.Vivienda v = (pantalla.Vivienda) request.getAttribute("vivienda");
            Vivienda vivi;
            vivi = new Vivienda(v.getCodigo(), v.getNombremunicipio(), v.getDireccion(), v.getMetroscuadrados(), v.getAnyo(), v.getImpuesto(), v.getNumeropersonas());
            int modif = viviDAO.ModificarVivienda(v.getCodigo(), vivi);
            String mensaje = "";
            if (modif == 0) {
                mensaje = "Vivienda " + v.getCodigo() + " modificada";
            } else if (modif == 1) {
                mensaje = "Vivienda " + v.getCodigo() + " ya existe";
            } else {
                mensaje = "Error al modificar vivienda " + v.getCodigo();
            }

            request.setAttribute("boton", "Modificaci&oacute;n de viviendas");
            request.setAttribute("titulo", "MODIFICACI&Oacute;N DE VIVIENDA");
            request.setAttribute("accion", "pantModifBorr");
            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/vivienda/ViviendaResultado.jsp");
            rd.forward(request, response);

        }

        if (op.equals("actualizar")) {

            viviDAO.ActualizarVivienda();
            request.setAttribute("boton", "");
            request.setAttribute("titulo", "ACTUALIZACI&Oacute;N DE VIVIENDAS");
            request.setAttribute("accion", "");
            request.setAttribute("mensaje", "VIVIENDAS ACTUALIZADAS"); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/vivienda/ViviendaResultado.jsp");
            rd.forward(request, response);

        }
        if (op.equals("consulta")) {
            ArrayList lista = municipioDAO.TodosMunicipios();
            request.setAttribute("municipios", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/vivienda/pantConsulta.jsp");
            rd.forward(request, response);
        }

        if (op.equals("resConsulta")) {
            pantalla.Municipio municipio = (pantalla.Municipio) request.getAttribute("municipio");
            ArrayList vi = viviDAO.ViviendasDeunMunicipio(municipio.getNombre());
            request.setAttribute("viviendas", vi);
            request.setAttribute("municipio", municipio.getNombre().toUpperCase());
            RequestDispatcher rd
                    = request.getRequestDispatcher("/vivienda/consulta.jsp");
            rd.forward(request, response);

        }
    }

    public void destroy() {
        bd = null;
    }

}
