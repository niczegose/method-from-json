package pl.kurs.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kurs.java.impl.MethodProvider;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.ActionResponse;
import pl.kurs.java.model.Methoder;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class MethodService {

    private final Map<String, Methoder<?>> methoderMap = new HashMap<>();
    private final Set<Methoder<?>> methoders;

    @Autowired
    public MethodService(Set<Methoder<?>> methoders) {
        this.methoders = methoders;
    }

    @PostConstruct
    private void init() {
        for (Methoder<?> methoder: methoders) {
            methoderMap.put(methoder.getClass().getSimpleName().toLowerCase(), methoder);
        }
    }

    public ActionResponse<?> createResponse(ActionOnWords actionOnWords){
        return new ActionResponse<>(methoderMap.get(Methoder.getMethodName(actionOnWords)).performAction(actionOnWords));
    }
}
