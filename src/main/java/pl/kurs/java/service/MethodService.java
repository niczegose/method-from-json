package pl.kurs.java.service;

import lombok.AllArgsConstructor;
import pl.kurs.java.impl.MethodProvider;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.ActionResponse;
import pl.kurs.java.model.Methoder;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class MethodService {

    private final ActionOnWords actionOnWords;

    public ActionResponse<?> createResponse(){
        Map<String, Methoder<?>> methoderMap = new HashMap<>();
        methoderMap.put("palindromes", MethodProvider::palindromes);

        methoderMap.put("length", stringList ->
                MethodProvider.length(stringList, Methoder.getIntArg(0, Methoder.getParameters(actionOnWords))));

        methoderMap.put("anagrams", stringList ->
                MethodProvider.anagrams(stringList, Methoder.getStringArg(0, Methoder.getParameters(actionOnWords))));

        methoderMap.put("longest", MethodProvider::longest);

        methoderMap.put("dates", stringList ->
                MethodProvider.dates(stringList, Methoder.getStringArg(0, Methoder.getParameters(actionOnWords))));


        return new ActionResponse<>(methoderMap.get(Methoder.getMethodName(actionOnWords)).performAction(actionOnWords.getWords()));

    }
}
