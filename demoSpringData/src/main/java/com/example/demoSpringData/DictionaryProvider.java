package com.example.demoSpringData;

import com.example.demoSpringData.model.Card;
import com.example.demoSpringData.model.Status;
import com.example.demoSpringData.repositories.CardCrudRepository;
import com.example.demoSpringData.repositories.StatusCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Service
public class DictionaryProvider {

    private static Logger logger = LoggerFactory.getLogger(DictionaryProvider.class);

    private Map<Class, CrudRepository> repositoryMap = new HashMap<>();

    @Autowired
    private KeyValueOperations keyValueTemplate;

    @Autowired
    private StatusCrudRepository statusRepository;
    @Autowired
    private CardCrudRepository cardRepository;

    @PostConstruct
    public void post() {
        repositoryMap.put(Status.class, statusRepository);
        repositoryMap.put(Card.class, cardRepository);
    }

    public <T> Optional<T> dictionaryById(Class<T> clazz, long id) {
        Optional<T> optDictionary = keyValueTemplate.findById(id, clazz);
        if (optDictionary.isPresent()) {
            logger.info("optDictionary {} found in keyValueTemplate", optDictionary.get());
            return optDictionary;
        }

        CrudRepository crudRepository = repositoryMap.get(clazz);
        optDictionary = crudRepository.findById(id);
        keyValueTemplate.insert(optDictionary.get());
        logger.info("optDictionary {} insert in keyValueTemplate", optDictionary.get());

        return optDictionary;
    }

    public <T> Optional<T> dictionaryByCode(Class<T> clazz, String code) {
        KeyValueQuery<String> query = new KeyValueQuery<>(String.format("code == '%s'", code));
        Iterable<T> iterable = keyValueTemplate.find(query, clazz);

        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            return Optional.of(iterator.next());
        }
        return Optional.empty();
    }

}
