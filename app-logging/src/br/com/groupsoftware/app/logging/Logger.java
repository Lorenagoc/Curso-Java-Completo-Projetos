package br.com.groupsoftware.app.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	public static void info(String msg) {
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println("[INFO]" + date.format(new Date()) + " - " + msg);
	}
}
