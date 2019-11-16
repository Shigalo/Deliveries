package by.bsuir.ucp.Services;

import by.bsuir.ucp.Entities.Transport;
import by.bsuir.ucp.Repositories.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    @Autowired
    TransportRepository transportRepository;

    public boolean addTransport(String name, String max_capacity, String unit_cost, String speed, String dangerous, String fragile, String perishable) {
        Transport transport = transportRepository.findByName(name);
        if (transport == null) {
            transportRepository.save(new Transport(name, Double.valueOf(max_capacity), Double.valueOf(unit_cost), Double.valueOf(speed),
                    Double.valueOf(dangerous), Double.valueOf(fragile), Double.valueOf(perishable)));
            return true;
        }
        return false;
    }

    public List<Transport> getTransportList() {
        return transportRepository.findAll();
    }

    public Transport getById(String id) {
        return transportRepository.findById(Integer.valueOf(id));
    }

    public boolean editTransport(String id, String transportName, String max_capacity, String unit_cost, String speed, String dangerous, String fragile, String perishable) {
        if(transportRepository.findByName(transportName) == null || transportRepository.findByName(transportName).getId().equals(Integer.valueOf(id))) {
            transportRepository.setUserInfoById(transportName, Double.valueOf(max_capacity), Double.valueOf(unit_cost),
                    Double.valueOf(speed), Integer.valueOf(id), Double.valueOf(dangerous), Double.valueOf(fragile), Double.valueOf(perishable));
            return true;
        }
        return false;
    }

    public void transportRemoveById(int id) {
        transportRepository.deleteById(id);
    }
}
