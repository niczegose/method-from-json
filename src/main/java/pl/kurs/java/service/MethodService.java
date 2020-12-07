package pl.kurs.java.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kurs.java.impl.MethodProvider;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.ActionResponse;
import pl.kurs.java.model.Methoder;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class MethodService {

    private final Map<String, Methoder<?>> methoderMap = new HashMap<>();

    @PostConstruct
    public void init() {
        methoderMap.put("palindromes", actionOnWords -> MethodProvider.palindromes(actionOnWords.getWords()));

        methoderMap.put("length", actionOnWords ->
                MethodProvider.length(actionOnWords.getWords(), Methoder.getIntArg(0, Methoder.getParameters(actionOnWords))));

        methoderMap.put("anagrams", actionOnWords ->
                MethodProvider.anagrams(actionOnWords.getWords(), Methoder.getStringArg(0, Methoder.getParameters(actionOnWords))));

        methoderMap.put("longest", actionOnWords -> MethodProvider.longest(actionOnWords.getWords()));

        methoderMap.put("dates", actionOnWords ->
                MethodProvider.dates(actionOnWords.getWords(), Methoder.getStringArg(0, Methoder.getParameters(actionOnWords))));
    }

    public ActionResponse<?> createResponse(ActionOnWords actionOnWords){
        return new ActionResponse<>(methoderMap.get(Methoder.getMethodName(actionOnWords)).performAction(actionOnWords));
    }
}
