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
    public boolean equals(Object obj){
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Client that = (Client)obj;
        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if(fullName != null ? !fullName.equals(that.fullName) : that.fullName != null)
            return false;
        if(greeting !=null ? !greeting.equals(that.greeting) : that.greeting != null)
            return false;
        return true;

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
