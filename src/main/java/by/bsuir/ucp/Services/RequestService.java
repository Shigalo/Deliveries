package by.bsuir.ucp.Services;

import by.bsuir.ucp.Entities.Request;
import by.bsuir.ucp.Entities.User;
import by.bsuir.ucp.Repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository RequestRepository;

    /*public boolean addRequest(String name, String max_capacity, String unit_cost, String speed, String dangerous, String fragile, String perishable) {
        Request Request = RequestRepository.findByName(name);
        if (Request == null) {
            RequestRepository.save(new Request(name, Double.valueOf(max_capacity), Double.valueOf(unit_cost), Double.valueOf(speed),
                    Double.valueOf(dangerous), Double.valueOf(fragile), Double.valueOf(perishable)));
            return true;
        }
        return false;
    }*/

    public List<Request> getRequestList(User user) {
        return RequestRepository.findByUser(user);
    }

    public Request getById(String id) {
        return RequestRepository.findById(Integer.valueOf(id));
    }

    /*public boolean editRequest(String id, String RequestName, String max_capacity, String unit_cost, String speed) {
        if(RequestRepository.findByName(RequestName) == null || RequestRepository.findByName(RequestName).getId().equals(Integer.valueOf(id))) {
            System.out.println();
            RequestRepository.setUserInfoById(RequestName, Double.valueOf(max_capacity), Double.valueOf(unit_cost), Double.valueOf(speed), Integer.valueOf(id));
            return true;
        }
        return false;
    }*/

    public void RequestRemoveById(int id) {
        RequestRepository.deleteById(id);
    }
}
