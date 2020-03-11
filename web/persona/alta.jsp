<%@page import="datos.Vivienda"%>
<%@page import="datos.Municipio"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ALTA DE PERSONA</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    </head>
    <jsp:useBean id="per" scope="request" 
                 class="pantalla.Persona" />
    <jsp:setProperty name="per" property="*"/>

    <%if (request.getParameter("nombre") != null) {%>

    <jsp:forward page="/ControlPersona?accion=insertar"/>
    <% }%>

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

        <div class="jumbotron text-center" style="margin-bottom:0">
            <center><h2>ENTRADA DE DATOS DE PERSONAS</h2>
            </center>
        </div>

        <div class="container p-30" style="padding-top: 30px;padding-bottom:  30px;">
            <form method="post">

                <div class="row">
                    <div class="col-sm-5">
                        <div class="form-group">
                            <p>Nombre de la persona:
                                <input class="form-control" name="nombre" 
                                       required type="text" size="25" maxlength="25" /> 
                            </p>
                        </div>
                        <div class="form-group">
                            <p>Edad: 
                                <input class="form-control" name="edad"  required 
                                       type="number"  min="1" max="9999"               
                                       size="4"  /> 
                            </p>
                        </div>  
                        <div class="form-group">
                            <p>N&uacute;mero de vivienda:             
                                <select class="form-control" name="codvivienda">
                                    <%
                                        ArrayList listadvivienda = (ArrayList) request.getAttribute("viviendas");
                                        if (listadvivienda != null)
                                            for (int i = 0; i < listadvivienda.size(); i++) {
                                                Vivienda v = (Vivienda) listadvivienda.get(i);%>
                                    <option value= <%=v.getCodigo()%> > 
                                        <%=v.getCodigo()%>/<%=v.getNombremunicipio()%>/<%=v.getDireccion()%>
                                    </option>
                                    <%}%>
                                </select> 
                            </p>
                        </div>
                        <div class="btn-group">  
                            <input class="btn btn-dark" type="submit" name="insertar" 
                                   value="Insertar persona" />
                            <input class="btn btn-dark" type="reset" name="cancelar" 
                                   value="Cancelar entrada" />
                        </div>
                    </div></div>
            </form>  
        </div>


        <div class="jumbotron text-center fixed-bottom" style="margin-bottom: 0;">
            <a href="menu.html">Inicio</a>
        </div>
    </body>
</html>



