package fit.ancinpet;

class Order {
    public Order(String id, String location, String name, String surname) {
        this.id = id;
        this.location = location;
        this.person = new person(name, surname);
    }

    private String id;
    private String location;
    private person person;
}