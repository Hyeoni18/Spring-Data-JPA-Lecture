package hello.springboot;

import javax.persistence.Embeddable;

@Embeddable //Composite Value 타입 맵핑
public class Address {

    private String street;

    private String city;

    private String state;

    private String zipCode;

}
