select employees.first_name, employees.last_name
from employees;

select departments.department_name, departments.location_id
from departments;

select jobs.job_id, jobs.job_title
from jobs;

select locations.street_address, locations.postal_code
from locations;

select country_id, country_name
from countries;

select regions.region_id, regions.region_name
from regions;

select employees.job_id
from employees;

select departments.department_id, departments.department_name
from departments;

select job_history.employee_id, job_history.department_id
from job_history;

select employees.first_name, employees.last_name, employees.salary
from employees
where salary >= 10000;

select departments.department_name
from departments
where location_id = 1700;

select *
from employees
where job_id = 'IT_PROG';

select employees.first_name, employees.last_name, employees.salary
from employees
where department_id = 90;

select jobs.job_title
from jobs
where min_salary > 5000;

select *
from employees
where last_name = 'King';

select locations.street_address
from locations
where country_id = 'US';

select *
from job_history
where start_date < '2001-01-01';

select employees.last_name, employees.first_name, employees.email
from employees
where last_name like '%a%';

select *
from departments
where department_name = 'Sales';