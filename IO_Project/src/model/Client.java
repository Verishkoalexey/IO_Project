package model;


import java.sql.Timestamp;
import java.util.Date;

public class Client implements Comparable<Client> {
	private int Id;
	private int number;
	private String firstName;
	private String lastName;
	private String gender;
	private String country;
	private int age;
	private Date date;
	private Timestamp createdTs;
	private Timestamp updatedTs;
	
	public Client(String firstName, String lastName, String gender, String country, int age, Date date) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.country = country;
		this.age = age;
		this.date = date;
	}

	public Client(int id){
		this.Id=id;
	}
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	public Timestamp getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Timestamp createdTs) {
		this.createdTs = createdTs;
	}

	public Timestamp getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Timestamp updateTs) {
		this.updatedTs = updateTs;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client(int id, int number, String firstName, String lastName, String gender, String country, int age,
			Date date) {
		super();
		Id = id;
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.country = country;
		this.age = age;
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + age;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (Id != other.Id)
			return false;
		if (age != other.age)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [Id=" + Id + ", number=" + number + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", country=" + country + ", age=" + age + ", date=" + date + ", createdTs="
				+ createdTs + ", updatedTs=" + updatedTs + "]";
	}

	@Override
	public int compareTo(Client c) {
		// TODO Auto-generated method stub
		return this.getDate().compareTo(c.date);
	}
	
	
	
	
	

}
