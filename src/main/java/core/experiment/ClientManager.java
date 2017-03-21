package core.experiment;

import core.Client;
import core.MapTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


public class ClientManager {

    protected Client createClient(){
        throw new IllegalStateException("countainer couldn't create proxy");
    }
}
