package core;

import lombok.Data;

@Data
public class Client {
    private Integer id;
    private String fullName;
    private String greeting;

    public Client() {
    }

    public Client(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", greeting='" + greeting + '\'' +
                '}';
    }
}
