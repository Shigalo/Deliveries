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

    public void addRequest(Request request) {
        RequestRepository.save(request);
    }

    public List<Request> getRequestList(User user) {
        return RequestRepository.findByUser(user);
    }

    public Request getById(String id) {
        return RequestRepository.findById(Integer.valueOf(id));
    }

    public void RequestRemoveById(int id) {
        RequestRepository.deleteById(id);
    }
}
