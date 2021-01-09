package ie.gmit.sw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends Thread {

	Scanner sc = new Scanner(System.in);
	private Socket conn;
	private int id;
	ObjectOutputStream out;
	ObjectInputStream in;
	private String message, option, response, input;
	private String storedUser = "default";
	private String storedEmail = "default";
	private String regid = "default";
	private String businessID, email, businessName;// users variables
	private String age, machineID, clubID;
	private String name, vendor;
	private String valuation, lastServiceKM, nextServiceinKM, currentkm;
	// update() variables
	private String serviceAgent, serverDesc;
	private String machineKM;
	private String salePrice;
	boolean exists;

	ArrayList<Users> myUsers = new ArrayList();
	ArrayList<Fleet> myFleet = new ArrayList();
	ArrayList<Sales> mySales = new ArrayList();

	public Server(Socket c, int i) {
		conn = c;
		id = i;
	}

	public void run() {
		try {
			out = new ObjectOutputStream(conn.getOutputStream());
			in = new ObjectInputStream(conn.getInputStream());

			// Server Conversation

			do {
				message = (String) in.readObject();

				out.writeObject("\nPress 1 for Login or 2 for Register");

				out.flush();

				option = (String) in.readObject();

				if (option.equalsIgnoreCase("1")) {

					// Login
					
					out.writeObject("Please enter the business name and id:");
					out.flush();

					businessName = (String) in.readObject();
					businessID = (String) in.readObject();

						
			            if (businessName.equalsIgnoreCase(storedUser) && businessID.equalsIgnoreCase(regid)) {
							response = "OK";
							out.writeObject("Logged in");
							out.flush();
						}

						else {
							response = "Fail";
							out.writeObject("FAIL");
							out.flush();
						}
			           
						
				}

				else if (option.equalsIgnoreCase("2")) {
					FileWriter file = new FileWriter("users", true);
					BufferedWriter br = new BufferedWriter(file);
					// Register
					out.writeObject("Please enter the business name, email and ID:");
					out.flush();

					storedUser = (String) in.readObject();
					storedEmail = (String) in.readObject();
					regid = (String) in.readObject();

					response = "OK";
					out.writeObject("Succesfully registered");

					Users users = new Users(storedUser, regid, storedEmail);
					myUsers.add(users);
					br.write( storedUser + "," +storedEmail + "," + regid );
					br.close();
					file.close();
				}

			} while (option.equalsIgnoreCase("2") || response.equalsIgnoreCase("FAIL"));

			// Log in - moving on to the arithmetic menu....

			do {		
//				out.writeObject("\n \tMenu" + "\n1) Add machine" + "\n2)Update machine" + "\n3) Update Current KM reading" + "\n4)Search all fleet Items"
//				+  "\n5)Remove fleet item" + "\n6)Register a fleet item"+ "\n7)Search all fleet items for sale");
//				ou.flush()
				out.writeObject("Selected option: ");
				out.flush();

				option = (String) in.readObject();

				if (option.equalsIgnoreCase("1")) {
					addmachinery();
				}

				else if (option.equalsIgnoreCase("2")) {
					updateMachinery();
				}

				else if (option.equalsIgnoreCase("3")) {
					updateCurrentKM();
				} else if (option.equalsIgnoreCase("4")) {
					searchFleetItem();
				} else if (option.equalsIgnoreCase("5")) {
					removeFleetItem();
				} else if (option.equalsIgnoreCase("6")) {
					registerForSale();
				} else if (option.equalsIgnoreCase("7")) {
					registerForSale();
				}

				out.writeObject("Please enter 1 to repeat the menu or 2 to leave the menu");
				out.flush();

				option = (String) in.readObject();

			} while (option.equalsIgnoreCase("1"));

			// Time to exit so close the connection
			in.close();
			out.close();
			conn.close();

		}

		catch (Exception e) {
			System.out.println("Error");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket listener;
		Socket connection;
		int id = 0;

		try {
			listener = new ServerSocket(25000, 10);

			while (true) {

				System.out.println("Listening for a connection");
				connection = listener.accept();
				System.out.println("Received Connection from " + connection.getInetAddress());

				Server th = new Server(connection, id);
				id++;
				th.start();
			}

		} catch (Exception e) {

		}

	}

	public void addmachinery() throws IOException, ClassNotFoundException {
		FileWriter file = new FileWriter("fleet", true);
		BufferedWriter br = new BufferedWriter(file);
		
		int fleetsize = myFleet.size() + 1;
		String machineID = Integer.toString(fleetsize);

		out.writeObject("Please enter the machine name:");
		out.flush();
		name = (String) in.readObject();

		out.writeObject("Please enter the machine vendor:");
		out.flush();
		vendor = (String) in.readObject();

		out.writeObject("Please enter the machine age:");
		out.flush();
		age = (String) in.readObject();

//		out.writeObject("Please enter the machine ID:");
//		out.flush();
//		machineID = (String) in.readObject();

		out.writeObject("Please enter the club ID:");
		out.flush();
		clubID = (String) in.readObject();

		out.writeObject("Please enter the machines valuation:");
		out.flush();
		valuation = (String) in.readObject();

		out.writeObject("Please enter the machine last Service in KM:");
		out.flush();
		lastServiceKM = (String) in.readObject();

		out.writeObject("Please enter the machines next Service in KM:");
		out.flush();
		nextServiceinKM = (String) in.readObject();

		out.writeObject("Please enter the machines current KM:");
		out.flush();
		currentkm = (String) in.readObject();

		for (int i = 0; i < myFleet.size(); i++) {
			if (machineID == (myFleet.get(i).getMachineID())) {
				exists = true;
			} else {
				exists = false;
			}
		}

		if (exists == false) {
			Fleet fleet = new Fleet(name, age, machineID, clubID, vendor, valuation, lastServiceKM, nextServiceinKM,
					currentkm);
			myFleet.add(fleet);
		}

		for (int i = 0; i < myFleet.size(); i++) {
			out.writeObject("You added a Machine with the following information: " + "\n-Machine ID: " + machineID
					+ ",\n-Name: " + name + ",\n-Age: " + age + ",\n-Club ID:" + clubID + ",\n-Vendor : " + vendor
					+ ",\n-Valuation: " + valuation + ",\n-Last serviced in KM: " + lastServiceKM
					+ ",\n-Next service in KM: " + nextServiceinKM + ",\n-Current Km:" + currentkm);
			out.flush();
		}
		
		br.write("\n\nmachine Info: " + "\n-Machine ID: " + machineID+ ",\n-Name: " + name + ",\n-Age: "
				+ age + ",\n-Club ID:" + clubID + ",\n-Vendor : " + vendor
				+ ",\n-Valuation: " + valuation + ",\n-Last serviced in KM: " + lastServiceKM
				+ ",\n-Next service in KM: " + nextServiceinKM + ",\n-Current Km:" + currentkm);
		br.close();
		file.close();

	}

	public void updateMachinery() throws IOException, ClassNotFoundException {
		String newlastServiceKM;
		String newnextServiceinKM;
		out.writeObject("Please enter the machine ID:");
		out.flush();
		input = (String) in.readObject();

		for (int i = 0; i < myFleet.size(); i++) {
			if (input.equalsIgnoreCase(myFleet.get(i).getMachineID())) {
//				out.writeObject("Name of service agent:");
//				out.flush();
//				serviceAgent = (String) in.readObject();
//				
//				out.writeObject("Kilometers on the maachine:");
//				out.flush();
//				machineKM = (String) in.readObject();
//				
//				out.writeObject("Description of the server:");
//				out.flush();
//				serverDesc = (String) in.readObject();
				out.writeObject("-Machines Last service in KM was:" + myFleet.get(i).getLastServiceKM()
						+ "\n-Machines next sevice in KM due in: " + myFleet.get(i).getNextServiceinKM());
				out.flush();

				out.writeObject("new Last service in KM:");
				out.flush();
				newlastServiceKM = (String) in.readObject();

				out.writeObject("new Next service in KM:");
				out.flush();
				newnextServiceinKM = (String) in.readObject();

				myFleet.get(i).setLastServiceKM(newlastServiceKM);
				myFleet.get(i).setNextServiceinKM(newnextServiceinKM);

				out.writeObject("you have updated last service record too: " + newlastServiceKM + " km"
						+ "\nYou have updated next service record too: " + newnextServiceinKM + " km");
				out.flush();
			} else {
				out.writeObject("That ID does not exist");
			}

		}

	}

	public void updateCurrentKM() throws IOException, ClassNotFoundException {
		String newCurrentKM = "";
		out.writeObject("Please enter the machine ID:");
		out.flush();
		input = (String) in.readObject();

		for (int i = 0; i < myFleet.size(); i++) {
			if (input.equalsIgnoreCase(myFleet.get(i).getMachineID())) {

				out.writeObject("Machines old current Km:" + myFleet.get(i).getCurrentkm());
				out.flush();

				out.writeObject("Machines new current KM:");
				out.flush();
				newCurrentKM = (String) in.readObject();

				myFleet.get(i).setCurrentkm(newCurrentKM);
			} else {
				System.out.println("No machine matching that ID.");
			}
		}

		out.writeObject("you have updated current KM too: " + newCurrentKM + " km");
		out.flush();

	}

	public void searchFleetItem() throws IOException, ClassNotFoundException {
//		for (int i = 0; i < myFleet.size(); i++) {
//			int LastService = Integer.parseInt(myFleet.get(i).getLastServiceKM());
//			int nextService = Integer.parseInt(myFleet.get(i).getNextServiceinKM());
//
//			if (LastService + 1000 <= nextService) {
//				out.writeObject("Machine :" + myFleet.get(i).getMachineID() + "Is within 1000km of their next service");
//				out.flush();
//			}
//		}

		out.writeObject("Please enter the machine ID:");
		out.flush();
		input = (String) in.readObject();

		for (int i = 0; i < myFleet.size(); i++) {
			if (input.equalsIgnoreCase(myFleet.get(i).getMachineID())) {
				out.writeObject("Machine " + machineID + "information:" + "\n-Machine ID: " + machineID + ",\n-Name: "
						+ name + ",\n-Age: " + age + ",\n-Club ID:" + clubID + ",\n-Vendor : " + vendor
						+ ",\n-Valuation: " + valuation + ",\n-Last serviced in KM: " + lastServiceKM
						+ ",\n-Next service in KM: " + nextServiceinKM + ",\n-Current Km:" + currentkm);
				out.flush();
			} else {
				out.writeObject("There is no amchine matching that ID.");
			}
		}

	}

	public void removeFleetItem() throws IOException, ClassNotFoundException {

		out.writeObject("Please enter the machine ID:");
		out.flush();
		input = (String) in.readObject();

		for (int i = 0; i < myFleet.size(); i++) {
			if (input.equalsIgnoreCase(myFleet.get(i).getMachineID())) {
				myFleet.remove(i);
				out.writeObject("Item removed.");
				out.flush();
			} else {
				out.writeObject("there is no machine matching that ID");
			}
		}
	}

	public void registerForSale() throws IOException, ClassNotFoundException {
		FileWriter file = new FileWriter("sales", true);
		BufferedWriter br = new BufferedWriter(file);
		
		out.writeObject("Enter machine Id of item you want to put up for sale");
		out.flush();
		input = (String) in.readObject();

		for (int i = 0; i < myFleet.size(); i++) {
			if (input.equalsIgnoreCase(myFleet.get(i).getMachineID())) {

				String newName = myFleet.get(i).getName();
				String newAge = myFleet.get(i).getAge();
				String newMachineID = myFleet.get(i).getMachineID();
				String newClubID = myFleet.get(i).getClubID();
				String newVendor = myFleet.get(i).getVendor();
				String newValuation = myFleet.get(i).getValuation();
				String newlastServiceKM = myFleet.get(i).getLastServiceKM();
				String newNextServiceKM = myFleet.get(i).getNextServiceinKM();
				String newCurrentKM = myFleet.get(i).getCurrentkm();

				out.writeObject("Enter sale price: ");
				out.flush();
				salePrice = (String) in.readObject();

				Sales sales = new Sales(newName, newAge, newMachineID, newClubID, newVendor, newValuation,
						newlastServiceKM, newNextServiceKM, newCurrentKM, salePrice);

				out.writeObject("You put a Machine for sale with the following information: " + "\n-Machine ID: "
						+ machineID + ",\n-Name: " + name + ",\n-Age: " + age + "\n-Club ID:" + clubID + "\n-Vendor : "
						+ vendor + "\n-Valuation: " + valuation + "\n-Last serviced in KM: " + lastServiceKM
						+ "\nNext service in KM: " + nextServiceinKM + "\n-Current Km:" + currentkm + "\nSale Price: "
						+ salePrice);
				out.flush();
			}

			else {

				out.writeObject("That machine Id does not exist");
			}
			
			br.write("\n\nSale Info: " + "\n-Machine ID: " + machineID+ ",\n-Name: " + name + ",\n-Age: "
					+ age + ",\n-Club ID:" + clubID + ",\n-Vendor : " + vendor
					+ ",\n-Valuation: " + valuation + ",\n-Last serviced in KM: " + lastServiceKM
					+ ",\n-Next service in KM: " + nextServiceinKM + ",\n-Current Km:" + currentkm);
			br.close();
			file.close();
			
		}
	}

	public void SearchSaleItems() throws IOException {
		for (int i = 0; i < mySales.size(); i++) {
			out.writeObject("Item for sales details: " + "\n-Machine ID: " + machineID + ",\n-Name: " + name
					+ ",\n-Age: " + age + "\n-Club ID:" + clubID + "\n-Vendor : " + vendor + "\n-Valuation: "
					+ valuation + "\n-Last serviced in KM: " + lastServiceKM + "\nNext service in KM: "
					+ nextServiceinKM + "\n-Current Km:" + currentkm + "\nSale Price: " + salePrice);
			out.flush();
		}
	}

}