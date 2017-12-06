package launch;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import beans.LiikeService;
import beans.MessageService;
import beans.UserService;
import database.HibernateUtil;
import utils.OsValidator;

public class Main {

	public static HibernateUtil hibernateUtil = new HibernateUtil();
	public static MessageService messageService2 = new MessageService();
	public static UserService userService = new UserService();	
	public static LiikeService liikeService = new LiikeService();
	
    private static File getRootFolder() {
        try {
            File root;
            String runningJarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
            int lastIndexOf = runningJarPath.lastIndexOf("/target/");
            if (lastIndexOf < 0) {
                root = new File("");
            } else {
                root = new File(runningJarPath.substring(0, lastIndexOf));
            }
            System.out.println("application resolved root folder: " + root.getAbsolutePath());
            return root;
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }
   
    
    
    private static void openBrowser(String url) {
    	if (OsValidator.isWindows()) {
			System.out.println("This is Windows");
			
	          try {
	        	  Runtime rt = Runtime.getRuntime();
	        	  rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
		} else if (OsValidator.isMac()) {
			System.out.println("This is Mac");
			if(Desktop.isDesktopSupported()){
		          Desktop desktop = Desktop.getDesktop();
		          Thread t = new Thread("New Thread") {
						public void run() {
							try {
				                desktop.browse(new URI(url));
				            }  catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
					t.start();
		      }
		} else if (OsValidator.isUnix()) {
			System.out.println("This is Unix or Linux");
			if(Desktop.isDesktopSupported()){
		          Desktop desktop = Desktop.getDesktop();
		          Thread t = new Thread("New Thread") {
						public void run() {
							try {
				                desktop.browse(new URI(url));
				            }  catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
					t.start();
		      }
		} else {
			System.out.println("Your OS is not support!!");
		}
    }

    public static void main(String[] args) throws Exception {
    	

    	
        File root = getRootFolder();        
        
        System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");
        Tomcat tomcat = new Tomcat();
        Path tempPath = Files.createTempDirectory("tomcat-base-dir");
        tomcat.setBaseDir(tempPath.toString());

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8888";
        }

        tomcat.setPort(Integer.valueOf(webPort));
        File webContentFolder = new File(root.getAbsolutePath(), "src/main/resources/webapp/");
        if (!webContentFolder.exists()) {
            webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
        }
        StandardContext ctx = (StandardContext) tomcat.addWebapp("", webContentFolder.getAbsolutePath());
        
        ctx.setParentClassLoader(Main.class.getClassLoader());

        System.out.println("configuring app with basedir: " + webContentFolder.getAbsolutePath());

        // Déclare un emplacement alternatif pour le dossier "WEB-INF/" (ancienne méthode de déclaration)
        // Servlet 3.0 pour les annotations --> nouvelle façon de déclarer les servlets. Plus moderne. Plus simple.
        File additionWebInfClassesFolder = new File(root.getAbsolutePath(), "target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);

        WebResourceSet resourceSet;
        if (additionWebInfClassesFolder.exists()) {
            resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClassesFolder.getAbsolutePath(), "/");
            System.out.println("loading WEB-INF resources from as '" + additionWebInfClassesFolder.getAbsolutePath() + "'");
        } else {
            resourceSet = new EmptyResourceSet(resources);
        }
        resources.addPreResources(resourceSet);
        resources.setCacheMaxSize(100000);
        ctx.setResources(resources);

        tomcat.start();
        openBrowser("http://localhost:8888/index"); // ouvre le navigateur par defaut
        tomcat.getServer().await();
        

        

        
        
        
		

    }
}
