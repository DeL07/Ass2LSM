
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
	
	
	}
