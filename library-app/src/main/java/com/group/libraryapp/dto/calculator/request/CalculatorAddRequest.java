package com.group.libraryapp.dto.calculator.request;

public class CalculatorAddRequest {

    private final int number1;
    private final int number2;

    // 생성자에서 number1, number2 값을 필드에 할당하고, 이후 변경 불가능
    public CalculatorAddRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    /**
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
