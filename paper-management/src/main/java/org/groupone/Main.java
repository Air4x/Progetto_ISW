package org.groupone;

import java.sql.SQLException;
import java.util.Timer;

import org.groupone.controller.NotificationController;


public class Main {
    public static void main(String[] args) {
		Timer new_timer = new Timer();
		long periodInMillis = 3 * 24 * 60 * 60 * 1000; // calcolo di 3 giorni in millisecondi
		
		Thread guiThread = new Thread(() -> {
			System.out.println("Gui thread\n");
		});
		guiThread.start();

		Thread notificationThread = new Thread(() -> {
			System.out.println("Email notifications thread\n");
			try {
				NotificationController notification_task = new NotificationController();
				new_timer.schedule(notification_task, 0, periodInMillis);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore nell'inizializzazione del NotificationController");
			}
		});
		notificationThread.start();
    	}

}
