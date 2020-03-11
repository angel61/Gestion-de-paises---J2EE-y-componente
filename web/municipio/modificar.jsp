<%@page import="datos.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>      
        <title>MODIFICACI&Oacute;N DE MUNICIPIO</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>

        <nav class="navbar navbar-expand-sm bg-dark navbar-dark" style="margin-bottom:0">
            <a class="navbar-brand" href="menu.html">Gesti&oacute;n</a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" 
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                            Paises
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlPais?accion=alta">Alta</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlPais?accion=listado">Listado</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlPais?accion=pantModifBorr">Editar/Eliminar</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                            Municipios
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlMunicipio?accion=alta">Alta</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlMunicipio?accion=listado">Listado</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlMunicipio?accion=pantModifBorr">Editar/Eliminar</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlMunicipio?accion=actualizar">Actualizar</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlMunicipio?accion=consulta">Consulta por pais</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                            Viviendas
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlVivienda?accion=alta">Alta</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlVivienda?accion=listado">Listado</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlVivienda?accion=pantModifBorr">Editar/Eliminar</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlVivienda?accion=actualizar">Actualizar</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlVivienda?accion=consulta">Consultar por municipio</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown">
                            Personas
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlPersona?accion=alta">Alta</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlPersona?accion=listado">Listado</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlPersona?accion=pantModifBorr">Editar/Eliminar</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlPersona?accion=consultaV">Consultar por vivienda</a>
                            <a class="dropdown-item" href="/RA6_LopezAngel/ControlPersona?accion=consultaM">Consultar por municipio</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <jsp:useBean id="municipio" scope="request" class="pantalla.Municipio" />
        <jsp:setProperty name="municipio" property="*"/>
        <% if (request.getParameter("modificar").
                    equals("Modificar municipio")) {
                //System.out.println("he pulsado boton");
        %>
        <jsp:forward page="/ControlMunicipio?accion=modificacion"/>
        <% } %>

        <div class="jumbotron text-center" style="margin-bottom:0">
            <h2 align="center">MODIFICACI&Oacute;N DE DATOS DE MUNICIPIO</h2>
        </div>
        <%
            Municipio m = (Municipio) request.getAttribute("municipios");
        %>
        <div class="container p-30" style="padding-top: 30px; padding-bottom: 45px;">

            <form  method="post">


                <div class="row">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <p>Nombre de municipio: 
                                <input class="form-control" name="nombre" 
                                       required type="text" size="40" maxlength="40" value="<%=m.getNombre()%>" readonly="readonly" /> 
                            </p>
                        </div>  
                        <div class="form-group">


                            <p>C&oacute;digo de pais:              
                                <select class="form-control"  name="codpais">
                                    <%
                                        ArrayList listapais = (ArrayList) request.getAttribute("paises");
                                        if (listapais != null)
                                            for (int i = 0; i < listapais.size(); i++) {
                                                Pais pa = (Pais) listapais.get(i);%>
                                    %>
                                    <option value= <%=pa.getCodigo()%>
                                            <% if (m.getCodpais() == pa.getCodigo()) { %> selected <% }%>
                                            > 
                                        <%=pa.getCodigo()%> / <%=pa.getNombrepais()%>
                                    </option>
                                    <%
                                        }%>
                                </select> 
                            </p>
                        </div>
                        <div class="form-group">
                            <p>Tasa de pago:
                                <input class="form-control" name="tasapago"  required 
                                       type="number"  min="1" max="999999"  step="any"               
                                       size="6"  value="<%=m.getTasapago()%>"/> 
                            </p>
                        </div>
                        <div class="form-group">
                            <p>Suma de impuestos:
                                <input class="form-control" name="sumaimpuestos"  required 
                                       type="number"  min="0" max="999999"  step="any"               
                                       size="6" value="<%=m.getSumaimpuestos()%>" /> 
                            </p>
                        </div>
                        <div class="form-group">
                            <p>N&uacute;mero de habitantes:
                                <input class="form-control" name="numerohabitantes"  required 
                                       type="number"  min="0" max="99999999999"               
                                       size="11" value="<%=m.getNumerohabitantes()%>" /> 
                            </p>
                        </div>
                        <div class="btn-group"> 
                            <input class="btn btn-dark" type="submit" name="modificar" value="Modificar municipio">
                            <input class="btn btn-dark" type="reset" name="cancelar" value="Cancelar entrada">
                        </div>
                        </form> 
                    </div>
                </div>
        </div>

        <div class="jumbotron text-center" style="margin-bottom: 0;">
            <a href="/RA6_LopezAngel/ControlMunicipio?accion=pantModifBorr">Modificaci&oacute;n de pais</a>
            <br/><br/>
            <a href="menu.html">Inicio</a>
        </div>
    </div>

</body>
</html>