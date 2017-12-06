<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test des expressions EL</title>
</head>
<body>
	<p>
		<!-- Logiques sur des booléens -->
		${ true && true } <br />
		<!-- Affiche true -->
		${ true && false } <br />
		<!-- Affiche false -->
		${ !true || false } <br />
		<!-- Affiche false -->

		<!-- Calculs arithmétiques -->
		${ 10 / 4 } <br />
		<!-- Affiche 2.5 -->
		${ 10 mod 4 } <br />
		<!-- Affiche le reste de la division entière, soit 2 -->
		${ 10 % 4 } <br />
		<!-- Affiche le reste de la division entière, soit 2 -->
		${ 6 * 7 } <br />
		<!-- Affiche 42 -->
		${ 63 - 8 } <br />
		<!-- Affiche 55 -->
		${ 12 / -8 } <br />
		<!-- Affiche -1.5 -->
		${ 7 / 0 } <br />
		<!-- Affiche Infinity -->

		<!-- Compare les caractères 'a' et 'b'. Le caractère 'a' étant bien situé avant le caractère 'b' dans l'alphabet ASCII, cette EL affiche true. -->
		${ 'a' < 'b' } <br />

		<!-- Compare les chaînes 'hip' et 'hit'. Puisque 'p' < 't', cette EL affiche false. -->
		${ 'hip' gt 'hit' } <br />

		<!-- Compare les caractères 'a' et 'b', puis les chaînes 'hip' et 'hit'. Puisque le premier test renvoie true et le second false, le résultat est false. -->
		${ 'a' < 'b' && 'hip' gt 'hit' } <br />

		<!-- Compare le résultat d'un calcul à une valeur fixe. Ici, 6 x 7 vaut 42 et non pas 48, le résultat est false. -->
		${ 6 * 7 == 48 } <br />

		<!-- Vérifications si vide ou null -->
		${ empty 'test' } <br />
		<!-- La chaîne testée n'est pas vide, le résultat est false -->
		${ empty '' } <br />
		<!-- La chaîne testée est vide, le résultat est true -->
		${ !empty '' } <br />
		<!-- La chaîne testée est vide, le résultat est false -->

		<!-- Conditions ternaires -->
		${ true ? 'vrai' : 'faux' } <br />
		<!-- Le booléen testé vaut true, vrai est affiché -->
		${ 'a' > 'b' ? 'oui'  : 'non' } <br />
		<!-- Le résultat de la comparaison vaut false, non est affiché -->
		${ empty 'test' ? 'vide' : 'non  vide'  } <br />
		<!-- La chaîne testée n'est pas vide, non vide est affiché -->
	</p>

	<!-- La ligne suivante : -->
	<p>12 est inférieur à 8 : ${ 12 lt 8 }.</p>

	<!-- Sera rendue ainsi après interprétation de l'expression, 12 n'étant pas inférieur à 8 : -->
	<p>12 est inférieur à 8 : false.</p>


</body>
</html>