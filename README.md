1.Изменить должность, департамент у работника.

UPDATE employees SET position_id = 2,dep_id=2 WHERE employees.id = 12;

2.Получить среднюю зарплату по департаментам и по работникам каждого из полов.

По полу:
SELECT avg(salary) FROM employees WHERE gender ='M';
SELECT avg(salary) FROM employees WHERE gender ='W';

По департаментам:
SELECT avg(salary) FROM employees WHERE dep_id =1;
SELECT avg(salary) FROM employees WHERE dep_id =2;
SELECT avg(salary) FROM employees WHERE dep_id =3;
SELECT avg(salary) FROM employees WHERE dep_id =4;

3. Получить департаменты в которых средняя зп выше определенного уровня (см. having).

SELECT departments.id,departments.name, avg(salary)
FROM departments,employees
WHERE employees.dep_id = departments.id
GROUP BY departments.id

HAVING avg(salary) >= 1000;

4. Получить перечень пустых департаментов (без подзапроса).

