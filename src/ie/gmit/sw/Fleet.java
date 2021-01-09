package ie.gmit.sw;

public class Fleet {
	
	private String name;
	private String age;
	private String machineID;
	private String clubID;
	private String vendor;
	private String valuation;
	private String lastServiceKM;
	private String nextServiceinKM;
	private String currentkm;
	
	public Fleet(String name, String age, String machineID, String clubID, String vendor, String valuation, String lastServiceKM,
			String nextServiceinKM, String currentkm) {
		super();
		this.name = name;
		this.age = age;
		this.machineID = machineID;
		this.clubID = clubID;
		this.vendor = vendor;
		this.valuation = valuation;
		this.lastServiceKM = lastServiceKM;
		this.nextServiceinKM = nextServiceinKM;
		this.currentkm = currentkm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMachineID() {
		return machineID;
	}

	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}

	public String getClubID() {
		return clubID;
	}

	public void setClubID(String clubID) {
		this.clubID = clubID;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getValuation() {
		return valuation;
	}

	public void setValuation(String valuation) {
		this.valuation = valuation;
	}

	public String getLastServiceKM() {
		return lastServiceKM;
	}

	public void setLastServiceKM(String lastServiceKM) {
		this.lastServiceKM = lastServiceKM;
	}

	public String getNextServiceinKM() {
		return nextServiceinKM;
	}

	public void setNextServiceinKM(String nextServiceinKM) {
		this.nextServiceinKM = nextServiceinKM;
	}

	public String getCurrentkm() {
		return currentkm;
	}

	public void setCurrentkm(String currentkm) {
		this.currentkm = currentkm;
	}
	
	
	
	
}
