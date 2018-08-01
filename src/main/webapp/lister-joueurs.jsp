<%-- 
    Document   : lister-joueurs
    Created on : 13 juil. 2018, 11:59:07
    Author     : Administrateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
                    <h2>Liste des joueurs</h2>
                </div>
                <div class="listeJoueurs">
                    <c:forEach items="${listeJoueurs}" var="joueur">
                        <div class="joueur"><img src="images/${joueur.avatar}.png"> <h2>${joueur.pseudo}</h2></div>
                    </c:forEach> 
                </div> 
                <form method="POST">
                    <button type="submit">Démarrer</button>
                </form> 
            </div>
        </div>
    </body>
</html>