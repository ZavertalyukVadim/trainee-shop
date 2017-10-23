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
(з подзапросом)
SELECT departments.name
FROM departments,employees
WHERE departments.id NOT IN (SELECT departments.id
                             FROM departments,employees
                             WHERE employees.dep_id = departments.id
                             GROUP BY departments.id)
GROUP BY departments.name

(без подзапроса)
SELECT d.id, d.name
FROM employees e  RIGHT JOIN  departments d ON d.id = e.dep_id
WHERE e.dep_id IS NULL;

5. Получить по всем работникам среднюю зп по его департаменту
, среднюю зп среди коллег того же пола по всей компании и по его департаменту
, количество работников того же пола в компании и в департаменте. 
(все это одним запросом. см. оконные функции)

5.1 SELECT employees.name,
      employees.gender,
      employees.year_of_birth,
      employees.salary,
      employees.dep_id,
      avg(salary) OVER (PARTITION BY employees.dep_id) avgsalary
    FROM employees;
5.2
SELECT employees.name,
  employees.gender,
  employees.year_of_birth,
  employees.salary,
  employees.dep_id,
  avg(salary) OVER (PARTITION BY employees.dep_id) avgsalary,
  avg(salary) OVER (PARTITION BY employees.gender) avgOnGenderInAllCompany,
  avg(salary) OVER (PARTITION BY employees.gender,employees.dep_id) avgOnGenderInDepartmantCompany
  
FROM employees;

5.3
SELECT employees.name,
  employees.gender,
  employees.year_of_birth,
  employees.salary,
  employees.dep_id,
  avg(salary) OVER (PARTITION BY employees.dep_id) avgsalary,
  avg(salary) OVER (PARTITION BY employees.gender) avgOnGenderInAllCompany,
  avg(salary) OVER (PARTITION BY employees.gender,employees.dep_id) avgOnGenderInDepartmantCompany,
  count(employees.id) OVER (PARTITION BY employees.gender ) countEmployeesOnCompany,
  count(employees.id) OVER (PARTITION BY employees.dep_id,employees.gender) countEmployeesOnCompanyPerDep
FROM employees;


6.Получить для определенного работника цепочку его начальников
    и перечень его подчиенных. (см. рекурсивный запрос)
6.1
(считает всех подчиненных для  id = 10)
WITH RECURSIVE fucn AS (
  SELECT id, chief_id, name
  FROM employees
  WHERE chief_id = 10

  UNION

  SELECT employees.id, employees.chief_id, employees.name
  FROM employees
    JOIN fucn
      ON employees.chief_id = fucn.id
)

SELECT * FROM fucn;

(добавление уровня вложености)
WITH RECURSIVE fucn AS (
  SELECT id, chief_id, name, 1 AS level
  FROM employees
  WHERE chief_id = 9

  UNION ALL

  SELECT employees.id, employees.chief_id, employees.name,fucn.level + 1 AS level
  FROM employees
    JOIN fucn
      ON employees.chief_id = fucn.id
)

SELECT * FROM fucn;
