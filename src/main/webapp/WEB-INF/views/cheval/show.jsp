<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Cheval" %>
<%@ page import="model.Race" %>
<html>
<head>
    <title>Détails du Cheval</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Détails du Cheval</h1>
        
        <% Cheval leCheval = (Cheval)request.getAttribute("pLeCheval"); %>
        
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><%=leCheval.getNom()%></h5>
                <div class="card-text">
                    <p><strong>ID :</strong> <%=leCheval.getId()%></p>
                    
                    <% if(leCheval.getDateNaissance() != null) { %>
                        <p><strong>Date de naissance :</strong> <%=leCheval.getDateNaissance()%></p>
                    <% } %>
                    
                    <% if(leCheval.getRace() != null) { %>
                        <p><strong>Race :</strong> <%=leCheval.getRace().getNom()%></p>
                    <% } %>
                </div>
            </div>
        </div>

        <div class="mt-3">
            <a href="<%=request.getContextPath()%>/cheval-servlet/lister" class="btn btn-primary">
                Retour à la liste
            </a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>