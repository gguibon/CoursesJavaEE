package fr.amu.util;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;



public class Utils {

	/**
	 * Fonction pour transformer un inputStream en String sans avoir à importer
	 * Apache Commons
	 * 
	 * @param is
	 * @return
	 */
	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	/**
	 * Classe utilisant l'OSValidator pour ouvrir le navigateur par défaut à l'url spécifiée
	 * @param url
	 */
	public static void openBrowser(String url) {
		if (OsValidator.isWindows()) {
			System.out.println("This is Windows");

			try {
				Runtime rt = Runtime.getRuntime();
				rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (OsValidator.isMac()) {
			System.out.println("This is Mac");
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				Thread t = new Thread("New Thread") {
					public void run() {
						try {
							desktop.browse(new URI(url));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				t.start();
			}
		} else if (OsValidator.isUnix()) {
			System.out.println("This is Unix or Linux");
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				Thread t = new Thread("New Thread") {
					public void run() {
						try {
							desktop.browse(new URI(url));
						} catch (Exception e) {
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

}
