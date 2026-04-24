INSERT INTO product (title, unit, active) VALUES
('Гречка', 'уп.', TRUE),
('Рис', 'уп.', TRUE),
('Макароны', 'уп.', TRUE),
('Чай', 'уп.', TRUE),
('Сахар', 'кг', TRUE),
('Консервы', 'банка', TRUE),
('Молоко', 'л', TRUE),
('Печенье', 'уп.', TRUE);

INSERT INTO ration (title, period, description, active) VALUES
('Базовый набор', 'на 3 дня', 'Крупы, чай, сахар и консервы для базовой поддержки.', TRUE),
('Семейный набор', 'на неделю', 'Расширенный набор для многодетной семьи.', TRUE),
('Набор для пожилых', 'на 5 дней', 'Продукты ежедневного спроса, удобные для пожилых людей.', TRUE);

INSERT INTO ration_composition (id_ration, id_product, quantity) VALUES
(1, 1, 1.00), (1, 4, 1.00), (1, 5, 1.00), (1, 6, 2.00),
(2, 1, 2.00), (2, 2, 2.00), (2, 3, 2.00), (2, 6, 4.00),
(3, 1, 1.00), (3, 4, 1.00), (3, 7, 2.00), (3, 8, 2.00);

INSERT INTO volunteer (full_name, phone, transport_type, availability_status) VALUES
('Петров Алексей Андреевич', '+7 (900) 100-10-10', 'Личный автомобиль', 'Доступен'),
('Смирнова Ольга Игоревна', '+7 (900) 200-20-20', 'Пешком', 'Доступен');

INSERT INTO beneficiary (category, full_name, phone, address, benefit_status, created_at) VALUES
('Пенсионер', 'Иванова Мария Петровна', '+7 (900) 111-22-33', 'г. Пермь, ул. Ленина, 10, кв. 5', 'Подтверждено', CURRENT_TIMESTAMP),
('Многодетная семья', 'Семья Соколовых', '+7 (900) 222-33-44', 'г. Пермь, ул. Мира, 14, кв. 21', 'Подтверждено', CURRENT_TIMESTAMP),
('Пенсионер', 'Кузнецов Николай Иванович', '+7 (900) 333-44-55', 'г. Пермь, ул. Парковая, 8, кв. 17', 'Подтверждено', CURRENT_TIMESTAMP);

INSERT INTO help_request (id_beneficiary, id_ration, request_date, delivery_address, status, intake_channel, comment) VALUES
(1, 1, CURRENT_TIMESTAMP, 'г. Пермь, ул. Ленина, 10, кв. 5', 'NEW', 'Сайт', ''),
(2, 2, CURRENT_TIMESTAMP, 'г. Пермь, ул. Мира, 14, кв. 21', 'IN_DELIVERY', 'Сайт', 'Назначена доставка'),
(3, 3, CURRENT_TIMESTAMP, 'г. Пермь, ул. Парковая, 8, кв. 17', 'DONE', 'Горячая линия', 'Доставка завершена');

INSERT INTO delivery (id_request, id_volunteer, planned_date, actual_date, status, note) VALUES
(2, 1, CURRENT_TIMESTAMP, NULL, 'ASSIGNED', 'Доставить во второй половине дня'),
(3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'DONE', 'Набор передан лично');
