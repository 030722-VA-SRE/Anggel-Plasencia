### Practicals

Given the following table 'employees'...

| id | firstName | lastName | salary | dept |
| --- | -------- | -------- | ------ | ---- |
| 1  | Michael   | Scott    | 65     | Sales|
| 2  | Dwight    | Schrute  | 75     | Sales|
| 3  | Toby      | Flenderson| 80    | HR  |
| 4  | Jim       | Halpert  | 90     | Sales|
| 5  | Oscar     | Martinez | 90     | Accounting |
| 6  | Angela    | Martin   | 75     | Accounting |
| 7  | Kevin     | Malone   | 70     | Accounting |
| 8  | Holly     | Flax     | 60     | HR |
| 9  | Creed     | Branton  | 70     | Quality Assurance |

* Write a query to find all data in the table
> 
    select * from employees; 

* Write a query to find employees with a salary over 75
>
    select * from employees where salary > 75;

* Write a query to find employees whose first name contains an 'e' or whose last name begins with 'S'\
>
    select * from employees where firstName like '%e%' OR lastName like 'S%';

* Write a query to find the first name of all employees who do not work in accounting
>
    select firstName from employees where dept != 'Accounting';
* Write a query to find the average salary of all employees whose last names begin with 'M'
>
    select AVG(salary) from employees where lastName like 'M%';
* Write a query to find the highest paid salesperson
>
    select * from employees 
    where dept = 'Sales' 
    order by salary 
    limit 1;
* Write a query to combine the resultsets of any two previous queries
>
    select * from employees where firstName like '%e%' or lastname like 's%';
    union
    select * from employees  where salary > 75;

* Remove all members of accounting from the database
>
    delete from employees where dept = 'Accounting';


* Given removing the dept column from the employees table and creating a table 'department' and linking the two via a foreign key relationship  

| dept_id | name |
| ------- | ---  |
| 1       | Sales |
| 2       | HR   |
| 3       | Accounting |
| 4       | Customer Service |

* Write a query to find the salary of the lowest paid salesperson (HINT: use a join)
>
    select MIN(salary) from employees
    join department on employees.dept_id = department.dept_id
    where name = 'Sales';

* Write a query to find the average salary of each department
>
    select name, AVG(salary) from employees
    join department on employees.dept_id = department.dept_id
    group by dept_id;

* Write a query to find all possible combinations of employees and departments. How many records do you expect?
>
    select * from employees
    cross join department;

    - 36 records expected    
* Change the name of department 4 to 'Quality Assurance'
>
    update department set name = 'Quality Assurance' where dept_id = 4; 

* Remove both tables
>
    delete table employees;
    delete table department;

# AWS

### Cloud / AWS Overview
* How would you describe AWS? What is "the cloud" or "cloud computing" and why is it so popular now?
   - AWS(Amazon Web Services) is scalable cloud service platform from Amazon. It's popular because it saves on costs, is scalable, and allows not having to have to maintain servers on-site.    

* Define Infrastructure, Platform, and Software as a Service
    - Infrastructure as a Service (IaaS) is a self-service model for managing remote data center infrastructures. AWS offers IaaS in the form of data centers.

    - Platform as a Service (PaaS) allows organizations to build, run and manage applications without the IT infrastructure. This makes it easier and faster to develop, test and deploy applications.

    - Software as a service (SaaS) replaces the traditional on-device software with software that is licensed on a subscription basis. It is centrally hosted in the cloud. A good example is Salesforce.com.
 
* What's the difference between a Region and an Availability Zone (AZ)?
    - An AWS Region is a geographical location with a collection of availability zones mapped to physical data centers in that region. Every region is physically isolated from and independent of every other region in terms of location, power, water supply, etc.

    - An availability zone is a logical data center in a region available for use by any AWS customer. Each zone in a region has redundant and separate power, networking and connectivity to reduce the likelihood of two zones failing simultaneously.
 
* How are you charged for using AWS services? Does it vary by service?
    - Due to autoscalability users are charged in AWS for what they use and yes it does vary by service.
 
* Different ways to interact with AWS services?
    - 



### EC2

* What are the configuration options for EC2?
    - An Amazon Machine Image (AMI) provides the information required to launch an instance. Think of it as a template for an EC2 Instance.
 
* What are the different EC2 instance sizes/types?
    - EC2 has various different instance sizes and types including types with higher or lower processing cores and memory depeding on the needs of what kind of service you are trying to deploy. 
 
* Once you create an EC2, how to connect to it?
    - Open the ssh client
    - Acquire the private key file you were provided during creation; Ex: mynewkeypair.pem
    - ssh -i [file.pem] ec2-user@[address] (There an example you can copy in AWS in the ssh section)
 
* What are Security Groups? When defining a rule for a security group, what 3 things do you need to specify?
    - A security group acts as a virtual firewall for your EC2 instances to control incoming and outgoing traffic based on their IP address.
        
        - Security group rules enable you to filter traffic based on protocols and port numbers.
        - Security groups are stateful — if you send a request from your instance, the response traffic for that request is allowed to flow in regardless of inbound security group rules. Responses to allowed inbound traffic are allowed to flow out, regardless of outbound rules.
        - You can add rules to each security group that allow traffic to or from its associated instances. You can modify the rules for a security group at any time.

* What's the difference between scalability, elasticity, and resiliency?
    - Scalability is the capability of a system to handle a growing amount of work by adding resources to the system
    - Elasticity is the nature of a service that allows developers to instantly scale to meet spikes in traffic or demand.
    - Resiliency is the systems ability to remain in operation even if some of the components used to build the system fail as well as the time to recover.

* Ways of paying for EC2?
    - Amazon EC2 is free to try. 
    - On-Demand: pay as you use 
    - Savings Plans, Reserved Instances, and Spot Instances


### RDS

* What's an RDS?
    - Amazon Relational Database Service (RDS) is a collection of managed services that makes it simple to set up, operate, and scale databases in the cloud.

* Which vendors are supported?
    - Amazon Aurora, MySQL, MariaDB, Oracle, SQL Server, and PostgreSQL

# UNIX/LINUX
Example Bash Script:

> 
    #!/bin/bash

    sudo yum update -y
    sudo yum install git
    sudo yum install java-1.8.0-openjdk-devel -y

    sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
    sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
    sudo yum install -y apache-maven


