package uk.co.mattburns.checkmend.differentpackage;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Person {
    // OPTIONAL - The family name/surname of the person being registered.
    private String familyname;
    // OPTIONAL - The first name/names of the person being registered.
    private String othernames;
    // OPTIONAL - The date of birth of the person being registered.
    private Date dob;
    // OPTIONAL - The gender of the person being registered
    private Gender gender;
    // A third party reference that can be used to identify the person on your
    // system.
    private String ref;

    public enum Gender {
        Male, Female;
    }

    public String getFamilyname() {
        return familyname;
    }

    public String getOthernames() {
        return othernames;
    }

    public Date getDob() {
        return dob;
    }

    public Gender getGender() {
        return gender;
    }

    public String getRef() {
        return ref;
    }

    private Person(String familyname, String othernames, Date dob,
            Gender gender, String ref) {
        this.familyname = familyname;
        this.othernames = othernames;
        this.dob = dob;
        this.gender = gender;
        this.ref = ref;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(this);
    }

    public static class PersonBuilder {
        private String familyname;
        private String othernames;
        private Date dob;
        private Gender gender;
        private String ref;

        public PersonBuilder(String ref) {
            this.ref = ref;
        }

        public PersonBuilder withFamilyname(String familyname) {
            this.familyname = familyname;
            return this;
        }

        public PersonBuilder withOthernames(String othernames) {
            this.othernames = othernames;
            return this;
        }

        public PersonBuilder withDob(Date dob) {
            this.dob = dob;
            return this;
        }

        public PersonBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(familyname, othernames, dob, gender, ref);
        }
    }
}
