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
    RequestRepository requestRepository;

    public void addRequest(Request request) {
        requestRepository.save(request);
    }

    public List<Request> getRequestList(User user) {
        return requestRepository.findByUser(user);
    }

    public Request getById(String id) {
        return requestRepository.findById(Integer.valueOf(id));
    }

    public void RequestRemoveById(int id) {
        requestRepository.deleteById(id);
    }

    public List<Request> getRequestList() {
        return requestRepository.findAll();
    }

    public List<Request> getWaitingRequestList() {
        return requestRepository.findByCompleteFalse();
    }

    public void complete(String id) {
        requestRepository.setComplete(Integer.valueOf(id));
    }
}
