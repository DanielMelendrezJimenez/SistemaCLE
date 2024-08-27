<%-- 
    Document   : navabar_general
    Created on : 31/03/2023, 01:09:53 AM
    Author     : Kevin Ivan Sanchez Vadin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${usuario.getTipo_usuario()=='COORDINADOR'}">
    <%@include file="navbar_coordinador.jsp" %>
</c:if>
<c:if test="${usuario.getTipo_usuario()=='SUPERVISOR'}">
    <%@include file="navbar_supervisor.jsp" %>
</c:if>
<c:if test="${usuario.getTipo_usuario()=='FACILITADOR'}">
    <%@include file="navbar_facilitador.jsp" %>
</c:if>
<%--<c:if test="${usuario.getTipo_usuario()=='Facilitador'}">
    <%@include file="navbar_cliente.jsp" %>
</c:if>--%>
<c:if test="${usuario.getTipo_usuario()!='COORDINADOR'&&usuario.getTipo_usuario()!='SUPERVISOR'&&usuario.getTipo_usuario()!='FACILITADOR'}">
    <%@include file="navbar_defecto.jsp" %>
</c:if>