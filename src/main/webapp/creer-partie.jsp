<%-- 
    Document   : creer-partie
    Created on : 13 juil. 2018, 11:50:02
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
                    <h2>Nom de la partie</h2>
                </div> 
                <div id="contenu">
                    <div>
                        <input type="text" name="nomPartie">
                    </div>
                    <div>    
                        <button type="submit">Créer la partie</button>
                    </div>    
                    <div>    
                        <a href="<c:url value="lister-parties"/>">Retour</a>  
                    </div>
                </div>
            </form>
        </div>>
    </body>
</html>
