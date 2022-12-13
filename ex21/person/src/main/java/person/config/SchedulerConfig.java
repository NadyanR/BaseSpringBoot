package person.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import person.model.Person;
import person.repository.PersonRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    private final PersonRepository personRepository;

    public SchedulerConfig(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //ex19 Вывод всех людей без отчетсва (=null),проверка каждые 10 сек
    @Scheduled(fixedRate = 10000)
    public void checkPersonPatronymic(){
        List<Person> persons = personRepository.findPersonsByPatronymicIsNull();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println(dateFormat.format(new Date()) + " " + persons);
    }
}
