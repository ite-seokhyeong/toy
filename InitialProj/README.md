# Spring Initializer 프로젝트 직접 만들기

## 목표

Spring Initializer를 사용하지 않고, 그래들을 사용하여 스프링부트 프로젝트를 생성한다. 

#

## 개발환경

- Mac OS
- Intellij Community
- Gradle 6.4.1
- Springboot 2.4.0
- Java 11

#

## Step

### 1. 프로젝트 생성 (*)

- Gradle > Project SDK: 11 > Java
- 프로젝트 Name 설정
- 프로젝트가 생성되면 Gradle Tasks의 build를 한 번 해본다.



#### 확인사항

- .gradle과 gradle > wrapper > gradle-wrapper.properties를 확인해보면 Gradle 버전은 5.2.1로 잡힌 것을 확인할 수 있다. 스프링 부트의 버전과 호환되는 버전으로 변경해야할 수도 있음을 염두해둘 것

#

### 2. 빌드 파일 수정 (*)

#### 2-1. setting.gradle

<B>(변경 전)</B>

```
rootProject.name = 'InitialProj'
```

<B>(변경 후)</B>

```
pluginManagement {
    repositories {
        maven { url 'https://repo.spring.io/milestone' }
        maven { url 'https://repo.spring.io/snapshot' }
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == 'org.springframework.boot') {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
        }
    }
}
rootProject.name = 'InitialProj'
```



#### 2-2. build.gradle

<B>(변경 전)</B>

```
plugins {
    id 'java'
}

group 'org.example'
version '1.0-SNAPSHOT'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
}
```



<B>(변경 후)</B>

```
plugins {
    id 'org.springframework.boot' version '2.4.0-SNAPSHOT'
    id 'io.spring.dependency-management' version '1.0.10.RELEASE'
    id 'java'
}

group = 'com.sh'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

repositories {
    mavenCentral()
    maven { url 'https://repo.spring.io/milestone' }
    maven { url 'https://repo.spring.io/snapshot' }
}

dependencies {
    //implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

test {
    useJUnitPlatform()
}
```
> <B>spring-boot-starter vs. spring-boot-starter-web </B>
>
> spring-boot-starter를 디펜던시로 추가하고 spring-boot 실행하면 바로 꺼진다. spring-boot-starter-web을 디펜던시로 추가해서 해결! spring-boot-starter-web이 포함하고 있는 모듈은 아래와 같다. 
>
> - spring-boot-starter-json
> - spring-boot-starter-tomcat
> - spring-web
> - spring-webmvc


> <B>위 처럼 변경하면 스프링 버전과 그래들 버전이 호환되지 않는다는 에러메시지가 출력된다. 위에서 언급했던 그래들 파일 버전을 적절하게 변경해주는 작업이 필요하다. </B>
>
> Build file '/Users/itesh/IdeaProjects/SampleProj/build.gradle' line: 2
>
> An exception occurred applying plugin request [id: 'org.springframework.boot', version: '2.4.0-SNAPSHOT', artifact: 'org.springframework.boot:spring-boot-gradle-plugin:2.4.0-SNAPSHOT']
>
> > Failed to apply plugin [id 'org.springframework.boot']
> > Spring Boot plugin requires Gradle 5 (5.6.x only) or Gradle 6 (6.3 or later). The current version is Gradle 5.2.1

#

### 3. 그래들 버전 변경 및 확인

#### gradle > gradle-wrapper.properties

<B>(변경 전)</B>

```
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-5.2.1-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```



<B>(변경 후)</B>

```
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-6.4.1-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```



> 경고가 발생하지 않도록 .gradle 폴더에서 기존 버전은 삭제해준다. 이렇게 하면 버전 문제는 없어졌다. 메인 클래스 못찾는 문제가 발생한다.
>
> Execution failed for task ':bootJar'.
>
> > Main class name has not been configured and it could not be resolved

#

### 4. 스프링부트 메인클래스 생성 (*)

```
package com.sh.initial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(String args[]) {
        SpringApplication.run(MainApplication.class, args);
    }

}
```

> (참고사항) 
>
> - src/main/java 아래에 com.sh.initial 패키지를 만들어주고 그 안에 MainApplication을 만들었다. 그렇게 하지 않으면 아래 오류가 발생한다.
>
>   Caused by: java.lang.IllegalStateException: Could not evaluate condition on org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$EmbeddedDatabaseConfiguration due to org/springframework/dao/DataAccessException not found. Make sure your own configuration does not rely on that class. This can also happen if you are @ComponentScanning a springframework package (e.g. if you put a @ComponentScan in the default package by mistake)
>
>   (TODO)
>
>   
>
> - Spring-boot-starter-web 디펜던시가 존재해야 SpringBootApplication 어노테이션이 인식된다. 
>
>   (TODO)

#

### 5. 간단한 컨트롤러 만들기

```
package com.sh.initial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialController {

    @RequestMapping("/initial")
    public String initial() {
        return "initial project ~ :)";
    }

}
```

http://localhost:8080/initial



