package com.example.demoSpringData;

import com.example.demoSpringData.model.Card;
import com.example.demoSpringData.model.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DemoSpringDataApplication.class })
public class DemoSpringDataApplicationTests {

    @Autowired
    private DictionaryProvider dictionaryProvider;

	@Before
	public void init() {
		Locale.setDefault(Locale.ENGLISH);
	}

	private void find() {
		Optional<Status> status = dictionaryProvider.dictionaryById(Status.class, 1L);
		Assert.assertTrue(status.isPresent());

		Optional<Card> card = dictionaryProvider.dictionaryById(Card.class, 100L);
		Assert.assertTrue(card.isPresent());
	}


	private void firstFind() {
		find();
	}

	private void secondFind() {
       find();
	}

	private void findByCode() {
		Optional<Card> card = dictionaryProvider.dictionaryByCode(Card.class, "VISA");
		Assert.assertTrue(card.isPresent());
	}

	@Test
	public void testDictionaryRepository() {
		firstFind();
		secondFind();

		findByCode();
	}

}

