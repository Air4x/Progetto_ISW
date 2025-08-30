package org.groupone;

public class Main {
    public static void main(String[] args) {
	Thread guiThread = new Thread(() -> {
		System.out.println("Gui thread\n");
	});
	guiThread.start();

	Thread notificationThread = new Thread(() -> {
		System.out.println("Email notifcations thread\n");
	});
	notificationThread.start();
    }

}
