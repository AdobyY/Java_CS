package com.shpp.p2p.cs.byarinko.a.lectures.lec14;

import acm.util.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;

public class ChatProgram extends WindowProgram {
	/* The port number on which to connect/listen. */
	private static final int PORT_NUMBER = 5001;
	
	/* Our preferred size. */
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 600;
	
	/* How many columns the input field should have. */
	private static final int NUM_COLUMNS = 30;
	
	/* Input and output streams. */
	private BufferedReader input;
	private PrintWriter output;
	
	/* Text field where the user can enter text to chat with. */
	private JTextField text;
	
	public void init() {
		Socket s = connect();
		println("=== Connection Established! ===");
		
		/* Extract the input and output streams from the socket. */
		try {
			input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			output = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			throw new ErrorException(e);
		}
		
		/* Add the text field to the bottom so that we can chat. */
		text = new JTextField(NUM_COLUMNS);
		text.addActionListener(this);
		add(text, SOUTH);
	}
	
	public void run() {
		try {
			/* Continuously read messages from the source. */
			while (true) {
				String line = input.readLine();
				if (line == null) break;
				
				println(">>> " + line);
			}
		} catch (IOException e) {
			throw new ErrorException(e);
		}
		println("=== Connection Closed ===");
	}
	
	/**
	 * Responds to the user entering text.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == text) {
			sendText(text.getText());
			
			/* Clear the text box so that we can send another
			 * message.
			 */
			text.setText("");
		}
	}
	
	/**
	 * Sends the specified text to the other user.
	 * 
	 * @param line The text to send.
	 */
	private void sendText(String line) {
		/* Output the data and flush the output stream to force
		 * the data to send.
		 */
		output.println(line);
		output.flush();
		
		/* For our sanity, display the text we sent. */
		println("<<< " + line);
	}
	
	/**
	 * Establishes a connection to another computer, returning
	 * a socket that connects to it.
	 * 
	 * @return A socket connecting to another computer.
	 */
	private Socket connect() {
		boolean isServer = getDialog().readBoolean("Do you want to host? ", "Yes", "No");
		if (isServer) {
			return setupServer();
		} else {
			return setupClient();
		}
	}
	
	/**
	 * Waits for an incoming connection, then returns a socket
	 * that connects to the calling computer.
	 * 
	 * @return A socket connected to the calling computer.
	 */
	private Socket setupServer() {
		try {
			ServerSocket s = new ServerSocket(PORT_NUMBER);
			println("Waiting for a connection...");
			
			Socket client = s.accept();
			s.close();
			return client;
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
	
	/**
	 * Connects to a remote computer, returning a socket that
	 * connects to that computer.
	 * 
	 * @return A socket connected to a remote computer.
	 */
	private Socket setupClient() {
		while (true) {
			try {
				String host = getDialog().readLine("Enter host: ");
				Socket s = new Socket(host, PORT_NUMBER);
				
				return s;
			} catch (IOException e) {
				getDialog().println("An error occurred trying to connect.");
			}
		}
	}
}
