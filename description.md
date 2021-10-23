# Documentation

## Set up

For building and running the app you will need the next resources:

- Kotlin, you can get it following the next [web](https://kotlinlang.org/docs/command-line.html)
- Java11, you can easily download it in the official oracle web or use alternatives libraries such as <https://adoptopenjdk.net/>

## Building the app

For building it you only have to use the tool `gradlew`, for listing all the options use the tasks command.

On the list you will see two important commands: `build` and `bootRun`, the first for building and the other for running the aplication.

For checking if it works you can find the next page on <http://localhost:8080>:

![image](https://user-images.githubusercontent.com/46299278/133663363-de39b281-131e-4c4e-8e35-e32b4c5c18a5.png)

## Testing the app

for testing all the unitary test run the command:

```bash
gradlew check
```

## Controller

The directory `src/main/controller/` contains the implementation of the controller of this web application following the MVC pattern.
The handler function `welcome()` in `src/main/controller/HelloController.kts` serves the view `src/main/resources/templates/welcome.html`.
The handler function `date()` in `src/main/controller/DataController.kts` serves the view `src/main/resources/templates/date.ftlh`.

## Error

The file `error.html` is a custom error page which is under `src/main/resources/templates/` for Spring MVC to detecting it automatically. The model variable `error` is passed to the view `error.html` to know what kind of error ocurred.

## Gradle Configuration

Gradle is a tool for automating building.  
The Gradle build file `build.gradle.kts` located in the main directory specifies Gradle's configuration for this project.

The build file consists of 4 main sections:

- Plugins
- Repositories
- Dependencies
- Kotlin
- Kotlin compiler options

### Plugins

Plugins are extensions that add features to Gradle.
In this project we add plugins for [Kotlin](https://github.com/JetBrains/kotlin) and [SpringBoot](https://github.com/spring-projects/spring-boot).

### Repositories

In this section the repository for solving dependencies is declared.  
In our case, `mavenCentral()` specifies that the [Maven Central public repository](https://repo.maven.apache.org/maven2/) will be used to solve dependencies.

### Dependencies

This sections contains the dependencies of the project which will be downloaded from the repository specified in the previous section.  
The dependencies used in this project are:

- [SpringBoot](https://github.com/spring-projects/spring-boot)
- [Jackson](https://github.com/FasterXML/jackson)
- [Kotlin Reflection](https://kotlinlang.org/docs/reflection.html#jvm-dependency)
- Kotlin Standard Library JDK 8 extension
- [Bootstrap WebJar](https://github.com/webjars/bootstrap)

### Kotlin

Kotlin is configured to run on the JVM.
When the Kotlin targets the JVM platform, options of the compile task are specified in the `compileKotlin` variable.
In our case, we specify that the target version of the JVM is 11 with `jvmTarget` and we configure the compiler to generate error by adding the `-Xjsr305=strict` flag.

## How to test the code

Testing is handled using JUnit, a powerful framework that allows you to check different aspects of your code.

Unit tests can be run with the following commands.

```bash
cd lab1-git-race
gradle test
```

All verification tasks, including unit tests, can be run with the following commands. Gradle offers a flag, -i, that can be used to show more information while running the checks.

```bash
cd lab1-git-race
gradle check
```

### Integration Tests

There's 3 tests, stored at `src/test/kotlin`, that have been made for this Kotlin Webpage

---

### HTML/CSS Tests

The file `src/test/kotlin/IntegrationTest.kt` contains two tests that checks the main behaivour of the HTML page itself:

- `testHome()` checks if making a request at `http://localhost:$port` (With `$port` in this case being 0 for the shake of the test), yields both:

  - A `OK` HTTP Status Code.
  - A HTML body with `<title>hello`.

    If this happens, we can assume the webpage's HTML is the one intended.
- `testCss()` checks if the CSS of the webpage has basic functionality. For this, it request `http://localhost:$port/webjars/bootstrap/5.1.0/css/bootstrap.min.css` from the Web Server, and checks if it has a response with:

  - A `OK` HTML Status.
  - A body with `"body"`.
  - A file with a header equal to that of `"text/css"`.

This ensures the webpage has a valid CSS file.

## Deployment

### With a Dockerfile

It's very simple, just follow the following steps:

1. Run following commands. This if going to create the image that we need:

   ```bash
   docker pull gradle:openj9
   docker build -t lab1-git-race .
   ```

1. If all went correctly, a image has been created (Image ID and Size may be different):

    ```bash
    $ docker images
    REPOSITORY      TAG       IMAGE ID       CREATED          SIZE
    lab1-git-race   latest    6de6b5e29bda   1 minutes ago   709MB
    ```

1. Finally, the following command run the container and is going to link the port 8080 of the container with the port 8080 of the host. This can be changed for example `5000:8080` to link the port 8080 of the container with the port 5000 of the host.

    ```bash
    docker run -p 8080:8080 lab1-git-race
    ```

#### Alternative approach

For runnicng the app inside a container run the next command:

```bash
docker run --expose=8080 --network="host" --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle bootRun
```

If you get permision denied try to use root privileges or adding you user to the docker group for using it without sudo.

```bash
sudo usermod -aG docker $USER`
```

### With a docker-compose

In order to run the app, you will need the following tools:

- [docker](https://docs.docker.com/engine/install/)
- [docker-compose](https://docs.docker.com/compose/install)

Run the following command to build the images if needed and run the app:

```bash
docker-compose up
```

It might show the following error:

```text
ERROR: Couldn't connect to Docker daemon at [...]
```

Try running with sudo:

```bash
sudo docker-compose up
```

Now you can access [the website](http://localhost:8080/) to check if everything works correctly.

**Note:** By default, local port 8080 is used to deploy the app, however, it can be modified in ``docker-compose.yml``, setting ``8080:8080`` to ``<your-port>:8080``.

## Localhost refused to connect issue

If you have followed the steps correctly, but you see this message when you search localhost:8080 in your web browser:

![Localhost refused to connect](https://raw.githubusercontent.com/dolansete/Imagenes/main/Screenshot%20from%202021-09-28%2015-02-42.png)

Try to follow these steps:

- Open your browser menu
- Hover the mouse on "More tools"
- Select "Clear browsing data"
- Choose "All time" option and mark the three boxes
- Press the clear data button
(This is how you do it in Google Chrome, but it should be almost the same with other browsers)

**Note:** If you have tried this and the error message is still there, check this [website](https://www.thecoldwire.com/localhost-refused-to-connect/#:~:text=Check%20Your%20Connection,router%20or%20call%20the%20ISP).

## With Kuvernetes

Using Tomas' [Dockerfile](https://github.com/Tomenos18/lab1-git-race/blob/master/Dockerfile) and [Guide for the basic Docker Deployment](https://github.com/Tomenos18/lab1-git-race/blob/master/README.md)

Requirements:

- Minikube single node cluster
- Kubectl
- Docker
- Helm

Steps to reproduce (adapted from [this tutorial](https://dev.to/gateixeira/deploying-a-spring-boot-kotlin-app-on-kubernetes-with-docker-and-helm-589p)):

1. Create the following files:

   - `Dockerfile` in root dir
   - `/charts/values.yaml`
   - `/charts/Chart.yaml`
   - `/charts/templates/deployment.yaml`
   - `/charts/templates/service.yaml`

  The only difference with the original tutorial is in `values.yaml` file, where `appName` is now `demo-minikube`, and `image/registry` is now your DockerID.

1. Switch Docker environment (from `Dockerfile` directory):

   - MacOS / Linux:

     ```bash
     eval $(minikube docker-env)
     ```

   - Windows:

     ```cmd
     minikube docker-env | Invoke-Expression
     ```

1. Build image:

   ```bash
   docker build -t <DockerID>/demo-minikube:latest .
   ```

1. Install App inside Minikube:

   ```bash
   helm upgrade --install demo-minikube charts --values charts/values.yaml
   ```

1. Tunnel app to publicly accesible url:

   ```bash
   minikube service demo-minikube --url
   ```

From here, a URL specifying the localhost should appear.

### On Heroku

> In order to deploy the app, you will need `git` and `heroku` CLI installed in your machine.

Detailed instructions can be found [here](https://devcenter.heroku.com/articles/git#creating-a-heroku-remote).

---

First of all, create an empty app:

```bash
$ heroku create
Creating app... done, ⬢ thawing-inlet-61413
https://thawing-inlet-61413.herokuapp.com/ | https://git.heroku.com/thawing-inlet-61413.git
```

Save the url, it will be needed.

Now, add a remote to your local repository with the `heroku git:remote` CLI command. All you need is your Heroku app's name:

```bash
$ heroku git:remote -a thawing-inlet-61413
set git remote heroku to https://git.heroku.com/thawing-inlet-61413.git
```

Deploy the code!

```bash
git push heroku master
```

Do not be afraid of detaching the `push` command, it won't cancel the build and the app will be deployed anyways.

### Configuration Metadata

Spring Boot generate a metadata file called `"spring-configuration-metadata.json"` that provide details of all supported configuration properties.
It's located in `src/main/resources/META-INF/`. There is a `property` identified by the `name` `app.message`, its default value is set in `application.properties`.

### On Google Cloud with App Engine

Google Cloud can be used to launch Spring Boot applications. One way to do it is using App Engine. App Engine is a web hosting service provided by Google for free.

To run in App Engine, you can create a project in the UI, which sets up a unique identifier for you and also sets up HTTP routes. Secondly, you have to add the Java app to the project and leave it empty. Then, you have to use the Google Cloud SDK to push your Spring Boot app into that slot from the command line or CI build. Therefore, you need to install Google Cloud SDK in your device. You can initialize the Cloud SDK using `gcloud init` int the command line.
Next [link](https://cloud.google.com/sdk/install) can help you to install CLoud SDK if you have any problem.

App Engine Standard requieres you to use WAR packaging. If you have an existing JAR packaging project, you can convert it into a WAR project by:

1. In pom.xml, change `<packaging>jar</packaging>` to `<packaging>war</packaging>`
2. Create a new SpringBootServletInitializer implementation

You can deploy the app (for example, with a Maven plugin) by adding the project ID to the build configuration, as shown in the following example:

```xml
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>appengine-maven-plugin<artifactId>
    <version>1.3.0</version>
    <configuration>
        <project>myproject</project>
    </configuration>
</plugin>
```

Then deploy with `mvn appengine:deploy` (if you need to authenticate first, the build fails).

## With a Dockerfile (using windows 10 home)

Since The standard Docker release needs Hyper-V to be available on windows to run properly and Hyper-V is only available on windows 10 pro, we need to follow the steps detailed [here](https://www.how2shout.com/how-to/install-docker-without-hyper-v-on-windows-10-7.html):

1. We need to download one version of `Docker Toolbox` installer (they are more than one available, I use the latest) :

2. When it finishes, we are going to start the `Docker Toolbox Setup Wizard` following the steps using the default options (or if you want, in addition to that you can install additional components like `kitematic`).

3. Later, when the installation is complete, you can simply double click on the `Docker Quickstar Terminal` that you will see on your Desktop and wait till Docker starts.

4. Finally follow the steps detailed in the Docker deploy seccion above to deploy the application.

### Watch out

Sometimes when cloning a github repository using windows the files adopt MS-DOS line ending format, and can lead to errors like:

```text
/usr/bin/env: ‘sh\r’: No such file or directory
```

In that case, a solution might be to use a tool like `dos2unix` that you can install using [chocolatery](https://community.chocolatey.org/packages/dos2unix) and use it to give the unix format to that files.

### On Azure Spring Cloud

The following prerequisites are required to deploy Spring Boot Web to Azure Spring Cloud:

- An Azure subscription, 1.8 JDK version or later and a Git client.

We will need to provision an Azure Spring Cloud cluster using [Azure Portal](https://azure.microsoft.com/es-es/account/) to complete the first step to config and deploy our Kotlin app to Azure Spring Cloud.

Then, we are going to config our web app from the terminal window using [Maven Plugin](https://github.com/microsoft/azure-maven-plugins/tree/develop/azure-spring-cloud-maven-plugin/) by typing: `./mvnw com.microsoft.azure:azure-spring-cloud-maven-plugin:1.3.0:config`. It will autenticate with Azure.

If we want to configure the deployment, we need to run the maven command in the command prompt selecting our Azure Spring Cloud cluster, accept default for app name and then press 'y' to expose public access for this app. When you get the confirm message (Y/N) prompt, press 'y' and our configuration will be done. Optionally we can see our configuration in file `pom.xml`.

Now, our configuration is done. Our Spring Boot Web is ready to be deployed. We will deploy it by executing `mvn azure-spring-cloud:deploy`. After a few minutes, we only have to navigate to the URL in a web browser.

## **Home improvement III**: Use moder JS framework (React) and a Restful web service (no MVC server side)

### Building the app using React JS

First of all, for building the project in React JS you must execute the following command: npm i && npm run build

The first command installs the modules used by react and the second one runs "Webpack" which is a module bundler for JavaScript applications.

After building the project, you can run your application with gradle: ./gradlew bootRun

For checking if it works you can find the page on <http://localhost:8080/react>

### Webpack

Webpack is a library that allows us to manage all the necessary resources to run our application through the use of ES6 syntax within Javascript source code. When this module bundler processes the application, it internally builds a dependency graph from one or more entry points and then combines every module the project needs into one or more bundles, which are static assets to serve the content from.

### Guide

The guide which I have used to carry out this practice section has been React.js and Spring Data REST:
<https://spring.io/guides/tutorials/react-and-spring-data-rest/>
