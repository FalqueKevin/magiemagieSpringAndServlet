<%-- 
    Document   : liste-parties
    Created on : 13 juil. 2018, 11:47:01
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Jeu Magie-Magie</title>
    </head> 

    <body>
        <div id="container">
            <header>
                <h1>MAGIE - MAGIE</h1>
            </header>
            <div id="tableauParties">
                <div id="entete">
                    <h2>Liste des parties</h2>
                    <a href="<c:url value="/creer-partie"/>">Créer une partie</a>
                </div>
                <c:forEach items="${listeParties}" var="partie">
                    <div class="lignes">
                        <div>
                            ${partie.nom}
                        </div>
                        <div>
                            ${partie.joueurs.size()} Joueurs
                        </div>
                        <div>
                            <a href="<c:url value="/rejoindre-partie"/>?idPartie=${partie.id}">&#9658;</a>    
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
