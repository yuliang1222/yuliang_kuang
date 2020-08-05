package com.pazl.authorization.baohuxin;

/**
 * @author czd
 */
public class SuspensionTest {
	public static void main(String[] args) {
		RequestQueue queue = new RequestQueue();
		new ClientThread(queue , "快递").start();
		ServerThread serverThread = new ServerThread(queue);
		serverThread.start();

	}
}

