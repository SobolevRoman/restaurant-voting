INSERT INTO USERS (EMAIL, NAME, PASSWORD, REGISTERED)
VALUES ('user1@gmail.com', 'User1', '{noop}password1', DATEADD('DAY', -10, CURRENT_DATE)),
       ('admin@gmail.com', 'Admin', '{noop}admin', DATEADD('DAY', -15, CURRENT_DATE)),
       ('user2@gmail.com', 'User2', '{noop}password2', DATEADD('DAY', -5, CURRENT_DATE));

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 2),
       ('ROLE_USER', 3);

INSERT INTO RESTAURANT(NAME, LABEL)
VALUES ('MAC', 'M'),
       ('SteakClub', 'SC'),
       ('Pizza', 'PZ');

INSERT INTO DISH(NAME, PRICE, RESTAURANT_ID)
VALUES ('Coffee', 99.99, 1),
       ('Maffin', 55, 1),
       ('Soup', 180.50, 2),
       ('Steak', 560, 2),
       ('Bear', 350.90, 2),
       ('Tea', 99, 3),
       ('BigPizza', 660.50, 3);

INSERT INTO MENU_ITEM(ACTUAL_DATE, DISH_ID)
VALUES (CURRENT_DATE , 1),
       (CURRENT_DATE , 2),
       (DATEADD('DAY', -1, CURRENT_DATE), 3),
       (DATEADD('DAY', -1, CURRENT_DATE), 4),
       (DATEADD('DAY', -3, CURRENT_DATE), 5),
       (DATEADD('DAY', -3, CURRENT_DATE), 4),
       (CURRENT_DATE , 4),
       (CURRENT_DATE , 6);

INSERT INTO VOTE(ACTUAL_DATE, USER_ID, RESTAURANT_ID)
VALUES (CURRENT_DATE , 1, 2),
       (CURRENT_DATE , 3, 1),
       (DATEADD('DAY', -1, CURRENT_DATE), 3, 3),
       (DATEADD('DAY', -1, CURRENT_DATE), 1, 1),
       (DATEADD('DAY', -3, CURRENT_DATE), 1, 3),
       (DATEADD('DAY', -3, CURRENT_DATE), 3, 3);
