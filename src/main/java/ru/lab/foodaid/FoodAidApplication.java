package ru.lab.foodaid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.lab.foodaid.model.*;
import ru.lab.foodaid.repository.*;

import java.time.LocalDateTime;

@SpringBootApplication
public class FoodAidApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodAidApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(RationRepository rationRepository,
                               VolunteerRepository volunteerRepository,
                               HelpRequestRepository helpRequestRepository,
                               DeliveryRepository deliveryRepository) {
        return args -> {
            if (rationRepository.count() > 0) {
                return;
            }

            Ration standard = rationRepository.save(new Ration("Базовый набор",
                    "Крупы, макароны, чай, сахар, консервы.", true));
            Ration family = rationRepository.save(new Ration("Семейный набор",
                    "Крупы, овощи, молочные продукты, фрукты и товары первой необходимости.", true));
            Ration senior = rationRepository.save(new Ration("Набор для пожилых",
                    "Лёгкие продукты, чай, печенье, крупы и консервы.", true));

            Volunteer petrov = volunteerRepository.save(new Volunteer("Петров Алексей Андреевич", "+7 (900) 100-10-10", true));
            Volunteer smirnova = volunteerRepository.save(new Volunteer("Смирнова Ольга Игоревна", "+7 (900) 200-20-20", true));

            HelpRequest request1 = new HelpRequest(
                    new Beneficiary("Иванова Мария Петровна", "+7 (900) 111-22-33", "г. Пермь, ул. Ленина, 10, кв. 5", "Пенсионер"),
                    standard,
                    RequestStatus.NEW,
                    ""
            );
            helpRequestRepository.save(request1);

            HelpRequest request2 = new HelpRequest(
                    new Beneficiary("Семья Соколовых", "+7 (900) 222-33-44", "г. Пермь, ул. Мира, 14, кв. 21", "Многодетная семья"),
                    family,
                    RequestStatus.ASSIGNED,
                    "Назначена тестовая доставка"
            );
            helpRequestRepository.save(request2);

            Delivery delivery2 = new Delivery(request2, petrov, LocalDateTime.now().plusDays(1), DeliveryStatus.PLANNED, "Доставить во второй половине дня");
            deliveryRepository.save(delivery2);
            request2.setDelivery(delivery2);
            helpRequestRepository.save(request2);

            HelpRequest request3 = new HelpRequest(
                    new Beneficiary("Кузнецов Николай Иванович", "+7 (900) 333-44-55", "г. Пермь, ул. Парковая, 8, кв. 17", "Пенсионер"),
                    senior,
                    RequestStatus.DONE,
                    "Доставка завершена"
            );
            helpRequestRepository.save(request3);

            Delivery delivery3 = new Delivery(request3, smirnova, LocalDateTime.now().minusDays(1), DeliveryStatus.DONE, "Набор передан лично");
            delivery3.setDeliveredAt(LocalDateTime.now().minusHours(2));
            deliveryRepository.save(delivery3);
            request3.setDelivery(delivery3);
            helpRequestRepository.save(request3);
        };
    }
}
