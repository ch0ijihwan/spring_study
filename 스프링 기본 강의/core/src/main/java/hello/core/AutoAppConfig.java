package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", // "hello.core.member" 해당 패키지 내에서만 검색 -> member 내 클래스만 검색됨
        basePackageClasses = AutoAppConfig.class, // AutoAppConfig 의 패키지, 즉 1라인에 있는 hello.core 를 포함하여 하위에서 검
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =  Configuration.class)
        // 기존에 수동으로 설정해둔 AppConfig 를 스캔하지 않기위해 설정. ->Because of 예제로 기존에 만들어둔 AppConfig 를 살리기 위해서
        // how? AppConfig 에 달아둔 @Configuration 를 타고 들어가보면 @Component 가 깔려 있기 때문에 Configuration.class 제
        //수동으로 설정한 것도 스캔 할 수 있기 때문에 예외적으로 제외 시킴
)// -> 위의 설정을 하지않은 경우(default) -> 현재의 패키지, 즉 hello.core 를 기준으로 검샥
public class AutoAppConfig {

    }
