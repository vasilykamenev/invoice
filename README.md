# Application for Bill creation
This project is designed to calculate the cost of services depending on the Package of the service itself.

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Boot 3](https://docs.spring.io/spring-boot/3.4.1/gradle-plugin)
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/3.3.7/reference/packaging/native-image/introducing-graalvm-native-images.html)
* [Spring Shell](https://docs.spring.io/spring-shell/reference/index.html)
* [Java 17]()

### Run the application
The following guides illustrate how to build and run application:

This project has been configured to let you generate either a executable. It is also possible to run application directly on EDI.

To create the boot jar, run the following goal:

```
$ ./gradlew bootJar
```

Then, you can run the app like any other container:

```
$ java -jar invoice:0.0.1-SNAPSHOT.jar
```

To run on IDE application use the class
```
eu.finbite.mnc.invoice.InvoiceApplication
```

### Working with the application

#### CLI
After launching the application, you will see a command prompt
```
shell:>
```
To get information about available commands, enter the command and you are got the list of commands with description.
```
shell:>help
```
The commands are divided into groups of command types: "Built-in" and "Bill"

To get detailed information about command please use a --help or -h argument under command 
```
shell:>bill -h
```
#### Create the Bill
To create the bill please enter the package short name as argument under command 
```
shell:>bill S
```
I case you are enter a wrong package short name you are received feedback about correct values
```
shell:>bill X
Wrong value X of Package name, select from list [S,M,L] 
```
To create the bill with all arguments use the next command:
```
shell:>bill <package> <sms amount> <minutes>
```
To get additional information about supported packages use command:
```
shell:>packages
```
To get additional information about price use command:
```
shell:>price
```


