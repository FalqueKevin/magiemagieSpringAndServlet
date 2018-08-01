<%-- 
    Document   : ecran-jeu
    Created on : 13 juil. 2018, 11:58:03
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
        <div id="containerEcranJeu">
            <div class="listeJoueurs">
                <c:forEach items="${adversaires}" var="adversaire">
                    <c:if test="${adversaire.id ne monJoueur.id}">
                        <div class="joueur"
                            <c:if test="${adversaire.etatJoueur eq 'A_LA_MAIN'}">
                                id="alamain"
                            </c:if>  
                        >        
                            <img src="images/${adversaire.avatar}.png">
                            <p>${adversaire.pseudo}</p>
                            <p>${adversaire.cartes.size()} Cartes</p>
                            <c:if test="${adversaire.etatJoueur eq 'A_LA_MAIN'}">
                                <p>A LUI DE JOUER !<p>
                            </c:if> 
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div id="milieu">
                <a href="<c:url value="/lancer-sort"/>">Lancer un sort</a>
                <a href="<c:url value="/passer-tour"/>">Passer ton tour</a>
            </div>
            <div id="monJeu">
                <div class="joueur">
                    <img src="images/${monJoueur.avatar}.png">
                    <p>${monJoueur.pseudo}</p>
                    <p>${monJoueur.cartes.size()} Cartes</p>
                </div>
                <div id="listeCartes">
                    <c:forEach items="${monJoueur.cartes}" var="cartes">
                        <img src="images/${cartes.ingredient}.png">
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
