package core;

import lombok.Data;

@Data
public class Client {
    private Integer id;
    private String fullName;

    public Client(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
