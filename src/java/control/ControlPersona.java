/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.MunicipioDAO;
import dao.ViviendaDAO;
import dao.PersonaDAO;
import datos.Persona;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paises.componente.ra6.DAOFactory;



public class ControlPersona extends HttpServlet {

    public DAOFactory bd = null;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int num=(int) getServletConfig().getServletContext().getAttribute("tipo");
        bd = DAOFactory.getDAOFactory(num);
        
        ViviendaDAO viviendaDAO = bd.getViviendaDAO();
        PersonaDAO personaDAO = bd.getPersonaDAO();
        MunicipioDAO municipioDAO = bd.getMunicipioDAO();
        String op = request.getParameter("accion");

        if (op.equals("alta")) {
            ArrayList lista = viviendaDAO.TodasViviendas();
            request.setAttribute("viviendas", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/persona/alta.jsp");
            rd.forward(request, response);
        }

        if (op.equals("insertar")) {
            pantalla.Persona per = (pantalla.Persona) request.getAttribute("per");

            Persona persona = new Persona(per.getNombre(),per.getEdad(),per.getCodvivienda());
            int insertar = personaDAO.InsertarPersona(persona);
            String mensaje = "";
            if (insertar == 0) {
                mensaje = "Persona " + per.getNombre() + " insertada";
            } else if (insertar == 1) {
                mensaje = "Persona " + per.getNombre() + " ya existe";
            } else {
                mensaje = "Error al insertar persona " + per.getNombre();
            }

            request.setAttribute("boton", "Alta de Personas");
            request.setAttribute("titulo", "INSERCI&Oacute;N DE PERSONAS");
            request.setAttribute("accion", "alta");
            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/persona/PersonaResultado.jsp");
            rd.forward(request, response);

        }

        if (op.equals("listado")) {
            ArrayList lista = personaDAO.TodosPersonas();
            request.setAttribute("personas", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/persona/listado.jsp");
            rd.forward(request, response);
        }
        if (op.equals("pantModifBorr")) {
            ArrayList lista = personaDAO.TodosPersonas();
            request.setAttribute("personas", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/persona/pantModifBorr.jsp");
            rd.forward(request, response);
        }

        if (op.equals("resModBor")) {
            pantalla.Persona persona = (pantalla.Persona) request.getAttribute("persona");
            if (persona.getModificar() == null) {
                int borrar = personaDAO.EliminarPersona(persona.getNombre());
                String mensaje = "";
                if (borrar == 0) {
                    mensaje = "Persona " + persona.getNombre()
                            + " eliminada";
                } else if (borrar == 1) {
                    mensaje = "Persona " + persona.getNombre() + " no puede ser eliminada porque tiene personas";
                } else {
                    mensaje = "Error al eliminar Persona. "
                            + persona.getNombre() + " NO SE PUEDE ELIMINAR";
                }
                request.setAttribute("boton", "Eliminaci&oacute;n de Personas");
                request.setAttribute("titulo", "ELIMINACI&Oacute;N DE PERSONA");
                request.setAttribute("accion", "pantModifBorr");
                request.setAttribute("mensaje", mensaje);
                RequestDispatcher rd
                        = request.getRequestDispatcher("/persona/PersonaResultado.jsp");
                rd.forward(request, response);
            } else {
                Persona per = personaDAO.ConsultarPersona(persona.getNombre());
                request.setAttribute("personas", per);
                ArrayList lista = viviendaDAO.TodasViviendas();
                request.setAttribute("viviendas", lista);
                RequestDispatcher rd
                        = request.getRequestDispatcher("/persona/modificar.jsp");
                rd.forward(request, response);
            }
        }

        if (op.equals("modificacion")) {
            pantalla.Persona p = (pantalla.Persona) request.getAttribute("persona");
            Persona per;
                per = new Persona(p.getNombre(),p.getEdad(),p.getCodvivienda());
            int modif = personaDAO.ModificarPersona(p.getNombre(),per);
            String mensaje = "";
            if (modif == 0) {
                mensaje = "Persona " + p.getNombre() + " modificada";
            } else if (modif == 1) {
                mensaje = "Persona " + p.getNombre() + " ya existe";
            } else {
                mensaje = "Error al modificar persona " + p.getNombre();
            }

            request.setAttribute("boton", "Modificaci&oacute;n de personas");
            request.setAttribute("titulo", "MODIFICACI&Oacute;N DE PERSONA");
            request.setAttribute("accion", "pantModifBorr");
            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/persona/PersonaResultado.jsp");
            rd.forward(request, response);

        }
        if (op.equals("consultaM")) {
            ArrayList lista = municipioDAO.TodosMunicipios();
            request.setAttribute("municipios", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/persona/pantConsultaM.jsp");
            rd.forward(request, response);
        }

        if (op.equals("resConsultaM")) {
            pantalla.Municipio municipio = (pantalla.Municipio) request.getAttribute("municipio");
                ArrayList vi = personaDAO.PersonasDeunMunicipio(municipio.getNombre());
                request.setAttribute("personas", vi);
                request.setAttribute("accion", "consultaM");
                request.setAttribute("nombre", municipio.getNombre().toUpperCase());
                RequestDispatcher rd
                        = request.getRequestDispatcher("/persona/consulta.jsp");
                rd.forward(request, response);
            
        }
        if (op.equals("consultaV")) {
            ArrayList lista = viviendaDAO.TodasViviendas();
            request.setAttribute("viviendas", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/persona/pantConsultaV.jsp");
            rd.forward(request, response);
        }

        if (op.equals("resConsultaV")) {
            pantalla.Vivienda vivienda = (pantalla.Vivienda) request.getAttribute("vivienda");
                ArrayList vi = personaDAO.TodosPersonasVivienda(vivienda.getCodigo());
                request.setAttribute("personas", vi);
                request.setAttribute("accion", "consultaV");
                request.setAttribute("nombre", "LA VIVIENDA "+vivienda.getCodigo());
                RequestDispatcher rd
                        = request.getRequestDispatcher("/persona/consulta.jsp");
                rd.forward(request, response);
            
        }
    }

    public void destroy() {
        bd = null;
    }
}
