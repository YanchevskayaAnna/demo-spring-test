package com.devcomanda.demotests.springboottest.repository;

import com.devcomanda.demotests.model.Person;
import com.devcomanda.demotests.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void shouldReturnPeopleWithGivenName() {
        String expectedName = "testName";
        String expectedEmail = "test@email.com";

        Person person = new Person(expectedName, expectedEmail);
        testEntityManager.persist(person);

        List<Person> people = this.personRepository.findAllByName(expectedName);

        assertThat(people)
                .isNotNull()
                .hasSize(1)
                .containsExactlyInAnyOrder(person);
    }

    @Test
    public void shouldReturnEmptyPeopleWithGivenNameIfPersonNotFound() {
        String expectedName = "testName";

        List<Person> people = this.personRepository.findAllByName(expectedName);

        assertThat(people)
                .isNotNull()
                .hasSize(0);
    }
}
