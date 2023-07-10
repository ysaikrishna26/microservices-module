# microservices-module

This is a single repository which has multiple modules related to different microservices.

## First Things First
### Application Start up Process

There are 3 ways in Spring Boot we can execute code automatically on starting of Application, without any user action (i.e., without hitting any REST API or CRON Expression etc.,)  

#### 1. Implementing <I>ApplicationListener</I> interface on Main Application
#### 2. Annotating with <I> @Postconstruct </I>
<p> For Implementing the <I>@Postconstruct</I> we have to override the below method
<br>
<code>
@Postconstruct
<br>
    public void init()
</code>
<br>
this Method will be triggered automatically once all the <b>Beans get initialized and before the Application Starts
</b>
Note: The method which we annotate with <I>@Postconstruct</I> should be un parameterized, as Spring Framework invokes this method, it cannot pass any parameters, as application is also not started by that time.  
</p>

#### 3. Implementing the <I>CommandLineRunner </I> Interface 
<p> For Implementing the <I>CommandLineRunner</I> we have to override the below method
<br>
<code>
@Override
<br>
    public void run(String... args) throws Exception
</code>
<br>
this Method will be triggered automatically once all the <b>Beans get initialized & Application Starts</b>
</p>

### Reason for using <I>CommandLineRunner</I> Interface in our current project.
