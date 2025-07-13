<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Cheval" %>
<%@ page import="model.Race" %>

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
                padding-top: 50px; 
            }
            .detail-table {
                width: 100%;
                margin-bottom: 20px;
            }
            .detail-table th {
                width: 200px;
                padding: 10px;
                font-weight: bold;
                vertical-align: top;
            }
            .detail-table td {
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a href='../ServletCheval/list' class="navbar-brand">
                        Système de gestion des ventes aux enchères de chevaux
                    </a>
                </div>
            </div>
        </nav>

        <div class="container special">
            <h2 class="h2">Détails du cheval</h2>
            <div class="table-responsive">
                <% Cheval leCheval = (Cheval)request.getAttribute("pLeCheval"); %>
                <table class="detail-table">
                    <tbody>
                        <tr>
                            <th>ID</th>
                            <td><%= leCheval.getId() %></td>
                        </tr>
                        <tr>
                            <th>Nom</th>
                            <td><%= leCheval.getNom() %></td>
                        </tr>
                        <% if(leCheval.getDateNaissance() != null) { %>
                            <tr>
                                <th>Date de naissance</th>
                                <td><%= leCheval.getDateNaissance() %></td>
                            </tr>
                        <% } %>
                        <% if(leCheval.getRace() != null) { %>
                            <tr>
                                <th>Race</th>
                                <td><%= leCheval.getRace().getNom() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <a href="<%= request.getContextPath() %>/cheval-servlet/list" class="btn btn-primary">Retour à la liste</a>
            </div>
        </div>
    </body>
</html>