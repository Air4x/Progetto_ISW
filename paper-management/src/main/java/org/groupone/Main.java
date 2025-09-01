package org.groupone;

import java.util.Timer;

import org.groupone.boundary.LoginView;
import org.groupone.controller.NotificationController;


public class Main {
    public static void main(String[] args) {
		Timer new_timer = new Timer();
		long periodInMillis = 3 * 24 * 60 * 60 * 1000; // calcolo di 3 giorni in millisecondi
		
		Thread guiThread = new Thread(() -> {
			System.out.println("Gui thread\n");
			LoginView loginView = new LoginView();
			loginView.setVisible(true);
			loginView.setLocationRelativeTo(null);

		});
		guiThread.start();

		Thread notificationThread = new Thread(() -> {
			System.out.println("Email notifications thread\n");
			NotificationController notification_task = new NotificationController();
			new_timer.schedule(notification_task, 0, periodInMillis);
		});
		notificationThread.start();
    	}

}
