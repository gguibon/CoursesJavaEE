# CoursesJavaEE
-----

Cours de JavaEE pour Master 2 AMU informatique. 

## Master 2 20182019

Les projets et l'examen et sa correction se trouvent [ici](https://github.com/gguibon/CoursesJavaEE/tree/master/20182019).

Les supports de cours sont ici :
- CM 1 : [PDF](https://github.com/gguibon/CoursesJavaEE/blob/master/20182019/cm1_beans_services_dao.pdf)
- CM 2 : [PDF](https://github.com/gguibon/CoursesJavaEE/blob/master/20182019/cm2_servlets_controllers_view_el-jstl.pdf)
- CM 3 : [PDF](https://github.com/gguibon/CoursesJavaEE/blob/master/20182019/cm3-tests.pdf)

Le support de TD est dans le [PDF](https://github.com/gguibon/CoursesJavaEE/blob/master/20182019/jee_tp20182019.pdf).

### Concepts étudiés

- Bean
- DAO (avec JDBC et JDBC Template)
- Service
- Spring
- Servlets / controllers
- JSP / EL / JSTL

Jquery, REST, ORM, EJB sont prévus dans un cours connexe.

### Évaluation

L'évaluation passera par un projet individuel (voir le projet de l'année dernière).

## Master 2 20172018

Les projets et l'examen et sa correction se trouvent [ici](https://github.com/gguibon/CoursesJavaEE/tree/master/20172018).

Le support de cours est dans le [PDF](https://github.com/gguibon/CoursesJavaEE/blob/master/20172018/javaEE_20172018.pdf).

### Concepts étudiés

- Bean
- ORM (Hibernate)
- Service
- Spring
- Servlets / controllers / RestControllers
- JSP / EL / JSTL
- JQuery / AJAX

### Liens

- [basic_hello_word](https://github.com/gguibon/CoursesJavaEE/tree/master/20172018/basic_hello_world) : javaEE basic pour commencer
- [servlet_usage](https://github.com/gguibon/CoursesJavaEE/tree/master/20172018/servlet_usage) : un petit exemple pour montrer l'utilisation sommaire de servlets
- [vue](https://github.com/gguibon/CoursesJavaEE/tree/master/20172018/vue) : un exemple d'intégration de l'application (simili twitter) et de sa vue. Pas d'ORM ici, que du stockage mémoire.
- [twitter-manual](https://github.com/gguibon/CoursesJavaEE/tree/master/20172018/twitter-manual) : un simili twitter simple (envoi de message, pseudo login, gestion de session, ORM, modèle MVC).
- [twitter-spring](https://github.com/gguibon/CoursesJavaEE/tree/master/20172018/twitter-spring) : la version spring-boot de notre application simili twitter.

### Évaluation

L'évaluation s'est faite par un projet surveillé de 3H. [Voir ici](https://github.com/gguibon/CoursesJavaEE/tree/master/20172018/Examen)

## Usage général

Compliler chaque projet maven à l'aide de la commande suivante dans la racine :

```mvn package```

Lancer l'application avec la commande suivante :

```java -jar target/NOM_DU_JAR_AVEC_DEPENDENCES.jar```

S'il vous manque maven, veuillez vous référer à la partie adéquate dans le [PDF](https://github.com/gguibon/CoursesJavaEE/blob/master/javaEE_20172018.pdf).

# Contacts
-----
gael dot guibon at lsis.org

@2018 LSIS-CNRS
