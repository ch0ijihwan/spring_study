package hello.core.singleton;

//간단한 싱글톤 예제
public class SingletonService {
    //1.static 영역에 객체를 1개만 생성해둠.
    private static final SingletonService instance = new SingletonService();

    //2.public 으로 열어 외부에서 이 객체가 필요하면 조회할 수 있도록 허용.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private 로 선언하여 외부에서 new 객체 를 할 수 없도록 막아둠.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체의 로직 메서드 호출");
    }
}
