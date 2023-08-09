package com.inikitagricenko.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public final class PersonRequest {

	private final long id;
	private final String email;
	private final String firstName;
	private final String lastName;
	private final int age;
	private final String address;

	public PersonRequest(long id, String email, String firstName, String lastName, int age, String address) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
	}

	public PersonRequest(String json) {
		Gson gson = new Gson();
		PersonRequest request = gson.fromJson(json, PersonRequest.class);

		this.id = request.getId();
		this.email = request.getEmail();
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.age = request.getAge();
		this.address = request.getAddress();
	}

	public long getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public int getAge() {
		return this.age;
	}

	public String getAddress() {
		return this.address;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PersonRequest other)) {
			return false;
		}
		if (this.getId() != other.getId()) {
			return false;
		}
		final Object this$firstName = this.getFirstName();
		final Object other$firstName = other.getFirstName();
		if (!Objects.equals(this$firstName, other$firstName)) {
			return false;
		}
		final Object this$lastName = this.getLastName();
		final Object other$lastName = other.getLastName();
		if (!Objects.equals(this$lastName, other$lastName)) {
			return false;
		}
		final Object this$email = this.getEmail();
		final Object other$email = other.getEmail();
		return Objects.equals(this$email, other$email);
	}

	public int hashCode() {
		final int PRIME = 59;
		long result = 1;
		result = result * PRIME + this.getId();
		final Object $email = this.getEmail();
		result = result * PRIME + ($email == null ? 43 : $email.hashCode());
		final Object $firstName = this.getFirstName();
		result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
		final Object $lastName = this.getLastName();
		result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
		result = result * PRIME + this.getAge();
		final Object $address = this.getAddress();
		result = result * PRIME + ($address == null ? 43 : $address.hashCode());
		return Math.toIntExact(result);
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
