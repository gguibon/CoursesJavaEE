<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8" />
<title>Test des expressions EL</title>
</head>
<body>
	<p>Mon prénom est ${ param.prenom } et je suis de nationalité ${ nationalite }.
	</p>
	<p>Mes prénoms sont ${ paramValues.prenoms[0] } et ${ paramValues.prenoms[1] }.</p>
	
			<c:out value="test" />
	
</body>
</html>