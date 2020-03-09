/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.*;
import datos.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paises.componente.ra6.DAOFactory;


public class ControlMunicipio extends HttpServlet {
    
    public DAOFactory bd =null;
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int num=(int) getServletConfig().getServletContext().getAttribute("tipo");
        bd = DAOFactory.getDAOFactory(num);
        
    //bd=(DAOFactory) request.getAttribute("database");
        MunicipioDAO municipioDAO=bd.getMunicipioDAO();
        PaisDAO paisDAO=bd.getPaisDAO();
        String op = request.getParameter("accion");
        

        
        if (op.equals("alta")) {
            ArrayList lista = paisDAO.TodosPaises();
            request.setAttribute("paises", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/municipio/alta.jsp");
            rd.forward(request, response);
        }

        
        if (op.equals("insertar")) {
            pantalla.Municipio mu = (pantalla.Municipio) request.getAttribute("muni");
            
            Municipio municipio= new Municipio(mu.getNombre(),mu.getCodpais(),mu.getTasapago(),mu.getSumaimpuestos(),mu.getNumerohabitantes());
            int insertar = municipioDAO.InsertaMunicipio(municipio);
            String mensaje = "";
            if (insertar==0) {
                mensaje = "Municipio " + mu.getNombre() + " insertado";
            } else if(insertar==1){
                mensaje = "Municipio " + mu.getNombre() + " ya existe";
            }else{
                mensaje = "Error al insertar municipio " + mu.getNombre();
            }
            
            request.setAttribute("boton", "Alta de Municipios");
            request.setAttribute("titulo", "INSERCI&Oacute;N DE MUNICIPIOS");
            request.setAttribute("accion", "alta");
            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/municipio/MunicipioResultado.jsp");
            rd.forward(request, response);

        }
        
        if (op.equals("listado")) {
            ArrayList lista = municipioDAO.TodosMunicipios();
            request.setAttribute("municipios", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/municipio/listado.jsp");
            rd.forward(request, response);
        }
        if (op.equals("pantModifBorr")) {
            ArrayList lista = municipioDAO.TodosMunicipios();
            request.setAttribute("municipios", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/municipio/pantModifBorr.jsp");
            rd.forward(request, response);
        }

        if (op.equals("resModBor")) {
            pantalla.Municipio municipio = (pantalla.Municipio) request.getAttribute("municipio");
            if (municipio.getModificar() == null) {
                int borrar = municipioDAO.EliminarMunicipio(municipio.getNombre());
                String mensaje = "";
                if (borrar==0) {
                    mensaje = "Municipio " + municipio.getNombre()
                            + " eliminado";
            } else if(borrar==1){
                mensaje = "Municipio " + municipio.getNombre() + " no puede ser eliminado porque tiene viviendas";
                } else {
                    mensaje = "Error al eliminar Municipio. "
                            + municipio.getNombre()+ " NO SE PUEDE ELIMINAR";
                }
            request.setAttribute("boton", "Eliminaci&oacute;n de Municipios");
            request.setAttribute("titulo", "ELIMINACI&Oacute;N DE MUNICIPIO");
            request.setAttribute("accion", "pantModifBorr");
            request.setAttribute("mensaje", mensaje); 
                RequestDispatcher rd
                        = request.getRequestDispatcher("/municipio/MunicipioResultado.jsp");
                rd.forward(request, response);
            } else {
                Municipio mu = municipioDAO.ConsultarMunicipio(municipio.getNombre());
                request.setAttribute("municipios", mu);
            ArrayList lista = paisDAO.TodosPaises();
            request.setAttribute("paises", lista);
                RequestDispatcher rd
                        = request.getRequestDispatcher("/municipio/modificar.jsp");
                rd.forward(request, response);
            }
        }


        if (op.equals("modificacion")) {
            pantalla.Municipio m = (pantalla.Municipio) request.getAttribute("municipio");// obtenerlos
            Municipio mun=new Municipio(m.getNombre(),m.getCodpais(),m.getTasapago(),m.getSumaimpuestos(),m.getNumerohabitantes());
            int modif = municipioDAO.ModificarMunicipio(m.getNombre(), mun);
            String mensaje = "";
            if (modif==0) {
                mensaje = "Municipio " + m.getNombre() + " modificado";
            } else if(modif==1){
                mensaje = "Municipio " + m.getNombre() + " ya existe";
            } else {
                mensaje = "Error al modificar municipio " +m.getNombre();
            }

            request.setAttribute("boton", "Modificaci&oacute;n de municipios");
            request.setAttribute("titulo", "MODIFICACI&Oacute;N DE MUNICIPIO");
            request.setAttribute("accion", "pantModifBorr");
            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/municipio/MunicipioResultado.jsp");
            rd.forward(request, response);

        }


        if (op.equals("actualizar")) {
            
            municipioDAO.ActualizarMunicipio();
            request.setAttribute("boton", "");
            request.setAttribute("titulo", "ACTUALIZACI&Oacute;N DE MUNICIPIOS");
            request.setAttribute("accion", "");
            request.setAttribute("mensaje", "MUNICIPIOS ACTUALIZADOS"); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/municipio/MunicipioResultado.jsp");
            rd.forward(request, response);

        }
        if (op.equals("consulta")) {
            ArrayList lista = paisDAO.TodosPaises();
            request.setAttribute("paises", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/municipio/pantConsulta.jsp");
            rd.forward(request, response);
        }

        if (op.equals("resConsulta")) {
            pantalla.Pais pais = (pantalla.Pais) request.getAttribute("pais");
            Pais pa=paisDAO.ConsultarPais(pais.getCodigo());
                ArrayList mu = municipioDAO.MunicipiosdeunPais(pais.getCodigo());
                request.setAttribute("municipios", mu);
                request.setAttribute("pais",  pa.getNombrepais().toUpperCase());
                RequestDispatcher rd
                        = request.getRequestDispatcher("/municipio/consulta.jsp");
                rd.forward(request, response);
            
        }
    }
    public void destroy() {
        bd = null;
    }

}
