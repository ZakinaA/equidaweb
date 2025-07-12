<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="model.Cheval"%>
<%@page import="model.Race"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Equida</title>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
        crossorigin="anonymous">



  <style>
    body {
      padding-top: 50px;
    }
    .special {
      padding-top:50px;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a  href ='../ServletCheval/list' class="navbar-brand" href=".">Système de gestion des ventes aux enchères de chevaux</a>
    </div>
  </div>
</nav>

<div class="container special">
  <h2 class="h2">Liste des chevaux</h2>
  <div class="table-responsive">
      <%
        ArrayList<Cheval> lesChevaux = (ArrayList)request.getAttribute("pLesChevaux");
      %>
    <table class="table table-striped table-sm">
      <thead>
      <tr>
        <th>id</th>
        <th>nom</th>
        <th>pays</th>
      </tr>
      </thead>
      <tbody>

        <%
          for (Cheval c : lesChevaux)
          {
            out.println("<tr><td>");
            out.println(c.getId());
            out.println("</td>");

           // out.println("<td><a href ='../ServletAthlete/consulter?idAthlete="+ a.getId()+ "'>");
            out.println("<td>");
            out.println(c.getNom());
            out.println("</a></td>");;

            out.println("<td>");
            out.println(c.getRace().getNom());
            out.println("</td></tr>");

          }
        %>
      </tr>
      </tbody>
    </table>
</body>
</div>
</div>

</html>