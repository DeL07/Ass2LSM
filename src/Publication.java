
public class Publication {
	
	private long publication_code;
	private String publication_name; // Name can be split with underscore
	private int publication_year;
	private String publication_authorname; // Name can be split with underscore
	private double publication_cost;
	private int publication_nbpages;
	
	
	
	public Publication(long code, String name, int year, String author, double cost, int pages) {
		publication_code = code;
		publication_name = name;
		publication_year = year;
		publication_authorname = name;
		publication_cost = cost;
		publication_nbpages = pages;
	}

	public long getPublication_code() {
		return publication_code;
	}

	public void setPublication_code(long publication_code) {
		this.publication_code = publication_code;
	}

	public String getPublication_name() {
		return publication_name;
	}

	public void setPublication_name(String publication_name) {
		this.publication_name = publication_name;
	}

	public int getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}

	public String getPublication_authorname() {
		return publication_authorname;
	}

	public void setPublication_authorname(String publication_authorname) {
		this.publication_authorname = publication_authorname;
	}

	public double getPublication_cost() {
		return publication_cost;
	}

	public void setPublication_cost(double publication_cost) {
		this.publication_cost = publication_cost;
	}

	public int getPublication_nbpages() {
		return publication_nbpages;
	}

	public void setPublication_nbpages(int publication_nbpages) {
		this.publication_nbpages = publication_nbpages;
	}
	
	
	
	
	
	}
