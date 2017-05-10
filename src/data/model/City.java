package data.model;

public class City {


	public City(String name, String countryCode, String district, int population) {
		super();
		id=0;
		Name = name;
		CountryCode = countryCode;
		District = district;
		this.population = population;
	}
	public City(int id, String name, String countryCode, String district, int population) {
		super();
		this.id = id;
		Name = name;
		CountryCode = countryCode;
		District = district;
		this.population = population;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	
	
	public String toString(){
		String state = "ID: " + id +
				" Name: " +Name +
				" CountryCode: " + CountryCode +
				" District: " + District+
				" Population: " +population;
		return state;
	}
	private int id, population;
	private String Name, CountryCode, District;
}
