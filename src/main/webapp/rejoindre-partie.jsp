<%-- 
    Document   : rejoindre-partie
    Created on : 13 juil. 2018, 11:49:04
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

        <form id="tableauParties" method="POST">
            <div id="entete">
                <h2>Personnage</h2>
            </div>
            <div id="contenu">
                <div>
                    <input type="radio" class="avatar" name="avatar" value="avatarVert" id="vert">
                    <label for="vert"><img src="images/avatarVert.png"></label>
                    <input type="radio" class="avatar" name="avatar" value="avatarBleu" id="bleu">
                    <label for="bleu"><img src="images/avatarBleu.png"></label>
                    <input type="radio" class="avatar" name="avatar" value="avatarJaune" id="jaune">
                    <label for="jaune"><img src="images/avatarJaune.png"></label>
                    <input type="radio" class="avatar" name="avatar" value="avatarRouge" id="rouge">
                    <label for="rouge"><img src="images/avatarRouge.png"></label>
                </div>
            <input type="text" name="login">
            <button type="submit">Rejoindre la partie</button>
            </div>>
        </form>
        </div>
    </body>
</html>
