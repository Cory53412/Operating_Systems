package ie.gmit.sw;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);

		Socket connection;
		ObjectOutputStream out;
		ObjectInputStream in;
		String message;
		String response = "";
		String option;

		try {
			connection = new Socket("127.0.0.1", 25000);
			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());

			// Conversation starts.....
			do {
				out.writeObject("GetMenu");
				out.flush();

				message = (String) in.readObject();
				System.out.println(message);
				option = input.nextLine();

				out.writeObject(option);
				out.flush();

				if (option.equalsIgnoreCase("1")) {
					message = (String) in.readObject();
					System.out.println(message);

					// Username
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// Password
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					response = (String) in.readObject();
					System.out.println(response);
				}

				else if (option.equalsIgnoreCase("2")) {
					message = (String) in.readObject();
					System.out.println(message);

					// Username
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// Password
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// ID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					response = (String) in.readObject();
					System.out.println(response);
				}
			} while (option.equalsIgnoreCase("2") || response.equalsIgnoreCase("FAIL"));

			//// Logged in - Arithmetic Menu
			do {
				System.out.println("\n\t Menu");
				System.out.println("1)Add Machine");
				System.out.println("2)Update Machine");
				System.out.println("3)Update current KM reading");
				System.out.println("4)Search all fleet items");
				System.out.println("5)Remove a fleet item");
				System.out.println("6)Register a fleet Item");
				System.out.println("7)Search fleet items for sale");

				message = (String) in.readObject();
				System.out.println(message);
				option = input.nextLine();
				out.writeObject(option);
				out.flush();

				if (option.equalsIgnoreCase("1")) {

					message = (String) in.readObject();
					System.out.println(message);

					// machine name
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// vendor
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// machine age
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

//					message = (String) in.readObject();
//					System.out.println(message);
//
//					// machine ID
//					response = input.nextLine();
//					out.writeObject(response);
//					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// club ID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// valuation
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// Last Service in KM
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// Next Service in KM
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// Current KM
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// display added info
					message = (String) in.readObject();
					System.out.println(message);

				} else if (option.equalsIgnoreCase("2")) {

					message = (String) in.readObject();
					System.out.println(message);

					// Entering machine ID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					message = (String) in.readObject();
					System.out.println(message);

					// Last Service in KM
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// next Service
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// output new values
					message = (String) in.readObject();
					System.out.println(message);

				} else if (option.equalsIgnoreCase("3")) {
					message = (String) in.readObject();
					System.out.println(message);

					// entering machine ID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// printing off old curret km
					message = (String) in.readObject();
					System.out.println(message);

					// asking for new Km
					message = (String) in.readObject();
					System.out.println(message);

					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// displaying new km
					message = (String) in.readObject();
					System.out.println(message);

				}

				else if (option.equalsIgnoreCase("4")) {
					message = (String) in.readObject();
					System.out.println(message);

					// entering machine ID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

				} else if (option.equalsIgnoreCase("5")) {
					message = (String) in.readObject();
					System.out.println(message);

					// entering item number to be removed
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);
				}

				else if (option.equalsIgnoreCase("6")) {
					message = (String) in.readObject();
					System.out.println(message);

					// entering machine ID
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					System.out.println(message);

					// entering sale price
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

				}

				else if (option.equalsIgnoreCase("7")) {
					message = (String) in.readObject();
					System.out.println(message);

				}

				message = (String) in.readObject();
				System.out.println(message);
				option = input.nextLine();
				out.writeObject(option);
				out.flush();

			} while (option.equalsIgnoreCase("1"));

			in.close();
			out.close();
			connection.close();
			input.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

}
