package com.group.libraryapp.dto.calculator.request;

public class CalculatorAddRequest {

    private final int number1;
    private final int number2;

    /**
     * 생성자 생성 이유

     * 1. 객체 초기화: 객체를 생성할 때 멤버 변수에 초기값을 설정할 수 있습니다.
     * 2. 유효성 검사: 생성자에서 멤버 변수 값의 유효성을 검사하고, 유효하지 않은 값을 입력하면 예외를 발생시킬 수 있습니다.
     * 3. 변경 불가능성: final 키워드를 사용하여 생성자에서만 멤버 변수 값을 설정하도록 제한할 수 있습니다.
     */
    // 생성자에서 number1, number2 값을 필드에 할당하고, 이후 변경 불가능
    public CalculatorAddRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }


    /**
     Getter 사용 이유
     Getter는 객체의 멤버 변수 값을 읽어오는 데 사용됩니다.

     Getter를 사용하는 이유:

     정보 은닉: 멤버 변수에 직접 접근하는 것을 막고, getter 메소드를 통해 제어된 방식으로 값을 제공합니다.
     캡슐화: 멤버 변수의 이름이나 타입을 변경해도 getter 메소드를 통해 호환성을 유지할 수 있습니다.
     추가 기능: 단순히 값을 제공하는 것 외에, 값을 변환하거나 로깅하는 등의 추가적인 기능을 추가할 수 있습니다.


     * 첫 번째 수를 **읽어**옵니다. (변경 불가능)
     *
     * @return 첫 번째 수
     */
    public int getNumber1() {
        // number1 값을 반환하지만, 외부에서 값 변경 불가능
        return number1;
    }

    /**
     * 두 번째 수를 **읽어**옵니다. (변경 불가능)
     * @return 두 번째 수
     */
    public int getNumber2() {
        // number2 값을 반환하지만, 외부에서 값 변경 불가능
        return number2;
    }
}
